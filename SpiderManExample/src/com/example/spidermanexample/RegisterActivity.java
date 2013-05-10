package com.example.spidermanexample;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
public class RegisterActivity extends SherlockFragmentActivity implements AboutUsFragment.OnAboutUsFragmentListener
{
	AboutUsFragment auf;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		auf=AboutUsFragment.newInstance("About Us");
		auf.setListener(this);
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
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
				startActivity(new Intent("com.spidermanexample.PosterActivity"));
				return true;
			case R.id.about_me:
				//Toast.makeText(this,"I'm the all singing all dancing crap of the world",Toast.LENGTH_SHORT).show();
				auf.show(getSupportFragmentManager(),"dialog");
				return true;
		}
		return false;
	}
	
	public void onClick(View v)
	{
		Toast.makeText(this,"You clicked on submit",Toast.LENGTH_SHORT).show();
	}
	
	public void doPositiveClick()
	{
		Toast.makeText(this,"You clicked on Ok",Toast.LENGTH_SHORT).show();
	}
	
	public void doNegativeClick()
	{
		Toast.makeText(this,"You clicked on Cancel",Toast.LENGTH_SHORT).show();
	}
}
