package com.example.nssvolprototype;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment to display UI to subscribe to various things.
 * @author killjoy
 *
 */
public class SubscribeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.subscribe, container, false);
		
		return view;
	}

	public void calendarSubscribe(View view){
		Uri uri = Uri
				.parse("https://www.google.com/calendar/render?cid=webops.nssiitm%40gmail.com");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
}
