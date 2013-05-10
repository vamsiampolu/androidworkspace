package com.dialogfragmentexample;

import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
public class FragmentEg extends DialogFragment {

	static FragmentEg newInstance(String title)
	{
		FragmentEg feg=new FragmentEg();
		Bundle args=new Bundle();
		args.putString("title", title);
		feg.setArguments(args);
		return feg;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		String title=getArguments().getString("title");
		return new AlertDialog.Builder(getActivity())
		.setIcon(R.drawable.ic_launcher)
		.setTitle(title)
		.setPositiveButton("OK",new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog,int whichButton)
					{
						((MainActivity)getActivity()).doPositiveClick();
					}
				})
		.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				((MainActivity)getActivity()).doNegativeClick();
			}
		}).create();		
	}
}
