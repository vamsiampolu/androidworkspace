package com.yambaproject;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

public class YambaApplication extends Application 
{
	static final String TAG="YambaApp";
	private Twitter twitter;
	SharedPreferences prefs;
	
	public void onCreate()
	{
		prefs=PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() 
		{
			
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
					String key) 
			{
				// TODO Auto-generated method stub
				twitter=null;
				Log.d(TAG,"Twitter object is going to be reinitialized because shared preferences "+key +"changed");
			}
		});
		twitter=getTwitter();
		super.onCreate();
		Log.d(TAG, "Application onCreate called");
	}
	
	public Twitter getTwitter()
	{
		
		if(twitter==null)
		{
			String username=prefs.getString(getString(R.string.editpref_key), "");
			String password=prefs.getString("password", "");
			String server=prefs.getString("server", "");		
			twitter=new Twitter(username,password);
			twitter.setAPIRootUrl(server);
		}
			return twitter;
	}
}
