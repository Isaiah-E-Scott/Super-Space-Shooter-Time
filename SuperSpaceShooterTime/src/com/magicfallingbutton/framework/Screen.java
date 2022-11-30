package com.magicfallingbutton.framework;

public abstract class Screen {
    protected final Game game;
    protected static boolean mutemusic = false;
    protected static boolean mutesound = false;
    private float shotvolume, deathvolume, loudvolume;

    public Screen(Game game) {
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void paint(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
    
	public abstract void backButton();
	
	public float getLoudvolume() {
		return loudvolume;
	}

	public void setLoudvolume(float loudvolume) {
		this.loudvolume = loudvolume;
	}

	public float getShotvolume() {
		return shotvolume;
	}

	public void setShotvolume(float shotvolume) {
		this.shotvolume = shotvolume;
	}

	public float getDeathvolume() {
		return deathvolume;
	}

	public void setDeathvolume(float deathvolume) {
		this.deathvolume = deathvolume;
	}
}
