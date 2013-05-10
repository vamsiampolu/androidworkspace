package com.listfragmentexample;


import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class ListFragmentExample extends ListFragment 
{
	String[] actor;
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragmentlv,container,false);
		
	}
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		actor=getResources().getStringArray(R.array.actors);
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,actor));
	}
	
	public void onListItemClick(ListView parent,View v,int location,long id)
	{
		
		Toast.makeText(getActivity()/*gets the actvity the fragment is asociated with*/,actor[location],Toast.LENGTH_SHORT).show();
	}
}
