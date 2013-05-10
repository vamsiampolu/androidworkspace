package com.mysecondsmsexample;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;

public class MainActivity extends ListActivity {

	public BroadcastReceiver messageReceiver;
	IntentFilter intentFilter;
	String from,message;
	int time;
	ArrayList<String> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		intentFilter=new IntentFilter();
		intentFilter.addAction("SMS_RECEIVED_ACTION");
		messageReceiver=new BroadcastReceiver()
		{
			public void onReceive(Context context,Intent intent)
			{
				list=getIntent().getStringArrayListExtra("details");
			}
		};
		registerReceiver(messageReceiver,intentFilter);
		
		
			this.getListView().setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
		
		
	}
	
	public void onDestroy()
	{
		super.onDestroy();
		unregisterReceiver(messageReceiver);
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
