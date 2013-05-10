package com.example.dynamicfragmentui;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.FragmentActivity;

//When creating an activity that uses fragments based on the support library extend FragmentActivity
public class MainActivity extends FragmentActivity implements HeadlinesFragment.OnHeadlinesFragmentSelected {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if((findViewById(R.id.flayout))!=null)
		{	
			if(savedInstanceState!=null)
			{
			return;
			}
		}
		//In case the activity was started with special instructions by another activity...
		HeadlinesFragment hlf=new HeadlinesFragment();
		hlf.setArguments(getIntent().getExtras());//...set Arguments
		
		getSupportFragmentManager().beginTransaction().add(R.id.flayout,hlf).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void checkLogin()
	{
		EditText login=(EditText)findViewById(R.id.login);
		EditText pwd=(EditText)findViewById(R.id.pwd);
		Button btn=(Button)findViewById(R.id.login_button);
	}

}
