package com.nimai.android;
import com.nimai.android.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
	 WebView browser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        browser=(WebView)findViewById(R.id.webkit);     
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setDefaultFontSize(20);
        browser.loadUrl("http://www.nimaikrsna.com");        
    }
}
