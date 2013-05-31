package com.example.spidermanexample;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class RegisterService extends IntentService 
{
	public RegisterService()
	{
		super("Spiderman");
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		// TODO Auto-generated method stub
		String name=intent.getStringExtra("name");
		String email=intent.getStringExtra("email");
		String username=intent.getStringExtra("username");
		String password=intent.getStringExtra("password");
		UserDatabase userdata=new UserDatabase(this);
		userdata.openDatabase();
		if(userdata.insert(UserDatabase.TABLE_NAME,name,email,username,password));
		{
			Toast.makeText(this,"Registration successfull",Toast.LENGTH_SHORT).show();
		}		
		
	}
	
	
}
