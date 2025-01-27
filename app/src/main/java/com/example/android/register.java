package com.example.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

	EditText email;
	EditText pass;
	EditText retypepass;
	Button register;
	EditText username;

	private FirebaseAuth mAuth;

	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		email=findViewById(R.id.email_text);
		pass=findViewById(R.id.pass_text);
		retypepass=findViewById(R.id.repass_text);
		register=findViewById(R.id.register_button);
		username=findViewById(R.id.display_name);

		retypepass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
				Log.d("connectapp","editorlistener "+i);
				if(i!=0)
				{
					Log.d("connectapp","editorlistener condition satisfied "+i);
					registration();
					return true;
				}
				return false;
			}
		});

		register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				registration();
			}
		});

		mAuth=FirebaseAuth.getInstance();

	}

	private void registration() {
		Log.d("connectapp","inside registration");

		String user=username.getText().toString();
		String mail=email.getText().toString();
		String password=pass.getText().toString();
		String repass=retypepass.getText().toString();

		Log.d("connectapp","inside registration " + user);

		boolean flag=false;
		View focusView = null;

		if(user.isEmpty())
		{
			username.setError("Enter Username");
			flag=true;
			focusView=username;
		}

		else if(mail.isEmpty() || !mail.contains("@"))
		{
			email.setError("Enter Valid E-Mail");
			flag=true;
			focusView=email;
		}
		else if(password.isEmpty() || password.length()<4)
		{
			pass.setError("Enter a valid password. Length(min. 4 characters)");
			flag=true;
			focusView=pass;
		}
		else if(!password.equals(repass))
		{
			pass.setError("Passwords do not match");
			flag=true;
			focusView=retypepass;
		}
		if(flag)
		{
			focusView.requestFocus();
		}
		else
		{
			Log.d("connectapp","Registering");
			createUser();
		}
	}

	private void createUser()
	{
		final String mail=email.getText().toString();
		String password=pass.getText().toString();
		mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(this,
				new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				Log.d("connectapp",""+task.isSuccessful());
				if(task.isSuccessful())
				{
					//Putting data into firebase.
					Toast.makeText(register.this,"Registration Successful.",Toast.LENGTH_SHORT).show();
					String user=username.getText().toString();
					SharedPreferences prefs = getSharedPreferences("chatPrefs", 0);
					prefs.edit().putString("username", user).apply();
					Intent intent=new Intent(register.this,MainActivity.class);
					finish();
					startActivity(intent);
				}
				else
				{
					showError("Registration Attempt Failed.");
				}
			}
		});
	}

	private void showError(String message)	{
		new AlertDialog.Builder(this)
				.setTitle("OOPS!!")
				.setMessage(message)
				.setPositiveButton("OK", null)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
			case android.R.id.home:
				//Write your logic here
				Log.d("connectapp","Going back to login page");
				this.finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
