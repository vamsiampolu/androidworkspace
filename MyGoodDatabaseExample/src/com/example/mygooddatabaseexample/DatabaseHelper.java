package com.example.mygooddatabaseexample;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.provider.BaseColumns;
import android.content.ContentValues;
public class DatabaseHelper extends SQLiteOpenHelper 
{
	SQLiteDatabase db;
	public static final String DATABASE_NAME="MyContactsDB.db";
	public static final int DATABASE_VERSION=1;
	private static class TableColumns implements BaseColumns
	{
		public static final String TABLE_NAME="mycontacts";
		public static final String COLUMN_ID="sno";
		public static final String COLUMN_NAME="name";
		public static final String COLUMN_PHONE="phone";
		public static final String COLUMN_EMAIL="email";
		public static final String COLUMN_ORG="organisation";
		public static final String COLUMN_TITLE="title";
		
		private TableColumns(){};
	}
	
	public static final String CREATE_TABLE="Create table mycontacts (sno integer primary key autoincrement,name text not null,phone text,email text,organisation text,title text);";
	
	public DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS"+TableColumns.TABLE_NAME);
		onCreate(db);
	}
	
	public SQLiteDatabase open()
	{
		db=this.getWritableDatabase();
		return db;
	}
	
	public void close()
	{
		db.close();
	}
	//---Inserting a new record---:
	
	public void insertRecord(String name,String phone,String email,String org,String title)
	{
		ContentValues values=new ContentValues();
		values.put(TableColumns.COLUMN_NAME, name);
		values.put(TableColumns.COLUMN_PHONE, phone);
		values.put(TableColumns.COLUMN_EMAIL, email);
		values.put(TableColumns.COLUMN_ORG, org);
		values.put(TableColumns.COLUMN_TITLE, title);
		db.insert(TableColumns.TABLE_NAME, null, values);
	}
	
	public void insertRecord(String name,String phone,String email)
	{
		ContentValues values=new ContentValues();
		values.put(TableColumns.COLUMN_NAME, name);
		values.put(TableColumns.COLUMN_PHONE, phone);
		values.put(TableColumns.COLUMN_EMAIL, email);
		db.insert(TableColumns.TABLE_NAME, null, values);
	}
	
	//get a record:
	
	public Cursor getSingleRecord(long id)
	{
		Cursor c=db.query(TableColumns.TABLE_NAME,new String[] {TableColumns.COLUMN_NAME,TableColumns.COLUMN_PHONE,TableColumns.COLUMN_EMAIL}, TableColumns.COLUMN_ID+"="+id,null, null,null,null);
		if(c!=null)
		{
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getSingleRecordFull(long id)
	{
		Cursor c=db.query(TableColumns.TABLE_NAME, new String[] {TableColumns.COLUMN_ID,TableColumns.COLUMN_NAME,TableColumns.COLUMN_PHONE,TableColumns.COLUMN_EMAIL,TableColumns.COLUMN_ORG,TableColumns.COLUMN_TITLE}, TableColumns.COLUMN_ID+"="+id, null, null, null, null);
		if(c!=null)
		{
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getAllRecords()
	{
		Cursor c=db.query(TableColumns.TABLE_NAME, new String[] {TableColumns.COLUMN_NAME,TableColumns.COLUMN_PHONE,TableColumns.COLUMN_EMAIL}, null, null, null, null, null);
		if(c!=null)
		{
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getAllRecordsFull()
	{
		Cursor c=db.query(TableColumns.TABLE_NAME, new String[] {TableColumns.COLUMN_ID,TableColumns.COLUMN_NAME,TableColumns.COLUMN_PHONE,TableColumns.COLUMN_EMAIL,TableColumns.COLUMN_ORG,TableColumns.COLUMN_TITLE}, null, null, null, null, null);
		if(c!=null)
		{
			c.moveToFirst();
		}
		return c;
	}
	
	public boolean updateRecord(long id,String name,String phone,String email)
	{
		ContentValues values=new ContentValues();
		values.put(TableColumns.COLUMN_NAME, name);
		values.put(TableColumns.COLUMN_PHONE, phone);
		values.put(TableColumns.COLUMN_EMAIL, email);
		return db.update(TableColumns.TABLE_NAME, values, TableColumns.COLUMN_ID+"="+id, null)>0;
	}
	
	public boolean updateRecordFull(long id,String name,String phone,String email,String org,String title)
	{
		ContentValues values=new ContentValues();
		values.put(TableColumns.COLUMN_NAME, name);
		values.put(TableColumns.COLUMN_PHONE, phone);
		values.put(TableColumns.COLUMN_EMAIL, email);
		values.put(TableColumns.COLUMN_ORG, org);
		values.put(TableColumns.COLUMN_TITLE,title);
		return db.update(TableColumns.TABLE_NAME, values,TableColumns.COLUMN_ID+"=?", new String[]{""+id})>0;
	}
	
	public boolean deleteRecord(long id)
	{
		return db.delete(TableColumns.TABLE_NAME, TableColumns.COLUMN_ID+"="+id,null)>0;
	}
}
