package com.example.mydatabaseexample;

import android.provider.BaseColumns;

public abstract class TableColumns implements BaseColumns 
{
	public static final String TABLE_NAME="Contacts";
	public static final String COLUMN_ID="contactid";
	public static final String COLUMN_NAME="name";
	public static final String COLUMN_EMAIL="email";
	
	private TableColumns(){	}
}
