package com.mydictcontentproviderexample;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;
import android.database.Cursor;
import android.net.Uri;
public class MainActivity extends Activity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Cursor mCursor;
		String[] projection={ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.HAS_PHONE_NUMBER};
		String selection=null;
		String[] selectionArgs={""};
		String sortOrder=null;
		Uri mContacts=ContactsContract.Contacts.CONTENT_URI;
		mCursor=getContentResolver().query(mContacts, projection, selection, selectionArgs, sortOrder);
		startManagingCursor(mCursor);
		/*
		 * 
		 //If a cursor has internal errors...it returns null
		if(mCursor==null)
		{	//Log.e(tag,message);
			Toast.makeText(this,"The contacts could not be retrieved because of an error", Toast.LENGTH_SHORT).show();
		}
		else if(mCursor.getCount()<1)
		{//if an empty cursor is returned...no results were found
			Toast.makeText(this, "No results were found", Toast.LENGTH_SHORT).show();
		}
		else
		{//the good use c.get...
			while(mCursor.moveToNext())
			{
				//retrieve the values
				 
				 String id=mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts._ID));
				 String name=mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				 int hasPhone=mCursor.getInt(mCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
				 Cursor pCursor;
				 if(hasPhone==1)
				 {
				 	pCursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{id},null);
				 	if(pCursor==null)
				 	{
				 		//Log.e(tag,message);
				 	}
				 	else
				 	{	do{
				 		String num=pCursor.getString(pCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				 		//do something here
				 		}while(pCursor.moveToNext());
				 	}
				 }
			}
		}
		//*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
