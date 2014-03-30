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
		webview.loadDataWithBaseURL("http://netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css", e.html(), "text/html", "utf-8", null);
	}
}
