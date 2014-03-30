package me.masterejay.testapp;

import android.net.Uri;
import android.widget.ImageView;
import me.masterejzz.testapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartingPoint extends Activity {


	ImageView view;
	Button forumBut;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting_point);
		forumBut = (Button) findViewById(R.id.forums);
		view = (ImageView) findViewById(R.id.imageView);
		forumBut.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 Intent intentMain = new Intent(StartingPoint.this ,
                         Forum.class);
				 StartingPoint.this.startActivity(intentMain);
			}
		});

		view.setImageResource(R.drawable.octcicon);

	}


}
