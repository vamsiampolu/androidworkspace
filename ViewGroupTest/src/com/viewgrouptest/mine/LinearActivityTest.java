package com.viewgrouptest.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.view.Window;
public class LinearActivityTest extends Activity {

	public void onCreate(Bundle savedInstancestate)
	{
		super.onCreate(savedInstancestate);
		setContentView(R.layout.linearexample);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}
	
	public void onClickLinearLayout(View view)
	{
		Toast.makeText(getBaseContext(), "This is a linear layout",Toast.LENGTH_SHORT).show();
	}
	
	public void onClickTableLayout(View view)
	{
		startActivity(new Intent("com.viewgrouptest.mine.TableLayoutTest"));
	}
	
	public void onClickScrollView(View view)
	{
		startActivity(new Intent("com.viewgrouptest.mine.ScrollViewTest"));
	}
	
	public void onClickRelativeLayout(View view)
	{
		startActivity(new Intent("com.viewgrouptest.mine.RelativeLayoutTest"));
	}
}
