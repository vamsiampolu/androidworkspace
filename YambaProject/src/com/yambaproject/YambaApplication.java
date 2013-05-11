package com.yambaproject;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.util.Log;

public class YambaApplication extends Application 
{
	static final String TAG="YambaApp";
	Twitter twitter;
	
	public void onCreate()
	{
		super.onCreate();
		twitter=new Twitter("student","password");
		twitter.setAPIRootUrl("http://yamba.marakana.com/api");
		Log.d(TAG, "Application onCreate called");
	}
}
