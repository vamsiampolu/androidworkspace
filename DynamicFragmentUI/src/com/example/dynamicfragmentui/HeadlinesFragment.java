package com.example.dynamicfragmentui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
public class HeadlinesFragment extends Fragment
{
	public interface OnHeadlinesFragmentSelected
	{
		public void checkLogin();
	}
	OnHeadlinesFragmentSelected mCallback;
	public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		return inflater.inflate(R.layout.headlines_fragment,parent,false);
	}
	
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		try
		{
			mCallback=(OnHeadlinesFragmentSelected)activity;
		}
		catch(ClassCastException ccx)
		{
			throw new ClassCastException(activity.toString()+"does not implement OnHeadlinesFragmentSelected");
		}
	}
	
}
