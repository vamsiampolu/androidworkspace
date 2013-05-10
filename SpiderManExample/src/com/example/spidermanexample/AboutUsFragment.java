package com.example.spidermanexample;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockDialogFragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;



public class AboutUsFragment extends SherlockDialogFragment
{
	private OnAboutUsFragmentListener mCallback;
	public interface OnAboutUsFragmentListener
	{
		public void doPositiveClick();
		public void doNegativeClick();
		//public void showDialog();
	}
	public static AboutUsFragment newInstance(String title)
	{
		AboutUsFragment auf=new AboutUsFragment();
		Bundle args=new Bundle();
		args.putString("title",title);
		auf.setArguments(args);
		return auf;
	}
	
	public void setListener(OnAboutUsFragmentListener listener)
	{
		mCallback=listener;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		String title=getArguments().getString("title");
		//AlertDialog.Builder builder= new AlertDialog.Builder(getSherlockActivity(),R.style.myHoloDialogFragment);
		//final View v=getSherlockActivity().getLayoutInflater().inflate(R.layout.custom_layout,null);//R.layout.custom_layout
		AlertDialog.Builder builder= new AlertDialog.Builder(getSherlockActivity());
		builder.setIcon(R.drawable.icon_explore);
		builder.setTitle(title);
		builder.setMessage("This is what happens when a geek who turns into a superhero when bitten by a spider meets a consulting detective wise in the ways of the world.We represent the union of youthful playfulness with great wisdom");
		builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//((MainActi1vity)getActivity()).doPositiveClick();
				if(mCallback!=null)
				{
					mCallback.doPositiveClick();
				}
				else
				{
					Toast.makeText(getSherlockActivity(), "mCallback is invalid", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		builder.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog,int which) 
			{
				// TODO Auto-generated method stub
				//((MainActivity)getActivity()).doNegativeClick();
				if(mCallback!=null)
				{
					mCallback.doNegativeClick();
				}	
				else
				{
					Toast.makeText(getSherlockActivity(), "mCallback is invalid", Toast.LENGTH_SHORT).show();
				}
			}
		});
		return builder.create();
	}
}
