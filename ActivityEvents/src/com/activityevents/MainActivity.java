package com.activityevents;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
//import android.view.KeyEvent;
import android.widget.Toast;
import android.widget.EditText;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditText et=(EditText)findViewById(R.id.edit01);
		et.setOnFocusChangeListener(new View.OnFocusChangeListener()
		{
			public void onFocusChange(View v,boolean hasFocus)
			{
				Toast.makeText(getBaseContext(),((EditText)v).getId()+"has focus now",Toast.LENGTH_LONG).show();
			}
		}
				);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/*
	public boolean onKeyDown(int keyCode,KeyEvent event)
	{
		switch(keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_CENTER:
				Toast.makeText(getBaseContext(),"Center Button Clicked",Toast.LENGTH_LONG).show();
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				Toast.makeText(getBaseContext(),"Left Button clicked",Toast.LENGTH_LONG).show();
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				Toast.makeText(getBaseContext(),"Right Button Clicked",Toast.LENGTH_LONG).show();
				break;
			case KeyEvent.KEYCODE_DPAD_UP:
				Toast.makeText(getBaseContext(),"Up Button Clicked",Toast.LENGTH_LONG).show();
				break;
		}
		return false;
	}
*/
}
