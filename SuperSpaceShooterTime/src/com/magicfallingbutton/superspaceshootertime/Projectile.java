package com.magicfallingbutton.superspaceshootertime;

import android.graphics.Rect;


public class Projectile {

	private int x, y, speedY;
	private Integer speedX;
	private boolean visible;
	
	private Rect r;
	
	public Projectile(int startX, int startY){
		x = startX;
		y = startY;
		speedY = 7;
		visible = true;
		
		r = new Rect(0, 0, 0, 0);
	}
	
	public Projectile(int i, int j, int sX) {
		x = i;
		y = j;
		speedY = 7;
		speedX = sX;
		visible = true;
		
		r = new Rect(0, 0, 0, 0);
	}

	public void update(){
		y -= speedY;
		if(speedX != null){
			x += speedX;
		}
		r.set(x, y, x+10, y+5);
		if (y < 0){
			visible = false;
			r = null;
		}
		if (visible){
			checkCollision();
		}
		
	}

	public void checkCollision() {
		
		if (Rect.intersects(r, GameScreen.enemy.r)){
			visible = false;

			if (GameScreen.enemy.health > 0) {
				GameScreen.enemy.health -= 1;
			}
			if (GameScreen.enemy.health == 0) {
				GameScreen.enemy.setCenterX(-100);
				

			}

		}
		
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedY() {
		return speedY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}
