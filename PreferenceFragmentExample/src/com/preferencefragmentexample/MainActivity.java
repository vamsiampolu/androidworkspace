package com.preferencefragmentexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		FragmentEg feg=new FragmentEg();
		ft.replace(android.R.id.content,feg);
		ft.addToBackStack(null);
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
