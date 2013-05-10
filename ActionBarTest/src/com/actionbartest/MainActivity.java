package com.actionbartest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewDebug.FlagToString;
import android.widget.Toast;
import android.app.ActionBar;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar=getActionBar();
		//actionBar.hide();
		//actionBar.show();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		return MenuChoice(item);
	}
	
	private void CreateMenu(Menu menu)
	{
		MenuItem mnu1=menu.add(0,0,0,"Item1");
		{
			mnu1.setIcon(R.drawable.ic_launcher);
			mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		
		MenuItem mnu2=menu.add(0,1,1,"Item2");
		{
			mnu2.setIcon(R.drawable.ic_launcher);
			mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		
		MenuItem mnu3=menu.add(0,3,3,"Item3");
		{
			mnu3.setIcon(R.drawable.ic_launcher);
			mnu3.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		
		MenuItem mnu4=menu.add(0,4,4,"Item4");
		{
			mnu4.setIcon(R.drawable.ic_launcher);
			mnu4.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		
		MenuItem mnu5=menu.add(0,5,5,"Item5");
		{
			mnu5.setIcon(R.drawable.ic_launcher);
			mnu5.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
	}
	
	private boolean MenuChoice(MenuItem item)
	{
		switch(item.getItemId())
		{
		case android.R.id.home:
			Toast.makeText(this,"You clicked on the Application icon",Toast.LENGTH_LONG).show();
			Intent intent=new Intent(this,MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			return true;
		case 0:
			Toast.makeText(this,"You clicked Item1",Toast.LENGTH_LONG).show();
			return true;
			
		case 1:
			Toast.makeText(this,"You clicked Item2",Toast.LENGTH_LONG).show();
			return true;
		case 2:
			Toast.makeText(this,"You clicked Item3",Toast.LENGTH_LONG).show();
			return true;
		case 3:
			Toast.makeText(this,"You clicked Item4",Toast.LENGTH_LONG).show();
			return true;
		case 4:
			Toast.makeText(this,"You clicked Item5",Toast.LENGTH_LONG).show();
			return true;
		}
		return false;
	}

}
