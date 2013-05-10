package com.basicviews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.view.View;
import android.os.Handler;
public class ProgressBarActivity extends Activity {

	static int progress;
	int progressStatus;
	ProgressBar progressBar;
	Handler handler=new Handler();
	
	
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pbar);
		progressBar=(ProgressBar)findViewById(R.id.progressBar);
		new Thread(new Runnable()
		{
			public void run()
			{
				while(progressStatus<10)
				{
					progressStatus=doSomeWork();
				}
				
				handler.post(new Runnable()
				{
					public void run()
					{
						Toast.makeText(getBaseContext(),"The work is completed",Toast.LENGTH_SHORT).show();
						progressBar.setVisibility(View.GONE);
					}
				});
			}
			
			public int doSomeWork()
			{
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}
				return ++progress;
			}
		}
				).start();
				
}
}
