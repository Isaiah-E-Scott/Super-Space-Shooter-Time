package com.magicfallingbutton.superspaceshootertime;


public class Background {
	
	private int bgX, bgY, speedX, speedY;
	
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedX = 0;
		speedY = 0;
	}
	
	public void updateX() {
		bgX += speedX;

		if (bgX <= -2160){
			bgX += 4320;
		}
	}
	
	public void updateY() {
		bgY += speedY;

		if (bgY >= 800){
			bgY = -800;
		}
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	
	
	
}
