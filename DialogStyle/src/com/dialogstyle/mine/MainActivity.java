package com.dialogstyle.mine;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.View;
public class MainActivity extends Activity {
//The application theme has been modified to: android:style/Theme.Dialog in the manifest in <application>
//For a hol	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//used to remove title of the application
		setContentView(R.layout.activity_main);
		
	}
	
	public void onClick(View view)
	{
		finish();
		
	}
	
}
