/**
 * 	Source: http://androidorigin.blogspot.com
 * 	Author: http://tamilcpu.blogspot.com	
 */
package com.nimai;

import com.nimai.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SuperStarActivity extends Activity implements OnRatingBarChangeListener {
	
	protected TextView lblRating;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ToggleButton toggleButton = (ToggleButton) findViewById(R.id.ToggleButton01);	
		final RatingBar myRatingBar = (RatingBar)findViewById(R.id.RatingBar01);		
		lblRating = (TextView)findViewById(R.id.TextView01);
		
		toggleButton.setChecked(true);
		
		//	Set appropriate listener to listen required events
		myRatingBar.setOnRatingBarChangeListener(this);
		toggleButton.setOnClickListener(new View.OnClickListener() {
				
			public void onClick(View v) {
				if (((ToggleButton) v).isChecked()) {
					DisplayToast("Toggle button is On");
					myRatingBar.setEnabled(true);
					lblRating.setText("Rating is " + myRatingBar.getRating());
				}
				else {
					DisplayToast("Toggle button is Off");
					lblRating.setText("");
					myRatingBar.setEnabled(false); // disable ratings bar
				}
			}
		});		
	}
	
	public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
		lblRating.setText("Rating is " + rating);		
	}
	
	private void DisplayToast(String msg) {
		//	Toast widget displays small messages
		Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
	}	
}