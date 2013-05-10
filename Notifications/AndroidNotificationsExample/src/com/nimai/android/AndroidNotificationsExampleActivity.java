
package com.nimai.android;

import com.nimai.android.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AndroidNotificationsExampleActivity extends Activity {
  private static final int NOTIFY_ME_ID=1337;
  private int count=0;
  private NotificationManager notifyMgr=null;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);    
    notifyMgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
  }
  
  public void triggerNotification(View v) {
    Notification notifyObj=new Notification(R.drawable.telephone,
                                        "Notification message!",
                                        System.currentTimeMillis());
    PendingIntent i=PendingIntent.getActivity(this, 0,
                            new Intent(this, NotifyActivity.class),
                                              0);
    
    notifyObj.setLatestEventInfo(this, "Notification Created",
                            "Click here to see the message", i);
    notifyObj.number=++count;
    notifyObj.defaults |= Notification.DEFAULT_VIBRATE;
    notifyObj.defaults |= Notification.DEFAULT_SOUND;
    notifyObj.flags|=Notification.FLAG_AUTO_CANCEL;    
    notifyMgr.notify(NOTIFY_ME_ID, notifyObj);
  }
  
  public void clearNotification(View v) {
    notifyMgr.cancel(NOTIFY_ME_ID);
  }
}
