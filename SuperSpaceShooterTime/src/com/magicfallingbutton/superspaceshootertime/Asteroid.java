package com.magicfallingbutton.superspaceshootertime;

public class Asteroid {

	private int speedY;
	private int posX;
	private int posY;
	
	public Asteroid(int speed, int x, int y){
		
		speedY = speed;
		posX = x;
		posY = y;
		
	}
	
	public int getSpeed(){
		return speedY;
	}
	
	public void setSpeed(int speed){
		speedY = speed;
	}
	
	public int getX(){
		return posX;
	}
	
	public void setX(){
		posX = (int)(Math.random()*440);
	}
	
	public int getY(){
		return posY;
	}
	
	public void setY(){
		posY = (int)(Math.random()*800-	1000);
	}
	
	public void update(boolean asteroidsActive) {
		if(asteroidsActive){
			posY += speedY;
		}
		else if(!asteroidsActive && posY > -35){
			posY += speedY;
		}
	}
	
}
