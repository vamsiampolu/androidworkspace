package com.yambaproject;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefsActivity extends PreferenceActivity 
{
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}
}
