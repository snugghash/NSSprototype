package com.example.smsbatch;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button buttonSendSMS, buttonSendEmail;
	Spinner targetsSpinner;
	EditText txtPhoneNo, txtMessage;
	String[] targets = { "Managerial Team", "Volunteers" };
	public String[] volNoArray = "56782394567 432456 765432324 567 5556 5667 456 78 568 5554 11 56 789  657 5554".split(" ");
	String[] manTeamArray = "stejasvin@gmail.com navneetbmehrol@gmail.com t.sabarish@gmail.com dianadravyam@gmail.com sagitariusshree@gmail.com rohityparasnis@gmail.com prathyusha.shine@gmail.com radhakrishnatg@gmail.com abinayasampath29@gmail.com brijittbonnie@gmail.com nirupamajayaraman@gmail.com vennelaganti.gopal@gmail.com csniru19@gmail.com kushal2202@gmail.com sagar1honnungar@gmail.com kavitha94@gmail.com gowtham.vg.7@gmail.com deepak.vk.1994@gmail.com abdealikothari@gmail.com gsuhas94@gmail.com remilna12b052@gmail.com deviraj743@gmail.com harishk.wayne@gmail.com arunkdas26@gmail.com vaishak_shankar@yahoo.co.in hitarthdshah@gmail.com vaibhsm@gmail.com shashankasrao@gmail.com rathees1994@gmail.com"
			.replace(' ', ',').split(",");
	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		 for(int i=0;i<volNoArray.length;i++){
//		 Log.v("debug", volNoArray[i]);
//		 }

		targetsSpinner = (Spinner) findViewById(R.id.targets_spinner);
		buttonSendSMS = (Button) findViewById(R.id.buttonSendSMS);
		txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		buttonSendEmail = (Button) findViewById(R.id.buttonSendEmail);

		// Populating Spinner
		new ArrayAdapter<String>(getApplicationContext(),
				targetsSpinner.getId(), android.R.layout.simple_spinner_item,
				targets);
		targetsSpinner.setOnItemSelectedListener(new Spinner(
				getApplicationContext()).getOnItemSelectedListener());

		// TODO Populating emails.

		// Ability to add custom numbers and default numbers
		String text = "";
		for (position = 0; position < 10; position++) {
			if (volNoArray.length < position + 1) {
				Toast.makeText(getApplicationContext(), "No more numbers",
						Toast.LENGTH_SHORT).show();
				break;
			}
			text += volNoArray[position] + ' ';
		}
		txtPhoneNo.setText(text);

		buttonSendSMS.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String[] phoneNo = txtPhoneNo.getText().toString().split(" ");
				String message = txtMessage.getText().toString();
				for (int i = 0; i < phoneNo.length; i++) {
					if (phoneNo[i].length() > 0 && message.length() > 0)
						sendSMS(phoneNo[i], message);
					else {
						Toast.makeText(getBaseContext(),
								"Please enter both phone number and message.",
								Toast.LENGTH_SHORT).show();
						return;
					}
				}
				String text = "";
				for (int i = 0; i < 10; i++) {
					if (volNoArray.length < position + 1) {
						Toast.makeText(getApplicationContext(),
								"No more contacts", Toast.LENGTH_SHORT).show();
						break;
					}
					text += volNoArray[position] + ' ';
					position++;
				}
				txtPhoneNo.setText(text);
			}
		});

		buttonSendEmail.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String[] phoneNo = txtPhoneNo.getText().toString().split(",");
				String message = txtMessage.getText().toString();
				for (int i = 0; i < phoneNo.length; i++) {
					if (phoneNo[i].length() > 0 && message.length() > 0)
						sendSMS(phoneNo[i], message);
					else
						Toast.makeText(getBaseContext(),
								"Please enter both phone number and message.",
								Toast.LENGTH_SHORT).show();
				}
				String text = "";
				for (int i = 0; i < 10; i++) {
					text += volNoArray[position];
					if (volNoArray.length == position + 1) {
						Toast.makeText(getApplicationContext(),
								"No more contacts", Toast.LENGTH_SHORT).show();
					}
					position++;
				}
				txtPhoneNo.setText(text);
			}
		});
	}

	public void sendSMS(String phoneNumber, String message) {

		//PendingIntent pi = PendingIntent.getActivity(this, 0,
		//new Intent(this, SMS.class), 0);
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
