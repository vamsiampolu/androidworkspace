package com.dialogfragmentexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentEg feg=FragmentEg.newInstance("Are you sure you want to do this?");
		feg.show(getFragmentManager(),"dialog");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void doPositiveClick()
	{
		Toast.makeText(getBaseContext(),"Ok Clicked",Toast.LENGTH_SHORT).show();
	}
	
	public void doNegativeClick()
	{
		Toast.makeText(getBaseContext(),"Cancel Clicked",Toast.LENGTH_SHORT).show();
	}
}
