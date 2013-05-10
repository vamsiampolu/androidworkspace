

package com.nimai.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NotifyActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    TextView txt=new TextView(this);
    
    txt.setText("You have successfully opened the activity associated with the notification!");
    setContentView(txt);
  }
}
