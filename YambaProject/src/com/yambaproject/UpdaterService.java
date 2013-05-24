package com.yambaproject;

import java.util.List;


import winterwell.jtwitter.Twitter.Status;
import winterwell.jtwitter.TwitterException;
import android.app.Service;
import android.content.Intent;

import android.os.IBinder;

import android.util.Log;
public class UpdaterService extends Service {

	static final String TAG="Yamba";
	public static final	int DELAY=30;
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
		Log.d(TAG,"UpdaterService onCreate called");
		
		
	}
	
	public int onStartCommand(Intent intent,int flags,int startId)
	{
		running=true;

		Log.d(TAG,"UpdaterService onStartCommand called");
        new Thread()
        {
            public void run()
            {
                ((YambaApplication)getApplication()).pullAndInsert();
               int delay=Integer.parseInt(((YambaApplication)getApplication()).prefs.getString("delay","3"));
                try
                {

                    Thread.sleep(delay*1000);
                }
                catch(InterruptedException ex)
                {
                    Log.d(TAG,"UpdaterService Error implementing delay",ex);
                }
            }
        }.start();
        return super.onStartCommand(intent,flags,startId);
    }
	
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG,"UpdaterService onDestroy called");
		running=false;
	}

}
