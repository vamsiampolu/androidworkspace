package com.example.mydatabaseexample;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	DBAdapter dbAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbAdapter=new DBAdapter(this);
		/*dbAdapter.open();
		long id=dbAdapter.insertContact("Vamsi Deepak Ampolu", "vamsideepak03@gmail.com");
		id=dbAdapter.insertContact("Wolverine", "wolverine@xmen.com");
		dbAdapter.close();
		
		dbAdapter.open();
		Cursor c=dbAdapter.getAllContacts();
		if(c.moveToFirst())
		{
			do
			{
				Toast.makeText(this,"The name"+c.getString(1)+"and email:"+c.getString(2), Toast.LENGTH_LONG).show();
			}
			while(c.moveToNext());
		}
		dbAdapter.close();
		
		dbAdapter.open();
		c=dbAdapter.getContact(1);
		if(c.moveToFirst())
		{
			Toast.makeText(this,"The name"+c.getString(1)+"and email:"+c.getString(2), Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "No contact found", Toast.LENGTH_SHORT).show();
		}
		dbAdapter.close();
		*/
		dbAdapter.open();
		if(dbAdapter.updateContact(1, "Vamsi Deepak", "vamsideepak03@gmail.com"))
		{
			Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this, "Could not be updated", Toast.LENGTH_SHORT).show();
		}
		dbAdapter.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
