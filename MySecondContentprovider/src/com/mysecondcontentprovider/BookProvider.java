package com.mysecondcontentprovider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;
import android.net.Uri;
public class BookProvider extends ContentProvider
{
	static UriMatcher sUriMatcher;
	DatabaseHelper helper;
	public static final String DATABASE_NAME="books.db";
	public static final int DATABASE_VERSION=1;
	public static final String CREATE_TABLE="create table books (_id INTEGER PRIMARY KEY autoincrement, title text not null, author text not null, genre text not null, price Integer not null);";
	public static final String AUTHORITY="com.mysecondcontentprovider.BookProvider";
	public static final Uri CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/books");
	public static final String TABLE_NAME="books";
	public static final String _ID="_id";
	public static final String TITLE="title";
	public static final String AUTHOR="author";
	public static final String GENRE="genre";
	public static final String PRICE="price";
	public static final int BOOKS=1;
	private static final int BOOK_ID=2;
	private static HashMap<String,String> projectionMap;
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		public DatabaseHelper(Context context)
		{
			super(context,DATABASE_NAME,null,DATABASE_VERSION);
		}
		
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(CREATE_TABLE);
		}
		
		public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
		{
			Log.w("BookProvider","The database shall be upgraded from"+oldVersion+" to "+newVersion);
			onCreate(db);
		}
	}
	
	public boolean onCreate()
	{
		helper=new DatabaseHelper(getContext());
		return true;
	}
	
	public int delete(Uri uri,String selection,String[] selectionArgs)
	{
		switch(sUriMatcher.match(uri))
		{
		case BOOKS:
			break;
		case BOOK_ID:
			selection=_ID+" = ? ";
			selectionArgs=new String[]{uri.getLastPathSegment()};
			break;
		default:
			throw new IllegalArgumentException("Some unknown Uri"+uri);
		}
		SQLiteDatabase db=helper.getWritableDatabase();
		int count=db.delete(TABLE_NAME, selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	public Uri insert(Uri uri,ContentValues initialValues)
	{
		ContentValues values=null;
		switch(sUriMatcher.match(uri))
		{
			
			case BOOKS:
				if(initialValues!=null)
				{
					values=new ContentValues(initialValues);
				}
				else
				{
					values=new ContentValues();
				}
				break;
			default: throw new IllegalArgumentException("An unknown uri"+uri);	
		}
		SQLiteDatabase db=helper.getWritableDatabase();
		long rowId=db.insert(TABLE_NAME, null, values);
		if(rowId>0)
		{
			Uri ret_uri=ContentUris.withAppendedId(CONTENT_URI, rowId);
			return ret_uri;
		}
		throw new SQLException("Error inserting data");
	}
	
	public int update(Uri uri,ContentValues values,String selection,String[] selectionArgs)
	{
		int count;
		SQLiteDatabase db;
		switch(sUriMatcher.match(uri))
		{
		case BOOKS:
			db=helper.getWritableDatabase();
			count=db.update(TABLE_NAME, values, selection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown uri"+uri);
		}
		return count;
	}
	
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) 
	{
		SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
		qb.setTables(TABLE_NAME);
		qb.setProjectionMap(projectionMap);
		switch(sUriMatcher.match(uri))
		{
		case BOOKS:
			break;
		case BOOK_ID:
			selection=_ID+"= ? ";
			selectionArgs=new String[]{uri.getLastPathSegment()};
		default:
			throw new IllegalArgumentException("Unknown uri"+uri);
		}
		SQLiteDatabase db=helper.getReadableDatabase();
		Cursor c=db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}
	
	public String getType(Uri uri)
	{
		switch(sUriMatcher.match(uri))
		{
			case BOOKS:
				return "vnd.android.cursor.dir/com.mysecondcontentprovider.books";
			default:
				throw new IllegalArgumentException("Unknown uri"+uri);
		}
	}


	static
	{
		sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AUTHORITY, TABLE_NAME, BOOKS);
		sUriMatcher.addURI(AUTHORITY, TABLE_NAME+"/#", BOOK_ID);
		projectionMap=new HashMap<String,String>();
		projectionMap.put(TITLE, TITLE);
		projectionMap.put(AUTHOR, AUTHOR);
		projectionMap.put(GENRE, GENRE);
		projectionMap.put(PRICE, PRICE);
		
	}
}