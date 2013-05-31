package com.example.mycontacts;

import android.database.Cursor;
import android.os.Bundle;
import android.app.ListActivity;
import android.provider.ContactsContract;
import android.view.Menu;
import android.net.Uri;
public class ContactsActivity extends ListActivity {

    String phone_number_home,phone_number_mobile;
    String email_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       Thread phoneRetriever=new Thread()
       {
           public void run()
           {
               Uri mContactsUri= ContactsContract.Contacts.CONTENT_URI;

               String[] projections={ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,ContactsContract.Contacts.HAS_PHONE_NUMBER};
               Cursor c=getContentResolver().query(mContactsUri,projections,null,null,null);
               while(c.moveToNext())
               {
                   String _ID=c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                   String name=c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                   Uri photoThumbnailUri=Uri.parse(c.getString(c.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)));
                   int hasPhoneNumber=c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                   if(hasPhoneNumber>0)
                   {
                       Uri phoneUri=ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                       Uri emailUri=ContactsContract.CommonDataKinds.Email.CONTENT_URI;
                       String pSelection=ContactsContract.CommonDataKinds.Phone._ID+" = ?";
                       String eSelection=ContactsContract.CommonDataKinds.Email._ID+"= ?";
                       String[] pSelectionArgs=new String[]{_ID};

                       Cursor pCur=getContentResolver().query(phoneUri,null,pSelection,pSelectionArgs,null);
                       while(pCur.moveToNext())
                       {
                           int phone_type=pCur.getInt(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                           switch(phone_type)
                           {
                               case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                   phone_number_mobile=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                   break;
                               case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                   phone_number_home=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                   break;
                           }
                       }
                       Cursor eCur=getContentResolver().query(emailUri,null,eSelection,pSelectionArgs,null);
                       while(eCur.moveToNext())
                       {
                            email_home=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                       }
                   }
               }
           }

           public Uri getThumbNailFromId(String id)
           {
               Cursor c=getContentResolver().query()
           }

       };

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts, menu);
        return true;
    }


    
}
