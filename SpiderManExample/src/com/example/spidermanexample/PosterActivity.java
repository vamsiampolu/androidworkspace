package com.example.spidermanexample;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ViewSwitcher.ViewFactory;
import android.widget.ImageSwitcher;
import android.view.View;
import android.view.ViewGroup.*;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Gallery;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import android.content.Intent;
public class PosterActivity extends SherlockFragmentActivity implements ViewFactory,AboutUsFragment.OnAboutUsFragmentListener
{
	Intent intent=new Intent("com.spidermanexample.ShowImageactivity");
	AboutUsFragment auf;
	private ImageSwitcher is;
	int[] imageIDs=new int[] {
			R.drawable.easy1,
			R.drawable.easy2,
			R.drawable.easy3,
			R.drawable.easy4,
			R.drawable.easy5,
			R.drawable.easy6,
			R.drawable.amazing_spiderman,
			R.drawable.webresources,
			R.drawable.firststeps
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.posters_layout);
		auf=AboutUsFragment.newInstance("About Us");
		auf.setListener(this);
		is=(ImageSwitcher)findViewById(R.id.switcher1);
		is.setFactory(this);
		is.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left));
		is.setOutAnimation(this,android.R.anim.slide_out_right);
	    is.setOnClickListener(new View.OnClickListener()
	    { 
	    	public void onClick(View v)
	    	{
	    		startActivity(intent);
	    	} 
	    	
	    });
		Gallery gallery=(Gallery)findViewById(R.id.gallery_poster);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> parent,View v,int position,long id)
				{
					intent.putExtra("position",position);
					is.setImageResource(imageIDs[position]);
					
				}
			}
				);
		
	}
	
	public View makeView()
	{
		ImageView imageView=new ImageView(this);
		imageView.setBackgroundColor(0XFF000000);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		return imageView;
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		getSupportMenuInflater().inflate(R.menu.main,menu);
		return true;
	}
	
	public boolean onOptionItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		switch(item.getItemId())
		{
		case R.id.homeMenu:
			startActivity(new Intent(this,MainActivity.class));
	 		return true;
	 	case R.id.comeIn:
	 		startActivity(new Intent("com.spidermanexample.RegisterActivity"));
	 		return true;
	 	case R.id.posters:
	 		//Toast.makeText(this,"Posters menu item has been selected",Toast.LENGTH_SHORT).show();
	 		startActivity(new Intent("com.spidermanexample.PosterActivity"));
	 		return true;
	 	case R.id.about_me:
	 		//Toast.makeText(this,"I'm the all singing all dancing crap of the world",Toast.LENGTH_SHORT).show();
	 		auf.show(getSupportFragmentManager(),"dialog");
	 		return true;
	 	case R.id.exit_here:
	 		System.exit(0);
	 		return true;
		}
		return false;
	}
	
	
	
	public class ImageAdapter extends BaseAdapter
	{
		Context context;
		int itemBackground;
		
		public ImageAdapter(Context c)
		{
			context=c;
			TypedArray ta=obtainStyledAttributes(R.styleable.poster_gallery);	
			itemBackground=ta.getResourceId(R.styleable.poster_gallery_android_galleryItemBackground,0);
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
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setImageResource(imageIDs[position]);
				imageView.setLayoutParams(new Gallery.LayoutParams(150,120));
				
			}
			else
			{
				imageView=(ImageView)convertView;
			}
			return imageView;
		}
	}



	@Override
	public void doPositiveClick() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "YOu clicked on Ok", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void doNegativeClick() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "You just destroyed a dialog's hopes", Toast.LENGTH_SHORT).show();
	}
	
	
	
}