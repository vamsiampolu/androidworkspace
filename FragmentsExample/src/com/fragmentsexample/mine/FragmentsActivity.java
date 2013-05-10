package com.fragmentsexample.mine;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.Display;
public class FragmentsActivity extends Activity 
{
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*FragmentManager fm=getFragmentManager();
		FragmentTransaction ftn=fm.beginTransaction();
		WindowManager wm=getWindowManager();
		Display d=wm.getDefaultDisplay();
		
		if(d.getWidth()>d.getHeight())
		{
			ftn.replace(android.R.id.content,new Fragment1());
		}
		else
		{
			ftn.replace(android.R.id.content,new Fragment2());
		}
		ftn.commit();*/
	}
}
