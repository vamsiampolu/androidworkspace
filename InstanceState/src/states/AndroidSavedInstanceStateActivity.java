package states;

import tut1.helloworld.R;
import android.app.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AndroidSavedInstanceStateActivity extends Activity {

	EditText edit;
	Button update;
	TextView text;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		edit = (EditText) findViewById(R.id.edit);
		update = (Button) findViewById(R.id.update);
		text = (TextView) findViewById(R.id.text);

		update.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				text.setText(edit.getText().toString());
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putString("TEXT", (String) text.getText());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		text.setText(savedInstanceState.getString("TEXT"));
	}

}