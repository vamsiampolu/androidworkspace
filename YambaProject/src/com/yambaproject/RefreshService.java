package com.yambaproject;

import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;
import winterwell.jtwitter.TwitterException;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class RefreshService extends IntentService
{
	static final String TAG="Yamba";
	
	public RefreshService()
	{
		super("RefreshService");
		// TODO Auto-generated constructor stub
		//do not do anything in the constructor that could potentially fail
	}
	
	public void onCreate()
	{
		super.onCreate();
		Log.d(TAG,"Refresh Intent Service Created");
		
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		// TODO Auto-generated method stub
		Log.d(TAG,"Refresh IntentService");
        ((YambaApplication)getApplication()).pullAndInsert();
		Log.d(TAG,"Refresh IntentService onHandleIntent called");
	}
	
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG,"Resfresh IntentService destroyed");
	}
}
