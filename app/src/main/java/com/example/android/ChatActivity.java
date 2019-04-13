package com.example.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

	//ActionBar actionBar;
	String displayname;
	DatabaseReference mDatabaseReference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Log.d("connectapp","Inside chat onCreate");
		//Adding action bar back button. check override function below
		//actionBar = getSupportActionBar();
		/*actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);*/
		SharedPreferences prefs=getSharedPreferences("chatprefs",MODE_PRIVATE);
		displayname=prefs.getString("username",null);
		if(displayname==null)
			displayname="Anonymous";

		mDatabaseReference= FirebaseDatabase.getInstance().getReference();
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
		Toast.makeText(this,"Logging out!",Toast.LENGTH_SHORT).show();
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
