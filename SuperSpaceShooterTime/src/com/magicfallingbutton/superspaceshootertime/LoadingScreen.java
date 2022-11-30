package com.magicfallingbutton.superspaceshootertime;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.magicfallingbutton.framework.Game;
import com.magicfallingbutton.framework.Graphics;
import com.magicfallingbutton.framework.Screen;
import com.magicfallingbutton.framework.Graphics.ImageFormat;
import com.magicfallingbutton.superspaceshootertime.Assets;

public class LoadingScreen extends Screen {
	
	FileInputStream fis;
	FileOutputStream fos;
	
	public LoadingScreen(Game game, FileInputStream fis, FileOutputStream fos) {
		
		super(game);
		
		this.fis = fis;
		this.fos = fos;
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		//menu
		Assets.menu = g.newImage("menu.jpg", ImageFormat.RGB565);
		Assets.word_LV  = g.newImage("word_LV.png", ImageFormat.RGB565);
		Assets.word_scr  = g.newImage("word_scr.png", ImageFormat.RGB565);
		Assets.retry_button = g.newImage("retry_button.png", 
				ImageFormat.RGB565);
		Assets.menu_button = g.newImage("menu_button.png", 
				ImageFormat.RGB565);
		Assets.menu_big = g.newImage("menu_big.png", ImageFormat.RGB565);
		Assets.hpRed = g.newImage("hpRed.png", ImageFormat.RGB565);
		Assets.hpYellow = g.newImage("hpYellow.png", ImageFormat.RGB565);
		Assets.hpGreen = g.newImage("hpGreen.png", ImageFormat.RGB565);
		Assets.hpGreen4 = g.newImage("hpGreen4.png", ImageFormat.RGB565);
		Assets.hpGreen5 = g.newImage("hpGreen5.png", ImageFormat.RGB565);
		Assets.hpGreen6 = g.newImage("hpGreen6.png", ImageFormat.RGB565);
		Assets.sound = g.newImage("sound.png", ImageFormat.RGB565);
		Assets.sound_mute = g.newImage("sound_mute.png", ImageFormat.RGB565);
		Assets.sound_small = g.newImage("sound_small.png", ImageFormat.RGB565);
		Assets.sound_mute_small = g.newImage("sound_mute_small.png", ImageFormat.RGB565);
		Assets.music = g.newImage("music.png", ImageFormat.RGB565);
		Assets.music_mute = g.newImage("music_mute.png", ImageFormat.RGB565);
		Assets.music_small = g.newImage("music_small.png", ImageFormat.RGB565);
		Assets.music_mute_small = g.newImage("music_mute_small.png", ImageFormat.RGB565);
		Assets.pause = g.newImage("pause.png", ImageFormat.RGB565);
		Assets.resume = g.newImage("resume.png", ImageFormat.RGB565);
		Assets.playerMenu = g.newImage("playerMenu.png", ImageFormat.RGB565);
		Assets.player1Menu = g.newImage("player1Menu.png", ImageFormat.RGB565);
		Assets.player2Menu = g.newImage("player2Menu.png", ImageFormat.RGB565);
		Assets.player3Menu = g.newImage("player3Menu.png", ImageFormat.RGB565);
		Assets.player4Menu = g.newImage("player4Menu.png", ImageFormat.RGB565);
		Assets.player5Menu = g.newImage("player5Menu.png", ImageFormat.RGB565);
		Assets.player6Menu = g.newImage("player6Menu.png", ImageFormat.RGB565);
		Assets.player7Menu = g.newImage("player7Menu.png", ImageFormat.RGB565);
		Assets.player8Menu = g.newImage("player8Menu.png", ImageFormat.RGB565);
		Assets.player9Menu = g.newImage("player9Menu.png", ImageFormat.RGB565);
		Assets.instructions = g.newImage("instructions.png", ImageFormat.RGB565);
		Assets.settings = g.newImage("settings.png", ImageFormat.RGB565);
		Assets.arrows = g.newImage("arrows.png", ImageFormat.RGB565);
		Assets.arrows_neon = g.newImage("arrows_neon.png", ImageFormat.RGB565);
		Assets.neon1 = g.newImage("neon1.png", ImageFormat.RGB565);
		Assets.neon2 = g.newImage("neon2.png", ImageFormat.RGB565);
		Assets.neon3 = g.newImage("neon3.png", ImageFormat.RGB565);
		Assets.back = g.newImage("back.png", ImageFormat.RGB565);
		Assets.finish = g.newImage("finish.png", ImageFormat.RGB565);
		
		//other
		Assets.blank = g.newImage("blank.png", ImageFormat.RGB565);
		Assets.expl1  = g.newImage("expl1.png", ImageFormat.RGB565);
		Assets.expl2  = g.newImage("expl2.png", ImageFormat.RGB565);
		Assets.expl3  = g.newImage("expl3.png", ImageFormat.RGB565);
		Assets.expl4  = g.newImage("expl4.png", ImageFormat.RGB565);
		Assets.boom1 = g.newImage("boom1.png", ImageFormat.RGB565);
		Assets.boom2 = g.newImage("boom2.png", ImageFormat.RGB565);
		Assets.boom3 = g.newImage("boom3.png", ImageFormat.RGB565);
		Assets.boom4 = g.newImage("boom4.png", ImageFormat.RGB565);
		Assets.boom5 = g.newImage("boom5.png", ImageFormat.RGB565);
		Assets.warning1 = g.newImage("warning1.png", ImageFormat.RGB565);
		Assets.warning2 = g.newImage("warning2.png", ImageFormat.RGB565);
		Assets.warning3 = g.newImage("warning3.png", ImageFormat.RGB565);
		
		//asteroids
		Assets.asteroid1 = g.newImage("asteroid1.png", ImageFormat.RGB565);
		Assets.asteroid2 = g.newImage("asteroid2.png", ImageFormat.RGB565);
		Assets.asteroid3 = g.newImage("asteroid3.png", ImageFormat.RGB565);
		Assets.asteroid4 = g.newImage("asteroid4.png", ImageFormat.RGB565);
		Assets.asteroid5 = g.newImage("asteroid5.png", ImageFormat.RGB565);
		Assets.asteroid6 = g.newImage("asteroid6.png", ImageFormat.RGB565);
		Assets.asteroid7 = g.newImage("asteroid7.png", ImageFormat.RGB565);
		Assets.asteroid1medium = g.newImage("asteroid1medium.png", ImageFormat.RGB565);
		Assets.asteroid2medium = g.newImage("asteroid2medium.png", ImageFormat.RGB565);
		Assets.asteroid3medium = g.newImage("asteroid3medium.png", ImageFormat.RGB565);
		Assets.asteroid4medium = g.newImage("asteroid4medium.png", ImageFormat.RGB565);
		Assets.asteroid5medium = g.newImage("asteroid5medium.png", ImageFormat.RGB565);
		Assets.asteroid6medium = g.newImage("asteroid6medium.png", ImageFormat.RGB565);
		Assets.asteroid7medium = g.newImage("asteroid7medium.png", ImageFormat.RGB565);
		Assets.asteroid1large = g.newImage("asteroid1large.png", ImageFormat.RGB565);
		Assets.asteroid2large = g.newImage("asteroid2large.png", ImageFormat.RGB565);
		Assets.asteroid3large = g.newImage("asteroid3large.png", ImageFormat.RGB565);
		Assets.asteroid4large = g.newImage("asteroid4large.png", ImageFormat.RGB565);
		Assets.asteroid5large = g.newImage("asteroid5large.png", ImageFormat.RGB565);
		Assets.asteroid6large = g.newImage("asteroid6large.png", ImageFormat.RGB565);
		Assets.asteroid7large = g.newImage("asteroid7large.png", ImageFormat.RGB565);
		
		// power ups
		Assets.invincibility_powerup = g.newImage("invincibility.png", ImageFormat.RGB565);
		Assets.spread_powerup = g.newImage("spread.png", ImageFormat.RGB565);
		Assets.rapid_powerup = g.newImage("rapid.png", ImageFormat.RGB565);
		Assets.wave_powerup = g.newImage("wave.png", ImageFormat.RGB565);
		
		//player
		Assets.player = g.newImage("player.png", ImageFormat.RGB565);
		Assets.player1 = g.newImage("player1.png", ImageFormat.RGB565);
		Assets.player2 = g.newImage("player2.png", ImageFormat.RGB565);
		Assets.player3 = g.newImage("player3.png", ImageFormat.RGB565);
		Assets.player4 = g.newImage("player4.png", ImageFormat.RGB565);
		Assets.player5 = g.newImage("player5.png", ImageFormat.RGB565);
		Assets.player6 = g.newImage("player6.png", ImageFormat.RGB565);
		Assets.player7 = g.newImage("player7.png", ImageFormat.RGB565);
		Assets.player8 = g.newImage("player8.png", ImageFormat.RGB565);
		Assets.player9 = g.newImage("player9.png", ImageFormat.RGB565);
		Assets.player_hit = g.newImage("player_hit.png", ImageFormat.RGB565);
		Assets.player_invincibility = g.newImage("player_invincibility.png", 
				ImageFormat.RGB565);
		Assets.player2Hit = g.newImage("player2Hit.png", ImageFormat.RGB565);
		Assets.player2Invincible = g.newImage("player2Invincible.png", 
				ImageFormat.RGB565);
		Assets.player3Hit = g.newImage("player3Hit.png", ImageFormat.RGB565);
		Assets.player3Invincible = g.newImage("player3Invincible.png", 
				ImageFormat.RGB565);
		Assets.player4Hit = g.newImage("player4Hit.png", ImageFormat.RGB565);
		Assets.player4Invincible = g.newImage("player4Invincible.png", 
				ImageFormat.RGB565);
		Assets.player5Hit = g.newImage("player5Hit.png", ImageFormat.RGB565);
		Assets.player5Invincible = g.newImage("player5Invincible.png", 
				ImageFormat.RGB565);
		Assets.player6Hit = g.newImage("player6Hit.png", ImageFormat.RGB565);
		Assets.player6Invincible = g.newImage("player6Invincible.png", 
				ImageFormat.RGB565);
		Assets.player8Hit = g.newImage("player8Hit.png", ImageFormat.RGB565);
		Assets.player8Invincible = g.newImage("player8Invincible.png", 
				ImageFormat.RGB565);
		Assets.player1Hit = g.newImage("player1Hit.png", ImageFormat.RGB565);
		Assets.player1Invincible = g.newImage("player1Invincible.png", 
				ImageFormat.RGB565);
		
		//bullets
		Assets.player_bullet = g.newImage("player_bullet.png", 
				ImageFormat.RGB565);
		Assets.enemy_bullet = g.newImage("enemy_bullet.png", 
				ImageFormat.RGB565);
		Assets.enemy2_bullet = g.newImage("enemy2_bullet.png", 
				ImageFormat.RGB565);
		Assets.enemy3_bullet = g.newImage("enemy3_bullet.png", 
				ImageFormat.RGB565);
		Assets.enemy_special_bullet  = g.newImage("enemy_special_bullet.png", 
				ImageFormat.RGB565);
		Assets.boss_bullet  = g.newImage("boss_bullet.png", ImageFormat.RGB565);
		Assets.wave_bullet  = g.newImage("wave_bullet.png", ImageFormat.RGB565);
		Assets.wave_bullet2  = g.newImage("wave_bullet2.png", ImageFormat.RGB565);
		Assets.wave_bullet3  = g.newImage("wave_bullet3.png", ImageFormat.RGB565);
		Assets.wave_bullet4  = g.newImage("wave_bullet4.png", ImageFormat.RGB565);
		Assets.wave_bullet5  = g.newImage("wave_bullet5.png", ImageFormat.RGB565);
		
		//enemies
		Assets.enemy_b = g.newImage("enemy_b.png", ImageFormat.RGB565);
		Assets.enemy_p = g.newImage("enemy_p.png", ImageFormat.RGB565);
		Assets.enemy_y  = g.newImage("enemy_y.png", ImageFormat.RGB565);
		Assets.enemy_r  = g.newImage("enemy_r.png", ImageFormat.RGB565);
		Assets.enemy_c  = g.newImage("enemy_c.png", ImageFormat.RGB565);
		Assets.enemy_special = g.newImage("enemy_special.png", 
				ImageFormat.RGB565);
		Assets.enemy2_b  = g.newImage("enemy2_b.png", ImageFormat.RGB565);
		Assets.enemy2_r  = g.newImage("enemy2_r.png", ImageFormat.RGB565);
		Assets.enemy2_y  = g.newImage("enemy2_y.png", ImageFormat.RGB565);
		Assets.enemy2_g  = g.newImage("enemy2_g.png", ImageFormat.RGB565);
		Assets.enemy2_p  = g.newImage("enemy2_p.png", ImageFormat.RGB565);
		Assets.enemy_points1  = g.newImage("enemy_points1.png", 
				ImageFormat.RGB565);
		Assets.enemy_points2  = g.newImage("enemy_points2.png", 
				ImageFormat.RGB565);
		Assets.enemy_points3  = g.newImage("enemy_points3.png", 
				ImageFormat.RGB565);
		Assets.cashew = g.newImage("cashew.png", ImageFormat.RGB565);
		Assets.enemy_v1 = g.newImage("enemy_v1.png", ImageFormat.RGB565);
		Assets.enemy_v1damage1 = g.newImage("enemy_v1damage1.png", ImageFormat.RGB565);
		Assets.enemy_v1damage2 = g.newImage("enemy_v1damage2.png", ImageFormat.RGB565);
		Assets.enemy_v2 = g.newImage("enemy_v2.png", ImageFormat.RGB565);
		Assets.enemy_v2damage1 = g.newImage("enemy_v2damage1.png", ImageFormat.RGB565);
		Assets.enemy_v2damage2 = g.newImage("enemy_v2damage2.png", ImageFormat.RGB565);
		
		//bosses
		Assets.boss1 = g.newImage("boss1.png", ImageFormat.RGB565);
		Assets.boss1green1 = g.newImage("boss1green1.png", ImageFormat.RGB565);
		Assets.boss1green2 = g.newImage("boss1green2.png", ImageFormat.RGB565);
		Assets.boss1green3 = g.newImage("boss1green3.png", ImageFormat.RGB565);
		Assets.boss1yellow1 = g.newImage("boss1yellow1.png", ImageFormat.RGB565);
		Assets.boss1yellow2 = g.newImage("boss1yellow2.png", ImageFormat.RGB565);
		Assets.boss1yellow3 = g.newImage("boss1yellow3.png", ImageFormat.RGB565);
		Assets.boss1red1 = g.newImage("boss1red1.png", ImageFormat.RGB565);
		Assets.boss1red2 = g.newImage("boss1red2.png", ImageFormat.RGB565);
		Assets.boss1red3 = g.newImage("boss1red3.png", ImageFormat.RGB565);
		Assets.boss2 = g.newImage("boss2.png", ImageFormat.RGB565);
		Assets.boss2frame1 = g.newImage("boss2frame1.png", ImageFormat.RGB565);
		Assets.boss2frame2 = g.newImage("boss2frame1.png", ImageFormat.RGB565);
		Assets.boss2frame3 = g.newImage("boss2frame2.png", ImageFormat.RGB565);
		Assets.boss2frame4 = g.newImage("boss2frame4.png", ImageFormat.RGB565);
		
		//level 1 - 99 background
		Assets.newbg = g.newImage("newbg.jpg", ImageFormat.RGB565);
		Assets.newbg11 = g.newImage("newbg11.png", ImageFormat.RGB565);
		Assets.newbg22 = g.newImage("newbg22.png", ImageFormat.RGB565);
		Assets.newbg33 = g.newImage("newbg33.png", ImageFormat.RGB565);
		Assets.newmiddlebg = g.newImage("newmiddlebg.png", ImageFormat.RGB565);
		Assets.newfrontbg = g.newImage("newfrontbg.png", ImageFormat.RGB565);
		//level 100 background
		Assets.colorbg = g.newImage("colorbg.jpg", ImageFormat.RGB565);
		Assets.colorbg11 = g.newImage("colorbg11.png", ImageFormat.RGB565);
		Assets.colorbg22 = g.newImage("colorbg22.png", ImageFormat.RGB565);
		Assets.colorbg33 = g.newImage("colorbg33.png", ImageFormat.RGB565);
		Assets.colormiddlebg = g.newImage("colormiddlebg.png", ImageFormat.RGB565);
		Assets.colorfrontbg = g.newImage("colorfrontbg.png", ImageFormat.RGB565);


		//enemy_shot, enemy_death, player_shot, player_death, ultimate_power, alert
		Assets.enemy_shot = game.getAudio().createSound("enemy_shot.mp3");
		Assets.enemy_death = game.getAudio().createSound("enemy_death.mp3");
		Assets.player_shot = game.getAudio().createSound("player_shot.mp3");
		Assets.player_death = game.getAudio().createSound("player_death.mp3");
		Assets.ultimate_power = game.getAudio().createSound("ultimate_power.mp3");
		Assets.hit = game.getAudio().createSound("hit.mp3");
		Assets.alert = game.getAudio().createSound("alert.mp3");
		Assets.waveExplosion = game.getAudio().createSound("waveExplosion.mp3");
		Assets.gameover = game.getAudio().createSound("gameover.mp3");

		game.setScreen(new MainMenuScreen(game, fis, fos));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
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