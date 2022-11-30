package com.magicfallingbutton.framework.implementation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.magicfallingbutton.framework.Audio;
import com.magicfallingbutton.framework.FileIO;
import com.magicfallingbutton.framework.Game;
import com.magicfallingbutton.framework.Graphics;
import com.magicfallingbutton.framework.Input;
import com.magicfallingbutton.framework.Screen;

public abstract class AndroidGame extends Activity implements Game {
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;
    FileOutputStream fos;
    FileInputStream fis;
    BufferedReader reader;
    public String value;
    SurfaceHolder ourHolder;
    int n;

    @SuppressLint({ "InlinedApi", "NewApi" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        int frameBufferWidth = isPortrait ? 480: 800;
        int frameBufferHeight = isPortrait ? 800: 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);
        
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();
        
        RelativeLayout layout = new RelativeLayout(this);
		
        // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
        /*mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.SMART_BANNER);*/
        //mAdView.setAdUnitId("ca-app-pub-4508709870142146/6303857712");
        //mAdView.setBackgroundResource(R.drawable.ic_launcher);
        
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        
        // Create an ad request.
        //AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        // Optionally populate the ad request builder.
        // adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);

        //layout.setGravity(Gravity.CENTER_VERTICAL | Gravity.TOP);
        
        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getInitScreen(fis, fos);
        
     // Add the AdView to the view hierarchy.
        //layout.addView(mAdView);
        layout.addView(renderView);
        
        //layout.bringChildToFront(mAdView);
        //mAdView.loadAd(adRequestBuilder.build());
        setContentView(layout);
        
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "MyGame");
        
     // FILE IO        
        String FILENAME = "SSST_Data.txt";
        String string = "music,1,sound,1,easy,0,normal,0,hard,0,impossible,0,gold,0";
        
        try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE/*MODE_APPEND*/);
			fos.write(string.getBytes());
	        fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /*try {
			fis = openFileInput(FILENAME);
			fis.read();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        try {
			fis = openFileInput(FILENAME);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer fileContent = new StringBuffer("");

        byte[] buffer = new byte[1024];

        try {
			while ((n = fis.read(buffer)) != -1) 
			{ 
			  fileContent.append(new String(buffer, 0, n)); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
        //mAdView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        //mAdView.pause();

        if (isFinishing())
            screen.dispose();
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }
    
    @Override
	public Screen getCurrentScreen() {

    	return screen;
    }
    
    public FileOutputStream getFileOut() {
    	return fos;
    }
    
    public FileOutputStream getFileIn() {
    	return fos;
    }
}