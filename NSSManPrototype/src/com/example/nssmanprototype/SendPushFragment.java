package com.example.nssmanprototype;

import java.util.Arrays;
import java.util.LinkedList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParsePush;

/** 
 * Fragment to display UI to send push notifications to various channels of volunteers
 * @author killjoy
 * 29-09-3013
 */
public class SendPushFragment extends Fragment {

	EditText pushMessage;
	Button sendPush;
	EditText selectChannel;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.send_push_layout, container, false);
		pushMessage = (EditText) view.findViewById(R.id.pushmessage);
		sendPush = (Button) view.findViewById(R.id.sendpush);
		selectChannel = (EditText) view.findViewById(R.id.selectChannel);
		
		//TODO Set channels
		
		//Send a push to Events channel with the message in EditText
		sendPush.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {		
				ParsePush push = new ParsePush();
				LinkedList<String> channels = new LinkedList<String>(Arrays.asList(selectChannel.getText().toString().trim().split(",")));
				push.setChannels(channels);
				push.setMessage(pushMessage.getText().toString());
				push.sendInBackground();
				//Toast.makeText(getActivity(), "lol", Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;	
	}	
}
