package com.mysecondcontentprovider;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
public class BookListActivity extends ListActivity 
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booklist);
		@SuppressWarnings("deprecation")
		Cursor c=managedQuery(BookProvider.CONTENT_URI,null,null,null,null);
		String[] columns=new String[]{BookProvider.TITLE,BookProvider.AUTHOR,BookProvider.PRICE};
		int[] views=new int[]{R.id.book_title,R.id.book_author,R.id.book_price};
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter simpleCurosrAdapter=new SimpleCursorAdapter(this, R.layout.activity_booklist, c, columns, views);
		this.setListAdapter(simpleCurosrAdapter);
	}
}
