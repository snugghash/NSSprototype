package com.example.batchsms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	int position = 0;
	int NO_BATCH = 10;
	public String TAG = "Debug";
	public String EXT = ".txt";
	public File mPath;
	public FileDialog fileDialog;
	public String fileString = null;
	public int ynflag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// View elements
		Button sendSMS = (Button) findViewById(R.id.sendSMS);
		Button saveNums = (Button) findViewById(R.id.saveNums);
		Button remNums = (Button) findViewById(R.id.remNums);

		// Retrieve phone numbers from shared preferences.
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		phoneNoString = preferences.getString("phoneNoString", "5554 5556");
		Log.v("debug1", phoneNoString);

		// Set phone numbers to phone number field
		setPhoneNos(phoneNoString, position);

		// Provision to add to the list of phone numbers
		saveNums.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SharedPreferences preferences = PreferenceManager
						.getDefaultSharedPreferences(getBaseContext());
				Editor editor = preferences.edit();
				EditText phoneNos = (EditText) findViewById(R.id.editText1);
				if (phoneNos == null)
					Log.v("troll", "troll");

				// Gets the phone numbers in the EditText and checks for already
				// existing, adds to list if not existing
				String[] phoneNumbers = phoneNos.getText().toString()
						.split(" ");
				for (int i = 0; i < phoneNumbers.length; i++) {
					boolean flag = true;
					for (int j = 0; j < phoneNoArray.length; j++) {
						if (phoneNumbers[i].compareTo(phoneNoArray[j]) == 0) {
							// Log.v("addComp","same");
							flag = false;
						}
					}
					if (flag) {
						phoneNoString += " " + phoneNumbers[i];
					}
				}
				editor.putString("phoneNoString", phoneNoString);
				editor.commit();
				Log.v("debugAdd", phoneNoString);
				Toast.makeText(getApplicationContext(), "Added",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Removes the numbers in the fields from the stored number list.
		remNums.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SharedPreferences preferences = PreferenceManager
						.getDefaultSharedPreferences(getBaseContext());
				Editor editor = preferences.edit();
				EditText phoneNos = (EditText) findViewById(R.id.editText1);

				// Gets the phone numbers in the EditText and checks for
				// existing, add to the final string if not existing.
				String[] phoneNumbers = phoneNos.getText().toString()
						.split(" ");
				phoneNoString = "";
				for (int i = 0; i < phoneNoArray.length; i++) {
					boolean flag = true;
					for (int j = 0; j < phoneNumbers.length; j++) {
						if (phoneNumbers[j]
								.compareToIgnoreCase(phoneNoArray[i]) == 0) {
							flag = false;
						}
					}
					if (flag) {
						phoneNoString += phoneNoArray[i] + ' ';
					}
				}
				Log.v("debugRem", phoneNoString);
				editor.putString("phoneNoString", phoneNoString);
				editor.commit();

				Toast.makeText(getApplicationContext(), "Removed",
						Toast.LENGTH_SHORT).show();

				// Retrieve phone numbers from shared preferences.
				phoneNoString = preferences.getString("phoneNoString",
						"5554 5556");
				setPhoneNos(phoneNoString, position);
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
				// Set phone numbers to phone number field TODO Verify
				setPhoneNos(phoneNoString,position);
			}
		});

		// Defines a dialogue to select a '*.txt' file
		mPath = new File(Environment.getExternalStorageDirectory() + "//DIR//");
		fileDialog = new FileDialog(this, mPath);
		fileDialog.setFileEndsWith(EXT);
		fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
			public void fileSelected(File file) {
				Log.d(getClass().getName(), "selected file " + file.toString());
				try {
					fileString = getStringFromFile(file.toString());
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(),
							"Bad file operation", Toast.LENGTH_SHORT).show();
				}
				if(ynflag == 1) {

					if (fileString.length() <= 0) {
						Toast.makeText(getApplicationContext(),
								"The file has not been selected or is empty",
								Toast.LENGTH_SHORT).show();
					}
					phoneNoString = fileString;
					setPhoneNos(phoneNoString, 0);
				}
				else {
					phoneNoString += fileString;
					phoneNoArray = phoneNoString.split(" ");
				}
			}
		});
	}

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				// Yes button clicked
				ynflag = 1;
				// Selects the file and overwrites the phoneNoString
				fileDialog.showDialog();
				break;

			case DialogInterface.BUTTON_NEGATIVE:
				// No button clicked
				ynflag = 0;
				// Selects the file and appends to the phoneNoString
				fileDialog.showDialog();
				break;
			}
		}
	};

	private void sendSMS(String phoneNumber, String message) {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}

	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Gets a string from a txt file provided by 'filepath'
	 * 
	 * @param filePath
	 *            The path to txt file
	 * @return Entire file as a string
	 * @throws Exception
	 */
	public static String getStringFromFile(String filePath) throws Exception {
		File fl = new File(filePath);
		FileInputStream fin = new FileInputStream(fl);
		String ret = convertStreamToString(fin);
		// Make sure you close all streams.
		fin.close();
		return ret;
	}

	/**
	 * Function to set the phone numbers in the phoneNos field
	 * 
	 * @param phoneNoString
	 *            The string to display in the phoneNo field
	 * @param flag
	 *            If zero, sets position to zero
	 */
	public void setPhoneNos(String phoneNoString, int pos) {

		position = pos;

		phoneNoArray = phoneNoString.split(" ");
		String phoneNoDisplayStr = "";
		for (int i=0; i< NO_BATCH; i++) {
			if (position >= phoneNoArray.length) {
				Toast.makeText(getApplicationContext(),
						"No more stored numbers",
						Toast.LENGTH_SHORT).show();
				break;
			}
			phoneNoDisplayStr += phoneNoArray[position] + ' ';
			position++;
		}
		EditText phoneNos = (EditText) findViewById(R.id.editText1);
		phoneNos.setText(phoneNoDisplayStr);
	}

	/**
	 * Listener for button click to select file.
	 * 
	 * @param view
	 *            The button which called the function.
	 */
	public void select(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Do you want to erase all previously saved numbers?")
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener).create().show();
		Log.v(TAG, "lolcat");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected void onResume(Bundle savedInstancestate) {
		super.onResume();
	}
}
