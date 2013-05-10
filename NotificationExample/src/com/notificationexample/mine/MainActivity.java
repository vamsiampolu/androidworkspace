package com.notificationexample.mine;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.app.Notification;
import android.app.NotificationManager;

public class MainActivity extends Activity {

	int notificationID=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickNotification(View view)
	{
		displayNotification();
	}
	
	@SuppressWarnings("deprecation")
	public void displayNotification()
	{
		Intent i=new Intent(this,NotificationView.class);
		i.putExtra("notificationID",notificationID);
		//WTF are PendingIntents:
		PendingIntent pi=PendingIntent.getActivity(this,0,i,0);
		NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification notif=new Notification(R.drawable.ic_launcher,"Remaunder:Meeting starts in five minutes",System.currentTimeMillis());
		CharSequence from="System Alarm";
		CharSequence message="Meeting with customer at 3pm";
		notif.setLatestEventInfo(this,from, message,pi);
		notif.vibrate=new long[]{100,250,100,500};
		nm.notify(notificationID,notif);
	}

}
