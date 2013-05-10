package com.fragmentsexample.mine;



import android.app.Fragment;
import android.view.*;//for View,ViewGroup,LayoutInflater
import android.os.Bundle;
import android.widget.*;//For Button,TextView,Toast
public class Fragment2 extends Fragment 
{
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
	{
		
		return inflater.inflate(R.layout.fragment2,container,false);
	}
	
	public void onStart()
	{
		super.onStart();
		Button button=(Button)getActivity().findViewById(R.id.clickme);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView tview=(TextView)getActivity().findViewById(R.id.frag1lbl);
				Toast.makeText(getActivity(), tview.getText(),Toast.LENGTH_SHORT).show();
			}
		});
	}
}
