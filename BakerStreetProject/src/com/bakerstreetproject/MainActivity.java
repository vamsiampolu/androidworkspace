package com.bakerstreetproject;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		switch(item.getItemId())
		{
			case R.id.mone:
				Toast.makeText(this,"Meet Jim Moriarty,world renown professor of mathemetics revered as a genius\n A criminal mastermind,the world's only consulting criminal",Toast.LENGTH_SHORT).show();
				return true;
			case R.id.mtwo:
				Toast.makeText(this,"A sophisticated and charming lady who is actually a burgler,the only woman who has managed to steal Sherlock's heart",Toast.LENGTH_SHORT).show();
				return true;
			case R.id.mthree:
				Toast.makeText(this,"A highly intelligent man a high-ranking government official...a man who bests Sherlock in intelligence,his brother", Toast.LENGTH_SHORT).show();
				return true;
		}
		return false;
	}

}
