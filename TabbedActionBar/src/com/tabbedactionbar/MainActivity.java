package com.tabbedactionbar;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar=getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setTitle("Tabbed Layout");//ActionBar name is set to app name by default but you can set it to anything.
		
		ActionBar.Tab frag_A_tab=actionBar.newTab().setText("Fragment A");
		ActionBar.Tab frag_B_tab=actionBar.newTab().setText("Fragment B");
		ActionBar.Tab frag_C_tab=actionBar.newTab().setText("Fragment C");
		ActionBar.Tab frag_D_tab=actionBar.newTab().setText("Fragment D");
		
		Fragment fragmentA=new FragmentA();
		Fragment fragmentB=new FragmentB();
		Fragment fragmentC=new FragmentC();
		Fragment fragmentD=new FragmentD();
		
		frag_A_tab.setTabListener(new MyTabListener(fragmentA)).setIcon(android.R.drawable.btn_star_big_on);
		frag_B_tab.setTabListener(new MyTabListener(fragmentB)).setIcon(android.R.drawable.btn_star_big_on);
		frag_C_tab.setTabListener(new MyTabListener(fragmentC)).setIcon(android.R.drawable.btn_star_big_on);
		frag_D_tab.setTabListener(new MyTabListener(fragmentD)).setIcon(android.R.drawable.btn_star_big_on);
	
		actionBar.addTab(frag_A_tab);
		actionBar.addTab(frag_B_tab);
		actionBar.addTab(frag_C_tab);
		actionBar.addTab(frag_D_tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class MyTabListener implements ActionBar.TabListener
	{
		public Fragment fragment;
		
		public MyTabListener(Fragment frag)
		{
			fragment=frag;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.replace(R.id.frag_rep, fragment);
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
