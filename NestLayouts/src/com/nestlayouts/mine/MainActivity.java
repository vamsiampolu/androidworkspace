package com.nestlayouts.mine;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.content.Intent;
import android.view.View;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	
	}
	
	public void onClick(View view)
	{
		startActivity(new Intent("com.nestlayouts.mine.SecondActivity"));
	}

	

}
