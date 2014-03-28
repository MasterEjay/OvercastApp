package me.masterejay.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Toast;
import me.masterejay.testapp.widget.UITableView;
import me.masterejzz.testapp.R;


public class Forum extends Activity{
	UITableView tableView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forum);
		tableView = (UITableView) findViewById(R.id.tableView);
		createList();
		Log.d("Forum","total items: "+ tableView.getCount());
		tableView.commit();

	}

	private void createList() {
		CustomClickListener listener = new CustomClickListener();
		tableView.setClickListener(listener);
		tableView.addBasicItem("Example 1", "Summary text 1");
		tableView.addBasicItem("Example 2", "Summary text 2");
		tableView.addBasicItem("Example 3", "Summary text 3");
		tableView.addBasicItem("Example 4", "Summary text 4");
	}

	private class CustomClickListener implements UITableView.ClickListener{
		@Override
		public void onClick(int index) {
			Toast.makeText(Forum.this, "item clicked: " + index, Toast.LENGTH_SHORT).show();
		}
	}

}

