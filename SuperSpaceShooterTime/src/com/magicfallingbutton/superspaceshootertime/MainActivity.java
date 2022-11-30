package com.magicfallingbutton.superspaceshootertime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.Map;
import com.magicfallingbutton.superspaceshootertime.R;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.sdk.InMobiSdk;
import com.magicfallingbutton.framework.implementation.AndroidFileIO;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	
	//AdView mAdView;
	AndroidFileIO file;
	//private Button bannerButton;
	//private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		InMobiBanner banner = (InMobiBanner)findViewById(R.id.banner);
		banner.load();
		
		ImageView i = new ImageView(this);
		//i.setAdjustViewBounds(false); // set the ImageView bounds to match the Drawable's dimensions
		RelativeLayout layout = new RelativeLayout(this);
		i.setBackgroundResource(R.drawable.menu);
			
		//LinearLayout layout = new LinearLayout(this);
        //layout.setOrientation(LinearLayout.VERTICAL);

		ImageView ships = new ImageView(this);
		
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;
		ships.setTop(height/4);
		ships.setLeft(width/4);
		
		ships.setImageResource(R.drawable.playermenu);
		//ships.setAdjustViewBounds(true);
		//ships.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		
		InMobiSdk.init(this, "70cf2bb043484788850aedbbc76514f7"); //'this' is used specify context
		
        // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
        /*mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId("ca-app-pub-8616470736447513/2568419382");*/
        
        // Create an ad request.
        //AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        // Optionally populate the ad request builder.
        //adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);

        //layout.setGravity(Gravity.CENTER_VERTICAL | Gravity.TOP);
        
        // Add the AdView to the view hierarchy.
        /*layout.addView(mAdView);
        layout.addView(i);
        layout.addView(ships);*/
        
        
        
        // Start loading the ad.
        /*layout.bringChildToFront(mAdView);
        mAdView.loadAd(adRequestBuilder.build());

        setContentView(layout);*/

		//finish();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    public void onResume() {
        super.onResume();

        // Resume the AdView.
        //mAdView.resume();
        System.out.println("test outside");
    }

    @Override
    public void onPause() {
        // Pause the AdView.
        //mAdView.pause();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Destroy the AdView.
        //mAdView.destroy();

        super.onDestroy();
    }
    
    /*private AdListener adlistener = new AdListener() {
    	
    	@Override
    	public void onAdOpened() {
    		Log.d("Tag", "Ad Opened");
    	}
    	
    	@Override
		public void onAdClosed() {
    		Log.d("Tag", "Ad Closed");
    	}
    	
    	@Override
		public void onAdLeftApplication() {
    		Log.d("Tag", "Ad Left Application");
    	}
    };*/
}
