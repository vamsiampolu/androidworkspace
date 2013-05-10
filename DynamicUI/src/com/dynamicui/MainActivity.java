package com.dynamicui;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		@SuppressWarnings("deprecation")
		//set params for views
		LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		
		//create views
		//Cannot refer to a non-final variable tv inside an inner class defined in a different method
		final TextView tv=new TextView(this);
		tv.setText("This is a text view");
		tv.setLayoutParams(params);
		
		Button button=new Button(this);
		button.setText("This is a button");
		button.setLayoutParams(params);
		button.setOnClickListener(new View.OnClickListener()
				{
					public void onClick(View view)
					{
						if(tv.getVisibility()==View.VISIBLE)
						{
						tv.setVisibility(View.INVISIBLE);
						}
						else
						{
							tv.setVisibility(View.VISIBLE);
						}
					}
				}
				);
		
		//Create params for layout:
		@SuppressWarnings("deprecation")
		LayoutParams layoutParams=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		
		//Create a Layout:
		LinearLayout layout=new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		//layout.setLayoutParams(layoutParams);
		
		layout.addView(tv);
		layout.addView(button);
		this.addContentView(layout, layoutParams);
		//setContentView(R.layout.activity_main);
		
		ActionBar actionBar=getActionBar();
		actionBar.show();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuItem mu1 = menu.add(0,1,1,"Item1");
		{
			mu1.setIcon(R.drawable.ic_launcher);
			mu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT|MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}
		
		MenuItem mu2=menu.add(0,2,2,"Item2");
		{
			mu2.setIcon(R.drawable.ic_launcher);
			mu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM|MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case android.R.id.home:
			Toast.makeText(this,"You clicked on the home icon",Toast.LENGTH_LONG).show();
			return true;
		case 1:
			Toast.makeText(this,"You clicked Item1", Toast.LENGTH_LONG).show();
			return true;
		case 2:
			Toast.makeText(this,"You clicked Item2",Toast.LENGTH_LONG).show();
			return true;
		
		}
		return false;
	}

}
