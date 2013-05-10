package com.menuexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;
import android.widget.Button;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.view.View;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button=(Button)findViewById(R.id.button01);
		button.setOnCreateContextMenuListener(this);
		WebView wv=(WebView)findViewById(R.id.webview);
		WebSettings ws=wv.getSettings();
		ws.setBuiltInZoomControls(true);
		wv.loadUrl("https://developers.google.com/chart/");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		createMenu(menu);
		return true;
	}
	
	public void onCreateContextMenu(ContextMenu menu,View view,ContextMenuInfo info)
	{
		super.onCreateContextMenu(menu, view, info);
		createMenu(menu);
	}
	
	
	public void createMenu(Menu menu)
	{
		menu.setQwertyMode(true);
		MenuItem mi=menu.add(0,0,0,"Item1");
		{
			mi.setAlphabeticShortcut('a');
			mi.setIcon(R.drawable.ic_launcher);
		}
		MenuItem mi1=menu.add(1,1,1,"Item2");
		{
			mi1.setAlphabeticShortcut('b');
			mi1.setIcon(R.drawable.ic_launcher);
		}
		MenuItem mi2=menu.add(2,2,2,"Item2");
		{
			mi2.setAlphabeticShortcut('c');
			mi2.setIcon(R.drawable.ic_launcher);
		}
	}	
		public boolean onOptionsItemSelected(MenuItem item)
		{
			return menuChoice(item);
		}
		
		public boolean menuChoice(MenuItem item)
		{
			
			switch(item.getItemId())
			{
				case 0:
					Toast.makeText(getBaseContext(), "You clicked on item 1",Toast.LENGTH_LONG).show();
					return true;
				case 1:
					Toast.makeText(getBaseContext(), "You clicked on item 2",Toast.LENGTH_LONG).show();
					return true;
				case 2:
					Toast.makeText(getBaseContext(), "You clicked on item 2",Toast.LENGTH_LONG).show();
					return true;
			}
			return false;
		}
	}


