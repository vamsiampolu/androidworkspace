package com.example.mycreatedcontentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
public class ThirdActivity extends Activity 
{
	
	Cursor c;
	int id;
	public void onCreate(Bundle savedInstanceState)
	{
		//instantiate your views after setContentView because the activity would otherwise not know what to instantiate
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdactivity);
		id=getIntent().getIntExtra("id", 1);
		final String[] projections={NotesContentProvider.Notes.NOTE_ID,NotesContentProvider.Notes.TITLE,NotesContentProvider.Notes.TEXT};
		final String selection=NotesContentProvider.Notes.NOTE_ID+"= ?";
		final String[] selectionArgs={""+id};
		EditText edit_title=(EditText)findViewById(R.id.edit_getTitle);
		EditText edit_text=(EditText)findViewById(R.id.edit_getNote);
		c=getContentResolver().query(NotesContentProvider.Notes.CONTENT_URI, projections, selection, selectionArgs, null);
		if(c!=null)
		{
			c.moveToFirst();
			String title=c.getString(c.getColumnIndex(NotesContentProvider.Notes.TITLE));
			String text=c.getString(c.getColumnIndex(NotesContentProvider.Notes.TEXT));
			edit_title.setText(title);
			edit_text.setText(text);
		}
	}
	
	public void onClickUpdate(View v)
	{
		int rowId;
		EditText edit_title=(EditText)findViewById(R.id.edit_getTitle);
		EditText edit_text=(EditText)findViewById(R.id.edit_getNote);
		try
		{
			ContentValues values=new ContentValues();
			values.put(NotesContentProvider.Notes.TITLE, edit_title.getText().toString());
			values.put(NotesContentProvider.Notes.TEXT, edit_text.getText().toString());
			rowId=getContentResolver().update(NotesContentProvider.Notes.CONTENT_URI, values, NotesContentProvider.Notes.NOTE_ID+" = ?", new String[]{""+id});
			
		}
		catch(IllegalArgumentException iex)
		{
			Log.e("Content provider update","Illegal URI");
		}
		
	}
	
}
