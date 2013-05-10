package com.viewgrouptest.mine;

import android.os.Bundle;
import android.view.Window;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}

	public void onClickLinearLayout(View view)
	{
	  startActivity(new Intent("com.viewgrouptest.mine.LinearActivityTest"));	
	}
	

}
