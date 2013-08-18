package com.example.emailtest;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Called when button "Send e-mail" is pressed.
	public void sendEmail(View view) {
		
		//Makes objects to hold all text entered
		EditText e1= (EditText)findViewById(R.id.editText1);
		EditText e2= (EditText)findViewById(R.id.editText2);
		EditText e3= (EditText)findViewById(R.id.editText3);
		
		//stored text into required string
		String emailid=e1.getText().toString();
		String subject=e2.getText().toString();
		String content=e3.getText().toString();
		
		//Send e-mail using intents
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO); 
		emailIntent.setType("text/plain");
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, content);
		emailIntent.setData(Uri.parse("mailto:"+emailid )); //Parses the address
		emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(emailIntent); //Calls the e-mail app to send the email.
	}
	

}
