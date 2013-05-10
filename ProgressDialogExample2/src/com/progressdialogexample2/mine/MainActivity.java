package com.progressdialogexample2.mine;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.app.Dialog;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {

	ProgressDialog progressDialog;
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
	
	@SuppressWarnings("deprecation")
	public void launchDetProgress(View view)
	{
		showDialog(0);
		progressDialog.setProgress(0);
		new Thread(new Runnable()
		{
			public void run()
			{
				for(int i=0;i<15;i++)
				{
					try
					{
						Thread.sleep(1000);
						progressDialog.incrementProgressBy((int)(100/15));
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
				).start();
	}
	
	protected Dialog onCreateDialog(int id)
	{
		 progressDialog=new ProgressDialog(this);
		progressDialog.setIcon(R.drawable.ic_launcher);
		progressDialog.setTitle("Downloading files");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "OK CLICKED",Toast.LENGTH_SHORT).show();
				
			}
				
			
		});
		progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"CANCEL",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "CANCEL CLICKED",Toast.LENGTH_SHORT).show();
			}
		});
		
		return progressDialog;
	}
	

}
