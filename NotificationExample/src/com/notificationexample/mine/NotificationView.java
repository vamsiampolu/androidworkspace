package com.notificationexample.mine;

import android.app.Activity;
import android.os.Bundle;
import android.app.NotificationManager;
public class NotificationView extends Activity {
  public void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_notification);
	  NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	  nm.cancel(getIntent().getExtras().getInt("notificationID"));
  }
}
