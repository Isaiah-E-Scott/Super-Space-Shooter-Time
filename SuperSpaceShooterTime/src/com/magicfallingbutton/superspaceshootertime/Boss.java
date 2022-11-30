package com.magicfallingbutton.superspaceshootertime;

import java.util.ArrayList;

public class Boss extends Enemy{
	
	private boolean readyToFire = true;
	private int speedX, speedY, centerX, centerY, health;
	private ArrayList<Enemy_Projectile> projectiles = new ArrayList<Enemy_Projectile>();
	private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	
	public Boss(int centerX, int centerY, int speedX, int speedY, int health) {
		setCenterX(centerX);
		setCenterY(centerY);
		setSpeedX(speedX);
		setSpeedY(speedY);
		setHealth(health);
	}
	
	@Override
	public void update(){
			centerX += speedX;
			centerY += speedY;
	}
	
	public void updateX(){
		centerX += speedX;
	}
	
	public void updateY(){
		centerY += speedY;
	}
	
	public void die(int x, int y) {
		this.centerY = y;
		this.centerX = x;
		this.speedY = 0;
		this.speedX = 0;
	}
	
	public void loseHealth(){
		health -= 1;
	}

	@Override
	public int getSpeedX() {
		return speedX;
	}
	
	public int getSpeedY() {
		return speedY;
	}

	@Override
	public int getCenterX() {
		return centerX;
	}

	@Override
	public int getCenterY() {
		return centerY;
	}
	
	public int getHealth() {
		return health;
	}

	@Override
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	@Override
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	@Override
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void explode() {
		Explosion e = new Explosion(centerX, centerY);
		explosions.add(e);
	}
	
	public void shoot(int sX, int sY) {
		if (readyToFire) {
			Enemy_Projectile p = new Enemy_Projectile(centerX+22, centerY+30, sX, sY);
			projectiles.add(p);
		}
	}
	
	public void shootLeft(int sX, int sY) {
		if (readyToFire) {
			Enemy_Projectile p = new Enemy_Projectile(centerX+7, centerY+30, sX, sY);
			projectiles.add(p);
		}
	}
	
	public void shootRight(int sX, int sY) {
		if (readyToFire) {
			Enemy_Projectile p = new Enemy_Projectile(centerX+37, centerY+30, sX, sY);
			projectiles.add(p);
		}
	}
	
	
	
	public ArrayList<Enemy_Projectile> getProjectiles() {
		return projectiles;
	}
	public ArrayList<Explosion> getExplosions() {
		return explosions;
	}

}
