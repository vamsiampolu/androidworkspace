package com.usingintent.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class SecondActivity extends Activity {
	int request_Code=1;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondactivity);
	}
	
	public void forOutput(View view)
	{
		startActivityForResult(new Intent("xyz2"),request_Code);
	}
	
	public void onActivityResult(int requestCode,int resultCode,Intent data)
	{
		if(requestCode==request_Code)
		{
			if(resultCode==RESULT_OK)
			{
				Toast.makeText(getBaseContext(),data.getData().toString(),Toast.LENGTH_SHORT).show();
			}
		}
	}
	//The <category> in <intent-filter> in has been set to DEFAULT i.e it can be called by other activities
}
