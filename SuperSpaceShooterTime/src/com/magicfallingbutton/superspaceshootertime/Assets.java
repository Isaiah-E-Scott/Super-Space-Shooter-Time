package com.magicfallingbutton.superspaceshootertime;

import com.magicfallingbutton.framework.Image;
import com.magicfallingbutton.framework.Music;
import com.magicfallingbutton.framework.Sound;

public class Assets {
	
	public static Image 
		//menu
		menu, word_LV, word_scr, retry_button, menu_button, menu_big, splash, 
		hpRed, hpYellow, hpGreen, hpGreen4, hpGreen5, hpGreen6, sound, sound_small,
		sound_mute, sound_mute_small, music, music_small, music_mute, music_mute_small,
		pause, resume, playerMenu, player1Menu, player2Menu, player3Menu, player4Menu, 
		player5Menu, player6Menu, player7Menu, player8Menu, player9Menu,instructions, 
		settings, arrows, arrows_neon, neon1, neon2, neon3, back, finish,
		//other
		blank, expl1, expl2, expl3, expl4, boom1, boom2, boom3, boom4, boom5,
		warning1, warning2, warning3,
		//asteroids
		asteroid1, asteroid2, asteroid3, asteroid4, asteroid5, asteroid6, asteroid7,
		asteroid1medium, asteroid2medium, asteroid3medium, asteroid4medium, 
		asteroid5medium, asteroid6medium, asteroid7medium, asteroid1large, asteroid2large, 
		asteroid3large, asteroid4large, asteroid5large, asteroid6large, asteroid7large,
		//power ups
		invincibility_powerup, spread_powerup, rapid_powerup, wave_powerup,
		//player
		player, player1, player2, player3, player4, player5, player6, player7, 
		player8, player9, player_hit, player_invincibility, player2Hit, player2Invincible, 
		player3Hit, player3Invincible, player4Hit, player4Invincible, player5Hit, 
		player5Invincible, player8Hit, player8Invincible, player6Hit, player6Invincible,
		player1Hit, player1Invincible,
		//bullets
		player_bullet, enemy_bullet, enemy2_bullet, enemy3_bullet, enemy_special_bullet,
		boss_bullet, wave_bullet, wave_bullet2, wave_bullet3, wave_bullet4, wave_bullet5, 
		//enemies
		enemy_b, enemy_p, enemy_y, enemy_r, enemy_c, enemy_special, enemy2_b, enemy2_r, 
		enemy2_y, enemy2_g, enemy2_p, enemy_points1, enemy_points2, enemy_points3, cashew, 
		enemy_v1, enemy_v1damage1, enemy_v1damage2, enemy_v2, enemy_v2damage1,
		enemy_v2damage2,
		//bosses
		boss1, boss1green1, boss1green2, boss1green3, boss1yellow1, boss1yellow2, 
		boss1yellow3, boss1red1, boss1red2, boss1red3, boss2, boss2frame1, 
		boss2frame2, boss2frame3, boss2frame4,
		//level 1 - 99 background
		colorbg, colorbg11, colorbg22, colorbg33, colormiddlebg, colorfrontbg,
		//level 100 background
		newbg, newbg11, newbg22, newbg33, newmiddlebg, newfrontbg;

	public static Music theme, bonus_enemy, cashew_intro, cashew_theme, invincibility_theme, boss_theme;
	public static Sound enemy_shot, enemy_death, player_shot, player_death, 
		ultimate_power, hit, alert, waveExplosion, gameover;
	
	public static void load(SampleGame sampleGame) {
		
		theme = sampleGame.getAudio().createMusic("theme.mp3");
		theme.setLooping(true);
		theme.setVolume(1.99f);
		cashew_intro = sampleGame.getAudio().createMusic("cashew_intro.mp3");
		cashew_intro.setLooping(false);
		cashew_intro.setVolume(1.99f);
		cashew_theme = sampleGame.getAudio().createMusic("cashew.mp3");
		cashew_theme.setLooping(true);
		cashew_theme.setVolume(1.99f);
		invincibility_theme = sampleGame.getAudio().createMusic("invincibility.mp3");
		invincibility_theme.setLooping(true);
		invincibility_theme.setVolume(1.99f);
		bonus_enemy = sampleGame.getAudio().createMusic("bonus_enemy.mp3");
		bonus_enemy.setLooping(true);
		bonus_enemy.setVolume(1.99f);
		boss_theme = sampleGame.getAudio().createMusic("boss_theme.mp3");
		boss_theme.setLooping(true);
		boss_theme.setVolume(1.99f);
		
	}
	
}
