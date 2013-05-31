package com.yambaproject;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;
import winterwell.jtwitter.TwitterException;
import java.util.List;
import android.content.Intent;

public class YambaApplication extends Application implements OnSharedPreferenceChangeListener
{
	static final String TAG="YambaApp";
    public static final String ACTION_NEW_STATUS="com.yambaproject.newstatus";
	private Twitter twitter;
	public SharedPreferences prefs;
	public StatusData data;
    long lastTimeStamp=-1;
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
			
			String username=prefs.getString(getString(R.string.editpref_key), "student");
			String password=prefs.getString("password", "password");
			String server=prefs.getString("server", "http://yamba.marakana.com/api");
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
        if(prefs==null)
        {
            startActivity(new Intent(this,PrefsActivity.class));
        }
		Log.d(TAG,"Twitter object is going to be reinitialized because shared preferences "+key +"changed");
	}

    public synchronized void pullAndInsert()
    {

        try
        {
            List<Twitter.Status> timeline=getTwitter().getPublicTimeline();
            StatusData statusData=data;
            for(Twitter.Status status:timeline)
            {
                Log.d(TAG,"Inserting status into database");
                statusData.insert(status);
            }
        }
        catch (TwitterException e)
        {

            //e.printStackTrace();
            Log.e(TAG, "Update Service failed to access twitter timeline",e);
        }
        catch(NullPointerException nex)
        {
            Log.e(TAG,"Null pointer exception",nex);
        }
        catch(RuntimeException rex)
        {
            Log.d(TAG,"runtime exception",rex);
        }

    }

}
