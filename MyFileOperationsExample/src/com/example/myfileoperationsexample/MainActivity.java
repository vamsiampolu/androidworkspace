package com.example.myfileoperationsexample;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import android.widget.Toast;
public class MainActivity extends Activity {
	EditText et;
	File sdCard,directory,file;
	boolean isReadyForAction=false;
	BufferedWriter writer;
	BufferedReader br;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et=(EditText)findViewById(R.id.edit01);
		onCreateFolder("MyAppFiles");
		onPrepareForExternalStorageOperations(directory,"myText.txt");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickSave(View view)
	{
		try
		{
			@SuppressWarnings("deprecation")
			FileOutputStream fOpen=openFileOutput("myText.txt",MODE_WORLD_READABLE);
			OutputStreamWriter osw=new OutputStreamWriter(fOpen);
			BufferedWriter bw=new BufferedWriter(osw);
			bw.write(et.getText().toString());
			bw.flush();
			bw.close();
		}	
		catch(IOException iox)
		{
			iox.printStackTrace();
		}
		et.setText("");
		Toast.makeText(this,"File saved successfully",Toast.LENGTH_SHORT).show();
	}
	
	public void onClickLoad(View view)
	{
		String s="";
		//int charRead;
		//char[] inputBuffer=new char[READ_BLOCK_SIZE];
		try
		{
			FileInputStream fLoad=openFileInput("myText.txt");
			InputStreamReader isr=new InputStreamReader(fLoad);
			BufferedReader br=new BufferedReader(isr);
			while((s=br.readLine())!=null)
			{
				et.setText(et.getText()+s);
			}
		}
		catch(IOException iox)
		{
			iox.printStackTrace();
		}
		Toast.makeText(this,"File loaded successfully"+s,Toast.LENGTH_SHORT).show();
	}
	
	public void onClickClear(View view)
	{
		et.setText("");
	}
	
	public void onClickSaveToSDCard(View view)
	{
		if(isReadyForAction)
		{
			try
			{
				writer.write(et.getText().toString());
				writer.flush();
				writer.close();
			}
			catch(IOException iox)
			{
				iox.printStackTrace();
			}
			et.setText("");
			Toast.makeText(this,"File saved to external storage",Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this,"File could not be saved to SDCard",Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onClickLoadFromSDCard(View view)
	{
		String s="";
		if(isReadyForAction && isExternalStorageReadable())
		{
			try
			{			
					while((s=br.readLine())!=null)
					{
					
						et.setText(et.getText()+s);
					}
			}
			catch(IOException iox)
			{
				iox.printStackTrace();
			}
			Toast.makeText(this, "File loaded from external storage",Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this,"File could not be loaded from external storage",Toast.LENGTH_SHORT).show();
		}
	}
	
	public boolean isExternalStorageWritable()
	{
		//check if external storage is available at least for reading and writing 
		String state=Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isExternalStorageReadable()
	{//check if external storage is at least available to read
		String state=Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)||Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
		{
			return true;
		}
		return false;
	}
	
	public void onCreateFolder(String dirName)
	{
		if(isExternalStorageWritable())
		{	
			sdCard=Environment.getExternalStorageDirectory();
			directory=new File(sdCard.getAbsolutePath()+"/"+dirName);
		}
		else
		{
			Toast.makeText(this,"The SD Card does not exist or is not currently mounted",Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onPrepareForExternalStorageOperations(File directoryName,String fileName)
	{
		directory.mkdirs();
		if(directory.isDirectory())
		{
			Toast.makeText(this,"Directory was created",Toast.LENGTH_SHORT).show();
			
			try
			{
				file=new File(directoryName,fileName);
				writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				br=new BufferedReader(new InputStreamReader((new FileInputStream(file))));
				isReadyForAction=true;
			}
			catch(IOException iox)
			{
				iox.printStackTrace();
			}
		}	
	}
	
	public void onClickLoadFromRaw(View view)
	{
		String str=null;
		try
		{
			InputStream is=this.getResources().openRawResource(R.raw.myrawtext);
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			while((str=br.readLine())!=null)
			{
				et.setText(str);
			}
		}
		catch(IOException iox)
		{
			iox.printStackTrace();
		}
	}

}
