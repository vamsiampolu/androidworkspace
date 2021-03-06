package com.example.basicviews2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TimePicker;
import android.view.View;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.widget.Toast;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class MainActivity extends Activity {

	TimePicker timePicker;
	int hour,minute;
	static final int TIME_DIALOG_ID=0;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		timePicker=(TimePicker)findViewById(R.id.time_select);
		timePicker.setIs24HourView(true);
		showDialog(TIME_DIALOG_ID);
	}

	protected Dialog onCreateDialog(int id)
	{
		switch(id)
		{
			case TIME_DIALOG_ID:
				return new TimePickerDialog(this, mTimeSetListener,hour,minute,false);
		}
		return null;
	}
	
	private OnTimeSetListener mTimeSetListener=new OnTimeSetListener()
	{
		public void onTimeSet(TimePicker view,int hourOfDay,int minuteOfHour)
		{
			hour=hourOfDay;
			minute=minuteOfHour;
			SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm:ss",Locale.getDefault());
			@SuppressWarnings("deprecation")
			Date date=new Date(0,0,0,hour,minute);
			String strDate=timeFormat.format(date);
			Toast.makeText(getBaseContext(),"You have selected"+strDate,Toast.LENGTH_SHORT).show();
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
