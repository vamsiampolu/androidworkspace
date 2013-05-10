package com.nimai;

import com.nimai.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckBoxTutorialActivity extends Activity {
	TextView tv;
	CheckBox cbS;
	CheckBox cbW;
	OnClickListener checkBoxListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        cbS=(CheckBox)findViewById(R.id.cbGoogle);
		cbW=(CheckBox)findViewById(R.id.cbWordPress);
        checkBoxListener =new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tv=(TextView)findViewById(R.id.tvDetails);
				tv.setText("I Like ");
				if(cbS.isChecked())
				{
					tv.setText(tv.getText().toString()+" "+ cbS.getText().toString());
				}
				if(cbW.isChecked())
				{
					tv.setText(tv.getText().toString()+ " "+cbW.getText().toString());
				}
				if(!cbS.isChecked()&&!cbW.isChecked())
				{
					tv.setText("");
				}
			}
		};
		
		cbS.setOnClickListener(checkBoxListener);
		cbW.setOnClickListener(checkBoxListener);
		
    }
}