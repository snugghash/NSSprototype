package com.example.nssvolprototype;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EventsPushActivity extends Activity {
	
	TextView showPush;
	Button viewPrevNotif;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events_push);
		
		//TODO register for event
		//TODO Cancel registration
		//TODO Description of event
		
		Toast.makeText(getApplicationContext(), "You're in events!", Toast.LENGTH_SHORT).show();
		Intent intent = getIntent();
		JSONObject data = null;
		String message = "Error Occured";
		try {
			data = new JSONObject(intent.getExtras().getString("com.parse.Data"));
			message = (String) data.get("alert");
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e("debug log", "in JSONException");
		} catch (NullPointerException e) {
			e.printStackTrace();
			Log.e("debug log", "in NPE");
		}
		// Log.v("debug Push", message);
		// Log.v("debug Push", data.toString());

		SharedPreferences prefs = this.getApplicationContext()
				.getSharedPreferences("org.saarang.app",
						Context.MODE_PRIVATE);
		
		showPush = (TextView) findViewById(R.id.push_view);
		//messageLog = (TextView) findViewById(R.id.message_log);
		viewPrevNotif = (Button) findViewById(R.id.action_see_push_log);
		viewPrevNotif.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences prefs = v.getContext()
						.getSharedPreferences("org.saarang.app",
								Context.MODE_PRIVATE);

				//messageLog.setVisibility(View.VISIBLE);
				showPush.setVisibility(View.GONE);
				viewPrevNotif.setVisibility(View.GONE);
				//messageLog.setText(prefs.getString("pushlog",
				//		"No previous logged notifications"));
				
				//List View to display a list of all the notifications
				ListView listView = (ListView) findViewById(R.id.list_message_log);
				listView.setVisibility(View.VISIBLE);
				
				String logString = prefs.getString("pushlog",
						"No previous logged notifications~");
				//Log.v("debug ergex", logString);
				
				String log[] = logString.split("~");
				ArrayList<String> list = new ArrayList<String>();
			    for (int i = 0; i < log.length; ++i) {
			      list.add(log[i]);
			    }
			    ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(),
			        R.layout.log_item, list);
			    listView.setAdapter(adapter);

			}
			
		});
		if (data == null || message==null) {
			//messageLog.setVisibility(View.VISIBLE);
			showPush.setVisibility(View.GONE);
			//viewPrevNotif.setVisibility(View.GONE);
			//messageLog.setText(prefs.getString("pushlog",
			//		"No previous logged notifications"));
		} else {
			showPush.setText(message);
			String pushLog = prefs.getString("pushlog","");
			prefs.edit().putString("pushlog", pushLog + message + "~").commit();
		}// Toast.makeText(getApplicationContext(), "You got a push with data "
			// +
			// data, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
