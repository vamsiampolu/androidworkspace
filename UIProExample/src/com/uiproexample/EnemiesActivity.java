package com.uiproexample;


import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageSwitcher;
import android.widget.ViewSwitcher.ViewFactory;//for ImageSwitcher
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
public class EnemiesActivity extends Activity  {

	int[] images=new int[] {R.drawable.easy1,R.drawable.easy2,R.drawable.easy3,R.drawable.easy4,R.drawable.easy5,R.drawable.easy6};
	public ImageSwitcher imageSwitcher;
	public void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enemies);
		imageSwitcher=(ImageSwitcher)findViewById(R.id.imgswitcher);
		imageSwitcher.setFactory(new ViewFactory()
			{
				public View makeView()
				{
					ImageView imageView=new ImageView(getBaseContext());
					imageView.setBackgroundColor(Color.BLACK);
					imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
					imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
					return imageView;
				}
			});
		
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right));
		
		GridView gridView=(GridView)findViewById(R.id.gridview01);
		gridView.setAdapter(new ImageAdapter(this));
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent,View view,int position,long id)
			{
				imageSwitcher.setImageResource(images[position]);
			}
		});
	}
	
	
	
	public class ImageAdapter extends BaseAdapter
	{
		
			private Context context;
			int itemBackground;
			public ImageAdapter(Context c)
			{
				context=c;
				TypedArray arr=obtainStyleAttributes(R.styleable.gridView);
				itemBackground=arr.getResourceId(R.styleable.gridView_android_gridViewItemBackground,0);
			}
			
			public int getCount()
			{
				return images.length;
			}
			
			public Object getItem(int position)
			{
				return position;
			}
			
			public long getItemId(int position)
			{
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) 
			{
				ImageView imageView;
				if(convertView==null)
				{
					imageView=new ImageView(context);
					imageView.setLayoutParams(new GridView.LayoutParams(85,85));
					imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
					imageView.setPadding(5,5,5,5);
					
				}
				else
				{
					imageView=(ImageView)convertView;
				}
				imageView.setImageResource(images[position]);
				return imageView;
			}
			
		}
	}

