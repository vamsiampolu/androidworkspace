package com.viewgrouptest.mine;

import android.app.Activity;
import android.view.Window;
import android.os.Bundle;
public class ScrollViewTest extends Activity {

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sviewexample);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
}
