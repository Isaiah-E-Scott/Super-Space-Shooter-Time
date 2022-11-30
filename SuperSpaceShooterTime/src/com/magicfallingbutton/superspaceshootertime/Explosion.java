package com.magicfallingbutton.superspaceshootertime;

public class Explosion {
	
	private int x, y;
	private boolean visible;
	
	public Explosion(int startX, int startY){
		x = startX;
		y = startY;
		visible = true;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
