package com.yambaproject;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends Activity {

	EditText edit_status;
	static final String TAG="Yamba";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Debug.startMethodTracing("yamba");
		Log.d(TAG,"Started traceView");
		setContentView(R.layout.activity_main);	
		
	}
	
	public void onClick(View v)
	{
		edit_status=(EditText)findViewById(R.id.edit_status);
		String status=edit_status.getText().toString();
		new StatusUpdateTask().execute(status);
		edit_status.setText("");
	}
	
	 class StatusUpdateTask extends AsyncTask<String,Void,String>
	{

		@Override
		protected String doInBackground(String... input) {
			// TODO Auto-generated method stub
			try {
				Twitter twitter=new Twitter("student","password");
				twitter.setAPIRootUrl("http://yamba.marakana.com/api");
				twitter.setStatus(input[0]);
				Log.d(TAG,"Successfully posted "+input[0]);
				return "Successfully posted "+input[0];
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				Log.e(TAG,"Could not update "+input[0],e);
				//e.printStackTrace();
				return "Could not update "+input[0];
			}
			
		}
		
		public void onPostExecute(String result)
		{
			super.onPostExecute(result);
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
		}
		
	}
	 
	 public void onStop()
	 {
		 super.onStop();
		 Debug.stopMethodTracing();
		 Log.d(TAG,"Stopped tracing");
	 }
	
	//Setting an onClick attribute in xml 
	//and using it directly in Java Reflection is used to accomplish this

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//Only called once,when the user clicks the menu button for the first time 
		getMenuInflater().inflate(R.menu.servicemenu, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent=new Intent(this,com.yambaproject.UpdaterService.class);
		switch(item.getItemId())
		{
			case R.id.startService:
				startService(intent);
				Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.stop_service:
				stopService(intent);
				Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
		}
		return false;
	}

}
