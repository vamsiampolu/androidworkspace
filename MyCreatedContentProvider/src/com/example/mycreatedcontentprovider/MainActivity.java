package com.example.mycreatedcontentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.Cursor;
import android.util.Log;
public class MainActivity extends Activity {

	Cursor c;
	EditText edit_title,edit_notes;
	//Do not use too many cursors...each cursor takes up 1 MB of memory,
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit_title=(EditText)findViewById(R.id.edit_title);
		edit_notes=(EditText)findViewById(R.id.edit_notes);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickClear(View v)
	{
		edit_title.setText("");
		edit_notes.setText("");
	}
	
	public void onClickSave(View v)
	{
		String title=edit_title.getText().toString();
		String text=edit_notes.getText().toString();
		ContentValues values=new ContentValues();
		values.put(NotesContentProvider.Notes.TITLE, title);
		values.put(NotesContentProvider.Notes.TEXT, text);
		try
		{
			Uri rowId=getContentResolver().insert(NotesContentProvider.Notes.CONTENT_URI, values);
		}
		catch(SQLException ex)
		{
			Log.e("Insert into Content Provider","There was an SQLException");
			Toast.makeText(this, "Could'nt do it,an SQLException ensued", Toast.LENGTH_SHORT).show();
		}
	}
	

	public void onClickGet(View v)
	{
		startActivity(new Intent("com.example.mycreatedcontentprovider.NotesListActivity"));
	}
	
	

}
