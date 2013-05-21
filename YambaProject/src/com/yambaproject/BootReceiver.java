package com.yambaproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


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
		context.startService(new Intent(context,com.yambaproject.UpdaterService.class));
		Log.d(TAG,"BroadcastReceiver onReceive method called");
		Log.d(TAG,"started the updater service");
	}

}
