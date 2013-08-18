package com.example.webviewtest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView myWebView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setLoadsImagesAutomatically(false);
		myWebView.loadUrl("http://www.nss.iitm.ac.in/accounts/login/?next=/accounts/807/performance/details/");
		webSettings.setJavaScriptEnabled(true);
		myWebView.setWebViewClient(new WebViewClient() {
		    public void onPageFinished(WebView view, String url) {
		        String user="u";
		        String pwd="p";
		        view.loadUrl("javascript:document.getElementById('username').value = '"+user+"';document.getElementById('password').value='"+pwd+"';");
		    }
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clicker(View view) {
	
		WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.setWebViewClient(new WebViewClient());
	}
}
