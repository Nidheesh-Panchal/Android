package com.example.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

	//ActionBar actionBar;
	String displayname;
	DatabaseReference mDatabaseReference;
	EditText input_text;
	ListView chat_list;
	ImageButton send_button;

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

		input_text=findViewById(R.id.message_text);
		chat_list=findViewById(R.id.chat_list_view);
		send_button=findViewById(R.id.send_button);

		//if pressed enter then send.
		input_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
				send_message();
				return true;
			}
		});

		send_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				send_message();
			}
		});
	}

	private void send_message()
	{
		Log.d("connectapp","Sent something");
		String send_text=input_text.getText().toString();
		if(!send_text.equals(""))
		{
			instantmessage chat=new instantmessage(send_text,displayname);
			mDatabaseReference.child("messages").push().setValue(chat);
			input_text.setText("");
		}
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
