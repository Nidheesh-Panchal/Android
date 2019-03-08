package com.example.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

	EditText email_text;
	EditText pass_text;
	Button login_button;
	Button register_button;

	private FirebaseAuth mAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		email_text=findViewById(R.id.email_text);
		pass_text=findViewById(R.id.password_text);
		login_button=findViewById(R.id.login_button);
		register_button=findViewById(R.id.register_button);

		mAuth = FirebaseAuth.getInstance();

		pass_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
				Log.d("connectapp",""+i);
				if (i==6)
				{
					Log.d("connectapp","Inside password action listener");
					attemptLogin();
					return true;
				}
				Log.d("connectapp","Inside password listener: unsatisfied");
				return false;
			}
		});

		login_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptLogin();
			}
		});
		register_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent=new Intent(MainActivity.this, register.class);
				finish();
				startActivity(intent);
			}
		});
	}

	private void attemptLogin()
	{
		String mail=email_text.getText().toString();
		String password=pass_text.getText().toString();
		Log.d("connectapp","Inside attemptLogin");
		if(mail.isEmpty())
		{
			if(mail.equals("") || password.equals(""))
			{
				Log.d("connectapp","Nothing entered");
				return;
			}
		}

		Toast.makeText(this,"Login in Progress!",Toast.LENGTH_SHORT).show();
	}

}
