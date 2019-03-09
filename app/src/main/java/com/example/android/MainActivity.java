package com.example.android;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
				if (i!=0)
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
			Toast.makeText(this,"Enter valid E-Mail!",Toast.LENGTH_SHORT).show();
			return;
		}
		if(password.isEmpty())
		{
			Toast.makeText(this,"Enter valid password!",Toast.LENGTH_SHORT).show();
			return;
		}

		Toast.makeText(this,"Login in Progress!",Toast.LENGTH_SHORT).show();
		mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(this,
				new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						Log.d("connectapp", "Signing in status :" + task.isSuccessful());

						if (!task.isSuccessful())
						{
							Log.d("FlashChat", "Problem signing in: " + task.getException());
							showErrorDialog("There was a problem signing in. \nTry Again!\nWrong email or password. OR\nCheck your internet connection");
						}
						else
						{
							Log.d("connectapp","logged in");
							/*Intent intent = new Intent(MainActivity.this, ChatActivity.class);
							finish();
							startActivity(intent);*/
						}
					}
				});
	}

	private void showErrorDialog(String message) {

		new AlertDialog.Builder(this)
				.setTitle("Oops")
				.setMessage(message)
				.setPositiveButton(android.R.string.ok, null)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.show();
	}

}
