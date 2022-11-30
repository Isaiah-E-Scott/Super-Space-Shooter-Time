package com.magicfallingbutton.superspaceshootertime;

import android.graphics.Rect;

public class PowerUp {
	
	private int x, y, speedY, type;
	private boolean visible, active;
	
	private Rect r;
	
	public PowerUp(int startX, int startY, int type){
		this.type = type;
		x = startX;
		y = startY;
		speedY = -4;
		visible = true;
		active = false;
		
		r = new Rect(0, 0, 0, 0);
	}
	
	public void update(){
		if(active == true){
			y -= speedY;
			if(this.y > 800){
				this.y = -120;
				active = false;
			}
			//avoid null references!!!!!!!!!!!!!!!!!!!!!!
			if(r != null){
				r.set(x, y, x+10, y+5);
				if (y > 800){
					visible = false;
					r = null;
				}
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
	
	public int getType() {
		return type;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
	public boolean getActive(){
		return active;
	}

}
