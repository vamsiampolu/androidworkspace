package tut1.logindisable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginDisable extends Activity {
    /** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TextView user = (TextView)findViewById(R.id.editText1);
        final TextView pass = (TextView)findViewById(R.id.editText2); 
        final Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(LoginDisable.this, "Congrats You Pressed Button !", Toast.LENGTH_LONG).show();
				
			}
		});
        pass.setEnabled(true);
        
        
        user.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
				final TextView stremail =(TextView)v;
				if(LoginDisable.this.validateEmail(stremail.getText()))
					Toast.makeText(LoginDisable.this, "Email Address is Valid", Toast.LENGTH_LONG).show();
				 else
					Toast.makeText(LoginDisable.this, "Invalid Email", Toast.LENGTH_LONG).show();
					
				
				
			}
		});
        
      pass.setOnKeyListener(new OnKeyListener() {
		
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			LoginDisable.this.Checkuserpass();
			return false;
		}
	});

       
        
			
    }
    
    public boolean validateEmail(CharSequence s) {
    	
    	 Pattern pattern;
   	     Matcher matcher;
    
   	      final String EMAIL_PATTERN ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
   		  pattern = Pattern.compile(EMAIL_PATTERN);
   		  matcher = pattern.matcher(s.toString());
   		  return matcher.matches();	
    }
    
    public void Checkuserpass(){
    	final Button btn1 = (Button)findViewById(R.id.button1);
    	final TextView user1 = (TextView)findViewById(R.id.editText1);
        final TextView pass1 = (TextView)findViewById(R.id.editText2);

        String p = pass1.getText().toString();
        String s = user1.getText().toString();
        if(s.equals("yagu.patel@gmail.com") && p.equals("yagnesh")) {
        	Toast.makeText(this, "Button is now Enabled",Toast.LENGTH_SHORT).show();
        	btn1.setClickable(true);
        }else {
        	
        	btn1.setClickable(false);
        }
	
    }
  }

