package com.example.spidermanexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDatabase extends SQLiteOpenHelper {

	public static final String DATABASE_NAME="myusers.db";
	public static final int DATABASE_VERSION=1;
	public static final String TABLE_NAME="users";
	public static final String COL_NAME="name";
	public static final String COL_EMAIL="email";
	public static final String COL_USERNAME="username";
	public static final String COL_PASSWORD="password";
	public static final String CREATE_TABLE="Create Table users (userid integer primary key autoincrement, name text not null, email text not null, username text not null, password text not null);";
	SQLiteDatabase db;
	public UserDatabase(Context context)
	{
		super(context,DATABASE_NAME,null,DATABASE_VERSION );
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
			Log.d("Spiderman","Upgrading the user database from "+oldVersion+" to "+newVersion);
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
			onCreate(db);
	}
	
	public SQLiteDatabase openDatabase()
	{
		db=this.getWritableDatabase();
		return db;
	}
	
	public boolean insert(String tableName,String name,String email,String username,String password)
	{
		ContentValues values=new ContentValues();
		values.put(COL_NAME, name);
		values.put(COL_EMAIL, email);
		values.put(COL_USERNAME, username);
		values.put(COL_PASSWORD, password);
		long rowId=db.insert(TABLE_NAME, null, values);
		if(rowId!=-1)
		{
			Log.d("Spiderman","Data successfully inserted into the table");
			return true;
		}
		else
		{
			Log.d("Spiderman","Data could not be inserted ");
			return false;
		}
	}
	
	public boolean update(String tableName,String name,String email,String username,String password)
	{
		ContentValues values=new ContentValues();
		values.put(COL_NAME, name);
		values.put(COL_EMAIL,email);
		values.put(COL_USERNAME, username);
		values.put(COL_PASSWORD, password);
		String where=COL_USERNAME+" = ? ";
		String[] whereArgs=new String[] {username};
		int rows=db.update(TABLE_NAME, values, where, whereArgs);
		if(rows>0)
		{
			Log.d("Spiderman","Table updated successfully");
			return true;
		}
		else
		{
			Log.d("Spiderman","Table could not be updated");
			return false;
		}
		
	}
	
	public Cursor query(String tableName,String userName)
	{
		String selection=COL_USERNAME+" = ? ";
		String[] columns={COL_PASSWORD};
		String[] selectionArgs={userName};
		Cursor c=db.query(TABLE_NAME, columns, selection, selectionArgs,null , null, null);
		return c;
	}
	
	public boolean delete(String tableName,String username)
	{
		String selection=COL_USERNAME+" = ? "; 
		String[] selectionArgs={username};
		int rows=db.delete(TABLE_NAME, selection, selectionArgs);
		if(rows>0)
		{
			Log.d("Spiderman","User deleted successfully");
			return true;
		}
		else
		{
			Log.d("Spiderman","Error,user not be deleted");
			return false;
		}
		
	}
	
	

}
