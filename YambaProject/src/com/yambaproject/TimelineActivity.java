package com.yambaproject;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
public class TimelineActivity extends Activity 
{
	 public void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.timeline_activity);

		 Cursor c=((YambaApplication)getApplication()).data.query();
         ListView list_timeline=(ListView)findViewById(R.id.list);
         SimpleCursorAdapter sca;
         sca = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,c,new  String[] {StatusData.C_USER,StatusData.C_TEXT},new int[] {android.R.id.text1,android.R.id.text2});
        list_timeline.setAdapter(sca);
     }

    //You cannot add the same library twice,it causes a dexing error when compiling...it is called IllegalArgumentException
//There are two types of adapters one based on array data(fixed size) another based on cursor data(dynamic)...
//ListView reuses UI components 	 
	 

}
