package com.nasaimageoftheday.mine;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NasaIOD extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nasa_iod);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_nasa_iod, menu);
		return true;
	}

}
