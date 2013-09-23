package com.example.choosefiletest;

import java.io.File;
import java.io.FilenameFilter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	public String TAG = "mainAct";
	public String EXT = ".txt";
	public File mPath;
	public FileDialog fileDialog;
	
	//Old code for removed implementation
//	private String[] mFileList;
//	private File mPath = new File(Environment.getExternalStorageDirectory() + "//test//");
//	private String mChosenFile;
//	private static final String FTYPE = ".txt";    
//	private static final int DIALOG_LOAD_FILE = 1000;
//
//	private void loadFileList() {
//	    try {
//	        mPath.mkdirs();
//	    }
//	    catch(SecurityException e) {
//	        Log.e(TAG, "unable to write on the sd card " + e.toString());
//	    }
//	    if(mPath.exists()) {
//	        FilenameFilter filter = new FilenameFilter() {
//	            public boolean accept(File dir, String filename) {
//	                File sel = new File(dir, filename);
//	                return filename.contains(FTYPE) || sel.isDirectory();
//	            }
//	        };
//	        mFileList = mPath.list(filter);
//	    }
//	    else {
//	        mFileList= new String[0];
//	    }
//	}
//
//	protected Dialog onCreateDialog(int id) {
//	    Dialog dialog = null;
//	    AlertDialog.Builder builder = new Builder(this);
//
//	    switch(id) {
//	        case DIALOG_LOAD_FILE:
//	            builder.setTitle("Choose your file");
//	            if(mFileList == null) {
//	                Log.e(TAG, "Showing file picker before loading the file list");
//	                dialog = builder.create();
//	                return dialog;
//	            }
//	            builder.setItems(mFileList, new DialogInterface.OnClickListener() {
//	                public void onClick(DialogInterface dialog, int which) {
//	                    mChosenFile = mFileList[which];
//	                    //you can do stuff with the file here too
//	                }
//	            });
//	            break;
//	    }
//	    dialog = builder.show();
//	    return dialog;
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Defines a dialogue to select a '*.txt' file
		mPath = new File(Environment.getExternalStorageDirectory() + "//DIR//");
        fileDialog = new FileDialog(this, mPath);
        fileDialog.setFileEndsWith(EXT);
        fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
            public void fileSelected(File file) {
                Log.d(getClass().getName(), "selected file " + file.toString());
                TextView display = (TextView) findViewById(R.id.display);
                display.setText(file.toString());
            }
        });
        //fileDialog.addDirectoryListener(new FileDialog.DirectorySelectedListener() {
        //  public void directorySelected(File directory) {
        //      Log.d(getClass().getName(), "selected dir " + directory.toString());
        //  }
        //});
        //fileDialog.setSelectDirectoryOption(false);
	}
	
	public void select(View view) {
		fileDialog.showDialog();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
