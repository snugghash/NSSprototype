package com.example.tabtester;
 
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
 
@SuppressWarnings("deprecation")
public class AndroidTabLayoutActivity extends TabActivity {
    // Called when the activity is first created. 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_tab_layout);
 
        TabHost tabHost = getTabHost();
 
        // Tab for E-Mail
        TabSpec emspec = tabHost.newTabSpec("E-Mail");
        // setting Title and Icon for the Tab
        Intent photosIntent = new Intent(this, emailActivity.class);
        emspec.setContent(photosIntent);
 
        // Tab for Tasks
        TabSpec taskspec = tabHost.newTabSpec("tasks");
        Intent tasksIntent = new Intent(this, TaskActivity.class);
        taskspec.setContent(tasksIntent);
 
        // Adding all TabSpec to TabHost
        tabHost.addTab(emspec); // Adding E-mail tab
        tabHost.addTab(taskspec); // Adding tasks tab
    }
}