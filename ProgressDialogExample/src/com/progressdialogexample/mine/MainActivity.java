package com.progressdialogexample.mine;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.app.ProgressDialog;
public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void launchProgress(View view)
	{
		final ProgressDialog pd=ProgressDialog.show(this,"Doing Something","Please Wait...", true);
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					Thread.sleep(5000);//thread is blocked for five minutes
					pd.dismiss();
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

}
