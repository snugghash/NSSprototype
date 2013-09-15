package com.example.batchsms;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	String[] phoneNoArray = "324567 2345675 442134567 32234567 254654 4325 6543 5554 345645 432 5654 3324564 324567 5556"
			.split(" ");
	int position;
	int NO_BATCH = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//View elements
		EditText phoneNos = (EditText) findViewById(R.id.editText1);
		Button sendSMS = (Button) findViewById(R.id.sendSMS);

		//Set phone numbers to phone number field
		String phoneNoStr = "";
		for(;position<NO_BATCH;position++) {
			if(position >= phoneNoArray.length) {
				Toast.makeText(getApplicationContext(), "No more numbers", Toast.LENGTH_SHORT).show();
				break;
			}
			phoneNoStr += phoneNoArray[position] + ' ';
		}
		phoneNos.setText(phoneNoStr);
		
		//Sets the Listener for button press
		sendSMS.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText phoneNos = (EditText) findViewById(R.id.editText1);
				EditText message = (EditText) findViewById(R.id.editText2);
				
				//Gets the phone numbers in the EditText and sends the same message to all.  
				String[] phoneNumbers = phoneNos.getText().toString().split(" ");
				for(int i=0;;i++) {
					if(i >= phoneNumbers.length) {
						break;
					}
					if(phoneNumbers[i].length()>0 && message.getText().toString().length()>0) {
						sendSMS(phoneNumbers[i],message.getText().toString());
					}
					else {
						Toast.makeText(getApplicationContext(), "Enter both phone number and message", Toast.LENGTH_SHORT).show();
						return;
					}
				}
				//Set phone numbers to phone number field
				String phoneNoStr = "";
				for(int i=0 ; i<NO_BATCH ; i++) {
					position++;
					if(position >= phoneNoArray.length) {
						Toast.makeText(getApplicationContext(), "No more numbers", Toast.LENGTH_SHORT).show();
						break;
					}
					phoneNoStr += phoneNoArray[position] + ' ';
				}
				phoneNos.setText(phoneNoStr);
			}
		});

	}

	private void sendSMS(String phoneNumber, String message) {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}

	//TODO Create setting for our own list of numbers.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
