package com.yambaproject;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

public class YambaApplication extends Application implements OnSharedPreferenceChangeListener
{
	static final String TAG="YambaApp";
	private Twitter twitter;
	public SharedPreferences prefs;
	public StatusData data;
	public void onCreate()
	{
		prefs=PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this); 				
		twitter=getTwitter();
		super.onCreate();
		data=new StatusData(this);
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
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) 
	{
		// TODO Auto-generated method stub
		twitter=null;
		this.prefs=sharedPreferences;
		Log.d(TAG,"Twitter object is going to be reinitialized because shared preferences "+key +"changed");
	}
}
