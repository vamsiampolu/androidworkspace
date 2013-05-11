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
	static final int DELAY=30000;
	boolean running=false;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		//Ignore this method
		return null;
	}
	
	public void onCreate()
	{
		super.onCreate();
		Log.d(TAG,"Service onCreate called");
		
	}
	
	public int onStartCommand(Intent intent,int flags,int startId)
	{
		running=true;
		Log.d(TAG,"Service onStartCommand called");
		new Thread() {
			public void run() {
				try {
					while(running)
					{
						List<Status> timeline = ((YambaApplication)getApplication()).getTwitter().getPublicTimeline();
						for (Status status : timeline)
						{
							Log.e(TAG, String.format("%s: %s", status.user.name,status.text));
						}
						try {
							Thread.sleep(DELAY);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							Log.e(TAG,"Interrupted exception",e);
							//e.printStackTrace();
						}
					}				
				}
				 
				catch (TwitterException e) 
				{
					// TODO Auto-generated catch block
					Log.d(TAG, "Could not retreive tweets", e);
					e.printStackTrace();
				}
			}
		}.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG,"Service onDestroy called");
		running=false;
	}

}
