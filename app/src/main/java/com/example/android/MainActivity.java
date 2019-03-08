package com.example.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

	EditText email_text;
	EditText pass_text;
	Button login_button;
	Button register_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		email_text=findViewById(R.id.email_text);
		pass_text=findViewById(R.id.password_text);
		login_button=findViewById(R.id.login_button);
		register_button=findViewById(R.id.register_button);

		login_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
		register_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
	}

}
