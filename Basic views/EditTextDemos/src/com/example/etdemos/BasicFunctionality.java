package com.example.etdemos;

import com.example.etdemos.R;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;

public class BasicFunctionality extends Activity implements TextWatcher {

	  EditText text;
	  int textCount;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_basic_functionality);
	    
	    text = (EditText)findViewById(R.id.url_field);
	    
	    text.addTextChangedListener(this);
	  
	  }
	  /* TextWatcher Implementation Methods */
	  public void beforeTextChanged(CharSequence s, int start, int count,
	      int after) {
	  }

	  public void onTextChanged(CharSequence s, int start, int before, int end) {
	    textCount = text.getText().length();
	    setTitle("Message :: "+String.valueOf(textCount)+"/100");
	  }

	  public void afterTextChanged(Editable s) {
	  }
	}