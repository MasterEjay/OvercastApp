package me.masterejay.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;
import me.masterejay.testapp.widget.UITableView;
import me.masterejzz.testapp.R;

/**
 * @author MasterEjay
 */
public class Thread extends Activity {

	WebView webview;

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
		webview.loadUrl(link);
	}
}
