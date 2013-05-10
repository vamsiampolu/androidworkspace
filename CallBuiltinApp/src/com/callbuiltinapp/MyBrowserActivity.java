package com.callbuiltinapp;

import android.app.Activity;
import android.os.Bundle;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
public class MyBrowserActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
		Uri url=getIntent().getData();
		WebView webview=(WebView)findViewById(R.id.webview01);
		webview.setWebViewClient(new Callback());
		webview.loadUrl(url.toString());
	}
	
	private class Callback extends WebViewClient
	{
		public boolean shouldOverrideUrlLoading(WebView view,String url)
		{
			return false;
		}
	}
	
	
	

}
