package com.dialogexample.mine;

import android.os.Bundle;
import android.app.Activity;

import android.app.Dialog;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.app.DialogFragment;
public class MainLayoutActivity extends Activity {

	CharSequence[] items={"Google","Apple","Microsoft"};
	boolean[] itemsChecked=new boolean[3];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
	}
	
	@SuppressWarnings("deprecation")
	public void onClick(View view)
	{
		showDialog(0);
	}
	
	
	
	protected Dialog onCreateDialog(int id)
	{
		
				Builder builder=new AlertDialog.Builder(this);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("This is a dialog with some text");
				builder.setPositiveButton("OK",new OnClickListener()/*sub class of DialogInterface-->OnClickListener*/
						{
							public void onClick(DialogInterface dialog,int whichButton)
							{
								Toast.makeText(getBaseContext(),"OK CLICKED",Toast.LENGTH_SHORT).show();
								
							}	
						});
				builder.setNegativeButton("CANCEL",new OnClickListener()
						{
							public void onClick(DialogInterface dialog,int whichButton)
							{
								Toast.makeText(getBaseContext(), "CANCEL CLICKED",Toast.LENGTH_SHORT).show();
								
							}
						});
				
				builder.setMultiChoiceItems(items,itemsChecked,new DialogInterface.OnMultiChoiceClickListener()
				{
					public void onClick(DialogInterface dialog,int which,boolean isChecked)
					{
						Toast.makeText(getBaseContext(), items[which],Toast.LENGTH_SHORT).show();
					}
					
				});
				return builder.create();	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_layout, menu);
		return true;
	}

}
