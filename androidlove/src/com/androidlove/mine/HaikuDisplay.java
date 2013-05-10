package com.androidlove.mine;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HaikuDisplay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_haiku_display);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_haiku_display, menu);
		return true;
	}
	
	public void onLoveButtonClick(View view)
	{
		TextView htv=(TextView)findViewById(R.id.haikuTextView);
		htv.setVisibility(View.VISIBLE);
		
	}

}
