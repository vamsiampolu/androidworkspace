package com.example.mycreatedcontentprovider;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NotesListActivity extends ListActivity 
{
	Uri uri=NotesContentProvider.Notes.CONTENT_URI;
	Cursor c;
	int id;
	SimpleCursorAdapter simpleCursorAdapter;
	String[] listColumns={NotesContentProvider.Notes.TITLE,NotesContentProvider.Notes.TEXT};
	int[] values={R.id.txt_title,R.id.txt_note};
	
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noteslist_activity);
		//String[] projection={NotesContentProvider.Notes.NOTE_ID,NotesContentProvider.Notes.TITLE,NotesContentProvider.Notes.TEXT};
		c=managedQuery(uri,null,null,null,null);
		simpleCursorAdapter=new SimpleCursorAdapter(this, R.layout.noteslist_activity, c, listColumns, values);
		this.setListAdapter(simpleCursorAdapter);
		getListView().setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> arg0,View arg1,int position,long arg2)
			{
				Intent intent= new Intent("com.example.mycreatedcontentprovider.ThirdActivity");
				final SimpleCursorAdapter sca=(SimpleCursorAdapter)arg0.getAdapter();
				c=sca.getCursor();
				id=c.getInt(c.getColumnIndex(NotesContentProvider.Notes.NOTE_ID));
				intent.putExtra("id", id);
				startActivity(intent);
				
			}
		}
				);
		//c.close();
		getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				registerForContextMenu(arg1);
				openContextMenu(arg1);
				return false;
			}
		
		});
		
	}	
	
	public void onCreateContextMenu(ContextMenu menu,ViewGroup v,ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu,v,menuInfo);
		getLayoutInflater().inflate(R.menu.listitemcontextmenu, v, false);
		
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 super.onContextItemSelected(item);
		 int a;
		 switch(item.getItemId())
		 {
		 	case R.id.deleteItem:
		 		String where=NotesContentProvider.Notes.NOTE_ID+" = ? ";
		 		String[] selectionArgs={""+id};
		 		a= getContentResolver().delete(NotesContentProvider.Notes.CONTENT_URI, where, selectionArgs);
		 		SimpleCursorAdapter simpleCursorAdapter=(SimpleCursorAdapter)getListView().getAdapter();
		 		simpleCursorAdapter.notifyDataSetChanged();
		 		if(a>0)
		 		{
		 			Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show();
		 		}
		 		else
		 		{
		 			Log.e("Deleting from database using listview and content provider failed","Could not delete note");
		 			Toast.makeText(this, "Could not delete note", Toast.LENGTH_SHORT).show();
		 		}
		 		//onRestart();
		 		return true;
		 }
		 return false;
	}
	
	
}
