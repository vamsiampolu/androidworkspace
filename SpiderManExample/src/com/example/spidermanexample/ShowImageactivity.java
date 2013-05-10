package com.example.spidermanexample;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ViewSwitcher.ViewFactory;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;
public class ShowImageactivity extends SherlockFragmentActivity implements ViewFactory,AboutUsFragment.OnAboutUsFragmentListener
{
	AboutUsFragment auf;
	
	File sdCard,directory,file;
	int idOfImage;
	boolean isReadyForAction=false;
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
	int position=1;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fullimage_activity);
		ImageSwitcher imageswitcher=(ImageSwitcher)findViewById(R.id.fsiswitcher);
		imageswitcher.setFactory(this);
		imageswitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
		imageswitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
		imageswitcher.setOnCreateContextMenuListener(this);
		auf=AboutUsFragment.newInstance("About Us");
		auf.setListener(this);
		onCreateFolder("SpidermanExample");
	}
	
	public View makeView()
	{
		ImageView imageView=new ImageView(this);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView.setBackgroundColor(0XFF000000);
		position=getIntent().getIntExtra("position",0);
		imageView.setImageResource(imageIDs[position]);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		return imageView;
	}
	
	
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		android.view.MenuInflater inflater= getMenuInflater();
			inflater.inflate(R.menu.imgcontext,menu);
	}

	@Override
	public boolean onContextItemSelected(android.view.MenuItem item) {
		// TODO Auto-generated method stub
		super.onContextItemSelected(item);
		switch(item.getItemId())
		{
			case R.id.saveImage:
				//Toast.makeText(this,"The image could not be saved",Toast.LENGTH_SHORT).show();
				saveImageToFile("savedImage",directory);
				return true;
			
				
				
		}
		return false;
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		getSupportMenuInflater().inflate(R.menu.main,menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
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
	
	public void doPositiveClick()
	{
		Log.d("PositiveClick","onPositiveClick reached");
		Toast.makeText(this,"You clicked on Ok",Toast.LENGTH_SHORT).show();
	}
	
	public void doNegativeClick()
	{
		Log.d("NegativeClick","onNegativeClick reached");
		Toast.makeText(this,"You clicked onCancel",Toast.LENGTH_SHORT).show();
	}
	
	public boolean isExternalStorageWritable()
	{
		String state=Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state))
		{
			return true;
		}
		return false;
	}
	
	public void onCreateFolder(String fileName)
	{
		if(isExternalStorageWritable())
		{
			sdCard=Environment.getExternalStorageDirectory();
			directory=new File(sdCard.getAbsolutePath()+"/"+fileName);
		}
		else
		{
			Toast.makeText(this,"The SD Card is not mounted or is not ready for use",Toast.LENGTH_SHORT).show();
		}
	}
	
	public void saveImageToFile(String fileName,File dirName)
	{
		directory.mkdirs();
		if(directory.isDirectory())
		{
			file=new File(dirName,fileName+".png");
			new Thread()
			{
				public void run()
				{
					try
					{
						InputStream is=getResources().openRawResource(imageIDs[position]);
						FileOutputStream fos=new FileOutputStream(file);
						byte[] data=new byte[is.available()];
						is.read(data);
						fos.write(data);
						is.close();
						fos.close();
						Log.d("Saving file", "Successfully saved");
						
						
						
					}
					catch(FileNotFoundException fnfe)
					{
						Log.e("Saving File","Died",fnfe);
						//fnfe.printStackTrace();
					}
					catch(IOException iox)
					{
						Log.e("Saving File","Died",iox);
						//iox.printStackTrace();
					}
				
				
			}
		}.start();
					
	}
		else
		{
			Toast.makeText(this, "Could not save file", Toast.LENGTH_SHORT).show();
		}
		
}
}
	
