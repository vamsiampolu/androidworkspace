package com.example.mycreatedcontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.UriMatcher;
import java.util.HashMap;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.database.Cursor;
import android.util.Log;
public class NotesContentProvider extends ContentProvider 
{
	private static final String TAG="NotesContentProvider";
	private static final String DATABASE_NAME="notes.db";//always declare database name as "mydatabase.db"
	private static final int DATABASE_VERSION=1;
	private static final String TABLE_NAME="notes";
	private static final String AUTHORITY="com.example.mycreatedcontentprovider.notes";
	private static final UriMatcher sUriMatcher;
	private static final int NOTES=1;
	private static final int NOTES_ID=2;
	private static HashMap<String,String> notesProjectionMap;
	Cursor c;
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context,DATABASE_NAME,null,DATABASE_VERSION);
		}
		
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + Notes.NOTE_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT," + Notes.TITLE + " VARCHAR(255)," + Notes.TEXT + " LONGTEXT" + ");");
		}
		
		public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
		{
			Log.w("NotesContentProvider","Erasing all date from table when upgrading from "+oldVersion+"to "+newVersion);
		}
		
	}
	
	private DatabaseHelper dbHelper;
	
	public int delete(Uri uri,String where,String[] whereArgs)
	{
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		switch(sUriMatcher.match(uri))
		{
		case NOTES:
			break;
		case NOTES_ID:
			where=where+" _id= "+uri.getLastPathSegment();
			break;
			default:
				throw new IllegalArgumentException("Unknown uri"+uri);
		}
		int count=db.delete(TABLE_NAME,where,whereArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	public String getType(Uri uri)
	{
		switch(sUriMatcher.match(uri))
		{
		case NOTES:
			return Notes.CONTENT_TYPE;
			default:throw new IllegalArgumentException("Unknown Uri "+uri);
		}			
	}
	
	public Uri insert(Uri uri,ContentValues initialValues)
	{
		if(sUriMatcher.match(uri)!=NOTES)
		{
			throw new IllegalArgumentException("Unknown uri "+uri);
		}
			ContentValues values;
			if(initialValues!=null)
			{
				values=new ContentValues(initialValues);
			}
			else
			{
				values=new ContentValues();
			}
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			long rowId=db.insert(TABLE_NAME, Notes.TEXT, values);
			if(rowId>0)
			{
				Uri noteUri=ContentUris.withAppendedId(Notes.CONTENT_URI, rowId);
				getContext().getContentResolver().notifyChange(noteUri, null);
				return noteUri;
			}
			throw new SQLException("Failed to inserted row into "+uri);
	}
	
	public boolean onCreate()
	{
		dbHelper=new DatabaseHelper(getContext());
		return true;
	}
	
	public Cursor query(Uri uri,String[] projection,String selection,String[] selectionArgs,String sortOrder)
	{
		SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
		qb.setTables(TABLE_NAME);
		qb.setProjectionMap(notesProjectionMap);
		switch(sUriMatcher.match(uri))
		{
		case NOTES:
			break;
		case NOTES_ID:
			selection=selection+"_id = "+uri.getLastPathSegment();
			break;
			default:
				throw new IllegalArgumentException("Unknow uri"+uri);
		}
		SQLiteDatabase db=dbHelper.getReadableDatabase();
		c=qb.query(db, projection, selection, selectionArgs,null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}
	
	public int update(Uri uri,ContentValues values,String where,String[] whereArgs)
	{
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		int count=-1;
		switch(sUriMatcher.match(uri))
		{
			case NOTES:
				db.update(TABLE_NAME, values, where, whereArgs);
				break;
			default:
				throw new IllegalArgumentException("Unknow uri"+uri);
					
		}
		getContext().getContentResolver().notifyChange(uri,null);
		return count;
	}
	
	static
	{
		sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AUTHORITY, TABLE_NAME, NOTES);
		sUriMatcher.addURI(AUTHORITY, TABLE_NAME+"/#", NOTES_ID);
		notesProjectionMap=new HashMap<String,String>();
		notesProjectionMap.put(Notes.NOTE_ID, Notes.NOTE_ID);
		notesProjectionMap.put(Notes.TITLE,Notes.TITLE);
		notesProjectionMap.put(Notes.TEXT, Notes.TEXT);
	}
	
		
		public static final class Notes implements BaseColumns
		{
			private Notes(){}
			public static final Uri CONTENT_URI=Uri.parse("content://"+ NotesContentProvider.AUTHORITY + "/notes");
			public static final String CONTENT_TYPE="vnd.android.curosr.dir/vnd.examplenotes.notes";
			public static final String NOTE_ID="_id";
			public static final String TITLE="title";
			public static final String TEXT="text";
		}
	

	
	
}
