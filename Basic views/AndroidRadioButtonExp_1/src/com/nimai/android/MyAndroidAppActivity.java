package com.nimai.android;

import com.nimai.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MyAndroidAppActivity extends Activity {

	private RadioGroup radioGenderGroup;
	private RadioButton radioGenderButton;
	private Button btnDisplay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		addListenerOnButton();

	}

	public void addListenerOnButton() {

		radioGenderGroup = (RadioGroup) findViewById(R.id.radioGender);
		btnDisplay = (Button) findViewById(R.id.btnDisplay);

		btnDisplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// get selected radio button from radioGroup
				int selectedId = radioGenderGroup.getCheckedRadioButtonId();

				// find the radiobutton by returned id
				radioGenderButton = (RadioButton) findViewById(selectedId);

				Toast.makeText(MyAndroidAppActivity.this,
						radioGenderButton.getText(), Toast.LENGTH_SHORT).show();

			}

		});

	}
}