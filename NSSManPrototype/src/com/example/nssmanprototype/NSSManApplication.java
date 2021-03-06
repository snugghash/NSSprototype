package com.example.nssmanprototype;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.parse.ParseUser;

import android.app.Application;

public class NSSManApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "PZUZRcwNG4MWFMEi0IRYjgpbhdtnFH1ExyZm4PNp", "iaZbpL6IzEJej5hcFlwLAMUBvsVRI5IAhqjnpW6d"); 

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
		
		//Set callback activity on receiving a push. TODO
		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}

}
