package com.viewgrouptest.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import java.util.*;
public class RelativeLayoutTest extends Activity {

	StringBuilder s=null;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relativexample);
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		ArrayList<String> al=(ArrayList<String>)getLastNonConfigurationInstance();
		for(Iterator<String> iterator=al.iterator();iterator.hasNext();)
		{
			s.append(iterator.next());
		}
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	
	
	
	
	
	
	
	
}
