package com.example.mysharedpreferencesexample;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends Activity {
	
	SharedPreferences pref;
	SharedPreferences.Editor edit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button=(Button)findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.mysharedpreferences.MyPreferenceActivity"));
			}
		});
		
		pref=this.getSharedPreferences("com.example.mysharedpreferenceexample.MainActivity_preferences",MODE_PRIVATE);
		edit=pref.edit();
		Button buttonGet=(Button)findViewById(R.id.buttonGet);
		buttonGet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(),pref.getString("editTextPref"," "),Toast.LENGTH_SHORT).show();
			}
		});
		
		Button buttonBe=(Button)findViewById(R.id.buttonBe);
		buttonBe.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				edit.putString("editTextPref",((EditText)findViewById(R.id.editText1)).getText().toString());
				edit.commit();
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
