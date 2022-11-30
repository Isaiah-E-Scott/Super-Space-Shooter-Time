package com.magicfallingbutton.superspaceshootertime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.magicfallingbutton.framework.Game;
import com.magicfallingbutton.framework.Graphics;
import com.magicfallingbutton.framework.Screen;
import com.magicfallingbutton.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen{
	
	enum GameState {
		Start, Settings, Settings2, Instructions
	}
	
	boolean settingschanged = false;
	GameState state = GameState.Start;
	Paint paint, paint2, paint3, paint4, paint5, paint6, paint7;
	public int shipType = 6, difficulty = 1, neon = 1;
	FileInputStream fis;
	FileOutputStream fos;
	String value = "String: ";
	
	public MainMenuScreen(Game game, FileInputStream fis, FileOutputStream fos) {
		
		super(game);
		
		this.fis = fis;
		this.fos = fos;
		
		paint = new Paint();
		paint.setTextSize(100);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		
		paint2 = new Paint();
		paint2.setTextSize(35);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);
		
		paint3 = new Paint();
		paint3.setTextSize(80);
		paint3.setTextAlign(Paint.Align.CENTER);
		paint3.setAntiAlias(true);
		paint3.setColor(Color.WHITE);
		
		paint4 = new Paint();
		paint4.setTextSize(70);
		paint4.setTextAlign(Paint.Align.CENTER);
		paint4.setAntiAlias(true);
		paint4.setColor(Color.WHITE);
		
		paint5 = new Paint();
		paint5.setTextSize(50);
		paint5.setTextAlign(Paint.Align.CENTER);
		paint5.setAntiAlias(true);
		paint5.setColor(Color.CYAN);
		
		paint6 = new Paint();
		paint6.setTextSize(50);
		paint6.setTextAlign(Paint.Align.CENTER);
		paint6.setAntiAlias(true);
		paint6.setColor(Color.WHITE);
		
		paint7 = new Paint();
		paint7.setTextSize(20);
		paint7.setTextAlign(Paint.Align.LEFT);
		paint7.setAntiAlias(true);
		paint7.setColor(Color.WHITE);
		
	}

	@Override
	public void update(float deltaTime) {
		
		//fileIo
		
		try {
			value = value + convertStreamToString(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assets.bonus_enemy.pause();
		Assets.invincibility_theme.pause();
		Assets.boss_theme.pause();
		if(mutemusic == false){
			Assets.theme.play();
		}
		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		if (state == GameState.Start)
			updateStart(touchEvents);
		if (state == GameState.Settings || state == GameState.Settings2)
			updateSettings(touchEvents);
		if (state == GameState.Instructions)
			updateInstructions(touchEvents);
	}
	
	public void updateStart(List<TouchEvent> touchEvents){
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			//touch for play
			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 115, 595, 250, 65)) {
					if(settingschanged == true){
						game.setScreen(new GameScreen(game, shipType, difficulty, neon, fis, fos));
					}else{
						state = GameState.Settings2;
					}
				}
				
				if (inBounds(event, 365, 348, 105, 105)) {
					shipType += 1;
					if(shipType > 9){
						shipType = 0;
					}
				}
				
				if (inBounds(event, 8, 348, 105, 105)) {
					shipType -= 1;
					if(shipType < 0){
						shipType = 9;
					}
				}

			}
			if (event.type == TouchEvent.TOUCH_UP) {
				//touch for settings
				if (inBounds(event, 148, 700, 65, 65)) {
					settingschanged = true;
					state = GameState.Settings;
				}
				//touch for instructions
				if (inBounds(event, 271, 700, 65, 65)) {
					state = GameState.Instructions;
				}

			}
		}
	}
	
	public void updateSettings(List<TouchEvent> touchEvents){
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 365, 258, 105, 105)) {
					difficulty += 1;
					if(difficulty > 3){
						difficulty = 0;
					}
				}
				
				if (inBounds(event, 8, 258, 105, 105)) {
					difficulty -= 1;
					if(difficulty < 0){
						difficulty = 3;
					}
				}
				
				if (inBounds(event, 365, 568, 105, 105)) {
					neon += 1;
					if(neon > 3){
						neon = 0;
					}
				}
				
				if (inBounds(event, 8, 568, 105, 105)) {
					neon -= 1;
					if(neon < 0){
						neon = 3;
					}
				}

			}
			
			if (event.type == TouchEvent.TOUCH_DOWN) {
				//touch for music
				if (inBounds(event, 148, 700, 64, 64)) {
					mutemusic = !mutemusic;
				}
				//touch for sound
				if (inBounds(event, 271, 700, 64, 64)) {
					mutesound = !mutesound;
				}

			}
			
			if (event.type == TouchEvent.TOUCH_UP) {

				//touch for back
				if (inBounds(event, 24, 700, 65, 65)) {
					this.backButton();
				}
				
				if(state == GameState.Settings){	
					//touch for back
					if (inBounds(event, 391, 700, 65, 65)) {
						state = GameState.Instructions;
					}
				}else{
					//touch for start
					if (inBounds(event, 391, 700, 65, 65)) {
						game.setScreen(new GameScreen(game, shipType, difficulty, neon, fis, fos));
					}
				}
				
			}
		}
	}
	
	public void updateInstructions(List<TouchEvent> touchEvents){
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_UP) {

				//touch for back
				if (inBounds(event, 24, 700, 65, 65)) {
					this.backButton();
				}
				
			}
			
		}
	}
		
	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);
		if(shipType == 0){
			g.drawImage(Assets.playerMenu, 125, 300);
		}else if(shipType == 1){
			g.drawImage(Assets.player1Menu, 125, 300);
		}else if(shipType == 2){
			g.drawImage(Assets.player2Menu, 125, 300);
		}else if(shipType == 3){
			g.drawImage(Assets.player3Menu, 125, 300);
		}else if(shipType == 4){
			g.drawImage(Assets.player4Menu, 125, 300);
		}else if(shipType == 5){
			g.drawImage(Assets.player5Menu, 125, 300);
		}else if(shipType == 6){
			g.drawImage(Assets.player6Menu, 125, 300);
		}else if(shipType == 7){
			g.drawImage(Assets.player7Menu, 125, 300);
		}else if(shipType == 8){
			g.drawImage(Assets.player8Menu, 125, 300);
		}else if(shipType == 9){
			g.drawImage(Assets.player9Menu, 125, 300);
		}
		
		g.drawImage(Assets.settings, 148, 700);
		g.drawImage(Assets.instructions, 271, 700);
		
		
		
		if(state == GameState.Settings){
			drawSettingsUI();
		}
		if(state == GameState.Settings2){
			drawSettingsUI();
		}
		if(state == GameState.Instructions){
			drawInstructionsUI();
		}
		
	}
	
	private void drawInstructionsUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(220, 0, 0, 0);
		
		g.drawString("Instructions", 240, 70, paint3);
		g.drawString("-tap anywhere on the", 180, 130, paint2);
		g.drawString("screen to teleport", 180, 165, paint2);
		g.drawString("-drag the screen", 320, 230, paint2);
		g.drawString("to move", 320, 265, paint2);
		g.drawString("-holding the screen will", 180, 330, paint2);
		g.drawString("activate auto-fire", 180, 365, paint2);
		g.drawString("-rapidly tapping will", 310, 430, paint2);
		g.drawString("will fire faster", 310, 465, paint2);
		g.drawString("-pause by tapping", 180, 530, paint2);
		g.drawString("the pause button", 180, 565, paint2);
		g.drawString("-press the back", 320, 630, paint2);
		g.drawString("button to return", 320, 665, paint2);
		g.drawString("to the previous", 320, 700, paint2);
		g.drawString("screen or to close", 320, 735, paint2);
		g.drawString("the app", 320, 770, paint2);
		g.drawString(value, 0, 750, paint7);
		g.drawImage(Assets.back, 24, 700);
		
	}
	
	public void drawSettingsUI(){
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(200, 0, 0, 0);
		
		g.drawString("Difficulty", 240, 230, paint);
		g.drawImage(Assets.arrows, 0, 240);
		if(difficulty == 0){
			g.drawString("Easy", 240, 330, paint);
			g.drawImage(Assets.enemy2_b, 120-20, 100);
			g.drawImage(Assets.enemy_b, 240-20, 100);
			g.drawImage(Assets.enemy_points1, 360-20, 100);
			g.drawImage(Assets.player, 240-20, 500);
			g.drawImage(Assets.player_bullet, 240-7, 460);
			g.drawImage(Assets.enemy2_b, 120, 360);
			g.drawImage(Assets.enemy_b, 260, 390);
		}
		if(difficulty == 1){
			g.drawString("Normal", 240, 330, paint4);
			g.drawImage(Assets.enemy2_b, 60-20, 100);
			g.drawImage(Assets.enemy_b, 120-20, 100);
			g.drawImage(Assets.enemy2_y, 180-20, 100);
			g.drawImage(Assets.enemy_y, 240-20, 100);
			g.drawImage(Assets.enemy_special, 300-20, 100);
			g.drawImage(Assets.enemy_points1, 360-20, 100);
			g.drawImage(Assets.asteroid1, 420-20, 100);
			g.drawImage(Assets.player6, 240-20, 500);
			g.drawImage(Assets.player_bullet, 220-7, 440);
			g.drawImage(Assets.player_bullet, 250-7, 470);
			g.drawImage(Assets.enemy2_b, 120-20, 360);
			g.drawImage(Assets.enemy_b, 290-20, 380);
			g.drawImage(Assets.enemy2_y, 350-20, 400);
			g.drawImage(Assets.enemy_y, 190-20, 420);
			g.drawImage(Assets.enemy_special, 120-20, 440);
			g.drawImage(Assets.enemy_points1, 350-20, 480);
			g.drawImage(Assets.asteroid1, 240-20, 340);
		}
		if(difficulty == 2){
			g.drawString("Hard", 240, 330, paint);
			g.drawImage(Assets.enemy2_b, 40-20, 100);
			g.drawImage(Assets.enemy_b, 80-20, 100);
			g.drawImage(Assets.enemy2_r, 120-20, 100);
			g.drawImage(Assets.enemy_p, 160-20, 100);
			g.drawImage(Assets.enemy2_y, 200-20, 100);
			g.drawImage(Assets.enemy_y, 240-20, 100);
			g.drawImage(Assets.enemy_special, 280-20, 100);
			g.drawImage(Assets.enemy_v1, 320-20, 100);
			g.drawImage(Assets.enemy_points1, 360-20, 100);
			g.drawImage(Assets.asteroid1, 400-20, 100);
			g.drawImage(Assets.asteroid1medium, 440-25, 100);
			g.drawImage(Assets.player8, 220, 500);
			g.drawImage(Assets.enemy2_b, 115, 410);
			g.drawImage(Assets.enemy_b, 375, 450);
			g.drawImage(Assets.enemy2_r, 325, 345);
			g.drawImage(Assets.enemy_p, 290, 425);
			g.drawImage(Assets.enemy2_y, 235, 385);
			g.drawImage(Assets.enemy_y, 70, 360);
			g.drawImage(Assets.enemy_special, 350, 400);
			g.drawImage(Assets.enemy_v1, 180, 430);
			g.drawImage(Assets.enemy_points1, 108, 500);
			g.drawImage(Assets.asteroid1, 75, 455);
			g.drawImage(Assets.asteroid1, 405, 390);
			g.drawImage(Assets.asteroid1medium, 155, 340);
			g.drawImage(Assets.asteroid1medium, 320, 495);
			g.drawImage(Assets.enemy2_bullet, 85, 410);
			g.drawImage(Assets.enemy2_bullet, 305, 470);
			g.drawImage(Assets.player_bullet, 235, 475);
			g.drawImage(Assets.enemy_bullet, 130, 465);
			g.drawImage(Assets.enemy_bullet, 245, 440);
		}
		if(difficulty == 3){
			g.drawString("Impossible", 240, 330, paint6);
			g.drawImage(Assets.enemy2_b, 36-20, 100);
			g.drawImage(Assets.enemy_b, 73-20, 100);
			g.drawImage(Assets.enemy2_r, 109-20, 100);
			g.drawImage(Assets.enemy_p, 146-20, 100);
			g.drawImage(Assets.enemy2_y, 182-20, 100);
			g.drawImage(Assets.enemy_y, 219-20, 100);
			g.drawImage(Assets.enemy_special, 255-20, 100);
			g.drawImage(Assets.enemy_v1, 292-20, 100);
			g.drawImage(Assets.enemy_points1, 328-20, 100);
			g.drawImage(Assets.asteroid1, 365-20, 100);
			g.drawImage(Assets.asteroid1medium, 401-25, 100);
			g.drawImage(Assets.asteroid1large, 438-30, 100);
			g.drawImage(Assets.player4, 240-20, 500);
			g.drawImage(Assets.enemy2_b, 115, 410);
			g.drawImage(Assets.enemy_b, 350, 400);
			g.drawImage(Assets.enemy2_r, 70, 360);
			g.drawImage(Assets.enemy_p, 290, 425);
			g.drawImage(Assets.enemy2_y, 180, 430);
			g.drawImage(Assets.enemy_y, 375, 450);
			g.drawImage(Assets.enemy_special, 325, 345);
			g.drawImage(Assets.enemy_v1, 235, 385);
			g.drawImage(Assets.enemy_points1, 25, 460);
			g.drawImage(Assets.asteroid1, 75, 455);
			g.drawImage(Assets.asteroid1, 425, 500);
			g.drawImage(Assets.asteroid1medium, 155, 340);
			g.drawImage(Assets.asteroid1medium, 320, 495);
			g.drawImage(Assets.asteroid1large, 15, 390);
			g.drawImage(Assets.asteroid1large, 405, 360);
			g.drawImage(Assets.player_bullet, 215, 350);
			g.drawImage(Assets.player_bullet, 145, 390);
			g.drawImage(Assets.player_bullet, 230, 470);
			g.drawImage(Assets.enemy_bullet, 90, 425);
			g.drawImage(Assets.enemy_bullet, 130, 470);
			g.drawImage(Assets.enemy_bullet, 190, 485);
			g.drawImage(Assets.enemy2_bullet, 280, 465);
			g.drawImage(Assets.enemy2_bullet, 310, 485);
			g.drawImage(Assets.enemy2_bullet, 350, 455);
			g.drawImage(Assets.enemy2_bullet, 390, 505);
			g.drawImage(Assets.enemy_special_bullet, 325, 400);
			g.drawImage(Assets.enemy_special_bullet, 165, 510);
			g.drawImage(Assets.enemy3_bullet, 245, 345);
			g.drawImage(Assets.enemy3_bullet, 195, 395);
			g.drawImage(Assets.enemy3_bullet, 300, 395);
			g.drawImage(Assets.enemy3_bullet, 245, 450);
			g.drawImage(Assets.enemy3_bullet, 170, 475);
			g.drawImage(Assets.enemy3_bullet, 320, 475);
		}
		
		g.drawString("NEON", 240, 600, paint5);
		g.drawImage(Assets.arrows_neon, 0, 550);
		if(neon == 0){
			g.drawImage(Assets.player6, 240-20, 620);
		}
		if(neon == 1){
			g.drawImage(Assets.neon1, 240-40, 620);
		}
		if(neon == 2){
			g.drawImage(Assets.neon2, 240-60, 620);
		}
		if(neon == 3){
			g.drawImage(Assets.neon3, 240-80, 620);
		}
		
		g.drawImage(Assets.back, 24, 700);
		if(state == GameState.Settings2){
			g.drawImage(Assets.finish, 391, 700);
		}else{
			g.drawImage(Assets.instructions, 391, 700);
		}
		
		//mute music
		if(mutemusic == false){
			g.drawImage(Assets.music_small, 148, 700);
			Assets.theme.play();
		}else{
			g.drawImage(Assets.music_mute_small, 148, 700);
			Assets.theme.pause();
		}
		//mute sound
		if(mutesound == false){
			g.drawImage(Assets.sound_small, 271, 700);//changed (original: 208, 700)
			setShotvolume(0.00f);
			setDeathvolume(0.00f);
		}
		else{
			g.drawImage(Assets.sound_mute_small, 271, 700);//changed (original: 208, 700)
			setShotvolume(1.99f);
			setDeathvolume(1.99f);
		}
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		if(state == GameState.Start){
			android.os.Process.killProcess(android.os.Process.myPid());
		}
		if(state == GameState.Settings){
			state = GameState.Start;
		}
		if(state == GameState.Settings2){
			state = GameState.Start;
		}
		if(state == GameState.Instructions){
			state = GameState.Start;
		}
		

	}
	
	public static String convertStreamToString(InputStream is) throws Exception {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	      sb.append(line).append("\n");
	    }
	    reader.close();
	    return sb.toString();
	}

	public static String getStringFromFile (String filePath) throws Exception {
	    File fl = new File(filePath);
	    FileInputStream fin = new FileInputStream(fl);
	    String ret = convertStreamToString(fin);
	    //Make sure you close all streams.
	    fin.close();        
	    return ret;
	}
	
}
