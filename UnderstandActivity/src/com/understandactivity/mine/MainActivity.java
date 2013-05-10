package com.understandactivity.mine;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;


public class MainActivity extends Activity {

	//Log.d sends a debug message to the Log tag-->used to identify source of log message,msg-->Source of log message 
	String tag="Lifecycle of an activity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(tag,"In onCreate Event");
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		Log.d(tag,"In onStart Event");
	}
	
	@Override
	public void onRestart()
	{
		super.onRestart();
		Log.d(tag,"In onRestart Event");
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(tag,"In onPause Event");
	}

	@Override
	public void onStop()
	{
		super.onStop();
		Log.d(tag,"In onStop Event");
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(tag,"In onDestroy Event");
	}

}
