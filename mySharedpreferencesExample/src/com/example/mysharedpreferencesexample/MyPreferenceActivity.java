package com.example.mysharedpreferencesexample;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class MyPreferenceActivity extends PreferenceActivity {
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.mypreference_activity);
	}
}
