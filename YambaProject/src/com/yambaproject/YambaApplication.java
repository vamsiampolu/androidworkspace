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

    public void pullAndInsert()
    {
        int count=0;
        long lastTimeStamp=-1;
        long largest=-1;

        try
        {
            List<Twitter.Status> timeline=getTwitter().getPublicTimeline();
            StatusData statusData=data;
            long time;
            for(Twitter.Status status:timeline)
            {
                Log.d(TAG,"Inserting status into database");
                statusData.insert(status);
                //time=status.createdAt.getTime();

            }

        }
        catch (TwitterException e)
        {

            //e.printStackTrace();
            Log.e(TAG, "Refresh Intent Service failed to access twitter timeline",e);
        }

            lastTimeStamp=largest;
            if(count>0)
            {
                Log.d(TAG,"you have "+count+" new tweets");
            }
            sendBroadcast(new Intent(YambaApplication.ACTION_NEW_STATUS));

    }

}
