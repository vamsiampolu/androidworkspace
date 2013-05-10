package com.example.etdemos;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class IMEOptionDemoActivity extends Activity {

    EditText searchTag;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imeoption_demo);
      searchTag = (EditText)findViewById(R.id.url_field);
        
        
        searchTag.setOnEditorActionListener(new OnEditorActionListener() {

			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {

				// TODO Auto-generated method stub

				// hiding maplayout if open
				// mapView.getOverlays().clear();

				if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE ||  actionId == EditorInfo.IME_NULL ||  actionId == KeyEvent.KEYCODE_ENTER) {
					
					String query = searchTag.getText().toString();
					// updateMapData(query);
					
					Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
					
					hideSoftKeyboard(IMEOptionDemoActivity.this);
				}
				return false;

			}
		});
        
        
    }
    
    public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}

   
    
}
