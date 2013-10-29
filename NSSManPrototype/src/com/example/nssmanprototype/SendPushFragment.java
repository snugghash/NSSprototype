package com.example.nssmanprototype;

import com.parse.ParsePush;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/** 
 * Fragment to display UI to send push notifications to various channels of volunteers
 * @author killjoy
 * 29-09-3013
 */
public class SendPushFragment extends Fragment {

	EditText pushMessage;
	Button sendPush;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.send_push_layout, container, false);
		pushMessage = (EditText) view.findViewById(R.id.pushmessage);
		sendPush = (Button) view.findViewById(R.id.sendpush);	

		//Send a push to Events channel with the message in EditText
		sendPush.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {		
				ParsePush push = new ParsePush();
				push.setChannel("Events");
				push.setMessage(pushMessage.getText().toString());
				push.sendInBackground();
				Toast.makeText(getActivity(), "lol", Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;	
	}	
}
