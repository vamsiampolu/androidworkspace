package com.basicviews;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
public class HorizontalProgressBarActivity extends Activity {
ProgressBar progressBar;
Handler handler=new Handler();
static int progress;
int progressStatus=0;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progressbar2);
		progressBar=(ProgressBar)findViewById(R.id.progressBar2);
		progressBar.setMax(200);
		new Thread(new Runnable()
		{
			public void run()
			{
				while(progressStatus<100)
				{
					progressStatus=doSomeWork();
					handler.post(new Runnable()
					{
						public void run()
						{
							progressBar.setProgress(progressStatus);
						}
					}
							);
				}
				
				handler.post(new Runnable()
				{
					public void run()
					{
						Toast.makeText(getBaseContext(),"The work has been completed",Toast.LENGTH_SHORT).show();
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
