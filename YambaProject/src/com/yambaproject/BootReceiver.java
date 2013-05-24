package com.yambaproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.app.AlarmManager;

public class BootReceiver extends BroadcastReceiver {

	final static String TAG="Yamba";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//Activities and Services are Context(is a relationship)
		//BroadcastReceivers have Context...(has a relationship)
		//Context represents the context in which something is running...an Activity,a Service,the Application???
		//Register to a BroadcastReceiver using IntentFilter,the BroadcastReceiver sends an Intent which is received by all
		//app components filtering for this...in case of a system wide activity...you must have permission
		long interval;
        interval = Long.parseLong(PreferenceManager.getDefaultSharedPreferences(context).getString("delay", "900000"));
        PendingIntent operation=PendingIntent.getService(context, -1, new Intent("com.yambaproject.REFRESH_YAMBA"), PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,System.currentTimeMillis(),interval,operation);
        Log.d(TAG,"BroadcastReceiver onReceive method called");
		Log.d(TAG,"BootReceiver delay is "+interval);
	}

}
