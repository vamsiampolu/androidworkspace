package com.callbuiltinapp;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void browserOnClick(View view)
	{
		Intent intent=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
		startActivity(intent);
	}
	
	public void callOnClick(View view)
	{
		Intent intent=new Intent(android.content.Intent.ACTION_DIAL,Uri.parse("tel:+9701588818"));
		startActivity(intent);
	}
	
	public void showMapOnClick(View view)//AVD must support Google APIs for this to work
	{
		Intent intent=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("geo:17.0000,83.0000"));
		startActivity(intent);
	}
	
	public void webviewOnClick(View view)
	{
		Intent i=new Intent("com.callbuiltinapp.MyBrowserActivity");
		i.addCategory("com.MyExamples.App");
		i.setData(Uri.parse("http://www.google.com"));
		startActivity(Intent.createChooser(i,"Open url with:"));
	}
	
	
	
}