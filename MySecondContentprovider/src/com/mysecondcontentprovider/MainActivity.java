package com.mysecondcontentprovider;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.database.SQLException;
public class MainActivity extends Activity {
	
	//EditText values
	EditText edit_title;
	EditText edit_author;
	
	EditText edit_genre,edit_price;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit_title=(EditText)findViewById(R.id.edit_title);
		edit_author=(EditText)findViewById(R.id.edit_author);
		edit_genre=(EditText)findViewById(R.id.edit_genre);
		edit_price=(EditText)findViewById(R.id.edit_price);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		switch(item.getItemId())
		{
		case R.id.btn_list:
			startActivity(new Intent("com.mysecondcontentprovider.BookListActivity"));
		}
		return false;
	}
	
	public void onClickClear(View v)
	{
		edit_title.setText("");
		edit_author.setText("");
		edit_genre.setText("");
		edit_price.setText("");
	}
	
	
	
	public void onClickSave(View v)
	{
		String title=edit_title.getText().toString();
		String author=edit_author.getText().toString();
		String genre=edit_genre.getText().toString();
		String price=edit_price.getText().toString();
		
		ContentValues values=new ContentValues();
		values.put(BookProvider.TITLE, title);
		values.put(BookProvider.AUTHOR, author);
		values.put(BookProvider.GENRE,genre);
		values.put(BookProvider.PRICE, price);
		try
		{
			Uri rowUri=getContentResolver().insert(BookProvider.CONTENT_URI, values);
		}
		catch(SQLException ex)
		{
			Log.e("Book Database","SQLException occured when inserting into database");
			Toast.makeText(this, "The book could not be placed on to the shelf", Toast.LENGTH_SHORT).show();
		}
	}

}
