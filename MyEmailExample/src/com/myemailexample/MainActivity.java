package com.myemailexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickSendEmail(View v)
	{
		String[] to={"vamsideepak.03@gmail.com","vamsi.deepak03@gmail.com"};
		String[] cc={"vamsi.deepak03@gmail.com"};
		sendEmail(to,cc,"Subject is subjected to subjective conditions","The message is messy in messing with my massaged head");
	}
	
	public void sendEmail(String[] emailAddresses,String[] carbonCopies,String subject,String message)
	{
		Intent emailIntent=new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		String[] to=emailAddresses;
		String[] cc=carbonCopies;
		emailIntent.putExtra(Intent.EXTRA_EMAIL,to);
		emailIntent.putExtra(Intent.EXTRA_CC,cc);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, message);
		emailIntent.setType("message/rfc822");
		startActivity(Intent.createChooser(emailIntent, "Email"));
	}

}
