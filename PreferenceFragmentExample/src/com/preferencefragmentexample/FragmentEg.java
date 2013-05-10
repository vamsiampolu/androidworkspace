package com.preferencefragmentexample;


import android.preference.PreferenceFragment;
import android.os.Bundle;
public class FragmentEg extends PreferenceFragment {
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//load preferences from xml
		addPreferencesFromResource(R.xml.preferences);
	}

}
