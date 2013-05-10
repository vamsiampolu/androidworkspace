package tut1.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LoginSuccess extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState); 
	        setContentView(R.layout.second);
	        Intent in = getIntent();
	        if (in.getCharSequenceExtra("usr") != null) {
	        	final TextView setmsg = (TextView)findViewById(R.id.showmsg);
	        	setmsg.setText("Welcome \n "+in.getCharSequenceExtra("usr"));	        	
	        }
	        

	        
	        
	       
			 
    }

}