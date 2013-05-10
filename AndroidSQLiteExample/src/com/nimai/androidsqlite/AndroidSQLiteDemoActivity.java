package com.nimai.androidsqlite;

import java.util.List;

import com.nimai.androidsqlite.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class AndroidSQLiteDemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        DatabaseHandler db = new DatabaseHandler(this);
        
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Raju", "9199999999"));
        db.addContact(new Contact("Raghu", "9522222222"));
        db.addContact(new Contact("Ram", "9533333333"));
 
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        
        List<Contact> contacts = db.getAllContacts();       
 
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();

            // Writing Contacts to log
            Log.d("Name: ", log);
        
        }
    }
}