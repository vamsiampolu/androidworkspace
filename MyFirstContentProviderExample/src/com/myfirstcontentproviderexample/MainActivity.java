package com.myfirstcontentproviderexample;

import android.app.ListActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.net.Uri;
import android.database.Cursor;
import android.content.CursorLoader;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.util.Log;
public class MainActivity extends ListActivity
{
	
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Uri contacts=ContactsContract.Contacts.CONTENT_URI;
		Cursor c;
		SimpleCursorAdapter simpleCursorAdapter;
		String[] columns=new String[] {ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.HAS_PHONE_NUMBER};
		int[] values=new int[] {R.id.contactId,R.id.contactName};
		String[] listColumns= new String[] {ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME};
		if(android.os.Build.VERSION.SDK_INT<11)
		{
			c=managedQuery(contacts, columns, ContactsContract.Contacts.DISPLAY_NAME+ " LIKE ?",new String[] {"%d%"},null);
			simpleCursorAdapter=new SimpleCursorAdapter(this,R.layout.activity_main,c,listColumns,values);
		}
		else
		{
			CursorLoader loader=new CursorLoader(this,contacts,columns,ContactsContract.Contacts.DISPLAY_NAME+" LIKE ?",new String[]{"%j%"},ContactsContract.Contacts.DISPLAY_NAME+" ASC");
			c=loader.loadInBackground();
			simpleCursorAdapter =new SimpleCursorAdapter(this,R.layout.activity_main,c,listColumns,values,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		}
		this.setListAdapter(simpleCursorAdapter);
		printContacts(c);
	}
	
	public void printContacts(Cursor c)
	{
		if(c.moveToFirst())
		{
			do
			{
				String name=c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				String id=c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
				int hasPhone=c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
				if(hasPhone==1)
				{
					Cursor phoneCursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+id, null, null);
					if(phoneCursor.moveToFirst())
					{
						do
						{
							String number=phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
							Log.v("MyContaCts","The name is "+name+" and the number is "+number);
						}while(phoneCursor.moveToNext());
					}
				}
			}while(c.moveToNext());
		}
	}
}