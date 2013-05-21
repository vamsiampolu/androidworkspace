package com.yambaproject;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.os.Build;
import winterwell.jtwitter.Twitter.Status;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StatusData 
{
	static final String TAG="Yamba";
	static final String C_ID="_id";
	static final String C_CREATED_AT="created_at";
	static final String C_TEXT="status_text";
	static final String C_USER="user_name";
	static final String DB_NAME="timeline.db";
	static final String TABLE_NAME="status";
	static final int DB_VERSION=1;
	SQLiteDatabase db;
	Context context;
	StatusHelper helper;
	public StatusData(Context context)
	{
		this.context=context;
		helper=new StatusHelper();
	}
	
	class StatusHelper extends SQLiteOpenHelper
	{

		public StatusHelper()
		{
			super(context, DB_NAME, null, DB_VERSION);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			// TODO Auto-generated method stub
			String create_table=String.format("create table %s "+"(%s int primary key, %s int, %s text, %s text)", TABLE_NAME,C_ID,C_CREATED_AT,C_USER,C_TEXT);
			Log.d(TAG,"Creating a table in the database  "+create_table);
			db.execSQL(create_table);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			// TODO Auto-generated method stub
			Log.d(TAG,"Removing the older version of the database "+oldVersion+"and creating a new version "+ newVersion);
			db.execSQL("drop table if exists "+TABLE_NAME);
			onCreate(db);
			//The oldVersion and newVersion must not be the same,they can be anything as long as newVersion>oldVersion...i.e nonSequential...
		}
		
	}
	
	@TargetApi(Build.VERSION_CODES.FROYO)
    public void insert(Status status)
	{
		db=helper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(C_ID, status.id);
		values.put(C_CREATED_AT, status.createdAt.getTime());
		values.put(C_USER, status.user.name);
		values.put(C_TEXT, status.text);
		//SQL Injection might occur when you try and parse user input directly into an sql statement and the user input has SQL statements
		db.insertWithOnConflict(TABLE_NAME, null, values,SQLiteDatabase.CONFLICT_IGNORE);
        //The method returns -1 if no row could not be inserted
		//you can use db.insertOrThrow which will throw an exception when stuff goes wrong...it does not fit the scenario...
	}
	
	public Cursor query()
	{
		
		db=helper.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, C_CREATED_AT +" DESC");//you can limit the number of values here: "DESC LIMIT 5"
		cursor.moveToFirst();
		return cursor;
	}
	
	
	
	
}
