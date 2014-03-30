package me.masterejay.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.WebView;
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
public class Thread extends Activity {

	WebView webview;
	Document doc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);

		setContentView(R.layout.thread);
		webview = (WebView) findViewById(R.id.webView);
		initWebView();
	}


	public void initWebView(){
		Bundle b = getIntent().getExtras();
		String link = b.getString("link");
		try {
			doc = Jsoup.connect(link).userAgent("Mozilla").get();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(Thread.this, "Something went wrong! Are you sure you're online?", Toast.LENGTH_LONG).show();
			return;
		}
		Element e = doc.getElementsByClass("span9").get(0);
		Elements buttons = doc.getElementsByClass("btn-group");
		int currentPage = 0;
		int totalPages= 0;
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
		webview.loadDataWithBaseURL("http://netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css", e.html(), "text/html", "utf-8", null);
	}
}
