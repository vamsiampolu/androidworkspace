package com.fragmentsexample.mine;


import android.app.Fragment;
import android.view.*;//for View,ViewGroup,LayoutInflater
import android.os.Bundle;

public class Fragment1 extends Fragment 
{
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
	{
		
		return inflater.inflate(R.layout.fragment1,container,false);
		
	}
	
}
