package com.usingintent.mine;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.net.Uri;

public class ThirdActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_activity);
	}
	
	public void reFromIntent(View view)
	{
		Intent data=new Intent();
		EditText txt=(EditText)findViewById(R.id.namehere);
		try
		{
			data.setData(Uri.parse(txt.getText().toString()));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		setResult(RESULT_OK,data);
		finish();
	}
	
	
}
