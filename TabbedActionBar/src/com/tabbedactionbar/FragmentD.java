package com.tabbedactionbar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentD extends Fragment 
{
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_d, container,false);
	}
}
