package com.example.contactslist;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        
        //Hard coded groups
        String[] groups = {"Web-Ops","Communications","PA","PRs","Finance"};
        
        //Adding groups to the list
        ListView groupsList = (ListView) findViewById(R.id.groupsListView);
        ListAdapter groupsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, groups);
        groupsList.setAdapter(groupsAdapter);
        groupsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				//Toast.makeText(getApplicationContext(), "You clicked on a group!"+Integer.toString(arg2), Toast.LENGTH_SHORT).show();
				Intent toContactList = new Intent(getApplicationContext(), com.example.contactslist.ContactsTabbedListActivity.class);
				
				//Starts the contacts activity with the group as extra detail.
				toContactList.putExtra("groupNumber", arg2);
				startActivity(toContactList);
			}
        	
        	
        });
        //groupsList.setClickable(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_list, menu);
        return true;
    }
    
}
