package me.masterejay.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

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
		Toast.makeText(Forum.this, "Please wait while we fetch the latest news!", Toast.LENGTH_SHORT).show();

		tableView = (UITableView) findViewById(R.id.tableView);

		parsePage();
		Log.d("Forum","total items: "+ tableView.getCount());
	}

	private void createList() {
		CustomClickListener listener = new CustomClickListener();
		tableView.setClickListener(listener);
		tableView.clear();
		for (ForumEntry e : entries){
			tableView.addBasicItem(e.getTopicName(), e.getPoster());
		}
		tableView.commit();

	}

	private class CustomClickListener implements UITableView.ClickListener{
		@Override
		public void onClick(int index) {
			Toast.makeText(Forum.this, "item clicked: " + index, Toast.LENGTH_SHORT).show();
		}
	}


	public void parsePage(){
				try {
					doc = Jsoup.connect("http://oc.tc/forums").userAgent("Mozilla").get();
				} catch (IOException e) {
					e.printStackTrace();
					Toast.makeText(Forum.this, "Something went wrong! Are you sure you're online?", Toast.LENGTH_LONG).show();
					return;
					}

					Elements topics = doc.getElementsByClass("topic");
					for (int i = 0; i < topics.size(); i++){
						Element e = topics.get(i);
						Elements td = e.getElementsByClass("td");
						for (int i2 = 0; i2 <= td.size(); i2++){
							Elements titleElement = e.getElementsByAttributeValueContaining("href", "topics");
							Elements authorElement = e.getElementsByAttributeValueContaining("style", "color");
							String author = authorElement.text().split(" ")[0];
							String title = titleElement.text();

							entries.add(new ForumEntry(author, title));

						}
					}
					createList();
					Elements startedBy = topics.addClass("started-by");
			}
	}



