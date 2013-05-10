package com.basicviews;
import android.os.Bundle;
import android.app.Activity;
//import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;
//import android.view.View;
//import android.app.ActionBar;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//Button has View.OnClickListener
		Button saveButton=(Button)findViewById(R.id.btn_save);
		
		saveButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				displayToast("You clciked the save button");
				startActivity(new Intent("com.basicviews.activa"));
			}
		}
				);
		
		Button openButton=(Button)findViewById(R.id.btn_open);
		openButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				displayToast("You clicked the open button");
				startActivity(new Intent("com.basicviews.ProgressBarActivity2"));
			}
		});
		
		//CheckBox has a View.OnClickListener
		
		CheckBox ch1=(CheckBox)findViewById(R.id.check01);
		ch1.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				if(((CheckBox)v).isChecked())
				{
					displayToast("The checkbox "+((CheckBox)v).getText()+" has been checked");
				}
				else
				{
					displayToast("The checkbox "+((CheckBox)v).getText()+" has been unchecked");
				}
			}
		}
				);
		
		CheckBox ch02=(CheckBox)findViewById(R.id.check02);
		ch02.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				if(((CheckBox)v).isChecked())
				{
					displayToast("You have star quality...I tell you");
				}
				else
				{
					displayToast("You cant handle the truth");
				}
			}
		}
				);
		
		RadioGroup group=(RadioGroup)findViewById(R.id.rbg);
		group.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			public void onCheckedChanged(RadioGroup group,int checkedId)
			{
				RadioButton rb1=(RadioButton)(findViewById(R.id.btn_radio1));
				if(rb1.isChecked())
				{
					displayToast("Option 1 is checked");
				}
				else
				{
					displayToast("Option 2 is checked");
				}
			}
		}
				);
		
		ToggleButton tb=(ToggleButton)findViewById(R.id.btn_toggle);
		tb.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(((ToggleButton)v).isChecked())
				{
					displayToast("The toggle button has been checked");
				}
				else
				{
					displayToast("The toggle button has been unchecked");
				}
			}
		});
		
		ImageButton imgbut=(ImageButton)findViewById(R.id.btn_img);
		imgbut.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				displayToast("You have clicked the  the Image Button");
				startActivity(new Intent("com.basicviews.ProgressBarActivity"));
			}
		}
				);
	}
	
	private void displayToast(String msg)
	{
		Toast.makeText(getBaseContext(), msg,Toast.LENGTH_LONG).show();
	}

	
}
