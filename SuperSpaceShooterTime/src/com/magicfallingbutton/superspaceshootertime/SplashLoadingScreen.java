package com.magicfallingbutton.superspaceshootertime;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.magicfallingbutton.framework.Game;
import com.magicfallingbutton.framework.Graphics;
import com.magicfallingbutton.framework.Screen;
import com.magicfallingbutton.framework.Graphics.ImageFormat;
import com.magicfallingbutton.superspaceshootertime.Assets;

public class SplashLoadingScreen extends Screen {
	
	FileInputStream fis;
	FileOutputStream fos;
	
	public SplashLoadingScreen(Game game, FileInputStream fis, FileOutputStream fos) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.splash = g.newImage("splash.jpg", ImageFormat.RGB565);
		
		game.setScreen(new LoadingScreen(game, fis, fos));

	}

	@Override
	public void paint(float deltaTime) {

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

	}
}