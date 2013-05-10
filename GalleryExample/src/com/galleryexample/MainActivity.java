package com.galleryexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
//import android.widget.Gallery;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements ViewFactory {

	int[] imageIDs={
			R.drawable.easy1,
			R.drawable.easy2,
			R.drawable.easy3,
			R.drawable.easy4,
			R.drawable.easy5,
			R.drawable.easy6,
			R.drawable.ic_launcher
	};
	
	private ImageSwitcher imageSwitcher;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageSwitcher=(ImageSwitcher)findViewById(R.id.switcher1);
		imageSwitcher.setFactory(this);
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
		/*@SuppressWarnings("deprecation")
		Gallery gallery=(Gallery)findViewById(R.id.gallery1);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemClickListener(new OnItemClickListener()
				{
					public void onItemClick(AdapterView parent,View v,int position,long id)
					{
						
						ImageView image=(ImageView)findViewById(R.id.imgView01);
						image.setImageResource(imageIDs[position]);
						
					}
				});*/
		
		GridView gridView=(GridView)findViewById(R.id.gridView1);
		gridView.setAdapter(new ImageAdapter(this));
		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView parent,View v,int position,long id)
			{
				Toast.makeText(getBaseContext(),"pic"+(position+1)+"selected",Toast.LENGTH_SHORT).show();
				imageSwitcher.setImageResource(imageIDs[position]);
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public View makeView()
	{
		ImageView imageView=new ImageView(this);
		imageView.setBackgroundColor(0XFF000000);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		return imageView;
	}
	
	public class ImageAdapter extends BaseAdapter
	{
		Context context;
		int itemBackground;
		public ImageAdapter(Context c)
		{
			context=c;
			TypedArray a=obtainStyledAttributes(R.styleable.Gallery1);
			itemBackground=a.getResourceId(R.styleable.Gallery1_android_galleryItemBackground,0);
			a.recycle();
		}
		public int getCount()
		{
			return imageIDs.length;
		}
		
		public Object getItem(int position)
		{
			return position;
		}
		
		public long getItemId(int position)
		{
			return position;
		}
		
		public View getView(int position,View convertView,ViewGroup parent)
		{
			ImageView imageView;
			if(convertView==null)
			{
				imageView=new ImageView(context);
				imageView.setImageResource(imageIDs[position]);
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setLayoutParams(new GridView.LayoutParams(150,120));
			}
			else
			{
				imageView=(ImageView)convertView;
			}
			imageView.setBackgroundResource(itemBackground);
			return imageView;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
