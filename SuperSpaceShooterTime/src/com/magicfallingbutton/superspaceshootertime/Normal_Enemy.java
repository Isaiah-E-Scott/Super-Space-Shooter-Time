package com.magicfallingbutton.superspaceshootertime;

import java.util.ArrayList;

import android.graphics.Rect;

public class Normal_Enemy extends Enemy{
	
	private boolean readyToFire = true;
	private int speedX = 10;
	public Rect rect = new Rect(0, 0, 0, 0);
	private int centerX;
	private int centerY;
	
	private ArrayList<Enemy_Projectile> projectiles = new ArrayList<Enemy_Projectile>();
	private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	
	public Normal_Enemy(int centerX, int centerY) {

		setCenterX(centerX);
		setCenterY(centerY);

	}
	
	@Override
	public void update(){
		
			centerX += speedX;
		
	}
	
	public void updateArmada(int speed){
		
		centerY += speed;
		if(centerY > 170){
			centerY = 170;
		}
		else if (centerY < -140){
			centerY = -140;
		}
	
	}
	
	public void updateV(int speed){
		
		centerY += speed;
		if(centerY > 400){
			centerY = 400;
		}
		else if (centerY < -160){
			centerY = -160;
		}
	
	}
	
	public void updateCashew(int speed){
		
		centerY += speed;
		if(centerY > 110){
			centerY = 110;
		}
		else if(centerY < -440){
			centerY = -440;
		}
	}
	
	public void die(int x) {
		this.centerX = x;
		this.speedX = 0;
	}
	
	public void dieArmada(int y){
		this.centerY = y;
		//this.speedY = 0;
	}

	@Override
	public void attack() {

	}

	@Override
	public int getSpeedX() {
		return speedX;
	}

	@Override
	public int getCenterX() {
		return centerX;
	}

	@Override
	public int getCenterY() {
		return centerY;
	}

	@Override
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	@Override
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	@Override
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	
	public void shoot() {
		if (readyToFire) {
			Enemy_Projectile p = new Enemy_Projectile(centerX, centerY);
			projectiles.add(p);
		}
	}
	
	public void explode() {
		Explosion e = new Explosion(centerX, centerY);
		explosions.add(e);
	}
	
	public void armadaShoot() {
		if (readyToFire) {
			Enemy_Projectile p = new Enemy_Projectile(centerX, centerY);
			Enemy_Projectile p1 = new Enemy_Projectile(centerX+40, centerY);
			Enemy_Projectile p2 = new Enemy_Projectile(centerX+80, centerY);
			Enemy_Projectile p3 = new Enemy_Projectile(centerX+120, centerY);
			Enemy_Projectile p4 = new Enemy_Projectile(centerX+160, centerY);
			Enemy_Projectile p5 = new Enemy_Projectile(centerX+200, centerY);
			Enemy_Projectile p6 = new Enemy_Projectile(centerX+240, centerY);
			Enemy_Projectile p7 = new Enemy_Projectile(centerX+280, centerY);
			Enemy_Projectile p8 = new Enemy_Projectile(centerX+320, centerY);
			Enemy_Projectile p9 = new Enemy_Projectile(centerX+360, centerY);
			Enemy_Projectile p10 = new Enemy_Projectile(centerX+400, centerY);
			Enemy_Projectile p11 = new Enemy_Projectile(centerX+440, centerY);
			projectiles.add(p);
			projectiles.add(p1);
			projectiles.add(p2);
			projectiles.add(p3);
			projectiles.add(p4);
			projectiles.add(p5);
			projectiles.add(p6);
			projectiles.add(p7);
			projectiles.add(p8);
			projectiles.add(p9);
			projectiles.add(p10);
			projectiles.add(p11);
		}
	}
	
	public void shoot(int sX, int sY) {
		if (readyToFire) {
			Enemy_Projectile p = new Enemy_Projectile(centerX, centerY, sX, sY);
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
