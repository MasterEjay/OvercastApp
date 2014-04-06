package me.masterejay.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
import me.masterejay.testapp.widget.UITableView;
import me.masterejzz.testapp.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author MasterEjay
 */
public class Thread extends Activity implements CustomWebView.OnBottomReachedListener{

	CustomWebView webview;
	Document doc;
	int currentPage = 0;
	int totalPages= 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		setContentView(R.layout.thread);
		StrictMode.setThreadPolicy(policy);
		webview = (CustomWebView) findViewById(R.id.webView);
		Bundle b = getIntent().getExtras();
		String link = b.getString("link");
		initWebView(link);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.thread_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.forward:
				goForward();
				return true;
			case R.id.back:
				goBackward();
				return true;
			case R.id.lastpage:
				goForward(totalPages);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	public void initWebView(String link){

		try {
			doc = Jsoup.connect(link).userAgent("Mozilla").get();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(Thread.this, "Something went wrong! Are you sure you're online?", Toast.LENGTH_LONG).show();
			return;
		}
		Element e = doc.getElementsByClass("span9").get(0);
		Elements buttons = doc.getElementsByClass("btn-group");

		Element pages;
		try {
			 pages = buttons.addClass("pull-left").get(0);
		}
		catch (IndexOutOfBoundsException ex){
			currentPage = 1;
			totalPages = 1;
			Toast.makeText(Thread.this, "You are on " + currentPage + " of " + totalPages, Toast.LENGTH_LONG).show();
			webview.loadDataWithBaseURL("http://netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css", e.html(), "text/html", "utf-8", null);
			return;
		}
		Elements buttonEach = pages.getElementsByClass("btn");

		Log.d("STUFF", pages.text());

		Log.d("STUFF",String.valueOf(buttonEach.size()));
		for (int i = 0; i < buttonEach.size(); i++){
			Element e1 = buttonEach.get(i);
			if (e1.hasClass("disabled") && !e1.text().equals("…")){
				currentPage = Integer.parseInt(e1.text());
			}
			else if (e1.text().contains("Last")){
				Log.d("STUFF A", e1.text());
				totalPages = Integer.parseInt(e1.attr("href").split("=")[1]);
			}
		}
		Toast.makeText(Thread.this, "You are on " + currentPage + " of " + totalPages, Toast.LENGTH_LONG).show();
		if (webview != null){
			webview.loadDataWithBaseURL("http://netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css", e.html(),"text/html","utf-8",null);
		}
	}


	public void goForward(){
		if (totalPages == 1){
			Toast.makeText(Thread.this, "Only 1 page!", Toast.LENGTH_SHORT).show();
			return;
		}
		if (currentPage == totalPages){
			Toast.makeText(Thread.this, "This is the last page", Toast.LENGTH_SHORT).show();
			return;
		}
		if (currentPage == 1){
			Toast.makeText(Thread.this, "Going to second page!", Toast.LENGTH_SHORT).show();
			Bundle b = getIntent().getExtras();
			String link = b.getString("link");
			link = link + "?page=2";
			Log.d("STUFF AAA", link);
			initWebView(link);

		}
		else {
			int cur = currentPage + 1;
			Toast.makeText(Thread.this, "Going to page " + cur, Toast.LENGTH_SHORT).show();
			Bundle b = getIntent().getExtras();
			String link = b.getString("link") + "?page=" + currentPage;
			String updatedLink = replaceLast(link, String.valueOf(currentPage), String.valueOf(cur));
			Log.d("STUFF AAA", updatedLink);
			initWebView(updatedLink);
		}
	}


	public void goBackward(){
		if (totalPages == 1){
			Toast.makeText(Thread.this, "Only 1 page!", Toast.LENGTH_SHORT).show();
			return;
		}
		if (currentPage == 1){
			Toast.makeText(Thread.this, "This is the first page", Toast.LENGTH_SHORT).show();
		}
		else {
			int cur = currentPage - 1;
			Toast.makeText(Thread.this, "Going to page " + cur, Toast.LENGTH_SHORT).show();
			Bundle b = getIntent().getExtras();
			String link = b.getString("link") + "?page=" + currentPage;
			String updatedLink = replaceLast(link, String.valueOf(currentPage), String.valueOf(cur));
			Log.d("STUFF AAA", updatedLink);
			initWebView(updatedLink);
		}
	}


	public void goForward(int pageTo){
		if (totalPages == 1){
			Toast.makeText(Thread.this, "Only 1 page!", Toast.LENGTH_SHORT).show();
			return;
		}
		if (currentPage == totalPages){
			Toast.makeText(Thread.this, "This is the last page", Toast.LENGTH_SHORT).show();
			return;
		}
		if (currentPage == 1){
			Toast.makeText(Thread.this, "Going to second page!", Toast.LENGTH_SHORT).show();
			Bundle b = getIntent().getExtras();
			String link = b.getString("link");
			link = link + "?page=2";
			Log.d("STUFF AAA", link);
			initWebView(link);

		}
		else {
			int cur = currentPage + pageTo;
			Toast.makeText(Thread.this, "Going to page " + cur, Toast.LENGTH_SHORT).show();
			Bundle b = getIntent().getExtras();
			String link = b.getString("link") + "?page=" + currentPage;
			String updatedLink = replaceLast(link, String.valueOf(currentPage), String.valueOf(cur));
			Log.d("STUFF AAA", updatedLink);
			initWebView(updatedLink);
		}
	}





	@Override
	public void onBottomReached(View v) {
		if (totalPages == 1){
			Toast.makeText(Thread.this, "Only 1 page!", Toast.LENGTH_SHORT).show();
			return;
		}
		if (currentPage == totalPages){
			Toast.makeText(Thread.this, "This is the last page", Toast.LENGTH_SHORT).show();
			return;
		}
		if (currentPage == 1){
			Toast.makeText(Thread.this, "Going to second page!", Toast.LENGTH_SHORT).show();
			Bundle b = getIntent().getExtras();
			String link = b.getString("link");
			link = link + "?page=2";
			Log.d("STUFF AAA", link);
			initWebView(link);

		}
		else {
			int cur = currentPage + 1;
			Toast.makeText(Thread.this, "Going to page " + cur, Toast.LENGTH_SHORT).show();
			Bundle b = getIntent().getExtras();
			String link = b.getString("link");
			String updatedLink = replaceLast(link, String.valueOf(currentPage), String.valueOf(cur));
			initWebView(updatedLink);
		}
	}

	public static String replaceLast(String string, String toReplace, String replacement) {
		int pos = string.lastIndexOf(toReplace);
		if (pos > -1) {
			return string.substring(0, pos)
					+ replacement
					+ string.substring(pos + toReplace.length(), string.length());
		} else {
			return string;
		}
	}


}
