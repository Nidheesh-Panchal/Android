package com.example.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;

public class ChatActivity extends AppCompatActivity {

	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Log.d("connectapp","Inside chat onCreate");
		//Adding action bar back button. check override function below
		/*actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);*/

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.item, menu);
		return true;
	}
	public void logout(MenuItem item) {
		// Handle action bar item clicks here.

		//process your onClick here
		Log.d("connectapp","Going back to login page");
		this.finish();
		Intent intent=new Intent(ChatActivity.this,MainActivity.class);
		startActivity(intent);
	}
	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
			case android.R.id.home:
				//Write your logic here
				Log.d("connectapp","Going back to login page");
				this.finish();
				Intent intent=new Intent(ChatActivity.this,MainActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}*/
}
