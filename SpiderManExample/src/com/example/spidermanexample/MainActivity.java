package com.example.spidermanexample;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;



public class MainActivity extends SherlockFragmentActivity implements AboutUsFragment.OnAboutUsFragmentListener {

	AboutUsFragment auf;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realmain);
		
		ActionBar actionBar=getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab loginTab=actionBar.newTab().setText("Login").setTabListener(new TabListener(this,"Login",LoginFragment.class));
		ActionBar.Tab regTab=actionBar.newTab().setText("Register").setTabListener(new TabListener(this,"Register",RegisterFragment.class));
		actionBar.addTab(loginTab);
		actionBar.addTab(regTab);
		
		auf=AboutUsFragment.newInstance("About Us");
		 auf.setListener(this);		
		 
		 Button button_login=(Button)findViewById(R.id.btn_login);
			button_login.setOnClickListener(new OnClickListener()
			{
				public void onClick(View v)
				{
					if(login())
					{
						Toast.makeText(getBaseContext(),"Login successsfull", Toast.LENGTH_SHORT).show();
					}
				}
			}
					);
			/*Button button_register=(Button)findViewById(R.id.btn_reg);
			button_register.setOnClickListener(new View.OnClickListener()
			{
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(getBaseContext(),com.example.spidermanexample.RegisterService.class);
					EditText edit_fname=(EditText)findViewById(R.id.edit_fname);
					EditText edit_lname=(EditText)findViewById(R.id.edit_lname);
					EditText edit_email=(EditText)findViewById(R.id.edit_email);
					EditText edit_uname=(EditText)findViewById(R.id.edit_uname);
					EditText edit_pwd=(EditText)findViewById(R.id.edit_pwd);
					String name=edit_fname.getText().toString()+" "+edit_lname.getText().toString();
					String email=edit_email.getText().toString();
					String username=edit_uname.getText().toString();
					String password=edit_pwd.getText().toString();
					i.putExtra("name",name);
					i.putExtra("email", email);
					i.putExtra("username", username);
					i.putExtra(password, password);
					startService(i);
				}
			});*/
			
			
	}
	
	public static class TabListener<T extends SherlockFragment> implements ActionBar.TabListener
	{
			String mTag;
			SherlockFragmentActivity mActivity;
			Class mClass;
			SherlockFragment mFragment;
			public TabListener(SherlockFragmentActivity mainActivity,String tag,Class clz)
			{
				mActivity=mainActivity;
				mTag=tag;
				mClass=clz;
			}
			
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if(mFragment==null)
			{
				mFragment=(SherlockFragment) Fragment.instantiate(mActivity, mClass.getName());
				ft.add(R.id.container, mFragment,mTag);
			}
			else
			{
				ft.attach(mFragment);
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.detach(mFragment);
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		
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
	
	
	public boolean login()
	{
		EditText edit_login=(EditText)findViewById(R.id.loginhere);
		EditText edit_password=(EditText)findViewById(R.id.passkey);
		String login=edit_login.getText().toString();
		String password=edit_password.getText().toString();
		UserDatabase udb=new UserDatabase(this);
		Cursor c=udb.query(UserDatabase.TABLE_NAME, login);
		if(c==null)
		{
			Log.d("Spiderman","Error retreiving data");
			return false;
		}
		else if(c.getCount()<1)
		{
			Log.d("Spiderman","This username does not exist");
			return false;
		}
		else
		{
			c.moveToFirst();
			String actual_password=c.getString(c.getColumnIndex(UserDatabase.COL_PASSWORD));
			if(password==actual_password)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}
