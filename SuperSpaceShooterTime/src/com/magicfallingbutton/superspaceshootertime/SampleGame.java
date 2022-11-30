package com.magicfallingbutton.superspaceshootertime;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import com.magicfallingbutton.superspaceshootertime.R;
import com.magicfallingbutton.framework.FileIO;
import com.magicfallingbutton.framework.Screen;
import com.magicfallingbutton.framework.implementation.AndroidGame;

@SuppressLint("NewApi")
public class SampleGame extends AndroidGame {

	public static String map;
	boolean firstTimeCreate = true;
	FileOutputStream fos;
	FileInputStream fis;
	//AdView mAdView;
	//View game;
	

	@Override
	public Screen getInitScreen(FileInputStream fis, FileOutputStream fos) {
		
		//String FILENAME = "SSST_Data";
		
		/*try {
			fis = openFileInput(FILENAME);
			value = fis.read();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}
		
		return new SplashLoadingScreen(this, fis, fos);

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
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	@Override
	public void onResume() {
		super.onResume();
		
		//music issue resides here
		Assets.theme.play();

	}

	@Override
	public void onPause() {
		Assets.theme.pause();
		
		
		super.onPause();
	}

	@Override
	public FileIO getFileIO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public void onDestroy() {
        

        super.onDestroy();
    }
	
	
}