package com.yambaproject;


import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.view.View;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.content.IntentFilter;
public class TimelineActivity extends ListActivity
{
    SimpleCursorAdapter sca;
    TimelineReceiver receiver;
    Cursor c;
    public static final ViewBinder VIEW_BINDER=new ViewBinder()
    {
        public boolean setViewValue(View v,Cursor c,int columnIndex)
        {
            //A ViewBinder is called every for every column the adapter retrieves so that ANY CHANGES can be made to
            Log.d("Yamba","ViewBinder called");
            //gets called for every single column to be bound...check if the view is the view that we care about...
            //or if the column index matches the index we care about...
            if(v.getId()!=R.id.txt_time)
            {
                return false;
            }
            //to use column index if(c.getColumnIndex(StatusData.CREATED_AT)!=columnIndex){return false;}
            long time=c.getLong(c.getColumnIndex(StatusData.C_CREATED_AT));
            CharSequence rel_time= DateUtils.getRelativeTimeSpanString(time);
            ((TextView)v).setText(rel_time);
            return true;
        }
    };
	 public void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		// setContentView(R.layout.timeline_activity);


         //ListView list_timeline=(ListView)findViewById(R.id.list);


         sca = new SimpleCursorAdapter(this,R.layout.list_item,c,new  String[] {StatusData.C_USER,StatusData.C_CREATED_AT,StatusData.C_TEXT},new int[] {R.id.txt_user,R.id.txt_time,R.id.txt_tweet});
         sca.setViewBinder(VIEW_BINDER);

        setTitle(R.string.timeline);
         setListAdapter(sca);
     }

    public void onResume()
    {
        super.onResume();
        if(receiver==null)
        {
            receiver=new TimelineReceiver();
        }
       registerReceiver(receiver,new IntentFilter(YambaApplication.ACTION_NEW_STATUS));
    }

    public void onPause()
    {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Only called once,when the user clicks the menu button for the first
        // time
        getMenuInflater().inflate(R.menu.servicemenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent updaterIntent = new Intent(this,
                com.yambaproject.UpdaterService.class);
        Intent refreshIntent = new Intent(this,
                com.yambaproject.RefreshService.class);
        switch (item.getItemId()) {
            case R.id.startService:
                startService(updaterIntent);
                Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.stop_service:
                stopService(updaterIntent);
                Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.refresh_service:
                startService(refreshIntent);
                return true;
            case R.id.show_preferences:
                startActivity(new Intent(getApplicationContext(),
                        PrefsActivity.class));
                return true;
            case R.id.m_timeline:
                startActivity(new Intent(this,MainActivity.class));
                return true;
        }
        return false;
    }

    class TimelineReceiver extends BroadcastReceiver
    {

        //This broadcast receiver updates the data in the cursor from the database and sets this updated cursor
        //as the adapter's cursor.

        //The activity registers with the broadcast receiver in onResume and unregisters in onPause...

        public void onReceive(Context context,Intent intent)
        {
            Cursor c=((YambaApplication)getApplication()).data.query();
            startManagingCursor(c);
            sca.changeCursor(c);
           int count= intent.getIntExtra("count",0);
            if(count>0)
            {
            Log.d("Yamba","TimelineReceiver has found "+count+" new tweets for you");
            }
                Log.d("Yamba","TimelineReceiver Changing Cursor");
        }
    }


    //You cannot add the same library twice,it causes a dexing error when compiling...it is called IllegalArgumentException
//There are two types of adapters one based on array data(fixed size) another based on cursor data(dynamic)...
//ListView reuses UI components 	 
	 

}
