package com.example.mygooddatabaseexample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;
	
public class MainActivity extends Activity {
	DatabaseHelper helper;
	Cursor c;
	EditText name,phone,email,id;
	String idText;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helper=new DatabaseHelper(this);
		try
		{
			String destPath="/data/data"+getPackageName()+"databasases";
			File f=new File(destPath);
			if(!f.exists())
			{
				f.mkdirs();
				f.createNewFile();
				copyDatabase(getBaseContext().getAssets().open("contacrs.db"),new FileOutputStream(destPath+"/MyDatabase.db"));
			}
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		name=(EditText)findViewById(R.id.nameEdit);
		phone=(EditText)findViewById(R.id.phoneEdit);
		email=(EditText)findViewById(R.id.emailEdit);
		id=(EditText)findViewById(R.id.idEdit);
	}
	
	public String getName()
	{
		String nameText=name.getText().toString();
		return nameText;
	}
	
	public void setName(String nameText)
	{
		name.setText(nameText);
	}
	
	public String getPhone()
	{
		String phoneText=phone.getText().toString();
		return phoneText;
	}
	
	public void setPhone(String phoneText)
	{
		phone.setText(phoneText);
	}
	
	
	public String getEmail()
	{
		String emailText=email.getText().toString();
		return emailText;
	}
	
	public void setEmail(String emailText)
	{
		email.setText(emailText);
	}
	public int getId()
	{
		int ids=Integer.parseInt(id.getText().toString());
		return ids;
	}
	
	public void setId(String ids)
	{
		id.setText(ids);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickGetRecord(View view)
	{
		helper.open();
		Cursor c=helper.getSingleRecord((long)getId());
		if(c!=null)
		{
			Toast.makeText(this, "The name is: "+c.getString(0)+" phone number is "+c.getString(1)+" and enail is"+c.getString(2), Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this,"No records found",Toast.LENGTH_SHORT).show();
		}
		helper.close();
		
	}
	
	public void onClickUpdateData(View view)
	{
		helper.open();
		if(helper.updateRecord(getId(),getName(), getPhone(), getEmail()))
		{
			Toast.makeText(this, "Update was successfull", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onClickDeleteRecord(View view)
	{
		helper.open();
		if(helper.deleteRecord(getId()))
		{
			Toast.makeText(this, "Delete process succeded", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this, "Record could not be deleted", Toast.LENGTH_SHORT).show();
		}
		helper.close();
	}
	
	public void onClickInsert(View view)
	{
		helper.open();
		helper.insertRecord(getName(),getPhone(), getEmail());
		//update method in DatabaseHelper to return long...check if long is -1...conditions
		helper.close();
		
	}
	
	public void onClickGetAllRecords(View view)
	{
		helper.open();
		Cursor c= helper.getAllRecords();
		if(c!=null)
		{	
			do 
			{
				Toast.makeText(this, "The name is "+c.getString(0)+"\nThe phone is "+c.getString(1)+"\nThe email is "+c.getString(2), Toast.LENGTH_SHORT).show();
			}while(c.moveToNext());
		}
		else
		{
			Toast.makeText(this, "No records found", Toast.LENGTH_SHORT).show();
		}
		helper.close();
		
	}
	
	public void onClickClear(View view)
	{
		setName("");setEmail("");setPhone("");setId("");
	}
	
	public void copyDatabase(InputStream inputStream,OutputStream outputStream) throws IOException
	{
		byte[] buffer=new byte[1024];
		int length;
		if((length=inputStream.read(buffer))>0)
		{
			outputStream.write(buffer,0,length);
		}
		inputStream.close();
		outputStream.close();
	}
}
