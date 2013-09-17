package com.example.batchsms;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	String phoneNoString;
	String[] phoneNoArray;
	int position;
	int NO_BATCH = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// View elements
		EditText phoneNos = (EditText) findViewById(R.id.editText1);
		Button sendSMS = (Button) findViewById(R.id.sendSMS);
		Button saveNums = (Button) findViewById(R.id.saveNums);
		Button remNums = (Button) findViewById(R.id.remNums);

		//Retrieve phone numbers from shared preferences.
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		phoneNoString = preferences.getString("phoneNoString", "324567 2345675 442134567 32234567 254654 4325 6543 5554 345645 432 5654 3324564 324567 5556");
		phoneNoArray = phoneNoString.split(" ");
		// Set phone numbers to phone number field
		String phoneNoDisplayStr = "";
		for (; position < NO_BATCH; position++) {
			if (position >= phoneNoArray.length) {
				// Toast.makeText(getApplicationContext(), "No more stored numbers",
				// Toast.LENGTH_SHORT).show();
				break;
			}
			phoneNoDisplayStr += phoneNoArray[position] + ' ';
		}
		phoneNos.setText(phoneNoDisplayStr);

		// Provision to add to the list of phone numbers
		saveNums.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SharedPreferences preferences = PreferenceManager
						.getDefaultSharedPreferences(getBaseContext());
				Editor editor = preferences.edit();
				EditText phoneNos = (EditText) findViewById(R.id.editText1);

				// Gets the phone numbers in the EditText and checks for already existing, adds to list if not existing
				String[] phoneNumbers = phoneNos.getText().toString()
						.split(" ");
				boolean flag = true;
				for (int i = 0; i < phoneNumbers.length; i++) {
					for (int j = 0; j < phoneNoArray.length; j++) {
						if (phoneNumbers[i]
								.compareToIgnoreCase(phoneNoArray[j]) == 0) {
							flag = false;
						}
					}
					if(flag) {
						phoneNoString += " " + phoneNumbers[i];
					}
				}
				editor.putString("phoneNoString", phoneNoString);
				editor.commit();
				
				Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
			}
		});
		
		//Removes the numbers in the fields from the stored number list.
		remNums.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SharedPreferences preferences = PreferenceManager
						.getDefaultSharedPreferences(getBaseContext());
				Editor editor = preferences.edit();
				EditText phoneNos = (EditText) findViewById(R.id.editText1);

				// Gets the phone numbers in the EditText and checks for existing, add to the final string if not existing.
				String[] phoneNumbers = phoneNos.getText().toString()
						.split(" ");
				phoneNoString = "";
				boolean flag = true;
				for (int i = 0; i < phoneNoArray.length; i++) {
					for (int j = 0; j < phoneNumbers.length; j++) {
						if (phoneNumbers[j]
								.compareToIgnoreCase(phoneNoArray[i]) == 0) {
							flag = false;
						}
					}
					if(flag) {
						phoneNoString += phoneNoArray[i] + ' ';
					}
				}
				Log.v("debug", phoneNoString);
				editor.putString("phoneNoString", phoneNoString);
				editor.commit();
				
				Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_SHORT).show();
				
				//Retrieve phone numbers from shared preferences.
				phoneNoString = preferences.getString("phoneNoString", "324567 2345675 442134567 32234567 254654 4325 6543 5554 345645 432 5654 3324564 324567 5556");
				phoneNoArray = phoneNoString.split(" ");
				// Set phone numbers to phone number field
				String phoneNoDisplayStr = "";
				for (; position < NO_BATCH; position++) {
					if (position >= phoneNoArray.length) {
						break;
					}
					phoneNoDisplayStr += phoneNoArray[position] + ' ';
				}
				phoneNos.setText(phoneNoDisplayStr);
			}
		});

		// Sets the listener for send button press
		sendSMS.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText phoneNos = (EditText) findViewById(R.id.editText1);
				EditText message = (EditText) findViewById(R.id.editText2);

				// Gets the phone numbers in the EditText and sends the same
				// message to all.
				String[] phoneNumbers = phoneNos.getText().toString()
						.split(" ");
				for (int i = 0;; i++) {
					if (i >= phoneNumbers.length) {
						break;
					}
					if (phoneNumbers[i].length() > 0
							&& message.getText().toString().length() > 0) {
						sendSMS(phoneNumbers[i], message.getText().toString());
					} else {
						Toast.makeText(getApplicationContext(),
								"Enter both phone number and message",
								Toast.LENGTH_SHORT).show();
						return;
					}
				}
				// Set phone numbers to phone number field
				String phoneNoDisplayStr = "";
				for (int i = 0; i < NO_BATCH; i++) {
					position++;
					if (position >= phoneNoArray.length) {
						Toast.makeText(getApplicationContext(),
								"No more stored numbers", Toast.LENGTH_SHORT).show();
						break;
					}
					phoneNoDisplayStr += phoneNoArray[position] + ' ';
				}
				phoneNos.setText(phoneNoDisplayStr);
			}
		});

	}

	private void sendSMS(String phoneNumber, String message) {
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
