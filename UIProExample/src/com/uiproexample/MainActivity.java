package com.uiproexample;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.Window;
import android.widget.Toast;
import android.content.Intent;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ActionBar actionBar=getActionBar();
		actionBar.setIcon(R.drawable.spiderman);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.enemies:
				startActivity(new Intent("com.uiproexample.EnemiesActivity"));
				return true;
		case R.id.gallery:
			Toast.makeText(getBaseContext(), "You clikcked on gallery",Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

}
