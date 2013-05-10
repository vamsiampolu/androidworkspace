package com.basicviews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View;
public class AutoCompleteTextViewActivity extends Activity {
	TimePicker timePicker;
	String[] presidents = {"Barack Obama","George W Bush","Bill Clinton","George H W Bush","Ronald Reagan","Jimmy Carter","Gerald Ford","Richard Nixon","Lyndon B Johnson","John F Kennedy","Dwight D Eisenhower","Frank D Roosevelet"};
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autocompltv);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,presidents);
		AutoCompleteTextView tv=(AutoCompleteTextView)findViewById(R.id.autocompltv);
		timePicker=(TimePicker)findViewById(R.id.timePicker);
		timePicker.setIs24HourView(true);
		tv.setThreshold(3);
		tv.setAdapter(adapter);
	}
	
	public void onClick(View v)
	{
		Toast.makeText(getBaseContext(), "The time picked: "+timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute(),Toast.LENGTH_SHORT).show();
	}
}
