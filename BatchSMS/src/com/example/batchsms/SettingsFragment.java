package com.example.batchsms;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Displays settings for the batch sms app.
 * 
 * @author GS4444
 *
 */
public class SettingsFragment extends PreferenceFragment{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

}
