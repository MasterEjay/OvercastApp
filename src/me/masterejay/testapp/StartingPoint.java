package me.masterejay.testapp;

import java.io.IOException;

import me.masterejzz.testapp.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartingPoint extends Activity {


	TextView kills, deaths;
	Button goButton, forumBut;
	String name = null;
	Document doc = null;
	EditText statsBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting_point);
		kills = (TextView) findViewById(R.id.kills);
		deaths = (TextView) findViewById(R.id.deaths);
		goButton = (Button) findViewById(R.id.goButton);
		statsBox = (EditText)findViewById(R.id.editText1);
		forumBut = (Button) findViewById(R.id.forums);
		goButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					String k = getKills();
					String d = getDeaths();
					kills.setText(k + "kills");
					deaths.setText(d + " deaths");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		forumBut.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 Intent intentMain = new Intent(StartingPoint.this , 
                         Forum.class);
				 StartingPoint.this.startActivity(intentMain);
			}
		});
	}

	protected String getKills() throws Exception{

		name = statsBox.getText().toString();
		kills.setText("Fetching...");
		try {
			doc = Jsoup.connect("http://oc.tc/" + name).userAgent("Mozilla").get();
		} catch (IOException e) {

			//e.printStackTrace();
			System.out.print("Seems like something went wrong! Are you connected to the internet?");
		}
		Elements content = doc.getElementsByClass("span5");
		Elements links = content.select("h2");
		String f = links.toString();
		String a = f.replaceAll("<h2>", "");
		String d = a.replaceAll("<small>kills</small> </h2>", "");
		return d;


	}

	protected String getDeaths() throws Exception{

		name = statsBox.getText().toString();
		deaths.setText("Fetching...");
		try {
			doc = Jsoup.connect("http://oc.tc/" + name).userAgent("Mozilla").get();
		} catch (IOException e) {

			//e.printStackTrace();
			System.out.print("Seems like something went wrong! Are you connected to the internet?");
		}
		Elements content = doc.getElementsByClass("span4");
		Elements links = content.select("h2");
		String f = links.toString();
		String h2 = f.replace("<h2> ", "");
		String[] t = h2.split(" ");
		String d = t[0];
		return d;








	}

}
