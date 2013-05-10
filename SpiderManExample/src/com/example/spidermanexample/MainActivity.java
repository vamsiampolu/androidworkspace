package com.example.spidermanexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockDialogFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;



public class MainActivity extends SherlockFragmentActivity implements AboutUsFragment.OnAboutUsFragmentListener {

	AboutUsFragment auf;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar=getSupportActionBar();
		actionBar.setTitle("Baker Street 21");
		auf=AboutUsFragment.newInstance("About Us");
		 auf.setListener(this);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// TODO Auto-generated method stub
		 super.onOptionsItemSelected(item);
		 switch(item.getItemId())
		 {
		 	case R.id.homeMenu:
		 		startActivity(new Intent(this,MainActivity.class));
		 		return true;
		 	case R.id.comeIn:
		 		startActivity(new Intent("com.spidermanexample.RegisterActivity"));
		 		return true;
		 	case R.id.posters:
		 		//Toast.makeText(this,"Posters menu item has been selected",Toast.LENGTH_SHORT).show();
		 		startActivity(new Intent("com.spidermanexample.PosterActivity"));
		 		return true;
		 	case R.id.about_me:
		 		//Toast.makeText(this,"I'm the all singing all dancing crap of the world",Toast.LENGTH_SHORT).show();
		 		auf.show(getSupportFragmentManager(),"dialog");
		 		return true;
		 	case R.id.exit_here:
		 		System.exit(0);
		 		return true;
		 }
		 return false;
	}
	
	
	

	@Override
	public void doNegativeClick() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "You just killed an innocent dialog", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void doPositiveClick() {
		// TODO Auto-generated method stub
		Toast.makeText(this,"You clicked on Ok", Toast.LENGTH_SHORT).show();
	}
	
}
