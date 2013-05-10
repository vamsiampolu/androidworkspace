package com.usingintent.mine;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void navigateAway(View view)
	{
		startActivity(new Intent("xyz"));//Here,the string specified is the name provided in<intent-filter><action android:name="xyz"> for the second activity
	//Any application can call this secondactivity using its intent-filter...if there is more than one activity with the same intent-filter name,the android system prompts for a choice
		//if the activity is defined in the same project startActivity(Context,<activity-name.class>)
		
	}

}
