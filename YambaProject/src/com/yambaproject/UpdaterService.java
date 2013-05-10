package com.yambaproject;

import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;
import winterwell.jtwitter.TwitterException;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
public class UpdaterService extends Service {

	static final String TAG="Yamba";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		//Ignore this method
		return null;
	}
	Twitter twitter;
	public void onCreate()
	{
		super.onCreate();
		Log.d(TAG,"Service onCreate called");
		twitter=new Twitter("student","password");
		twitter.setAPIRootUrl("http://yamba.marakana.com/api");
	}
	
	public int onStartCommand(Intent intent,int flags,int startId)
	{
		Log.d(TAG,"Service onStartCommand called");
		new Thread() {
			public void run() {
				try {
					List<Status> timeline = twitter.getPublicTimeline();
					for (Status status : timeline) {
						Log.d(TAG, String.format("%s: %s", status.user.name,
								status.text));
					}
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					Log.e(TAG, "Could not retreive tweets", e);
					//e.printStackTrace();
				}
			}
		};
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG,"Service onDestroy called");
	}

}
