package com.magicfallingbutton.superspaceshootertime;

import android.graphics.Rect;


public class Enemy {

	private int power, centerX, speedX, centerY;
	private Player player = GameScreen.getPlayer();

	public Rect r = new Rect(0, 0, 0, 0);
	public int health = 5;

	// Behavioral Methods
	public void update() {
		follow();
		centerX += speedX;
		r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);

		if (Rect.intersects(r, Player.yellowRed)) {
			checkCollision();
		}
		

	}

	private void checkCollision() {
		if (Rect.intersects(r, Player.rect)|| Rect.intersects(r, Player.rect2)
				|| Rect.intersects(r, Player.rect3) || Rect.intersects(r, Player.rect4)) {

		}
	}

	public void follow() {
		
		if (centerX < -95 || centerX > 810){
		}

		else if (Math.abs(player.getCenterX() - centerX) < 5) {
		}

		else {

			if (player.getCenterX() >= centerX) {
			} else {
			}
		}

	}

	public void die() {

	}

	public void attack() {

	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

}