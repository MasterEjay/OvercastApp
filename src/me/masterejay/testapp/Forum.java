package me.masterejay.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import me.masterejay.testapp.widget.UITableView;
import me.masterejzz.testapp.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Forum extends Activity{
	UITableView tableView;
	List<ForumEntry> entries = new ArrayList<ForumEntry>();
	Document doc = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.forum);
		Toast.makeText(Forum.this, "Loading...", Toast.LENGTH_SHORT).show();

		tableView = (UITableView) findViewById(R.id.tableView);
		sendAsyncTask();

		Log.d("Forum","total items: "+ tableView.getCount());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.forum_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.action_settings:
				Toast.makeText(Forum.this, "Loading...", Toast.LENGTH_SHORT).show();
				sendAsyncTask();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void createList() {
		CustomClickListener listener = new CustomClickListener();
		tableView.setClickListener(listener);
		tableView.clear();
		int index = 0;
		for (ForumEntry e : entries){
			tableView.addBasicItem(e.getTopicName(), e.getPoster() + " posted " + e.getDate());
			e.setIndex(index);
			index++;
		}
		tableView.commit();

	}

	public void sendAsyncTask(){
		ParseTask task = new ParseTask();
		task.doInBackground(null);
	}


	public ForumEntry getEntryFromId(int id){
		for (ForumEntry e : entries){
			if (id == e.getIndex()){
				return e;
			}
		}
		return null;
	}

	private class CustomClickListener implements UITableView.ClickListener{
		@Override
		public void onClick(int index) {
			Intent intent = new Intent(Forum.this, Thread.class);
			Bundle b = new Bundle();
			b.putString("link",getEntryFromId(index).getLink()); //Your id
			intent.putExtras(b); //Put your id to your next Intent
			startActivity(intent);
		}
	}


	protected class ParseTask extends AsyncTask<String, String, String> {
		public void parsePage(){
			try {
				doc = Jsoup.connect("http://oc.tc/forums").userAgent("Mozilla").get();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(Forum.this, "Something went wrong! Are you sure you're online?", Toast.LENGTH_LONG).show();
				return;
			}
			entries.clear();
			Elements topics = doc.getElementsByClass("topic");
			for (int i = 0; i < topics.size(); i++){
				Element e = topics.get(i);
				Elements td = e.getElementsByClass("td");
				for (int i2 = 0; i2 <= td.size(); i2++){
					Elements titleElement = e.getElementsByAttributeValueContaining("href", "topics");
					Elements authorElement = e.getElementsByAttributeValueContaining("style", "color");
					String author = authorElement.text().split(" ")[0];
					String title = titleElement.text();
					String link = "https://oc.tc/" + titleElement.attr("href").split("/unread")[0];
					Elements dateElement = e.getElementsByTag("span");
					String date = dateElement.text();
					entries.add(new ForumEntry(author, date, title, link));

				}
			}
			createList();
		}

		@Override
		protected String doInBackground(String... params){
		   parsePage();
			return null;
		}
	}




	}



