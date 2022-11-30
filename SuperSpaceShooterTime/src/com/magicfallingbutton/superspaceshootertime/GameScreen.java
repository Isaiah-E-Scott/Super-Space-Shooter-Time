package com.magicfallingbutton.superspaceshootertime;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.magicfallingbutton.framework.Game;
import com.magicfallingbutton.framework.Graphics;
import com.magicfallingbutton.framework.Image;
import com.magicfallingbutton.framework.Screen;
import com.magicfallingbutton.framework.Input.TouchEvent;
import com.magicfallingbutton.superspaceshootertime.Animation;
import com.magicfallingbutton.superspaceshootertime.Assets;
import com.magicfallingbutton.superspaceshootertime.Projectile;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	private static Player player;
	private boolean levelUp, gethit = true, isInvincible = false, spread = false,
		rapid = false, havePowerUp = false, auto = false, 
		asteroidsActive = false, start = false, warning = false, 
		enemyhit = false, bosshit = true, boss1easy_appear = false, boss2easy_appear = false,
		boss1hard_appear = false, boss2hard_appear = false;
	public ArrayList<Integer> explosions = new ArrayList<Integer>();
	public static Normal_Enemy enemy, enemy1, enemy2, enemyp, enemy3, enemy4,
		enemy5, special_enemy, enemy_v, cashew_enemy, armada1, armada2, armada3,
		armada4, armada5, armada6, armada7, armada8, armada9, armada10, armada11,
		armada12;
	public static Boss boss1easy, boss1hard, boss2easy, boss2hard, boss3easy, 
	boss3hard, bossCashew;
	public static Asteroid asteroid1, asteroid2, asteroid3, asteroid4, asteroid5,
		asteroid1medium, asteroid2medium, asteroid3medium, asteroid4medium, 
		asteroid5medium, asteroid1large, asteroid2large, asteroid3large, 
		asteroid4large, asteroid5large;
	public int finalScore, score, level = 0, x, y, shipType, next_level, 
			asteroidChance, difficulty, neon, asteroidLevel = 0, 
			enemy_v_health = 3, enemy1_color = 3, enemy2_color = 4, enemy3_color = 4, 
			enemy4_color = 3, enemy5_color = 2, enemy_color = 2;
	private PowerUp invincible, spreadShot, rapidFire, wave; // power ups
	private Image playerpic, player1, player2, player2Hit, player2Invincible, 
		player3, player3Hit, player3Invincible, player4, player4Hit, 
		player4Invincible, player5, player5Hit, player5Invincible, player6, 
		player6Hit, player6Invincible, player7, player8, player9,  player8Hit, 
		player8Invincible, player1Hit, player1Invincible,
		//level 1 - 99 frames
		colorbg, colorbg11, colorbg22, colorbg33, colormiddlebg, colorfrontbg,
		//level 100 frames
		newbg, newmiddlebg, newfrontbg,
		//enemies
		enemy2_b, enemy2_r, enemy2_y, enemy2_g, enemy2_p, enemy_points1, enemy_points2, 
		enemy_points3, enemy_b, enemy_p, enemy_y, enemy_r, enemy_c, cashew, special, 
		enemy_v1, enemy_v2, enemy_v1damage1, enemy_v2damage1, enemy_v1damage2, 
		enemy_v2damage2,
		//bosses
		boss1, boss1green1, boss1green2, boss1green3, boss1yellow1, boss1yellow2, 
		boss1yellow3, boss1red1, boss1red2, boss1red3, boss2frame1, 
		boss2frame2, boss2frame3, boss2frame4,
		//other
		boom1, boom2, boom3, boom4, boom5, expl1, expl2, expl3, expl4, blank, 
		warning1, warning2, warning3, 
		//asteroid
		asteroidPic1, asteroidPic2, asteroidPic3, asteroidPic4, asteroidPic5, 
		asteroidPic6, asteroidPic7,asteroidPic1medium, asteroidPic2medium, 
		asteroidPic3medium, asteroidPic4medium,	asteroidPic5medium, 
		asteroidPic6medium, asteroidPic7medium,asteroidPic1large, 
		asteroidPic2large, asteroidPic3large, asteroidPic4large, 
		asteroidPic5large, asteroidPic6large, asteroidPic7large,
		//power ups
		invincible_pic, spreadShot_pic, rapidFire_pic, wave_pic,
		//menu
		retry_button, menu_button, pause,  resume, menu_big, hpRed, hpYellow, 
		hpGreen, hpGreen4, hpGreen5, hpGreen6, player_hit, music, music_mute,
		sound,sound_mute, player_invincibility;
	
	private long frametime, invincibleTime, rapidTime, spreadTime, armadaTime, 
		shootTime, asteroidsTime, warningTime, enemyShootTime, enemy1ShootTime, 
		enemy2ShootTime, enemy3ShootTime, enemy4ShootTime, enemy5ShootTime, 
		enemySpecialShootTime, enemyvShootTime, enemyFrameTime, boss1FrameTime,
		boss1ShootTime;
	private Animation bonusanim, asteroidanim, asteroidanimmedium, 
		asteroidanimlarge, warninganim, enemy_v_anim, enemy_v_damaged1_anim, 
		enemy_v_damaged2_anim, boomanim, explanim, waveanim, boss1green_anim,
		boss1yellow_anim, boss1red_anim, boss2_anim;
	private int hint = (int)(Math.random()*12);
	private int power;
	private int livesLeft = 6;
	private int modified = 1;
	private Paint paint, paint2, paint3;
	private Background colorback1, colorback2, colormiddle1, colormiddle2, 
		colorfront1, colorfront2, blackback1, blackback2, blackmiddle1, 
		blackmiddle2, blackfront1, blackfront2;
	FileInputStream fis;
	FileOutputStream fos;
	

	public GameScreen(Game game, int shipType, int difficulty, int neon, FileInputStream fis, FileOutputStream fos) {
		super(game);

		// Initialize game objects here
		this.fis = fis;
		this.fos = fos;
		this.shipType = shipType;
		this.difficulty = difficulty;
		this.neon = neon;
		
		asteroid1 = new Asteroid(13, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid2 = new Asteroid(13, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid3 = new Asteroid(13, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid4 = new Asteroid(13, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid5 = new Asteroid(13, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid1medium = new Asteroid(10, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid2medium = new Asteroid(10, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid3medium = new Asteroid(10, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid4medium = new Asteroid(10, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid5medium = new Asteroid(10, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid1large = new Asteroid(7, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid2large = new Asteroid(7, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid3large = new Asteroid(7, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid4large = new Asteroid(7, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		asteroid5large = new Asteroid(7, (int)(Math.random()*440), 
				(int)(Math.random()*800-1000));
		
		armada1 = new Normal_Enemy(0, -40);
		armada2 = new Normal_Enemy(40, -40);
		armada3 = new Normal_Enemy(80, -40);
		armada4 = new Normal_Enemy(120, -40);
		armada5 = new Normal_Enemy(160, -40);
		armada6 = new Normal_Enemy(200, -40);
		armada7 = new Normal_Enemy(240, -40);
		armada8 = new Normal_Enemy(280, -40);
		armada9 = new Normal_Enemy(320, -40);
		armada10 = new Normal_Enemy(360, -40);
		armada11 = new Normal_Enemy(400, -40);
		armada12 = new Normal_Enemy(440, -40);
		
		boss1easy = new Boss(210, -70, 8, 2, 8);
		boss2easy = new Boss(210, -70, 8, 2, 8);
		
		boss1hard = new Boss(210, -70, 12, 6, 10);
		boss2hard = new Boss(210, -70, 12, 6, 10);
		
		armadaTime = System.currentTimeMillis();
		shootTime = System.currentTimeMillis();

		player = new Player();
		enemy = new Normal_Enemy(-900, 110);
		enemy1 = new Normal_Enemy(-900, 170);
		enemy2 = new Normal_Enemy(-900, 230);
		enemyp = new Normal_Enemy(-55, 290);
		enemy3 = new Normal_Enemy(-900, 110);
		enemy4 = new Normal_Enemy(-900, 170);
		enemy5 = new Normal_Enemy(-900, 230);
		cashew_enemy = new Normal_Enemy(220, -340);
		special_enemy = new Normal_Enemy(-120, 350);
		special_enemy.setSpeedX(3);
		enemy_v = new Normal_Enemy(240, -60);
		score = 0;
		level = 0;
		
		playerpic = Assets.player;
		player1 = Assets.player1;
		player2 = Assets.player2;
		player3 = Assets.player3;
		player4 = Assets.player4;
		player5 = Assets.player5;
		player6 = Assets.player6;
		player7 = Assets.player7;
		player8 = Assets.player8;
		player9 = Assets.player9;
		player2Hit = Assets.player2Hit;
		player3Hit = Assets.player3Hit;
		player4Hit = Assets.player4Hit;
		player5Hit = Assets.player5Hit;
		player6Hit = Assets.player6Hit;
		player8Hit = Assets.player8Hit;
		player1Hit = Assets.player1Hit;
		player_hit = Assets.player_hit;
		player2Invincible = Assets.player2Invincible;
		player3Invincible = Assets.player3Invincible;
		player4Invincible = Assets.player4Invincible;
		player5Invincible = Assets.player5Invincible;
		player6Invincible = Assets.player6Invincible;
		player8Invincible = Assets.player8Invincible;
		player1Invincible = Assets.player1Invincible;
		player_invincibility = Assets.player_invincibility;
		colorbg = Assets.colorbg;
		colorbg11 = Assets.colorbg11;
		colorbg22 = Assets.colorbg22;
		colorbg33 = Assets.colorbg33;
		colormiddlebg = Assets.colormiddlebg;
		colorfrontbg = Assets.colorfrontbg;
		newbg = Assets.newbg;
		newmiddlebg = Assets.newmiddlebg;
		newfrontbg = Assets.newfrontbg;
		enemy2_b = Assets.enemy2_b;
		enemy2_r = Assets.enemy2_r;
		enemy2_y = Assets.enemy2_y;
		enemy2_g = Assets.enemy2_g;
		enemy2_p = Assets.enemy2_p;
		enemy_b = Assets.enemy_b;
		enemy_p = Assets.enemy_p;
		enemy_y = Assets.enemy_y;
		enemy_r = Assets.enemy_r;
		enemy_c = Assets.enemy_c;
		enemy_points1 = Assets.enemy_points1;
		enemy_points2 = Assets.enemy_points2;
		enemy_points3 = Assets.enemy_points3;
		retry_button = Assets.retry_button;
		menu_button = Assets.menu_button;
		pause = Assets.pause;
		cashew = Assets.cashew;
		special = Assets.enemy_special;
		enemy_v1 = Assets.enemy_v1;
		enemy_v2 = Assets.enemy_v2;
		enemy_v1damage1 = Assets.enemy_v1damage1;
		enemy_v2damage1 = Assets.enemy_v2damage1;
		enemy_v1damage2 = Assets.enemy_v1damage2;
		enemy_v2damage2 = Assets.enemy_v2damage2;
		boss1 = Assets.boss1;
		boss1green1 = Assets.boss1green1;
		boss1green2 = Assets.boss1green2;
		boss1green3 = Assets.boss1green3;
		boss1yellow1 = Assets.boss1yellow1;
		boss1yellow2 = Assets.boss1yellow2;
		boss1yellow3 = Assets.boss1yellow3;
		boss1red1 = Assets.boss1red1;
		boss1red2 = Assets.boss1red2;
		boss1red3 = Assets.boss1red3;
		boss2frame1 = Assets.boss2frame1; 
		boss2frame2 = Assets.boss2frame2;
		boss2frame3 = Assets.boss2frame3;
		boss2frame4 = Assets.boss2frame4;
		boom1 = Assets.boom1;
		boom2 = Assets.boom2;
		boom3 = Assets.boom3;
		boom4 = Assets.boom4;
		boom5 = Assets.boom5;
		expl1 = Assets.expl1;
		expl2 = Assets.expl2;
		expl3 = Assets.expl3;
		expl4 = Assets.expl4;
		blank = Assets.blank;
		warning1 = Assets.warning1;
		warning2 = Assets.warning2;
		warning3 = Assets.warning3;
		
		//menu
		resume = Assets.resume;
		menu_big = Assets.menu_big;
		hpRed = Assets.hpRed;
		hpYellow = Assets.hpYellow;
		hpGreen = Assets.hpGreen;
		hpGreen4 = Assets.hpGreen4;
		hpGreen5 = Assets.hpGreen5;
		hpGreen6 = Assets.hpGreen6;
		music = Assets.music;
		music_mute = Assets.music_mute;
		sound = Assets.sound;
		sound_mute = Assets.sound_mute;
		
		asteroidPic1 = Assets.asteroid1;
		asteroidPic2 = Assets.asteroid2;
		asteroidPic3 = Assets.asteroid3;
		asteroidPic4 = Assets.asteroid4;
		asteroidPic5 = Assets.asteroid5;
		asteroidPic6 = Assets.asteroid6;
		asteroidPic7 = Assets.asteroid7;
		asteroidPic1medium = Assets.asteroid1medium;
		asteroidPic2medium = Assets.asteroid2medium;
		asteroidPic3medium = Assets.asteroid3medium;
		asteroidPic4medium = Assets.asteroid4medium;
		asteroidPic5medium = Assets.asteroid5medium;
		asteroidPic6medium = Assets.asteroid6medium;
		asteroidPic7medium = Assets.asteroid7medium;
		asteroidPic1large = Assets.asteroid1large;
		asteroidPic2large = Assets.asteroid2large;
		asteroidPic3large = Assets.asteroid3large;
		asteroidPic4large = Assets.asteroid4large;
		asteroidPic5large = Assets.asteroid5large;
		asteroidPic6large = Assets.asteroid6large;
		asteroidPic7large = Assets.asteroid7large;
		
		// power ups
		invincible_pic = Assets.invincibility_powerup;
		spreadShot_pic = Assets.spread_powerup;
		rapidFire_pic = Assets.rapid_powerup;
		wave_pic = Assets.wave_powerup;
		
		asteroidanim = new Animation();
		asteroidanim.addFrame(asteroidPic1, 20);
		asteroidanim.addFrame(asteroidPic2, 20);
		asteroidanim.addFrame(asteroidPic3, 20);
		asteroidanim.addFrame(asteroidPic4, 20);
		asteroidanim.addFrame(asteroidPic5, 20);
		asteroidanim.addFrame(asteroidPic6, 20);
		asteroidanim.addFrame(asteroidPic7, 20);
		
		asteroidanimmedium = new Animation();
		asteroidanimmedium.addFrame(asteroidPic1medium, 20);
		asteroidanimmedium.addFrame(asteroidPic2medium, 20);
		asteroidanimmedium.addFrame(asteroidPic3medium, 20);
		asteroidanimmedium.addFrame(asteroidPic4medium, 20);
		asteroidanimmedium.addFrame(asteroidPic5medium, 20);
		asteroidanimmedium.addFrame(asteroidPic6medium, 20);
		asteroidanimmedium.addFrame(asteroidPic7medium, 20);
		
		asteroidanimlarge = new Animation();
		asteroidanimlarge.addFrame(asteroidPic1large, 20);
		asteroidanimlarge.addFrame(asteroidPic2large, 20);
		asteroidanimlarge.addFrame(asteroidPic3large, 20);
		asteroidanimlarge.addFrame(asteroidPic4large, 20);
		asteroidanimlarge.addFrame(asteroidPic5large, 20);
		asteroidanimlarge.addFrame(asteroidPic6large, 20);
		asteroidanimlarge.addFrame(asteroidPic7large, 20);
		
		bonusanim = new Animation();
		bonusanim.addFrame(enemy_points1, 20);
		bonusanim.addFrame(enemy_points2, 20);
		bonusanim.addFrame(enemy_points3, 20);
		
		enemy_v_anim = new Animation();
		enemy_v_anim.addFrame(enemy_v1, 20);
		enemy_v_anim.addFrame(enemy_v2, 20);
		
		enemy_v_damaged1_anim = new Animation();
		enemy_v_damaged1_anim.addFrame(enemy_v1damage1, 20);
		enemy_v_damaged1_anim.addFrame(enemy_v2damage1, 20);
		
		enemy_v_damaged2_anim = new Animation();
		enemy_v_damaged2_anim.addFrame(enemy_v1damage2, 20);
		enemy_v_damaged2_anim.addFrame(enemy_v2damage2, 20);
		
		boomanim = new Animation();
		boomanim.addFrame(boom1, 20);
		boomanim.addFrame(boom2, 20);
		boomanim.addFrame(boom3, 20);
		boomanim.addFrame(boom4, 20);
		boomanim.addFrame(boom5, 20);
		boomanim.addFrame(blank, 40);
		
		explanim = new Animation();
		explanim.addFrame(expl1, 20);
		explanim.addFrame(expl2, 20);
		explanim.addFrame(expl3, 20);
		explanim.addFrame(expl4, 40);
		explanim.addFrame(blank, 40);
		
		warninganim = new Animation();
		warninganim.addFrame(warning1, 20);
		warninganim.addFrame(warning2, 20);
		warninganim.addFrame(warning3, 20);
		
		waveanim = new Animation();
		waveanim.addFrame(Assets.wave_bullet, 20);
		waveanim.addFrame(Assets.wave_bullet2, 20);
		waveanim.addFrame(Assets.wave_bullet3, 20);
		waveanim.addFrame(Assets.wave_bullet4, 20);
		waveanim.addFrame(Assets.wave_bullet5, 12);
		
		boss1green_anim = new Animation();
		boss1green_anim.addFrame(boss1green1, 20);
		boss1green_anim.addFrame(boss1green2, 20);
		boss1green_anim.addFrame(boss1green3, 20);
		boss1green_anim.addFrame(boss1green2, 20);
		boss1green_anim.addFrame(boss1green1, 20);
		
		boss1yellow_anim = new Animation();
		boss1yellow_anim.addFrame(boss1yellow1, 20);
		boss1yellow_anim.addFrame(boss1yellow2, 20);
		boss1yellow_anim.addFrame(boss1yellow3, 20);
		boss1yellow_anim.addFrame(boss1yellow2, 20);
		boss1yellow_anim.addFrame(boss1yellow1, 20);
		
		boss1red_anim = new Animation();
		boss1red_anim.addFrame(boss1red1, 20);
		boss1red_anim.addFrame(boss1red2, 20);
		boss1red_anim.addFrame(boss1red3, 20);
		boss1red_anim.addFrame(boss1red2, 20);
		boss1red_anim.addFrame(boss1red1, 20);
		
		boss2_anim = new Animation();
		boss2_anim.addFrame(boss2frame1, 20);
		boss2_anim.addFrame(boss2frame2, 20);
		boss2_anim.addFrame(boss2frame3, 20);
		boss2_anim.addFrame(boss2frame4, 20);
		
		//background animations
		colorback1 = new Background(0, -800);
		colormiddle1 = new Background(0, -800);
		colorfront1 = new Background(0, -800);
		colorback2 = new Background(0, 0);
		colormiddle2 = new Background(0, 0);
		colorfront2 = new Background(0, 0);
		blackback1 = new Background(0, -800);
		blackmiddle1 = new Background(0, -800);
		blackfront1 = new Background(0, -800);
		blackback2 = new Background(0, 0);
		blackmiddle2 = new Background(0, 0);
		blackfront2 = new Background(0, 0);

		//background speeds
		colorback1.setSpeedY(8);
		colorback2.setSpeedY(8);
		blackback1.setSpeedY(8);
		blackback2.setSpeedY(8);
		colormiddle1.setSpeedY(16);
		colormiddle2.setSpeedY(16);
		blackmiddle1.setSpeedY(16);
		blackmiddle2.setSpeedY(16);
		colorfront1.setSpeedY(32);
		colorfront2.setSpeedY(32);
		blackfront1.setSpeedY(32);
		blackfront2.setSpeedY(32);

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);
		
		paint3 = new Paint();
		paint3.setTextSize(30);
		paint3.setTextAlign(Paint.Align.LEFT);
		paint3.setAntiAlias(true);
		paint3.setColor(Color.WHITE);
		
		// power ups
		invincible = new PowerUp(230, -100, 0);
		spreadShot = new PowerUp(230, -100, 1);
		rapidFire = new PowerUp(230, -100, 2);
		wave = new PowerUp(230, -100, 3);

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!
		
		if(mutemusic == true){
			Assets.theme.pause();
			Assets.cashew_intro.pause();
			Assets.cashew_theme.pause();
			Assets.invincibility_theme.pause();
			Assets.bonus_enemy.pause();
			Assets.boss_theme.pause();
		}
		if(level < 100 && mutemusic == false){
			Assets.theme.play();
		}

		if (touchEvents.size() > 0){
			start = true;
			state = GameState.Running;
		}
	}
	//way incomplete
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		
		if(level != 25 && level != 50){
			Assets.boss_theme.pause();
		}
		if(level != 25 && level != 50 && mutemusic == false && Assets.invincibility_theme.isPlaying() == false){
			Assets.theme.play();
		}
		if((level == 25 || level == 50) && mutemusic == false && Assets.invincibility_theme.isPlaying() == false){
			Assets.boss_theme.play();
		}
		
		if(level == 25 && boss1easy_appear == false){
			boss1easy.updateY();
			enemy_v.explode();
			enemy.explode();
			enemy1.explode();
			enemy2.explode();
			enemy3.explode();
			enemy4.explode();
			enemy5.explode();
			special_enemy.explode();
			enemyp.explode();
			enemy_v.setCenterY(-160);
			splitDie(enemy, -600, 600);
			splitDie(enemy1, -600, 600);
			splitDie(enemy2, -600, 600);
			splitDie(enemy3, -600, 600);
			splitDie(enemy4, -600, 600);
			splitDie(enemy5, -600, 600);
			splitDie(special_enemy, -600, 600);
			splitDie(enemyp, -55, 500);
			Assets.theme.pause();
			if(!Assets.invincibility_theme.isPlaying() && mutemusic == false){
				Assets.boss_theme.play();
			}
		}
		
		if(level == 50 && boss2easy_appear == false){
			boss2easy.updateY();
			enemy_v.explode();
			enemy.explode();
			enemy1.explode();
			enemy2.explode();
			enemy3.explode();
			enemy4.explode();
			enemy5.explode();
			special_enemy.explode();
			enemyp.explode();
			enemy_v.setCenterY(-160);
			splitDie(enemy, -600, 600);
			splitDie(enemy1, -600, 600);
			splitDie(enemy2, -600, 600);
			splitDie(enemy3, -600, 600);
			splitDie(enemy4, -600, 600);
			splitDie(enemy5, -600, 600);
			splitDie(special_enemy, -600, 600);
			splitDie(enemyp, -55, 500);
			Assets.theme.pause();
			if(!Assets.invincibility_theme.isPlaying() && mutemusic == false){
				Assets.boss_theme.play();
			}
		}
		
		if(boss1easy.getCenterY() >= 150 && boss1easy_appear == false){
			boss1easy_appear = true;
		}
		if(boss2easy.getCenterY() >= 150 && boss2easy_appear == false){
			boss2easy_appear = true;
		}
		
		if(boss1easy.getCenterY() >= 150 && System.currentTimeMillis() >= boss1ShootTime + 480 && boss1easy.getHealth() > 2){
				boss1ShootTime = System.currentTimeMillis();
				boss1easy.shootLeft(0, 8);
				boss1easy.shootLeft(4, 4);
				boss1easy.shootLeft(-4, 4);
				boss1easy.shootLeft(8, 0);
				boss1easy.shootLeft(-8, 0);
				boss1easy.shootRight(0, 8);
				boss1easy.shootRight(4, 4);
				boss1easy.shootRight(-4, 4);
				boss1easy.shootRight(8, 0);
				boss1easy.shootRight(-8, 0);
				Assets.enemy_shot.play(getShotvolume());
		}
		if(boss1easy.getHealth() == 6 || boss1easy.getHealth() == 5){
			boss1easy.updateX();
			if(boss1easy.getCenterX() >= 480 || boss1easy.getCenterX() <= 0){
				boss1easy.setSpeedX(-boss1easy.getSpeedX());
			}
		}
		if(boss1easy.getHealth() == 4 || boss1easy.getHealth() == 3){
			boss1easy.update();
			if(boss1easy.getCenterX() >= 480 || boss1easy.getCenterX() <= 0){
				boss1easy.setSpeedX(-boss1easy.getSpeedX());
			}
			if(boss1easy.getCenterY() >= 800 || boss1easy.getCenterY() <= 60){
				boss1easy.setSpeedY(-boss1easy.getSpeedY());
			}
		}
		if(boss1easy.getHealth() == 2 || boss1easy.getHealth() == 1){
			boss1easy.update();
			if(boss1easy.getCenterX() >= 480 || boss1easy.getCenterX() <= 0){
				boss1easy.setSpeedX(-boss1easy.getSpeedX());
				if(boss1easy.getSpeedY() > 0){
					boss1easy.setSpeedY(8);
				}
				if(boss1easy.getSpeedY() < 0){
					boss1easy.setSpeedY(-8);
				}
			}
			if(boss1easy.getCenterY() >= 800){
				boss1easy.setSpeedY(-8);
			}
			if(boss1easy.getCenterY() <= 60){
				boss1easy.setSpeedY(8);
			}
		}
		if(boss1easy.getHealth() <= 0){
			boss1easy.explode();
			score = score + 1500;
			Assets.enemy_death.play(getDeathvolume());
			boss1easy.setCenterY(-160);
			boss1easy.setHealth(4);
			livesLeft = 6;
		}
		
		if(boss2easy.getHealth() <= 0){
			boss2easy.explode();
			score = score + 1500;
			Assets.enemy_death.play(getDeathvolume());
			boss2easy.setCenterY(-160);
			boss2easy.setHealth(4);
			livesLeft = 6;
		}
		
		updatePowerUps();		
		
		if(mutesound == true){
			setShotvolume(0.00f);
			setDeathvolume(0.00f);
			setLoudvolume(0.00f);
		}else{
			setShotvolume(0.25f);
			setDeathvolume(0.75f);
			setLoudvolume(1.99f);
		}
		
		if(level >= 100 && mutemusic == false){
			Assets.theme.pause();
			Assets.boss_theme.pause();
			Assets.cashew_intro.play();
			if(Assets.cashew_intro.isStopped()){
				Assets.cashew_theme.setLooping(true);
				Assets.cashew_theme.play();
			}
		}
		
		if(boss2easy.getCenterY() >= 150 && System.currentTimeMillis() >= boss1ShootTime + 480 && boss2easy.getHealth() > 2){
			boss1ShootTime = System.currentTimeMillis();
			boss2easy.shootLeft(0, 8);
			boss2easy.shootLeft(4, 4);
			boss2easy.shootLeft(-4, 4);
			boss2easy.shootLeft(8, 0);
			boss2easy.shootLeft(-8, 0);
			boss2easy.shootRight(0, 8);
			boss2easy.shootRight(4, 4);
			boss2easy.shootRight(-4, 4);
			boss2easy.shootRight(8, 0);
			boss2easy.shootRight(-8, 0);
			Assets.enemy_shot.play(getShotvolume());
		}
		
		//tracks screen presses for ship movement
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_UP) {
				auto = false;
				if(spread == true){
					player.shoot(-2);
					player.shoot();
					player.shoot(2);
				}
				
				Assets.player_shot.play(getShotvolume());
				
				if (inBounds(event, 0, 70, 50, 50)) {//changed (original: 0,0)
					pause();
				}

			}
			
			//auto fire
			if (event.type == TouchEvent.TOUCH_DOWN || 
					event.type == TouchEvent.TOUCH_DRAGGED) {
				if (!inBounds(event, 0, 70, 50, 50)) {//change for new pause button
					auto = true;
					int x = event.x;
					int y = event.y;
				
					player.setCenterX(x + 65);
					player.setCenterY(y - 7);
					
					if(level > 1000){
						player.shoot();
					}
				}
			}
			
			//tap to shoot
			if (event.type == TouchEvent.TOUCH_DOWN ) {
				player.shoot();
			}
		}
		//auto fire
		if(auto == true){
			if(rapid == true){
				player.shoot(); 
			}
		
			if(System.currentTimeMillis() >= shootTime + 350){
				shootTime = System.currentTimeMillis();
				
				if(spread == true){
					player.shoot(-2);
					player.shoot();
					player.shoot(2);
				} else {
					player.shoot();
				}
			}
		}
		
		enemyFiring();
		
		//enemy_v_health
		if(enemy_v_health <= 0){
			score = score + 500;
			Assets.enemy_death.play(getDeathvolume());
			enemy_v.setCenterY(-160);
		}
		if(enemy_v.getCenterY() < 0){
			enemy_v_health = 3;
		}
		
		int n = level % 100;
		
		if(level != 25){
			enemyLevels(n);
		}
		if(level >= 100){
			enemy_color = (int)(Math.random()*5);
			enemy1_color = (int)(Math.random()*5);
			enemy2_color = (int)(Math.random()*5);
			enemy3_color = (int)(Math.random()*5);
			enemy4_color = (int)(Math.random()*5);
			enemy5_color = (int)(Math.random()*5);
		}
		
		if(enemyp.getCenterX() >= 0 && enemyp.getCenterX() <= 480 && mutemusic == false)
			Assets.bonus_enemy.play();
		else
			Assets.bonus_enemy.pause();
		
		//Code for points enemy
		if(score > 100 && (enemypLevels() == true && level != 25)){
			if(enemyp.getCenterX() >= 500 && enemyp.getSpeedX() > 0)
				enemyp.die(-55);
			if(enemyp.getCenterX() <= -40 && enemyp.getSpeedX() < 0)
				enemyp.die(500);
			if(enemyp.getCenterX() <= 20)
				enemyp.setSpeedX(5);
			if(enemyp.getCenterX() >= 480)
				enemyp.setSpeedX(-5);
			if(enemyp.getCenterX() != -1000)
				enemyp.update();
		}
		else if(enemyp.getCenterX() >= 500 || enemyp.getCenterX() <= -40){
			splitDie(enemyp, -55, 500);
		}
		else if (enemyp.getCenterX() <= 500 && enemyp.getCenterX() >= -20){
			enemyp.update();
		}
		
		//asteroid updates
		if(difficulty > 0){
			asteroid1.update(asteroidsActive);
			asteroid2.update(asteroidsActive);
			asteroid1medium.update(asteroidsActive);
			asteroid2medium.update(asteroidsActive);
			asteroid1large.update(asteroidsActive);
			asteroid2large.update(asteroidsActive);
		}
		if(difficulty > 1){
			asteroid3.update(asteroidsActive);
			asteroid3medium.update(asteroidsActive);
			asteroid3large.update(asteroidsActive);
		}
		if(difficulty > 2){
			asteroid4.update(asteroidsActive);
			asteroid5.update(asteroidsActive);
			asteroid4medium.update(asteroidsActive);
			asteroid5medium.update(asteroidsActive);
			asteroid4large.update(asteroidsActive);
			asteroid5large.update(asteroidsActive);
		
		}
		
		if(asteroid1.getY() > 900){
			asteroid1.setY();
			asteroid1.setX();
		}
		if(asteroid2.getY() > 900){
			asteroid2.setY();
			asteroid2.setX();
		}
		
		if(asteroid3.getY() > 900){
			asteroid3.setY();
			asteroid3.setX();
		}
		if(asteroid4.getY() > 900){
			asteroid4.setY();
			asteroid4.setX();
		}
		if(asteroid5.getY() > 900){
			asteroid5.setY();
			asteroid5.setX();
		}
		if(asteroid1medium.getY() > 900){
			asteroid1medium.setY();
			asteroid1medium.setX();
		}
		if(asteroid2medium.getY() > 900){
			asteroid2medium.setY();
			asteroid2medium.setX();
		}
		if(asteroid3medium.getY() > 900){
			asteroid3medium.setY();
			asteroid3medium.setX();
			}
		if(asteroid4medium.getY() > 900){
			asteroid4medium.setY();
			asteroid4medium.setX();
		}
		if(asteroid5medium.getY() > 900){
			asteroid5medium.setY();
			asteroid5medium.setX();
		}
		if(asteroid1large.getY() > 900){
			asteroid1large.setY();
			asteroid1large.setX();
		}
		if(asteroid2large.getY() > 900){
			asteroid2large.setY();
			asteroid2large.setX();
		}
		if(asteroid3large.getY() > 900){
			asteroid3large.setY();
			asteroid3large.setX();
		}
		if(asteroid4large.getY() > 900){
			asteroid4large.setY();
			asteroid4large.setX();
		}
		if(asteroid5large.getY() > 900){
			asteroid5large.setY();
			asteroid5large.setX();
		}
		
		// check timer
		if(System.currentTimeMillis() > asteroidsTime + 20000){
			asteroidsActive = false;
		}
		
		
		//code for special enemy
		if(level < 100 && level%15 == 0 && level != 25){
			if(difficulty != 0){
				if(special_enemy.getCenterX() >= 478 && special_enemy.getCenterX() <= 481)
					special_enemy.setSpeedX(-3);
				if(special_enemy.getCenterX() == -160)
					special_enemy.setSpeedX(3);
				if(special_enemy.getCenterX() != -1000)
					special_enemy.update();
			}
			if(difficulty == 1){
				if(special_enemy.getCenterX() >= 67 && special_enemy.getCenterX() <= 70){
					special_enemy.shoot();
					Assets.ultimate_power.play(getShotvolume());
				}
				if(special_enemy.getCenterX() >= 342 && special_enemy.getCenterX() <= 345){
					special_enemy.shoot();
					Assets.ultimate_power.play(getShotvolume());
				}
				if(special_enemy.getCenterX() >= 139 && special_enemy.getCenterX() <= 141){
					special_enemy.shoot();
					Assets.ultimate_power.play(getShotvolume());
				}
				if(special_enemy.getCenterX() >= 402 && special_enemy.getCenterX() <= 405){
					special_enemy.shoot();
					Assets.ultimate_power.play(getShotvolume());
				}
			}
			if(difficulty == 2){
				if(special_enemy.getCenterX() > 0 && special_enemy.getCenterX() < 480){
					if(System.currentTimeMillis() >= enemySpecialShootTime + 480){
						enemySpecialShootTime = System.currentTimeMillis();
						special_enemy.shoot();
						Assets.ultimate_power.play(getShotvolume());
					}
				}
			}
			if(difficulty == 3){
				if(special_enemy.getCenterX() > 0 && special_enemy.getCenterX() < 480){
					if(System.currentTimeMillis() >= enemySpecialShootTime + 240){
						enemySpecialShootTime = System.currentTimeMillis();
						special_enemy.shoot();
						Assets.ultimate_power.play(getShotvolume());
					}
				}
			}
		}
		//marked
		else if(level < 100 && level%15 == 0 && special_enemy.getCenterX() != -160 && difficulty != 0 && level != 25){
			special_enemy.setSpeedX(-3);
			special_enemy.update();
			if(special_enemy.getCenterX() == -160){
				special_enemy.setSpeedX(0);
			}
			if(difficulty == 1){
				if(special_enemy.getCenterX() >= 67 && special_enemy.getCenterX() <= 70){
					special_enemy.shoot();
					Assets.ultimate_power.play(getShotvolume());
				}
				if(special_enemy.getCenterX() >= 342 && special_enemy.getCenterX() <= 345){
					special_enemy.shoot();
					Assets.ultimate_power.play(getShotvolume());
				}
				if(special_enemy.getCenterX() >= 139 && special_enemy.getCenterX() <= 141){
					special_enemy.shoot();
					Assets.ultimate_power.play(getShotvolume());
				}
				if(special_enemy.getCenterX() >= 402 && special_enemy.getCenterX() <= 405){
					special_enemy.shoot();
					Assets.ultimate_power.play(getShotvolume());
				}
			}
			if(difficulty == 2){
				if(special_enemy.getCenterX() > 0 && special_enemy.getCenterX() < 480){
					if(System.currentTimeMillis() >= enemyShootTime + 480){
						enemyShootTime = System.currentTimeMillis();
						special_enemy.shoot();
						Assets.ultimate_power.play(getShotvolume());
					}
				}
			}
			if(difficulty == 3){
				if(special_enemy.getCenterX() > 0 && special_enemy.getCenterX() < 480){
					if(System.currentTimeMillis() >= enemyShootTime + 240){
						enemyShootTime = System.currentTimeMillis();
						special_enemy.shoot();
						Assets.ultimate_power.play(getShotvolume());
					}
				}
			}
		}
		if(level % 15 != 0 && special_enemy.getCenterX() >= -10 && special_enemy.getCenterX() <= 500){
			special_enemy.update();
		}
		
		if(System.currentTimeMillis() >= armadaTime + 2000 && armada1.getCenterY() > 109 && armada2.getCenterY() > 109 && armada3.getCenterY()
				> 109 && armada4.getCenterY() > 109 && armada5.getCenterY() > 109 && 
				armada6.getCenterY() > 109 && armada7.getCenterY() > 109 && armada8.getCenterY() 
				> 109 && armada9.getCenterY() > 109 && armada10.getCenterY() > 109 && 
				armada11.getCenterY() > 109 && armada12.getCenterY() > 109){
			armada1.armadaShoot();
			Assets.ultimate_power.play(getShotvolume());
			armadaTime = System.currentTimeMillis();
		}
		
		if(level % 8 == 0 && level != 25){
			if(difficulty == 2){
				enemy_v.updateV(5);
			}
			if(difficulty == 3){
				enemy_v.updateV(10);
			}
		}
		
		// cashew's armada, move all enemies down
		else if(level >= 100 && (level % 5 == 0 || level % 5 == 1)){
			enemy.setCenterY(230);
			enemy1.setCenterY(280);
			enemy2.setCenterY(360);
			enemy3.setCenterY(230);
			enemy4.setCenterY(280);
			enemy5.setCenterY(360);
			enemyp.setCenterY(380);
			special_enemy.setCenterY(400);
			
			armada1.updateArmada(1);
			armada2.updateArmada(1);
			armada3.updateArmada(1);
			armada4.updateArmada(1);
			armada5.updateArmada(1);
			armada6.updateArmada(1);
			armada7.updateArmada(1);
			armada8.updateArmada(1);
			armada9.updateArmada(1);
			armada10.updateArmada(1);
			armada11.updateArmada(1);
			armada12.updateArmada(1);
			cashew_enemy.updateCashew(1);
		}
		else if(level >= 100 && level % 25 != 0){
			armada1.updateArmada(-1);
			armada2.updateArmada(-1);
			armada3.updateArmada(-1);
			armada4.updateArmada(-1);
			armada5.updateArmada(-1);
			armada6.updateArmada(-1);
			armada7.updateArmada(-1);
			armada8.updateArmada(-1);
			armada9.updateArmada(-1);
			armada10.updateArmada(-1);
			armada11.updateArmada(-1);
			armada12.updateArmada(-1);
			cashew_enemy.updateCashew(-1);
		}
		
		ArrayList<?> enemy_projectiles = enemy.getProjectiles();
		ArrayList<?> enemy1_projectiles = enemy1.getProjectiles();
		ArrayList<?> enemy2_projectiles = enemy2.getProjectiles();
		ArrayList<?> enemy3_projectiles = enemy3.getProjectiles();
		ArrayList<?> enemy4_projectiles = enemy4.getProjectiles();
		ArrayList<?> enemy5_projectiles = enemy5.getProjectiles();
		ArrayList<?> enemy_special_projectiles = special_enemy.getProjectiles();
		ArrayList<?> armada_projectiles = armada1.getProjectiles();
		ArrayList<?> enemy_v_projectiles = enemy_v.getProjectiles();
		ArrayList<?> boss1easy_projectiles = boss1easy.getProjectiles();
		ArrayList<?> boss2easy_projectiles = boss2easy.getProjectiles();
		ArrayList<?> player_explosions = player.getExplosions();
		ArrayList<?> enemy_explosions = enemy.getExplosions();
		ArrayList<?> enemy1_explosions = enemy1.getExplosions();
		ArrayList<?> enemy2_explosions = enemy2.getExplosions();
		ArrayList<?> enemy3_explosions = enemy3.getExplosions();
		ArrayList<?> enemy4_explosions = enemy4.getExplosions();
		ArrayList<?> enemy5_explosions = enemy5.getExplosions();
		ArrayList<?> special_enemy_explosions = special_enemy.getExplosions();
		ArrayList<?> enemyp_explosions = enemyp.getExplosions();
		ArrayList<?> enemy_v_explosions = enemy_v.getExplosions();
		ArrayList<?> boss1easy_explosions = boss1easy.getExplosions();
		ArrayList<?> boss2easy_explosions = boss2easy.getExplosions();
		
		animate();
		
		ArrayList<Projectile> waves = player.getWaves();
		
		//code for wave
		for (int i = 0; i < waves.size(); i++) {
			Projectile w = waves.get(i);
			if(w.isVisible()){
				w.update();
				if(waveanim.getImage() != Assets.wave_bullet5){
					waveanim.update(12);
				}
			}else{
				if(waveanim.getImage() != Assets.wave_bullet){
					waveanim.update(1);
				}
				if(waveanim.getImage() == Assets.wave_bullet){
					waves.remove(i);
				}
			}
		}
		
		//code for shots
		ArrayList<?> projectiles = player.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.getX() >= enemy.getCenterX() - 17 && p.getX() <= enemy.getCenterX() + 17
					&& p.getY() >= enemy.getCenterY() - 10 && p.getY() <= enemy.getCenterY() + 10){
				score += 100*modified;
				enemy.explode();
				splitDie(enemy, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
				p.setVisible(false);
			}
			if (p.getX() >= enemy1.getCenterX() - 17 && p.getX() <= enemy1.getCenterX() + 17
					&& p.getY() >= enemy1.getCenterY() - 10 && p.getY() <= enemy1.getCenterY() + 10){
				score += 100*modified;
				enemy1.explode();
				splitDie(enemy1, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
				p.setVisible(false);
			}
			if (p.getX() >= enemy2.getCenterX() - 17 && p.getX() <= enemy2.getCenterX() + 17
					&& p.getY() >= enemy2.getCenterY() - 10 && p.getY() <= enemy2.getCenterY() + 10){
				score += 100*modified;
				enemy2.explode();
				splitDie(enemy2, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
				p.setVisible(false);
			}
			if (p.getX() >= enemy3.getCenterX() - 17 && p.getX() <= enemy3.getCenterX() + 17
					&& p.getY() >= enemy3.getCenterY() - 10 && p.getY() <= enemy3.getCenterY() + 10){
				score += 150*modified;
				enemy3.explode();
				splitDie(enemy3, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
				p.setVisible(false);
			}
			if (p.getX() >= enemy4.getCenterX() - 17 && p.getX() <= enemy4.getCenterX() + 17
					&& p.getY() >= enemy4.getCenterY() - 10 && p.getY() <= enemy4.getCenterY() + 10){
				score += 150*modified;
				enemy4.explode();
				splitDie(enemy4, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
				p.setVisible(false);
			}
			if (p.getX() >= enemy5.getCenterX() - 17 && p.getX() <= enemy5.getCenterX() + 17
					&& p.getY() >= enemy5.getCenterY() - 10 && p.getY() <= enemy5.getCenterY() + 10){
				score += 150*modified;
				enemy5.explode();
				splitDie(enemy5, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
				p.setVisible(false);
			}
			//Code for points enemy
			if (p.getX() >= enemyp.getCenterX() - 17 && p.getX() <= enemyp.getCenterX() + 17
					&& p.getY() >= enemyp.getCenterY() - 10 && p.getY() <= enemyp.getCenterY() + 10){
				score += 500*modified;
				enemyp.explode();
				splitDie(enemyp, -55, 500);
				Assets.enemy_death.play(getDeathvolume());
				p.setVisible(false);
			}
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
			//code for special enemy
			if (p.getX() >= special_enemy.getCenterX() - 20 && p.getX() <= special_enemy.getCenterX() + 20
					&& p.getY() >= special_enemy.getCenterY() - 20 && p.getY() <= special_enemy.getCenterY() + 20){
				Assets.enemy_death.play(getDeathvolume());
				p.setVisible(false);
			}
			
			//wave hit box for special enemy
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (w.getY() >= special_enemy.getCenterY() - 20 && w.getY() <= special_enemy.getCenterY() + 20 &&
						special_enemy.getCenterX() > 10 && special_enemy.getCenterX() < 470 &&
						special_enemy.getCenterY() > 0 && special_enemy.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					special_enemy.explode();
					splitDie(special_enemy, -600, 600);
					p.setVisible(false);
				}
			}
			
			//wave hit box for enemy_v
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (w.getY() >= enemy_v.getCenterY() - 20 && w.getY() <= enemy_v.getCenterY() + 20 &&
						enemy_v.getCenterX() > 10 && enemy_v.getCenterX() < 470 &&
						enemy_v.getCenterY() > 0 && enemy_v.getCenterY() < 800){
					if(w.getY() > 0 && enemyhit == false){
						enemy_v.explode();
						Assets.enemy_death.play(getDeathvolume());
						enemy_v_health = enemy_v_health - 1;
						enemyFrameTime = System.currentTimeMillis();
						enemyhit = true;
					}
				}
				
			}
			
			//enemy_v - bullet collisions
			if (p.getX() >= enemy_v.getCenterX() - 20 && p.getX() <= enemy_v.getCenterX() + 20
					&& p.getY() >= enemy_v.getCenterY() - 20 && p.getY() <= enemy_v.getCenterY() + 20){
				if(p.getY() > 0 && enemyhit == false){
					Assets.enemy_death.play(getDeathvolume());
					enemy_v.explode();
					enemy_v_health = enemy_v_health - 1;
					enemyFrameTime = System.currentTimeMillis();
					enemyhit = true;
					p.setVisible(false);
				}
			}
			
			//wave hit for boss1easy
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (w.getY() >= boss1easy.getCenterY() - 32 && w.getY() <= boss1easy.getCenterY() + 32 &&
						boss1easy.getCenterX() > 10 && boss1easy.getCenterX() < 470 &&
						boss1easy.getCenterY() > 0 && boss1easy.getCenterY() < 800){
					if(w.getY() > 0 && bosshit == false && boss1easy_appear == true){
						Assets.enemy_death.play(getDeathvolume());
						boss1easy.loseHealth();
						boss1FrameTime = System.currentTimeMillis();
						bosshit = true;
					}
				}
				
			}
			
			//bosses hit boxes
			if (p.getX() >= boss1easy.getCenterX() - 32 && p.getX() <= boss1easy.getCenterX() + 32
					&& p.getY() >= boss1easy.getCenterY() - 32 && p.getY() <= boss1easy.getCenterY() + 32){
				if(p.getY() > 0 && bosshit == false && boss1easy_appear == true){
					Assets.enemy_death.play(getDeathvolume());
					boss1easy.loseHealth();
					boss1FrameTime = System.currentTimeMillis();
					bosshit = true;
					p.setVisible(false);
				}
			}
			if (p.getX() >= boss2easy.getCenterX() - 32 && p.getX() <= boss2easy.getCenterX() + 32
					&& p.getY() >= boss2easy.getCenterY() - 32 && p.getY() <= boss2easy.getCenterY() + 32){
				if(p.getY() > 0 && bosshit == false && boss2easy_appear == true){
					Assets.enemy_death.play(getDeathvolume());
					boss2easy.loseHealth();
					boss1FrameTime = System.currentTimeMillis();
					bosshit = true;
					p.setVisible(false);
				}
			}
			
			//wave hit box for armada
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (w.getY() >= armada1.getCenterY() - 20 && w.getY() <= armada1.getCenterY() + 20 &&
						armada1.getCenterX() > 0 && armada1.getCenterX() < 480 &&
						armada1.getCenterY() > 0 && armada1.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada1.explode();
					armada1.dieArmada(-140);
				}
				if (w.getY() >= armada2.getCenterY() - 20 && w.getY() <= armada2.getCenterY() + 20 &&
						armada2.getCenterX() > 0 && armada2.getCenterX() < 480 &&
						armada2.getCenterY() > 0 && armada2.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada2.explode();
					armada2.dieArmada(-140);
				}
				if (w.getY() >= armada3.getCenterY() - 20 && w.getY() <= armada3.getCenterY() + 20 &&
						armada3.getCenterX() > 0 && armada3.getCenterX() < 480 &&
						armada3.getCenterY() > 0 && armada3.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada3.explode();
					armada3.dieArmada(-140);
				}
				if (w.getY() >= armada4.getCenterY() - 20 && w.getY() <= armada4.getCenterY() + 20 &&
						armada4.getCenterX() > 0 && armada4.getCenterX() < 480 &&
						armada4.getCenterY() > 0 && armada4.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada4.explode();
					armada4.dieArmada(-140);
				}
				if (w.getY() >= armada5.getCenterY() - 20 && w.getY() <= armada5.getCenterY() + 20 &&
						armada5.getCenterX() > 0 && armada5.getCenterX() < 480 &&
						armada5.getCenterY() > 0 && armada5.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada5.explode();
					armada5.dieArmada(-140);
				}
				if (w.getY() >= armada6.getCenterY() - 20 && w.getY() <= armada6.getCenterY() + 20 &&
						armada6.getCenterX() > 0 && armada6.getCenterX() < 480 &&
						armada6.getCenterY() > 0 && armada6.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada6.explode();
					armada6.dieArmada(-140);
				}
				if (w.getY() >= armada7.getCenterY() - 20 && w.getY() <= armada7.getCenterY() + 20 &&
						armada7.getCenterX() > 0 && armada7.getCenterX() < 480 &&
						armada7.getCenterY() > 0 && armada7.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada7.explode();
					armada7.dieArmada(-140);
				}
				if (w.getY() >= armada8.getCenterY() - 20 && w.getY() <= armada8.getCenterY() + 20 &&
						armada8.getCenterX() > 0 && armada8.getCenterX() < 480 &&
						armada8.getCenterY() > 0 && armada8.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada8.explode();
					armada8.dieArmada(-140);
				}
				if (w.getY() >= armada9.getCenterY() - 20 && w.getY() <= armada9.getCenterY() + 20 &&
						armada9.getCenterX() > 0 && armada9.getCenterX() < 480 &&
						armada9.getCenterY() > 0 && armada9.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada9.explode();
					armada9.dieArmada(-140);
				}
				if (w.getY() >= armada10.getCenterY() - 20 && w.getY() <= armada10.getCenterY() + 20 &&
						armada10.getCenterX() > 0 && armada10.getCenterX() < 480 &&
						armada10.getCenterY() > 0 && armada10.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada10.explode();
					armada10.dieArmada(-140);
				}
				if (w.getY() >= armada11.getCenterY() - 20 && w.getY() <= armada11.getCenterY() + 20 &&
						armada11.getCenterX() > 0 && armada11.getCenterX() < 480 &&
						armada11.getCenterY() > 0 && armada11.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada11.explode();
					armada11.dieArmada(-140);
				}
				if (w.getY() >= armada12.getCenterY() - 20 && w.getY() <= armada12.getCenterY() + 20 &&
						armada12.getCenterX() > 0 && armada12.getCenterX() < 480 &&
						armada12.getCenterY() > 0 && armada12.getCenterY() < 800){
					Assets.enemy_death.play(getDeathvolume());
					score += 750*modified;
					armada12.explode();
					armada12.dieArmada(-140);
				}
			}
			
			// armada - bullet collisions
			if (p.getX() >= armada1.getCenterX() - 20 && p.getX() <= armada1.getCenterX() + 20
					&& p.getY() >= armada1.getCenterY() - 20 && p.getY() <= armada1.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada2.getCenterX() - 20 && p.getX() <= armada2.getCenterX() + 20
					&& p.getY() >= armada2.getCenterY() - 20 && p.getY() <= armada2.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada3.getCenterX() - 20 && p.getX() <= armada3.getCenterX() + 20
					&& p.getY() >= armada3.getCenterY() - 20 && p.getY() <= armada3.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada4.getCenterX() - 20 && p.getX() <= armada4.getCenterX() + 20
					&& p.getY() >= armada4.getCenterY() - 20 && p.getY() <= armada4.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada5.getCenterX() - 20 && p.getX() <= armada5.getCenterX() + 20
					&& p.getY() >= armada5.getCenterY() - 20 && p.getY() <= armada5.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada6.getCenterX() - 20 && p.getX() <= armada6.getCenterX() + 20
					&& p.getY() >= armada6.getCenterY() - 20 && p.getY() <= armada6.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada7.getCenterX() - 20 && p.getX() <= armada7.getCenterX() + 20
					&& p.getY() >= armada7.getCenterY() - 20 && p.getY() <= armada7.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada8.getCenterX() - 20 && p.getX() <= armada8.getCenterX() + 20
					&& p.getY() >= armada8.getCenterY() - 20 && p.getY() <= armada8.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada9.getCenterX() - 20 && p.getX() <= armada9.getCenterX() + 20
					&& p.getY() >= armada9.getCenterY() - 20 && p.getY() <= armada9.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada10.getCenterX() - 20 && p.getX() <= armada10.getCenterX() + 20
					&& p.getY() >= armada10.getCenterY() - 20 && p.getY() <= armada10.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada11.getCenterX() - 20 && p.getX() <= armada11.getCenterX() + 20
					&& p.getY() >= armada11.getCenterY() - 20 && p.getY() <= armada11.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			if (p.getX() >= armada12.getCenterX() - 20 && p.getX() <= armada12.getCenterX() + 20
					&& p.getY() >= armada12.getCenterY() - 20 && p.getY() <= armada12.getCenterY() + 20){
				if(p.getY() > 0){
					Assets.enemy_death.play(getDeathvolume());
				}
				p.setVisible(false);
			}
			
			if(p.getX() < 0 || p.getX() > 480 || p.getY() < 0 || p.getY() > 800){
				p.setVisible(false);
			}
			
		}
		
		for (int i = 0; i < enemy_v_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy_v_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 75 && p.getX() <= player.getCenterX() - 35
					&& p.getY() >= player.getCenterY() - 70 && p.getY() <= player.getCenterY() - 30){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
					score += 30;
				}
			}
			if (p.isVisible() == true) {
				p.updateAngle();
			} else {
				enemy_v_projectiles.remove(i);
			}
		}
		
		//explosions code
		for (int i = 0; i < player_explosions.size(); i++) {
			Explosion p = (Explosion) player_explosions.get(i);
			if(p.isVisible()){
				explanim.update(20);
				if(explanim.getImage() == Assets.blank){
					p.setVisible(false);
					player_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < enemy_explosions.size(); i++) {
			Explosion p = (Explosion) enemy_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					enemy_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < enemy1_explosions.size(); i++) {
			Explosion p = (Explosion) enemy1_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					enemy1_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < enemy2_explosions.size(); i++) {
			Explosion p = (Explosion) enemy2_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					enemy2_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < enemy3_explosions.size(); i++) {
			Explosion p = (Explosion) enemy3_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					enemy3_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < enemy4_explosions.size(); i++) {
			Explosion p = (Explosion) enemy4_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					enemy4_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < enemy5_explosions.size(); i++) {
			Explosion p = (Explosion) enemy5_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					enemy5_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < special_enemy_explosions.size(); i++) {
			Explosion p = (Explosion) special_enemy_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					special_enemy_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < enemyp_explosions.size(); i++) {
			Explosion p = (Explosion) enemyp_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					enemyp_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < enemy_v_explosions.size(); i++) {
			Explosion p = (Explosion) enemy_v_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					enemy_v_explosions.remove(i);
				}
			}
		}
		for (int i = 0; i < boss1easy_explosions.size(); i++) {
			Explosion p = (Explosion) boss1easy_explosions.get(i);
			if(p.isVisible()){
				boomanim.update(20);
				if(boomanim.getImage() == Assets.blank){
					p.setVisible(false);
					boss1easy_explosions.remove(i);
				}
			}
		}
		
		for (int i = 0; i < enemy_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 80 && p.getX() <= player.getCenterX() - 40
					&& p.getY() >= player.getCenterY() - 70 && p.getY() <= player.getCenterY() - 30){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
					score += 10;
				}
			}
			if (p.isVisible() == true) {
				p.update();
			} else {
				enemy_projectiles.remove(i);
			}
		}
		
		for (int i = 0; i < enemy1_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy1_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 80 && p.getX() <= player.getCenterX() - 40
					&& p.getY() >= player.getCenterY() - 70 && p.getY() <= player.getCenterY() - 30){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
					score += 10;
				}
			}
			if (p.isVisible() == true) {
				p.update();
			} else {
				enemy1_projectiles.remove(i);
			}
		}
		
		for (int i = 0; i < enemy2_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy2_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 80 && p.getX() <= player.getCenterX() - 40
					&& p.getY() >= player.getCenterY() - 70 && p.getY() <= player.getCenterY() - 30){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
					score += 10;
				}
			}
			if (p.isVisible() == true) {
				p.update();
			} else {
				enemy2_projectiles.remove(i);
			}
		}
		
		for (int i = 0; i < enemy3_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy3_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 80 && p.getX() <= player.getCenterX() - 40
					&& p.getY() >= player.getCenterY() - 70 && p.getY() <= player.getCenterY() - 30){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
					score += 20;
				}
			}
			if (p.isVisible() == true) {
				p.updateAngle();
			} else {
				enemy3_projectiles.remove(i);
			}
		}
		
		for (int i = 0; i < enemy4_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy4_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 80 && p.getX() <= player.getCenterX() - 40
					&& p.getY() >= player.getCenterY() - 70 && p.getY() <= player.getCenterY() - 30){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
					score += 20;
				}
			}
			if (p.isVisible() == true) {
				p.updateAngle();
			} else {
				enemy4_projectiles.remove(i);
			}
		}
		
		for (int i = 0; i < enemy5_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy5_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 80 && p.getX() <= player.getCenterX() - 40
					&& p.getY() >= player.getCenterY() - 70 && p.getY() <= player.getCenterY() - 30){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
					score += 20;
				}
			}
			if (p.isVisible() == true) {
				p.updateAngle();
			} else {
				enemy5_projectiles.remove(i);
			}
		}
		
		for (int i = 0; i < enemy_special_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy_special_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 85 && p.getX() <= player.getCenterX() - 35
					&& p.getY() >= player.getCenterY() - 75 && p.getY() <= player.getCenterY() - 25){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 2;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
					score += 40;
				}
			}
			if (p.isVisible() == true) {
				p.updateAngle();
			} else {
				enemy_special_projectiles.remove(i);
			}
		}
		
		for (int i = 0; i < armada_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) armada_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 85 && p.getX() <= player.getCenterX() - 35
					&& p.getY() >= player.getCenterY() - 75 && p.getY() <= player.getCenterY() - 25){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 2;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
					score += 40;
				}
			}
			if (p.isVisible() == true) {
				p.updateAngle();
			} else {
				armada_projectiles.remove(i);
			}
		}
		
		for (int i = 0; i < boss1easy_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) boss1easy_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 80 && p.getX() <= player.getCenterX() - 40
					&& p.getY() >= player.getCenterY() - 70 && p.getY() <= player.getCenterY() - 30){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
				}
			}
			if (p.isVisible() == true) {
				p.updateAngle();
			} else {
				boss1easy_projectiles.remove(i);
			}
		}
		
		for (int i = 0; i < boss2easy_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) boss2easy_projectiles.get(i);
			if(player.isReadyToFire() == true && gethit == true){
				if (p.getX() >= player.getCenterX() - 80 && p.getX() <= player.getCenterX() - 40
					&& p.getY() >= player.getCenterY() - 70 && p.getY() <= player.getCenterY() - 30){
					p.setVisible(false);
					Assets.hit.play(getDeathvolume());
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}
			}
			//wave hit box for bullets
			for (int j = 0; j < waves.size(); j++) {
				Projectile w = waves.get(j);
				if (p.getY() >= w.getY() - 70 && p.getY() <= w.getY() - 30 &&
						p.getX() > 0 && p.getX() < 480 &&
						p.getY() > 0 && p.getY() < 800){
					p.setVisible(false);
				}
			}
			if (p.isVisible() == true) {
				p.updateAngle();
			} else {
				boss2easy_projectiles.remove(i);
			}
		}
		
		if(level % 10 == 0 && livesLeft <= 6 && levelUp == true && livesLeft > 0){
			livesLeft += 1;
			levelUp = false;
		}
		if(level % 10 != 0){
			levelUp = true;
		}
		
		if(System.currentTimeMillis() > warningTime + 2000){
			warning = false;
		}

		if(score >= next_level){
			level += 1;
			asteroidChance = (int)(Math.random()*5 + 1);
			warningTime = System.currentTimeMillis();
			if(level >= 10 && asteroidChance == 1 && level > asteroidLevel+1 
					&& difficulty != 0 && level != 25)
			{
				if(mutesound == false){
					Assets.alert.play(1.99f);
				}
				// asteroids go here
				warning = true;
				asteroidLevel = level;
				asteroidsActive = true;
				asteroidsTime = System.currentTimeMillis();
			}
			next_level = score + (level * 100);
			if(next_level > score + 1500){
				next_level = score + 1500;
			}
		}
		
		
		if(livesLeft <= 0){
			/*Assets.theme.stop();
			Assets.theme1.stop();
			Assets.theme2.stop();
			Assets.theme3.stop();
			Assets.cashew_theme.stop();*/
			player.setReadyToFire(false);
			
			if(System.currentTimeMillis() >= frametime && System.currentTimeMillis() <= frametime + 30)
				Assets.player_death.play(getDeathvolume());
			
			if(System.currentTimeMillis() >= frametime + 2000){
				state = GameState.GameOver;
				Assets.gameover.play(getLoudvolume());
			}
		}
		
		//wave hit boxes for enemies
		for (int j = 0; j < waves.size(); j++) {
			Projectile w = waves.get(j);
			if (w.getY() >= enemy.getCenterY()-40 && w.getY() <= enemy.getCenterY()+25 &&
					enemy.getCenterX() > 10 && enemy.getCenterX() < 470 &&
					enemy.getCenterY() > 0 && enemy.getCenterY() < 800){
				score += 100*modified;
				enemy.explode();
				splitDie(enemy, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		for (int j = 0; j < waves.size(); j++) {
			Projectile w = waves.get(j);
			if (w.getY() >= enemy1.getCenterY()-40 && w.getY() <= enemy1.getCenterY()+25 &&
					enemy1.getCenterX() > 0 && enemy1.getCenterX() < 480 &&
					enemy1.getCenterY() > 0 && enemy1.getCenterY() < 800){
				score += 100*modified;
				enemy1.explode();
				splitDie(enemy1, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		for (int j = 0; j < waves.size(); j++) {
			Projectile w = waves.get(j);
			if (w.getY() >= enemy2.getCenterY()-40 && w.getY() <= enemy2.getCenterY()+25 &&
					enemy2.getCenterX() > 10 && enemy2.getCenterX() < 470 &&
					enemy2.getCenterY() > 0 && enemy2.getCenterY() < 800){
				score += 100*modified;
				enemy2.explode();
				splitDie(enemy2, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		for (int j = 0; j < waves.size(); j++) {
			Projectile w = waves.get(j);
			if (w.getY() >= enemy3.getCenterY()-40 && w.getY() <= enemy3.getCenterY()+25 &&
					enemy3.getCenterX() > 10 && enemy3.getCenterX() < 470 &&
					enemy3.getCenterY() > 0 && enemy3.getCenterY() < 800){
				score += 100*modified;
				enemy3.explode();
				splitDie(enemy3, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		for (int j = 0; j < waves.size(); j++) {
			Projectile w = waves.get(j);
			if (w.getY() >= enemy4.getCenterY()-40 && w.getY() <= enemy4.getCenterY()+25 &&
					enemy4.getCenterX() > 10 && enemy4.getCenterX() < 470 &&
					enemy4.getCenterY() > 0 && enemy4.getCenterY() < 800){
				score += 100*modified;
				enemy4.explode();
				splitDie(enemy4, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		for (int j = 0; j < waves.size(); j++) {
			Projectile w = waves.get(j);
			if (w.getY() >= enemy5.getCenterY()-40 && w.getY() <= enemy5.getCenterY()+25 &&
					enemy5.getCenterX() > 10 && enemy5.getCenterX() < 470 &&
					enemy5.getCenterY() > 0 && enemy5.getCenterY() < 800){
				score += 100*modified;
				enemy5.explode();
				splitDie(enemy5, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		for (int j = 0; j < waves.size(); j++) {
			Projectile w = waves.get(j);
			if (w.getY() >= special_enemy.getCenterY()-40 && w.getY() <= special_enemy.getCenterY()+25 &&
					special_enemy.getCenterX() > 10 && special_enemy.getCenterX() < 470 &&
					special_enemy.getCenterY() > 0 && special_enemy.getCenterY() < 800){
				score += 100*modified;
				special_enemy.explode();
				splitDie(special_enemy, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		for (int j = 0; j < waves.size(); j++) {
			Projectile w = waves.get(j);
			if (w.getY() >= enemyp.getCenterY()-40 && w.getY() <= enemyp.getCenterY()+25 &&
					enemyp.getCenterX() > 10 && enemyp.getCenterX() < 470 &&
					enemyp.getCenterY() > 0 && enemyp.getCenterY() < 800){
				score += 100*modified;
				enemyp.explode();
				splitDie(enemyp, -55, 500);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		
		//code for enemy hit boxes
		if (player.getCenterX()-40 >= enemy.getCenterX()-17 && player.getCenterX()-40 <= enemy.getCenterX()+17
				&& player.getCenterY()-80 >= enemy.getCenterY()-40 && player.getCenterY()-80 <= enemy.getCenterY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
				score += 100*modified;
				enemy.explode();
				splitDie(enemy, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
			
		}
		if (player.getCenterX()-40 >= enemy1.getCenterX()-17 && player.getCenterX()-40 <= enemy1.getCenterX()+17
				&& player.getCenterY()-80 >= enemy1.getCenterY()-40 && player.getCenterY()-80 <= enemy1.getCenterY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
				score += 100*modified;
				enemy1.explode();
				splitDie(enemy1, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		if (player.getCenterX()-40 >= enemy2.getCenterX()-17 && player.getCenterX()-40 <= enemy2.getCenterX()+17
				&& player.getCenterY()-80 >= enemy2.getCenterY()-40 && player.getCenterY()-80 <= enemy2.getCenterY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
				score += 100*modified;
				enemy2.explode();
				splitDie(enemy2, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		if (player.getCenterX()-40 >= enemy3.getCenterX()-17 && player.getCenterX()-40 <= enemy3.getCenterX()+17
				&& player.getCenterY()-80 >= enemy3.getCenterY()-40 && player.getCenterY()-80 <= enemy3.getCenterY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
				score += 150*modified;;
				enemy3.explode();
				splitDie(enemy3, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		if (player.getCenterX()-40 >= enemy4.getCenterX()-17 && player.getCenterX()-40 <= enemy4.getCenterX()+17
				&& player.getCenterY()-80 >= enemy4.getCenterY()-40 && player.getCenterY()-80 <= enemy4.getCenterY()+25){
			if(gethit == true){
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
				score += 150*modified;
				enemy4.explode();
				splitDie(enemy4, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		if (player.getCenterX()-40 >= enemy5.getCenterX()-17 && player.getCenterX()-40 <= enemy5.getCenterX()+17
				&& player.getCenterY()-80 >= enemy5.getCenterY()-40 && player.getCenterY()-80 <= enemy5.getCenterY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
				score += 150*modified;
				enemy5.explode();
				splitDie(enemy5, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		if (player.getCenterX()-40 >= special_enemy.getCenterX()-20 && player.getCenterX()-40 <= special_enemy.getCenterX()+20
				&& player.getCenterY()-80 >= special_enemy.getCenterY()-40 && player.getCenterY()-80 <= special_enemy.getCenterY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
				score += 750*modified;
				special_enemy.explode();
				splitDie(special_enemy, -600, 600);
				Assets.enemy_death.play(getDeathvolume());
			}
		}
		if (player.getCenterX()-40 >= enemyp.getCenterX()-20 && player.getCenterX()-40 <= enemyp.getCenterX()+20
				&& player.getCenterY()-80 >= enemyp.getCenterY()-40 && player.getCenterY()-80 <= enemyp.getCenterY()+25
				&& isInvincible == true){
			gethit = false;
			score += 500*modified;
			enemyp.explode();
			splitDie(enemyp, -55, 500);
			Assets.enemy_death.play(getDeathvolume());
			
		}
		
		if(asteroidsActive == true){
			asteroidHitEnemies();
			asteroidsHitPlayer();
		}
		
		if(System.currentTimeMillis() >= frametime + 2000 && isInvincible == false){ // added isInvincible parameter
			gethit = true;
		}
		if(System.currentTimeMillis() >= enemyFrameTime + 2000){
			enemyhit = false;
		}
		if(System.currentTimeMillis() >= boss1FrameTime + 2000){
			bosshit = false;
		}
		
		if (player.getCenterX()-40 >= enemy_v.getCenterX()-20 && player.getCenterX()-40 <= enemy_v.getCenterX()+20
				&& player.getCenterY()-80 >= enemy_v.getCenterY()-40 && player.getCenterY()-80 <= enemy_v.getCenterY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true && enemyhit == false){
				gethit = false;
				enemyhit = true;
				enemy_v.explode();
				enemy_v_health = enemy_v_health - 1;
				enemyFrameTime = System.currentTimeMillis();
			}
		}
		
		if (player.getCenterX()-40 >= boss1easy.getCenterX()-32 && player.getCenterX()-40 <= boss1easy.getCenterX()+32
				&& player.getCenterY()-80 >= boss1easy.getCenterY()-52 && player.getCenterY()-80 <= boss1easy.getCenterY()+37){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true && bosshit == false && boss1easy_appear == true){
				gethit = false;
				bosshit = true;
				boss1easy.loseHealth();
				boss1FrameTime = System.currentTimeMillis();
			}
		}
		
		if (player.getCenterX()-40 >= boss2easy.getCenterX()-32 && player.getCenterX()-40 <= boss2easy.getCenterX()+32
				&& player.getCenterY()-80 >= boss2easy.getCenterY()-52 && player.getCenterY()-80 <= boss2easy.getCenterY()+37){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 1;
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true && bosshit == false && boss2easy_appear == true){
				gethit = false;
				bosshit = true;
				boss2easy.loseHealth();
				boss1FrameTime = System.currentTimeMillis();
			}
		}
		
		// armada checking - collision with enemies
		if (level >=100){
			if (player.getCenterX()-40 >= armada1.getCenterX()-20 && player.getCenterX()-40 <= armada1.getCenterX()+20
					&& player.getCenterY()-80 >= armada1.getCenterY()-40 && player.getCenterY()-80 <= armada1.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada1.explode();
					armada1.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada2.getCenterX()-20 && player.getCenterX()-40 <= armada2.getCenterX()+20
					&& player.getCenterY()-80 >= armada2.getCenterY()-40 && player.getCenterY()-80 <= armada2.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada2.explode();
					armada2.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada3.getCenterX()-20 && player.getCenterX()-40 <= armada3.getCenterX()+20
					&& player.getCenterY()-80 >= armada3.getCenterY()-40 && player.getCenterY()-80 <= armada3.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada3.explode();
					armada3.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada4.getCenterX()-20 && player.getCenterX()-40 <= armada4.getCenterX()+20
					&& player.getCenterY()-80 >= armada4.getCenterY()-40 && player.getCenterY()-80 <= armada4.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada4.explode();
					armada4.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada5.getCenterX()-20 && player.getCenterX()-40 <= armada5.getCenterX()+20
					&& player.getCenterY()-80 >= armada5.getCenterY()-40 && player.getCenterY()-80 <= armada5.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada5.explode();
					armada5.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada6.getCenterX()-20 && player.getCenterX()-40 <= armada6.getCenterX()+20
					&& player.getCenterY()-80 >= armada6.getCenterY()-40 && player.getCenterY()-80 <= armada6.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada6.explode();
					armada6.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada7.getCenterX()-20 && player.getCenterX()-40 <= armada7.getCenterX()+20
					&& player.getCenterY()-80 >= armada7.getCenterY()-40 && player.getCenterY()-80 <= armada7.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada7.explode();
					armada7.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada8.getCenterX()-20 && player.getCenterX()-40 <= armada8.getCenterX()+20
					&& player.getCenterY()-80 >= armada8.getCenterY()-40 && player.getCenterY()-80 <= armada8.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada8.explode();
					armada8.dieArmada(-40);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada9.getCenterX()-20 && player.getCenterX()-40 <= armada9.getCenterX()+20
					&& player.getCenterY()-80 >= armada9.getCenterY()-40 && player.getCenterY()-80 <= armada9.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada9.explode();
					armada9.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada10.getCenterX()-20 && player.getCenterX()-40 <= armada10.getCenterX()+20
					&& player.getCenterY()-80 >= armada10.getCenterY()-40 && player.getCenterY()-80 <= armada10.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada10.explode();
					armada10.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada11.getCenterX()-20 && player.getCenterX()-40 <= armada11.getCenterX()+20
					&& player.getCenterY()-80 >= armada11.getCenterY()-40 && player.getCenterY()-80 <= armada11.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada11.explode();
					armada11.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
			if (player.getCenterX()-40 >= armada12.getCenterX()-20 && player.getCenterX()-40 <= armada12.getCenterX()+20
					&& player.getCenterY()-80 >= armada12.getCenterY()-40 && player.getCenterY()-80 <= armada12.getCenterY()+25){
				if(gethit == true){
					player.explode();
					livesLeft = livesLeft - 1;
					gethit = false;
					frametime = System.currentTimeMillis();
				}else if(isInvincible == true){
					gethit = false;
					score += 750*modified;
					armada12.explode();
					armada12.dieArmada(-140);
					Assets.enemy_death.play(getDeathvolume());
				}
			}
		}
		
		// power ups
		
		//invincibility
		if (invincible.getX() >= player.getCenterX() - 85 && invincible.getX() <= player.getCenterX() - 35 
				&& invincible.getY() >= player.getCenterY() - 75 && invincible.getY() <= player.getCenterY() - 25){
			Assets.theme.pause();
			Assets.boss_theme.pause();
			if(mutemusic == false){
				Assets.invincibility_theme.play();
			}
			havePowerUp = true;
			invincible.setActive(false);
			isInvincible = true;
			gethit = false;
			invincibleTime = System.currentTimeMillis();
			invincible.setX(230);
			invincible.setY(-40);
			invincible.setActive(false);
		}
		
		//spread shot
		if (spreadShot.getX() >= player.getCenterX() - 85 && spreadShot.getX() <= player.getCenterX() - 35 
				&& spreadShot.getY() >= player.getCenterY() - 75 && spreadShot.getY() <= player.getCenterY() - 25){
			havePowerUp = true;
			spreadShot.setActive(false);
			spread = true;
			spreadShot.setX(230);
			spreadShot.setY(-40);
			spreadShot.setActive(false);
			spreadTime = System.currentTimeMillis();
		}
		
		// rapid fire
		if (rapidFire.getX() >= player.getCenterX() - 85 && rapidFire.getX() <= player.getCenterX() - 35 
				&& rapidFire.getY() >= player.getCenterY() - 75 && rapidFire.getY() <= player.getCenterY() - 25){
			havePowerUp = true;
			rapidFire.setActive(false);
			rapid = true;
			rapidFire.setX(230);
			rapidFire.setY(-40);
			rapidFire.setActive(false);
			rapidTime = System.currentTimeMillis();
		}
		
		// wave
		if (wave.getX() >= player.getCenterX() - 85 && wave.getX() <= player.getCenterX() - 35 
				&& wave.getY() >= player.getCenterY() - 75 && wave.getY() <= player.getCenterY() - 25){
			player.waveShoot();
			Assets.waveExplosion.play(getLoudvolume());
			havePowerUp = true;
			wave.setActive(false);
			wave.setX(230);
			wave.setY(-40);
			wave.setActive(false);
		}
		
		if(System.currentTimeMillis() >= invincibleTime + 23000 && System.currentTimeMillis() >= frametime + 2000 && gethit == false ){ // after invincibility ends, set to get hit again CHANGED
			gethit = true;
			isInvincible = false;
			Assets.invincibility_theme.pause();
			Assets.invincibility_theme.seekBegin();
			
			if(level < 100 && mutemusic == false)
				Assets.theme.play();
			if(level >= 100 && mutemusic == false){
				Assets.cashew_theme.play();
			}
		}
		
		if(System.currentTimeMillis() >= spreadTime + 26000){
			spread = false;
		}
		
		if(System.currentTimeMillis() >= rapidTime + 13000){
			rapid = false;
		}
		
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		
		if(mutemusic == true){
			Assets.theme.pause();
			Assets.cashew_intro.pause();
			Assets.cashew_theme.pause();
			Assets.invincibility_theme.pause();
			Assets.bonus_enemy.pause();
			Assets.boss_theme.pause();
		}
		if(level < 100 && mutemusic == false && level != 25 && level != 50){
			Assets.theme.play();
		}
		if((level == 25 || level == 50) && mutemusic == false){
			Assets.boss_theme.play();
		}
		if(level != 25 && level != 50){
			Assets.boss_theme.pause();
		}
		if(level >= 100 && mutemusic == false){
			Assets.cashew_theme.play();
		}
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 0, 300, 400, 140)) {
					unpause();
				}

				if (inBounds(event, 0, 480, 800, 140)) {
					nullify();
					goToMenu();
				}
				if (inBounds(event, 40, 645, 128, 128)) {
					mutemusic = !mutemusic;
				}
				
				if (inBounds(event, 308, 645, 128, 128)) {
					mutesound = !mutesound;
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		
		Assets.theme.seekBegin();
		Assets.boss_theme.seekBegin();
		Assets.cashew_theme.seekBegin();
		Assets.cashew_intro.seekBegin();
		Assets.invincibility_theme.seekBegin();
		
		if(difficulty == 0){
			finalScore = (int)(score * 0.8);
		}
		if(difficulty == 1){
			finalScore = score * 1;
		}
		if(difficulty == 2){
			finalScore = (int)(score * 1.5);
		}
		if(difficulty == 3){
			finalScore = score * 2;
		}
		
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 360, 60, 120, 100)) {
					nullify();
					Assets.cashew_theme.pause();
					Assets.cashew_theme.seekBegin();
					game.setScreen(new MainMenuScreen(game, fis, fos));
					return;
				}
				if (inBounds(event, 0, 60, 120, 100)) {
					nullify();
					Assets.cashew_theme.pause();
					Assets.cashew_theme.seekBegin();
					game.setScreen(new GameScreen(game, shipType, difficulty, neon, fis, fos));
					return;
				}
			}
		}
		
		Assets.bonus_enemy.pause();
		Assets.theme.pause();
		Assets.cashew_theme.pause();
		Assets.boss_theme.pause();
		Assets.cashew_intro.pause();
		Assets.invincibility_theme.pause();
	}
	//incomplete
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		
		// First draw the game elements.
		
		if(level < 100){
			if(neon == 0){
				g.drawImage(colorbg, 0, colorback1.getBgY());
				g.drawImage(colorbg, 0, colorback2.getBgY());
			}
			if(neon == 1){
				g.drawImage(colorbg33, 0, colorback1.getBgY());
				g.drawImage(colorbg33, 0, colorback2.getBgY());
			}
			if(neon == 2){
				g.drawImage(colorbg22, 0, colorback1.getBgY());
				g.drawImage(colorbg22, 0, colorback2.getBgY());
			}
			if(neon == 3){
				g.drawImage(colorbg11, 0, colorback1.getBgY());
				g.drawImage(colorbg11, 0, colorback2.getBgY());
			}
			g.drawImage(colormiddlebg, 0, colormiddle1.getBgY());
			g.drawImage(colormiddlebg, 0, colormiddle2.getBgY());
			g.drawImage(colorfrontbg, 0, colorfront1.getBgY());
			g.drawImage(colorfrontbg, 0, colorfront2.getBgY());
		}else{
			g.drawImage(newbg, 0, blackback1.getBgY());
			g.drawImage(newbg, 0, blackback2.getBgY());
			g.drawImage(newmiddlebg, 0, blackmiddle1.getBgY());
			g.drawImage(newmiddlebg, 0, blackmiddle2.getBgY());
			g.drawImage(newfrontbg, 0, blackfront1.getBgY());
			g.drawImage(newfrontbg, 0, blackfront2.getBgY());
		}
		
		//POWER UPS
		if(shipType == 0){
			if(gethit == false && isInvincible == false)
				g.drawImage(player_hit, player.getCenterX() - 80,
						player.getCenterY() - 70);
			else if(isInvincible == true){
				g.drawImage(player_invincibility, player.getCenterX() - 80,
						player.getCenterY() - 70);
			}			
		}
		
		if(shipType == 1){
			if(gethit == false && isInvincible == false)
				g.drawImage(player1Hit, player.getCenterX() - 80,
						player.getCenterY() - 70);
			else if(isInvincible == true){
				g.drawImage(player1Invincible, player.getCenterX() - 80,
						player.getCenterY() - 70);
			}			
			
		}
		if(shipType == 2){
			if(gethit == false && isInvincible == false)
				g.drawImage(player2Hit, player.getCenterX() - 80,
						player.getCenterY() - 70);
			else if(isInvincible == true){
				g.drawImage(player2Invincible, player.getCenterX() - 80,
						player.getCenterY() - 70);
			}			
		}
		
		if(shipType == 3){
			if(gethit == false && isInvincible == false)
				g.drawImage(player3Hit, player.getCenterX() - 80,
						player.getCenterY() - 70);
			else if(isInvincible == true){
				g.drawImage(player3Invincible, player.getCenterX() - 80,
						player.getCenterY() - 70);
			}			
		}
		if(shipType == 4){
			if(gethit == false && isInvincible == false)
				g.drawImage(player4Hit, player.getCenterX() - 80,
						player.getCenterY() - 70);
			else if(isInvincible == true){
				g.drawImage(player4Invincible, player.getCenterX() - 80,
						player.getCenterY() - 70);
			}			
		}
		if(shipType == 5){
			if(gethit == false && isInvincible == false)
				g.drawImage(player5Hit, player.getCenterX() - 80,
						player.getCenterY() - 70);
			else if(isInvincible == true){
				g.drawImage(player5Invincible, player.getCenterX() - 80,
						player.getCenterY() - 70);
			}			
		}
		if(shipType == 6 || shipType == 7){
			if(gethit == false && isInvincible == false)
				g.drawImage(player6Hit, player.getCenterX() - 80,
						player.getCenterY() - 70);
			else if(isInvincible == true){
				g.drawImage(player6Invincible, player.getCenterX() - 80,
						player.getCenterY() - 70);
			}
		}
		if(shipType == 8 || shipType == 9){
			if(gethit == false && isInvincible == false)
				g.drawImage(player8Hit, player.getCenterX() - 80,
						player.getCenterY() - 70);
			else if(isInvincible == true){
				g.drawImage(player8Invincible, player.getCenterX() - 80,
						player.getCenterY() - 70);
			}
		}
				
		if(shipType == 0){
			if(gethit == true && isInvincible == false)
				g.drawImage(playerpic, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		if(shipType == 1){
			if(gethit == true && isInvincible == false)
				g.drawImage(player1, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		if(shipType == 2){
			if(gethit == true && isInvincible == false)
				g.drawImage(player2, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		if(shipType == 3){
			if(gethit == true && isInvincible == false)
				g.drawImage(player3, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		if(shipType == 4){
			if(gethit == true && isInvincible == false)
				g.drawImage(player4, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		if(shipType == 5){
			if(gethit == true && isInvincible == false)
				g.drawImage(player5, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		if(shipType == 6){
			if(gethit == true && isInvincible == false)
				g.drawImage(player6, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		if(shipType == 7){
			if(gethit == true && isInvincible == false)
				g.drawImage(player7, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		if(shipType == 8){
			if(gethit == true && isInvincible == false)
				g.drawImage(player8, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		if(shipType == 9){
			if(gethit == true && isInvincible == false)
				g.drawImage(player9, player.getCenterX() - 80,
					player.getCenterY() - 70);
		}
		
		if(enemy_v_health == 3){
			g.drawImage(enemy_v_anim.getImage(), enemy_v.getCenterX()-10, enemy_v.getCenterY()-10);
		}
		if(enemy_v_health == 2){
			g.drawImage(enemy_v_damaged1_anim.getImage(), enemy_v.getCenterX()-10, enemy_v.getCenterY()-10);
		}
		if(enemy_v_health == 1){
			g.drawImage(enemy_v_damaged2_anim.getImage(), enemy_v.getCenterX()-10, enemy_v.getCenterY()-10);
		}
		
		g.drawImage(invincible_pic, invincible.getX(), invincible.getY());
		g.drawImage(spreadShot_pic, spreadShot.getX(), spreadShot.getY());
		g.drawImage(rapidFire_pic, rapidFire.getX(), rapidFire.getY());
		g.drawImage(wave_pic, wave.getX(), wave.getY());
		if( enemy_color == 0 ){
			g.drawImage(enemy2_b, enemy.getCenterX()-10, enemy.getCenterY());
		}else if( enemy_color == 1 ){
			g.drawImage(enemy2_y, enemy.getCenterX()-10, enemy.getCenterY());
		}else if( enemy_color == 2 ){
			g.drawImage(enemy2_r, enemy.getCenterX()-10, enemy.getCenterY());
		}else if( enemy_color == 3 ){
			g.drawImage(enemy2_g, enemy.getCenterX()-10, enemy.getCenterY());
		}else if( enemy_color == 4 ){
			g.drawImage(enemy2_p, enemy.getCenterX()-10, enemy.getCenterY());
		}
		if( enemy1_color == 0 ){
			g.drawImage(enemy2_b, enemy1.getCenterX()-10, enemy1.getCenterY());
		}else if( enemy1_color == 1 ){
			g.drawImage(enemy2_y, enemy1.getCenterX()-10, enemy1.getCenterY());
		}else if( enemy1_color == 2 ){
			g.drawImage(enemy2_r, enemy1.getCenterX()-10, enemy1.getCenterY());
		}else if( enemy1_color == 3 ){
			g.drawImage(enemy2_g, enemy1.getCenterX()-10, enemy1.getCenterY());
		}else if( enemy1_color == 4 ){
			g.drawImage(enemy2_p, enemy1.getCenterX()-10, enemy1.getCenterY());
		}
		if( enemy2_color == 0 ){
			g.drawImage(enemy2_b, enemy2.getCenterX()-10, enemy2.getCenterY());
		}else if( enemy2_color == 1 ){
			g.drawImage(enemy2_y, enemy2.getCenterX()-10, enemy2.getCenterY());
		}else if( enemy2_color == 2 ){
			g.drawImage(enemy2_r, enemy2.getCenterX()-10, enemy2.getCenterY());
		}else if( enemy2_color == 3 ){
			g.drawImage(enemy2_g, enemy2.getCenterX()-10, enemy2.getCenterY());
		}else if( enemy2_color == 4 ){
			g.drawImage(enemy2_p, enemy2.getCenterX()-10, enemy2.getCenterY());
		}
		if( enemy3_color == 0 ){
			g.drawImage(enemy_b, enemy3.getCenterX()-10, enemy3.getCenterY());
		}else if( enemy3_color == 1 ){
			g.drawImage(enemy_p, enemy3.getCenterX()-10, enemy3.getCenterY());
		}else if( enemy3_color == 2 ){
			g.drawImage(enemy_y, enemy3.getCenterX()-10, enemy3.getCenterY());
		}else if( enemy3_color == 3 ){
			g.drawImage(enemy_c, enemy3.getCenterX()-10, enemy3.getCenterY());
		}else if( enemy3_color == 4 ){
			g.drawImage(enemy_r, enemy3.getCenterX()-10, enemy3.getCenterY());
		}
		if( enemy4_color == 0 ){
			g.drawImage(enemy_b, enemy4.getCenterX()-10, enemy4.getCenterY());
		}else if( enemy4_color == 1 ){
			g.drawImage(enemy_p, enemy4.getCenterX()-10, enemy4.getCenterY());
		}else if( enemy4_color == 2 ){
			g.drawImage(enemy_y, enemy4.getCenterX()-10, enemy4.getCenterY());
		}else if( enemy4_color == 3 ){
			g.drawImage(enemy_c, enemy4.getCenterX()-10, enemy4.getCenterY());
		}else if( enemy4_color == 4 ){
			g.drawImage(enemy_r, enemy4.getCenterX()-10, enemy4.getCenterY());
		}
		if( enemy5_color == 0 ){
			g.drawImage(enemy_b, enemy5.getCenterX()-10, enemy5.getCenterY());
		}else if( enemy5_color == 1 ){
			g.drawImage(enemy_p, enemy5.getCenterX()-10, enemy5.getCenterY());
		}else if( enemy5_color == 2 ){
			g.drawImage(enemy_y, enemy5.getCenterX()-10, enemy5.getCenterY());
		}else if( enemy5_color == 3 ){
			g.drawImage(enemy_c, enemy5.getCenterX()-10, enemy5.getCenterY());
		}else if( enemy5_color == 4 ){
			g.drawImage(enemy_r, enemy5.getCenterX()-10, enemy5.getCenterY());
		}
		g.drawImage(enemy_p, enemy4.getCenterX()-10, enemy4.getCenterY());
		g.drawImage(enemy_y, enemy5.getCenterX()-10, enemy5.getCenterY());
		g.drawImage(special, special_enemy.getCenterX()-10, special_enemy.getCenterY());
		g.drawImage(special, armada1.getCenterX()-10, armada1.getCenterY());
		g.drawImage(special, armada2.getCenterX()-10, armada2.getCenterY());
		g.drawImage(special, armada3.getCenterX()-10, armada3.getCenterY());
		g.drawImage(special, armada4.getCenterX()-10, armada4.getCenterY());
		g.drawImage(special, armada5.getCenterX()-10, armada5.getCenterY());
		g.drawImage(special, armada6.getCenterX()-10, armada6.getCenterY());
		g.drawImage(special, armada7.getCenterX()-10, armada7.getCenterY());
		g.drawImage(special, armada8.getCenterX()-10, armada8.getCenterY());
		g.drawImage(special, armada9.getCenterX()-10, armada9.getCenterY());
		g.drawImage(special, armada10.getCenterX()-10, armada10.getCenterY());
		g.drawImage(special, armada11.getCenterX()-10, armada11.getCenterY());
		g.drawImage(special, armada12.getCenterX()-10, armada12.getCenterY());
		g.drawImage(cashew, cashew_enemy.getCenterX()-10, cashew_enemy.getCenterY());
		g.drawImage(bonusanim.getImage(), enemyp.getCenterX()-10, enemyp.getCenterY());
		if(level >= 10 && asteroidChance == 1 && System.currentTimeMillis() <= warningTime 
				+ 2000 && warning == true && difficulty != 0){
			g.drawImage(warninganim.getImage(), 0, 360);
		}
		
		g.drawImage(asteroidanim.getImage(), asteroid1.getX()-10, asteroid1.getY());
		g.drawImage(asteroidanim.getImage(), asteroid2.getX()-10, asteroid2.getY());
		g.drawImage(asteroidanim.getImage(), asteroid3.getX()-10, asteroid3.getY());
		g.drawImage(asteroidanim.getImage(), asteroid4.getX()-10, asteroid4.getY());
		g.drawImage(asteroidanim.getImage(), asteroid5.getX()-10, asteroid5.getY());
		g.drawImage(asteroidanimmedium.getImage(), asteroid1medium.getX()-10, asteroid1medium.getY());
		g.drawImage(asteroidanimmedium.getImage(), asteroid2medium.getX()-10, asteroid2medium.getY());
		g.drawImage(asteroidanimmedium.getImage(), asteroid3medium.getX()-10, asteroid3medium.getY());
		g.drawImage(asteroidanimmedium.getImage(), asteroid4medium.getX()-10, asteroid4medium.getY());
		g.drawImage(asteroidanimmedium.getImage(), asteroid5medium.getX()-10, asteroid5medium.getY());
		g.drawImage(asteroidanimlarge.getImage(), asteroid1large.getX()-10, asteroid1large.getY());
		g.drawImage(asteroidanimlarge.getImage(), asteroid2large.getX()-10, asteroid2large.getY());
		g.drawImage(asteroidanimlarge.getImage(), asteroid3large.getX()-10, asteroid3large.getY());
		g.drawImage(asteroidanimlarge.getImage(), asteroid4large.getX()-10, asteroid4large.getY());
		g.drawImage(asteroidanimlarge.getImage(), asteroid5large.getX()-10, asteroid5large.getY());
		
		ArrayList<?> waves = player.getWaves();
		for (int i = 0; i < waves.size(); i++) {
			Projectile p = (Projectile) waves.get(i);
			g.drawImage(waveanim.getImage(), p.getX()-245, p.getY()-2);
		}
		ArrayList<?> projectiles = player.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.drawImage(Assets.player_bullet, p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy_projectiles = enemy.getProjectiles();
		for (int i = 0; i < enemy_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy_projectiles.get(i);
			g.drawImage(Assets.enemy_bullet, p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy1_projectiles = enemy1.getProjectiles();
		for (int i = 0; i < enemy1_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy1_projectiles.get(i);
			g.drawImage(Assets.enemy_bullet, p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy2_projectiles = enemy2.getProjectiles();
		for (int i = 0; i < enemy2_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy2_projectiles.get(i);
			g.drawImage(Assets.enemy_bullet, p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy3_projectiles = enemy3.getProjectiles();
		for (int i = 0; i < enemy3_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy3_projectiles.get(i);
			g.drawImage(Assets.enemy2_bullet, p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy4_projectiles = enemy4.getProjectiles();
		for (int i = 0; i < enemy4_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy4_projectiles.get(i);
			g.drawImage(Assets.enemy2_bullet, p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy5_projectiles = enemy5.getProjectiles();
		for (int i = 0; i < enemy5_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy5_projectiles.get(i);
			g.drawImage(Assets.enemy2_bullet, p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy_special_projectiles = special_enemy.getProjectiles();
		for (int i = 0; i < enemy_special_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy_special_projectiles.get(i);
			g.drawImage(Assets.enemy_special_bullet, p.getX()-7, p.getY()-4);
		}
		ArrayList<?> armada_projectiles = armada1.getProjectiles();
		for (int i = 0; i < armada_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) armada_projectiles.get(i);
			g.drawImage(Assets.enemy_special_bullet, p.getX()-7, p.getY()-4);
		}
		ArrayList<?> enemy_v_projectiles = enemy_v.getProjectiles();
		for (int i = 0; i < enemy_v_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) enemy_v_projectiles.get(i);
			g.drawImage(Assets.enemy3_bullet, p.getX(), p.getY()-2);
		}
		ArrayList<?> boss1easy_projectiles = boss1easy.getProjectiles();
		for (int i = 0; i < boss1easy_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) boss1easy_projectiles.get(i);
			g.drawImage(Assets.boss_bullet, p.getX(), p.getY()-2);
		}
		ArrayList<?> boss2easy_projectiles = boss2easy.getProjectiles();
		for (int i = 0; i < boss2easy_projectiles.size(); i++) {
			Enemy_Projectile p = (Enemy_Projectile) boss2easy_projectiles.get(i);
			g.drawImage(Assets.boss_bullet, p.getX(), p.getY()-2);
		}
		
		//explosions
		ArrayList<?> player_explosions = player.getExplosions();
		for (int i = 0; i < player_explosions.size(); i++) {
			Explosion p = (Explosion) player_explosions.get(i);
			g.drawImage(explanim.getImage(), p.getX()-15, p.getY()+5);
		}
		
		ArrayList<?> enemy_explosions = enemy.getExplosions();
		for (int i = 0; i < enemy_explosions.size(); i++) {
			Explosion p = (Explosion) enemy_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy1_explosions = enemy1.getExplosions();
		for (int i = 0; i < enemy1_explosions.size(); i++) {
			Explosion p = (Explosion) enemy1_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy2_explosions = enemy2.getExplosions();
		for (int i = 0; i < enemy2_explosions.size(); i++) {
			Explosion p = (Explosion) enemy2_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy3_explosions = enemy3.getExplosions();
		for (int i = 0; i < enemy3_explosions.size(); i++) {
			Explosion p = (Explosion) enemy3_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy4_explosions = enemy4.getExplosions();
		for (int i = 0; i < enemy4_explosions.size(); i++) {
			Explosion p = (Explosion) enemy4_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy5_explosions = enemy5.getExplosions();
		for (int i = 0; i < enemy5_explosions.size(); i++) {
			Explosion p = (Explosion) enemy5_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> special_enemy_explosions = special_enemy.getExplosions();
		for (int i = 0; i < special_enemy_explosions.size(); i++) {
			Explosion p = (Explosion) special_enemy_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemy_v_explosions = enemy_v.getExplosions();
		for (int i = 0; i < enemy_v_explosions.size(); i++) {
			Explosion p = (Explosion) enemy_v_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> enemyp_explosions = enemyp.getExplosions();
		for (int i = 0; i < enemyp_explosions.size(); i++) {
			Explosion p = (Explosion) enemyp_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> boss1easy_explosions = boss1easy.getExplosions();
		for (int i = 0; i < boss1easy_explosions.size(); i++) {
			Explosion p = (Explosion) boss1easy_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		ArrayList<?> boss2easy_explosions = boss2easy.getExplosions();
		for (int i = 0; i < boss2easy_explosions.size(); i++) {
			Explosion p = (Explosion) boss2easy_explosions.get(i);
			g.drawImage(boomanim.getImage(), p.getX()-5, p.getY()-2);
		}
		
		g.drawImage(Assets.word_scr, 230, 70);//changed (original: 0, 5)
		g.drawString(Integer.toString(score), 298, 90, paint3);//changed (original: text_alignment*10+120, 25)
		g.drawImage(Assets.word_LV, 60, 70);//changed (original: 0,45)
		if(level > 100)
			g.drawString(Integer.toString(100), 130, 90, paint);//changed (original: text_alignment*10+120, 60)
		else
			g.drawString(Integer.toString(level), 130, 90, paint);//changed (original: text_alignment*10+120, 60)
		
		if(livesLeft >= 6)
			g.drawImage(hpGreen6, 60, 100);//changed (original: 3, 90)
		else if(livesLeft == 5)
			g.drawImage(hpGreen5, 60, 100);//changed (original: 3, 90)
		else if(livesLeft == 4)
			g.drawImage(hpGreen4, 60, 100);//changed (original: 3, 90)
		else if(livesLeft == 3)
			g.drawImage(hpGreen, 60, 100);//changed (original: 3, 90)
		else if(livesLeft == 2)
			g.drawImage(hpYellow, 60, 100);//changed (original: 3, 90)
		else if(livesLeft == 1)
			g.drawImage(hpRed, 60, 100);//changed (original: 3, 90)
		else
			g.drawImage(blank, 3, 120);//changed (original: 3, 90)
		
		g.drawImage(pause, 0, 70);//changed (original: 430,0)
		
		if(boss1easy.getHealth() == 8 || boss1easy.getHealth() == 7){
			g.drawImage(boss1green_anim.getImage(), boss1easy.getCenterX(), boss1easy.getCenterY());
		}else if(boss1easy.getHealth() == 6 || boss1easy.getHealth() == 5){
			g.drawImage(boss1yellow_anim.getImage(), boss1easy.getCenterX(), boss1easy.getCenterY());
		}else if(boss1easy.getHealth() == 4 || boss1easy.getHealth() == 3){
			g.drawImage(boss1red_anim.getImage(), boss1easy.getCenterX(), boss1easy.getCenterY());
		}else{
			g.drawImage(boss1, boss1easy.getCenterX(), boss1easy.getCenterY());
		}
		
		g.drawImage(boss2_anim.getImage(), boss2easy.getCenterX(), boss2easy.getCenterY());
		
		if(livesLeft <= 0){
			g.drawImage(boomanim.getImage(), player.getCenterX()-100, player.getCenterY()-80);
			boomanim.update(100);
		}
		
		// Example:
		// g.drawImage(Assets.background, 0, 0);
		// g.drawImage(Assets.character, characterX, characterY);

		// Secondly, draw the UI above the game elements.
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	public void animate() {
		boss1green_anim.update(1);
		boss1yellow_anim.update(1);
		boss1red_anim.update(1);
		boss2_anim.update(10);
		warninganim.update(5);
		bonusanim.update(12);
		enemy_v_anim.update(1);
		enemy_v_damaged1_anim.update(1);
		enemy_v_damaged2_anim.update(1);
		asteroidanim.update(15);
		asteroidanimmedium.update(15);
		asteroidanimlarge.update(15);
		colorback1.updateY();
		colorback2.updateY();
		blackback1.updateY();
		blackback2.updateY();
		colormiddle1.updateY();
		colormiddle2.updateY();
		blackmiddle1.updateY();
		blackmiddle2.updateY();
		colorfront1.updateY();
		colorfront2.updateY();
		blackfront1.updateY();
		blackfront2.updateY();
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		player = null;

		// Call garbage collector to clean up memory.
		System.gc();

	}
	
	private void updatePowerUps(){
		if(level > 1 && level % 4 == 0 && spreadShot.getActive() == false &&
				invincible.getActive() == false && rapidFire.getActive() == 
				false && wave.getActive() == false && havePowerUp == false){
			power = (int)(Math.random()*6);
			if(power == 0 || power == 1){
				havePowerUp = true;
				spreadShot.setActive(true);
			}
			if(power == 2){
				havePowerUp = true;
				invincible.setActive(true);
			}
			if(power == 3){
				havePowerUp = true;
				rapidFire.setActive(true);
			}
			if(power == 4 || power == 5){
				havePowerUp = true;
				wave.setActive(true);
			}
		}
		if(level % 4 == 1){
			havePowerUp = false;
		}
		
		spreadShot.update();
		rapidFire.update();
		invincible.update();
		wave.update();
	}
	
	private void splitDie(Normal_Enemy e, int left, int right){
		if(e.equals(enemy)){
			enemy_color = (int)(Math.random()*5);
		}
		if(e.equals(enemy1)){
			enemy1_color = (int)(Math.random()*5);
		}
		if(e.equals(enemy2)){
			enemy2_color = (int)(Math.random()*5);
		}
		if(e.equals(enemy3)){
			enemy3_color = (int)(Math.random()*5);
		}
		if(e.equals(enemy4)){
			enemy4_color = (int)(Math.random()*5);
		}
		if(e.equals(enemy5)){
			enemy5_color = (int)(Math.random()*5);
		}
		if((int)(Math.random()*2) == 0){
			e.die(left);
		}else{
			e.die(right);
		}
	}
	
	private void enemyFiring(){
		if(difficulty < 2){
			if(enemy.getCenterX() >= 96 && enemy.getCenterX() <= 106 || 
				enemy.getCenterX() >= 192 && enemy.getCenterX() <= 202 ||
				enemy.getCenterX() >= 288 && enemy.getCenterX() <= 298 ||
				enemy.getCenterX() >= 384 && enemy.getCenterX() <= 394){
				enemy.shoot();
				Assets.enemy_shot.play(getShotvolume());
			}
			if(enemy1.getCenterX() >= 36 && enemy1.getCenterX() <= 46 || 
				enemy1.getCenterX() >= 132 && enemy1.getCenterX() <= 142 ||
				enemy1.getCenterX() >= 228 && enemy1.getCenterX() <= 238 ||
				enemy1.getCenterX() >= 324 && enemy1.getCenterX() <= 334){
				enemy1.shoot();
				Assets.enemy_shot.play(getShotvolume());
			}
			if(enemy2.getCenterX() >= 156 && enemy2.getCenterX() <= 166 || 
				enemy2.getCenterX() >= 252 && enemy2.getCenterX() <= 262 ||
				enemy2.getCenterX() >= 348 && enemy2.getCenterX() <= 358 ||
				enemy2.getCenterX() >= 444 && enemy2.getCenterX() <= 454){
				enemy2.shoot();
				Assets.enemy_shot.play(getShotvolume());
			}
			if(enemy3.getCenterX() >= 20 && enemy3.getCenterX() <= 25){
				enemy3.shoot();
				enemy3.shoot(6, 7);
				enemy3.shoot(4, 7);
				enemy3.shoot(2, 7);
				Assets.enemy_shot.play(getShotvolume());
			}
			if(enemy4.getCenterX() >= 455 && enemy4.getCenterX() <= 460){
				enemy4.shoot();
				enemy4.shoot(-6, 7);
				enemy4.shoot(-4, 7);
				enemy4.shoot(-2, 7);
				Assets.enemy_shot.play(getShotvolume());
			}
			if(enemy5.getCenterX() >= 237 && enemy5.getCenterX() <= 242){
				enemy5.shoot(-4, 7);
				enemy5.shoot(-2, 7);
				enemy5.shoot(4, 7);
				enemy5.shoot(2, 7);
				enemy5.shoot();
				Assets.enemy_shot.play(getShotvolume());
			}
		}
		if(difficulty == 2){
			if(enemy.getCenterX() > 0 && enemy.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemyShootTime + 260){
					enemyShootTime = System.currentTimeMillis();
					enemy.shoot();
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy1.getCenterX() > 0 && enemy1.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy1ShootTime + 260){
					enemy1ShootTime = System.currentTimeMillis();
					enemy1.shoot();
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy2.getCenterX() > 0 && enemy2.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy2ShootTime + 260){
					enemy2ShootTime = System.currentTimeMillis();
					enemy2.shoot();
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy3.getCenterX() > 0 && enemy3.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy3ShootTime + 260){
					enemy3ShootTime = System.currentTimeMillis();
					enemy3.shoot(-3, 7);
					enemy3.shoot(-1, 7);
					enemy3.shoot(1, 7);
					enemy3.shoot(3, 7);
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy4.getCenterX() > 0 && enemy4.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy4ShootTime + 260){
					enemy4ShootTime = System.currentTimeMillis();
					enemy4.shoot(-3, 7);
					enemy4.shoot(-1, 7);
					enemy4.shoot(1, 7);
					enemy4.shoot(3, 7);
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy5.getCenterX() > 0 && enemy5.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy5ShootTime + 260){
					enemy5ShootTime = System.currentTimeMillis();
					enemy5.shoot(-3, 7);
					enemy5.shoot(-1, 7);
					enemy5.shoot(1, 7);
					enemy5.shoot(3, 7);
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy_v.getCenterY() > 0){
				if(System.currentTimeMillis() >= enemyvShootTime + 480){
					enemyvShootTime = System.currentTimeMillis();
					enemy_v.shoot(0, 8);
					enemy_v.shoot(0, -8);
					enemy_v.shoot(4, 4);
					enemy_v.shoot(-4, 4);
					enemy_v.shoot(4, -4);
					enemy_v.shoot(-4, -4);
					enemy_v.shoot(8, 0);
					enemy_v.shoot(-8, 0);
					Assets.enemy_shot.play(getShotvolume());
				}
			}
		}
		if(difficulty == 3){
			if(enemy.getCenterX() > 0 && enemy.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemyShootTime + 130){
					enemyShootTime = System.currentTimeMillis();
					enemy.shoot();
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy1.getCenterX() > 0 && enemy1.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy1ShootTime + 130){
					enemy1ShootTime = System.currentTimeMillis();
					enemy1.shoot();
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy2.getCenterX() > 0 && enemy2.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy2ShootTime + 130){
					enemy2ShootTime = System.currentTimeMillis();
					enemy2.shoot();
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy3.getCenterX() > 0 && enemy3.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy3ShootTime + 130){
					enemy3ShootTime = System.currentTimeMillis();
					enemy3.shoot(-4, 7);
					enemy3.shoot(-2, 7);
					enemy3.shoot(4, 7);
					enemy3.shoot(2, 7);
					enemy3.shoot();
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy4.getCenterX() > 0 && enemy4.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy4ShootTime + 130){
					enemy4ShootTime = System.currentTimeMillis();
					enemy4.shoot(-4, 7);
					enemy4.shoot(-2, 7);
					enemy4.shoot(4, 7);
					enemy4.shoot(2, 7);
					enemy4.shoot();
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy5.getCenterX() > 0 && enemy5.getCenterX() < 480){
				if(System.currentTimeMillis() >= enemy5ShootTime + 130){
					enemy5ShootTime = System.currentTimeMillis();
					enemy5.shoot(-4, 7);
					enemy5.shoot(-2, 7);
					enemy5.shoot(4, 7);
					enemy5.shoot(2, 7);
					enemy5.shoot();
					Assets.enemy_shot.play(getShotvolume());
				}
			}
			if(enemy_v.getCenterY() > 0){
				if(System.currentTimeMillis() >= enemyvShootTime + 240){
					enemyvShootTime = System.currentTimeMillis();
					enemy_v.shoot(0, 8);
					enemy_v.shoot(0, -8);
					enemy_v.shoot(4, 4);
					enemy_v.shoot(-4, 4);
					enemy_v.shoot(4, -4);
					enemy_v.shoot(-4, -4);
					enemy_v.shoot(8, 0);
					enemy_v.shoot(-8, 0);
					Assets.enemy_shot.play(getShotvolume());
				}
			}
		}
	}
	
	private void enemyLevels(int n){
		if((n >= 0 && n <= 10) || (n >= 25 && n < 35) || (n >= 45 && n < 55) || 
				(n >= 70 && n < 75) || (n >= 85 && n < 95)){
			if(enemy.getCenterX() >= 460)
				enemy.setSpeedX(-10);
			if(enemy.getCenterX() <= 20)
				enemy.setSpeedX(10);
			if(enemy.getCenterX() != -1000)
				enemy.update();
		}else{
			splitDie(enemy, -600, 600);
		}
		
		if((n >= 2 && n <= 13) || (n >= 35 && n < 55) || (n >= 60 && n < 70) ||
				(n >= 85 && n < 95)){
			if(enemy1.getCenterX() >= 460)
				enemy1.setSpeedX(-10);
			if(enemy1.getCenterX() <= 20)
				enemy1.setSpeedX(10);
			if(enemy1.getCenterX() != -1000)
				enemy1.update();
		}else{
			splitDie(enemy1, -600, 600);
		}
		
		if( (n >= 5 && n <= 15) || (n >= 20 && n < 30) || (n >= 35 && n < 40) ||
				(n >= 50 && n < 55) || (n >= 65 && n < 80) || (n >= 90)){
			if(enemy2.getCenterX() >= 460)
				enemy2.setSpeedX(-10);
			if(enemy2.getCenterX() <= 20)
				enemy2.setSpeedX(10);
			if(enemy2.getCenterX() != -1000)
				enemy2.update();
		}else{
			splitDie(enemy2, -600, 600);
		}
		
		if((n > 10 && n < 25) || (n >= 35 && n < 45) || (n >= 55 && n < 70) ||
				(n >= 75 && n < 85) || (n >= 95)){
			if(enemy3.getCenterX() >= 460)
				enemy3.setSpeedX(-10);
			if(enemy3.getCenterX() <= 20)
				enemy3.setSpeedX(10);
			if(enemy3.getCenterX() != -1000)
				enemy3.update();
		}else{
			splitDie(enemy3, -600, 600);
		}
		
		if((n > 13 && n < 35) || (n >= 55 && n < 60) || (n >= 70 && n < 85) ||
				(n >= 95)){
			if(enemy4.getCenterX() >= 460)
				enemy4.setSpeedX(-10);
			if(enemy4.getCenterX() <= 20)
				enemy4.setSpeedX(10);
			if(enemy4.getCenterX() != -1000)
				enemy4.update();
		}else{
			splitDie(enemy4, -600, 600);
		}
		
		if((n > 15 && n < 20) || (n >= 30 && n < 35) || (n >= 40 && n < 50) ||
				(n >= 55 && n < 65) || (n >= 80 && n < 90)){
			if(enemy5.getCenterX() >= 460)
				enemy5.setSpeedX(-10);
			if(enemy5.getCenterX() <= 20)
				enemy5.setSpeedX(10);
			if(enemy5.getCenterX() != -1000)
				enemy5.update();
		}else{
			splitDie(enemy5, -600, 600);
		}
	}
	
	private void asteroidsHitPlayer(){
		if (player.getCenterX()-40 >= asteroid1.getX() && player.getCenterX()-40 <= asteroid1.getX()+60
				&& player.getCenterY()-80 >= asteroid1.getY()-40 && player.getCenterY()-80 <= asteroid1.getY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid2.getX() && player.getCenterX()-40 <= asteroid2.getX()+60
				&& player.getCenterY()-80 >= asteroid2.getY()-40 && player.getCenterY()-80 <= asteroid2.getY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid3.getX() && player.getCenterX()-40 <= asteroid3.getX()+60
				&& player.getCenterY()-80 >= asteroid3.getY()-40 && player.getCenterY()-80 <= asteroid3.getY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid4.getX() && player.getCenterX()-40 <= asteroid4.getX()+60
				&& player.getCenterY()-80 >= asteroid4.getY()-40 && player.getCenterY()-80 <= asteroid4.getY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid5.getX() && player.getCenterX()-40 <= asteroid5.getX()+60
				&& player.getCenterY()-80 >= asteroid5.getY()-40 && player.getCenterY()-80 <= asteroid5.getY()+25){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid1medium.getX()-10 && player.getCenterX()-40 <= asteroid1medium.getX()+70
				&& player.getCenterY()-80 >= asteroid1medium.getY()-50 && player.getCenterY()-80 <= asteroid1medium.getY()+35){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}if (player.getCenterX()-40 >= asteroid2medium.getX()-10 && player.getCenterX()-40 <= asteroid2medium.getX()+70
				&& player.getCenterY()-80 >= asteroid2medium.getY()-50 && player.getCenterY()-80 <= asteroid2medium.getY()+35){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid3medium.getX()-10 && player.getCenterX()-40 <= asteroid3medium.getX()+70
				&& player.getCenterY()-80 >= asteroid3medium.getY()-50 && player.getCenterY()-80 <= asteroid3medium.getY()+35){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid4medium.getX()-10 && player.getCenterX()-40 <= asteroid4medium.getX()+70
				&& player.getCenterY()-80 >= asteroid4medium.getY()-50 && player.getCenterY()-80 <= asteroid4medium.getY()+35){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid5medium.getX()-10 && player.getCenterX()-40 <= asteroid5medium.getX()+70
				&& player.getCenterY()-80 >= asteroid5medium.getY()-50 && player.getCenterY()-80 <= asteroid5medium.getY()+35){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid1large.getX()-20 && player.getCenterX()-40 <= asteroid1large.getX()+80
				&& player.getCenterY()-80 >= asteroid1large.getY()-60 && player.getCenterY()-80 <= asteroid1large.getY()+45){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}if (player.getCenterX()-40 >= asteroid2large.getX()-20 && player.getCenterX()-40 <= asteroid2large.getX()+80
				&& player.getCenterY()-80 >= asteroid2large.getY()-60 && player.getCenterY()-80 <= asteroid2large.getY()+45){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid3large.getX()-20 && player.getCenterX()-40 <= asteroid3large.getX()+80
				&& player.getCenterY()-80 >= asteroid3large.getY()-60 && player.getCenterY()-80 <= asteroid3large.getY()+45){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid4large.getX()-20 && player.getCenterX()-40 <= asteroid4large.getX()+80
				&& player.getCenterY()-80 >= asteroid4large.getY()-60 && player.getCenterY()-80 <= asteroid4large.getY()+45){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
		if (player.getCenterX()-40 >= asteroid5large.getX()-20 && player.getCenterX()-40 <= asteroid5large.getX()+80
				&& player.getCenterY()-80 >= asteroid5large.getY()-60 && player.getCenterY()-80 <= asteroid5large.getY()+45){
			if(gethit == true){
				player.explode();
				livesLeft = livesLeft - 2;
				Assets.player_shot.play(getDeathvolume());
				gethit = false;
				frametime = System.currentTimeMillis();
			}else if(isInvincible == true){
				gethit = false;
			}
		}
	}
	
	private void asteroidHitEnemies() {
		asteroidsSmallHitEnemies();
		asteroidsMediumHitEnemies();
		asteroidsLargeHitEnemies();
	}
	
	private void asteroidsSmallHitEnemies(){
		if (enemy.getCenterX()-20 >= asteroid1.getX() && enemy.getCenterX()+20 <= asteroid1.getX()+60
				&& enemy.getCenterY()-40 >= asteroid1.getY()-40 && enemy.getCenterX()+25 <= asteroid1.getY()+25){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid1.getX() && enemy1.getCenterX()+20 <= asteroid1.getX()+60
				&& enemy1.getCenterY()-40 >= asteroid1.getY()-40 && enemy1.getCenterX()+25 <= asteroid1.getY()+25){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid1.getX() && enemy2.getCenterX()+20 <= asteroid1.getX()+60
				&& enemy2.getCenterY()-40 >= asteroid1.getY()-40 && enemy2.getCenterX()+25 <= asteroid1.getY()+25){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid1.getX() && enemy3.getCenterX()+20 <= asteroid1.getX()+60
				&& enemy3.getCenterY()-40 >= asteroid1.getY()-40 && enemy3.getCenterX()+25 <= asteroid1.getY()+25){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid1.getX() && enemy4.getCenterX()+20 <= asteroid1.getX()+60
				&& enemy4.getCenterY()-40 >= asteroid1.getY()-40 && enemy4.getCenterX()+25 <= asteroid1.getY()+25){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid1.getX() && enemy5.getCenterX()+20 <= asteroid1.getX()+60
				&& enemy5.getCenterY()-40 >= asteroid1.getY()-40 && enemy5.getCenterX()+25 <= asteroid1.getY()+25){
			enemy.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid2.getX() && enemy.getCenterX()+20 <= asteroid2.getX()+60
				&& enemy.getCenterY()-40 >= asteroid2.getY()-40 && enemy.getCenterX()+25 <= asteroid2.getY()+25){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid2.getX() && enemy1.getCenterX()+20 <= asteroid2.getX()+60
				&& enemy1.getCenterY()-40 >= asteroid2.getY()-40 && enemy1.getCenterX()+25 <= asteroid2.getY()+25){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid2.getX() && enemy2.getCenterX()+20 <= asteroid2.getX()+60
				&& enemy2.getCenterY()-40 >= asteroid2.getY()-40 && enemy2.getCenterX()+25 <= asteroid2.getY()+25){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid2.getX() && enemy3.getCenterX()+20 <= asteroid2.getX()+60
				&& enemy3.getCenterY()-40 >= asteroid2.getY()-40 && enemy3.getCenterX()+25 <= asteroid2.getY()+25){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid2.getX() && enemy4.getCenterX()+20 <= asteroid2.getX()+60
				&& enemy4.getCenterY()-40 >= asteroid2.getY()-40 && enemy4.getCenterX()+25 <= asteroid2.getY()+25){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid2.getX() && enemy5.getCenterX()+20 <= asteroid2.getX()+60
				&& enemy5.getCenterY()-40 >= asteroid2.getY()-40 && enemy5.getCenterX()+25 <= asteroid2.getY()+25){
			enemy.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid3.getX() && enemy.getCenterX()+20 <= asteroid3.getX()+60
				&& enemy.getCenterY()-40 >= asteroid3.getY()-40 && enemy.getCenterX()+25 <= asteroid3.getY()+25){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid3.getX() && enemy1.getCenterX()+20 <= asteroid1.getX()+60
				&& enemy1.getCenterY()-40 >= asteroid3.getY()-40 && enemy1.getCenterX()+25 <= asteroid3.getY()+25){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid3.getX() && enemy2.getCenterX()+20 <= asteroid3.getX()+60
				&& enemy2.getCenterY()-40 >= asteroid3.getY()-40 && enemy2.getCenterX()+25 <= asteroid3.getY()+25){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid3.getX() && enemy3.getCenterX()+20 <= asteroid3.getX()+60
				&& enemy3.getCenterY()-40 >= asteroid3.getY()-40 && enemy3.getCenterX()+25 <= asteroid3.getY()+25){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid3.getX() && enemy4.getCenterX()+20 <= asteroid3.getX()+60
				&& enemy4.getCenterY()-40 >= asteroid3.getY()-40 && enemy4.getCenterX()+25 <= asteroid3.getY()+25){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid3.getX() && enemy5.getCenterX()+20 <= asteroid3.getX()+60
				&& enemy5.getCenterY()-40 >= asteroid3.getY()-40 && enemy5.getCenterX()+25 <= asteroid3.getY()+25){
			enemy.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid4.getX() && enemy.getCenterX()+20 <= asteroid4.getX()+60
				&& enemy.getCenterY()-40 >= asteroid4.getY()-40 && enemy.getCenterX()+25 <= asteroid4.getY()+25){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid4.getX() && enemy1.getCenterX()+20 <= asteroid4.getX()+60
				&& enemy1.getCenterY()-40 >= asteroid4.getY()-40 && enemy1.getCenterX()+25 <= asteroid4.getY()+25){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid4.getX() && enemy2.getCenterX()+20 <= asteroid4.getX()+60
				&& enemy2.getCenterY()-40 >= asteroid4.getY()-40 && enemy2.getCenterX()+25 <= asteroid4.getY()+25){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid4.getX() && enemy3.getCenterX()+20 <= asteroid4.getX()+60
				&& enemy3.getCenterY()-40 >= asteroid4.getY()-40 && enemy3.getCenterX()+25 <= asteroid4.getY()+25){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid4.getX() && enemy4.getCenterX()+20 <= asteroid4.getX()+60
				&& enemy4.getCenterY()-40 >= asteroid4.getY()-40 && enemy4.getCenterX()+25 <= asteroid4.getY()+25){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid4.getX() && enemy5.getCenterX()+20 <= asteroid4.getX()+60
				&& enemy5.getCenterY()-40 >= asteroid4.getY()-40 && enemy5.getCenterX()+25 <= asteroid4.getY()+25){
			enemy.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid5.getX() && enemy.getCenterX()+20 <= asteroid5.getX()+60
				&& enemy.getCenterY()-40 >= asteroid5.getY()-40 && enemy.getCenterX()+25 <= asteroid5.getY()+25){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid5.getX() && enemy1.getCenterX()+20 <= asteroid5.getX()+60
				&& enemy1.getCenterY()-40 >= asteroid5.getY()-40 && enemy1.getCenterX()+25 <= asteroid5.getY()+25){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid5.getX() && enemy2.getCenterX()+20 <= asteroid5.getX()+60
				&& enemy2.getCenterY()-40 >= asteroid5.getY()-40 && enemy2.getCenterX()+25 <= asteroid5.getY()+25){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid5.getX() && enemy3.getCenterX()+20 <= asteroid5.getX()+60
				&& enemy3.getCenterY()-40 >= asteroid5.getY()-40 && enemy3.getCenterX()+25 <= asteroid5.getY()+25){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid5.getX() && enemy4.getCenterX()+20 <= asteroid5.getX()+60
				&& enemy4.getCenterY()-40 >= asteroid5.getY()-40 && enemy4.getCenterX()+25 <= asteroid5.getY()+25){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid5.getX() && enemy5.getCenterX()+20 <= asteroid5.getX()+60
				&& enemy5.getCenterY()-40 >= asteroid5.getY()-40 && enemy5.getCenterX()+25 <= asteroid5.getY()+25){
			enemy.explode();
			splitDie(enemy5, -600, 600);
		}
	}

	private void asteroidsMediumHitEnemies(){
		if (enemy.getCenterX()-20 >= asteroid1medium.getX()-10 && enemy.getCenterX()+20 <= asteroid1medium.getX()+70
				&& enemy.getCenterY()-40 >= asteroid1medium.getY()-50 && enemy.getCenterX()+25 <= asteroid1medium.getY()+35){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid1medium.getX()-10 && enemy1.getCenterX()+20 <= asteroid1medium.getX()+70
				&& enemy1.getCenterY()-40 >= asteroid1medium.getY()-50 && enemy1.getCenterX()+25 <= asteroid1medium.getY()+35){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid1medium.getX()-10 && enemy2.getCenterX()+20 <= asteroid1medium.getX()+70
				&& enemy2.getCenterY()-40 >= asteroid1medium.getY()-50 && enemy2.getCenterX()+25 <= asteroid1medium.getY()+35){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid1medium.getX()-10 && enemy3.getCenterX()+20 <= asteroid1medium.getX()+70
				&& enemy3.getCenterY()-40 >= asteroid1medium.getY()-50 && enemy3.getCenterX()+25 <= asteroid1medium.getY()+35){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid1medium.getX()-10 && enemy4.getCenterX()+20 <= asteroid1medium.getX()+70
				&& enemy4.getCenterY()-40 >= asteroid1medium.getY()-50 && enemy4.getCenterX()+25 <= asteroid1medium.getY()+35){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid1medium.getX()-10 && enemy5.getCenterX()+20 <= asteroid1medium.getX()+70
				&& enemy5.getCenterY()-40 >= asteroid1medium.getY()-50 && enemy5.getCenterX()+25 <= asteroid1medium.getY()+35){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid2medium.getX()-10 && enemy.getCenterX()+20 <= asteroid2medium.getX()+70
				&& enemy.getCenterY()-40 >= asteroid2medium.getY()-50 && enemy.getCenterX()+25 <= asteroid2medium.getY()+35){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid2medium.getX()-10 && enemy1.getCenterX()+20 <= asteroid2medium.getX()+70
				&& enemy1.getCenterY()-40 >= asteroid2medium.getY()-50 && enemy1.getCenterX()+25 <= asteroid2medium.getY()+35){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid2medium.getX()-10 && enemy2.getCenterX()+20 <= asteroid2medium.getX()+70
				&& enemy2.getCenterY()-40 >= asteroid2medium.getY()-50 && enemy2.getCenterX()+25 <= asteroid2medium.getY()+35){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid2medium.getX()-10 && enemy3.getCenterX()+20 <= asteroid2medium.getX()+70
				&& enemy3.getCenterY()-40 >= asteroid2medium.getY()-50 && enemy3.getCenterX()+25 <= asteroid2medium.getY()+35){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid2medium.getX()-10 && enemy4.getCenterX()+20 <= asteroid2medium.getX()+70
				&& enemy4.getCenterY()-40 >= asteroid2medium.getY()-50 && enemy4.getCenterX()+25 <= asteroid2medium.getY()+35){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid2medium.getX()-10 && enemy5.getCenterX()+20 <= asteroid2medium.getX()+70
				&& enemy5.getCenterY()-40 >= asteroid2medium.getY()-50 && enemy5.getCenterX()+25 <= asteroid2medium.getY()+35){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid3medium.getX()-10 && enemy.getCenterX()+20 <= asteroid3medium.getX()+70
				&& enemy.getCenterY()-40 >= asteroid3medium.getY()-50 && enemy.getCenterX()+25 <= asteroid3medium.getY()+35){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid3medium.getX()-10 && enemy1.getCenterX()+20 <= asteroid3medium.getX()+70
				&& enemy1.getCenterY()-40 >= asteroid3medium.getY()-50 && enemy1.getCenterX()+25 <= asteroid3medium.getY()+35){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid3medium.getX()-10 && enemy2.getCenterX()+20 <= asteroid3medium.getX()+70
				&& enemy2.getCenterY()-40 >= asteroid3medium.getY()-50 && enemy2.getCenterX()+25 <= asteroid3medium.getY()+35){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid3medium.getX()-10 && enemy3.getCenterX()+20 <= asteroid3medium.getX()+70
				&& enemy3.getCenterY()-40 >= asteroid3medium.getY()-50 && enemy3.getCenterX()+25 <= asteroid3medium.getY()+35){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid3medium.getX()-10 && enemy4.getCenterX()+20 <= asteroid3medium.getX()+70
				&& enemy4.getCenterY()-40 >= asteroid3medium.getY()-50 && enemy4.getCenterX()+25 <= asteroid3medium.getY()+35){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid3medium.getX()-10 && enemy5.getCenterX()+20 <= asteroid3medium.getX()+70
				&& enemy5.getCenterY()-40 >= asteroid3medium.getY()-50 && enemy5.getCenterX()+25 <= asteroid3medium.getY()+35){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid4medium.getX()-10 && enemy.getCenterX()+20 <= asteroid4medium.getX()+70
				&& enemy.getCenterY()-40 >= asteroid4medium.getY()-50 && enemy.getCenterX()+25 <= asteroid4medium.getY()+35){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid4medium.getX()-10 && enemy1.getCenterX()+20 <= asteroid4medium.getX()+70
				&& enemy1.getCenterY()-40 >= asteroid4medium.getY()-50 && enemy1.getCenterX()+25 <= asteroid4medium.getY()+35){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid4medium.getX()-10 && enemy2.getCenterX()+20 <= asteroid4medium.getX()+70
				&& enemy2.getCenterY()-40 >= asteroid4medium.getY()-50 && enemy2.getCenterX()+25 <= asteroid4medium.getY()+35){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid4medium.getX()-10 && enemy3.getCenterX()+20 <= asteroid4medium.getX()+70
				&& enemy3.getCenterY()-40 >= asteroid4medium.getY()-50 && enemy3.getCenterX()+25 <= asteroid4medium.getY()+35){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid4medium.getX()-10 && enemy4.getCenterX()+20 <= asteroid4medium.getX()+70
				&& enemy4.getCenterY()-40 >= asteroid4medium.getY()-50 && enemy4.getCenterX()+25 <= asteroid4medium.getY()+35){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid4medium.getX()-10 && enemy5.getCenterX()+20 <= asteroid4medium.getX()+70
				&& enemy5.getCenterY()-40 >= asteroid4medium.getY()-50 && enemy5.getCenterX()+25 <= asteroid4medium.getY()+35){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid5medium.getX()-10 && enemy.getCenterX()+20 <= asteroid5medium.getX()+70
				&& enemy.getCenterY()-40 >= asteroid5medium.getY()-50 && enemy.getCenterX()+25 <= asteroid5medium.getY()+35){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid5medium.getX()-10 && enemy1.getCenterX()+20 <= asteroid5medium.getX()+70
				&& enemy1.getCenterY()-40 >= asteroid5medium.getY()-50 && enemy1.getCenterX()+25 <= asteroid5medium.getY()+35){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid5medium.getX()-10 && enemy2.getCenterX()+20 <= asteroid5medium.getX()+70
				&& enemy2.getCenterY()-40 >= asteroid5medium.getY()-50 && enemy2.getCenterX()+25 <= asteroid5medium.getY()+35){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid5medium.getX()-10 && enemy3.getCenterX()+20 <= asteroid5medium.getX()+70
				&& enemy3.getCenterY()-40 >= asteroid5medium.getY()-50 && enemy3.getCenterX()+25 <= asteroid5medium.getY()+35){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid5medium.getX()-10 && enemy4.getCenterX()+20 <= asteroid5medium.getX()+70
				&& enemy4.getCenterY()-40 >= asteroid5medium.getY()-50 && enemy4.getCenterX()+25 <= asteroid5medium.getY()+35){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid5medium.getX()-10 && enemy5.getCenterX()+20 <= asteroid5medium.getX()+70
				&& enemy5.getCenterY()-40 >= asteroid5medium.getY()-50 && enemy5.getCenterX()+25 <= asteroid5medium.getY()+35){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
	}
	
	private void asteroidsLargeHitEnemies(){
		if (enemy.getCenterX()-20 >= asteroid1large.getX()-20 && enemy.getCenterX()+20 <= asteroid1large.getX()+80
				&& enemy.getCenterY()-40 >= asteroid1large.getY()-60 && enemy.getCenterX()+25 <= asteroid1large.getY()+45){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid1large.getX()-20 && enemy1.getCenterX()+20 <= asteroid1large.getX()+80
				&& enemy1.getCenterY()-40 >= asteroid1large.getY()-60 && enemy1.getCenterX()+25 <= asteroid1large.getY()+45){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid1large.getX()-20 && enemy2.getCenterX()+20 <= asteroid1large.getX()+80
				&& enemy2.getCenterY()-40 >= asteroid1large.getY()-60 && enemy2.getCenterX()+25 <= asteroid1large.getY()+45){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid1large.getX()-20 && enemy3.getCenterX()+20 <= asteroid1large.getX()+80
				&& enemy3.getCenterY()-40 >= asteroid1large.getY()-60 && enemy3.getCenterX()+25 <= asteroid1large.getY()+45){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid1large.getX()-20 && enemy4.getCenterX()+20 <= asteroid1large.getX()+80
				&& enemy4.getCenterY()-40 >= asteroid1large.getY()-60 && enemy4.getCenterX()+25 <= asteroid1large.getY()+45){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid1large.getX()-20 && enemy5.getCenterX()+20 <= asteroid1large.getX()+80
				&& enemy5.getCenterY()-40 >= asteroid1large.getY()-60 && enemy5.getCenterX()+25 <= asteroid1large.getY()+45){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid2large.getX()-20 && enemy.getCenterX()+20 <= asteroid2large.getX()+80
				&& enemy.getCenterY()-40 >= asteroid2large.getY()-60 && enemy.getCenterX()+25 <= asteroid2large.getY()+45){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid2large.getX()-20 && enemy1.getCenterX()+20 <= asteroid2large.getX()+80
				&& enemy1.getCenterY()-40 >= asteroid2large.getY()-60 && enemy1.getCenterX()+25 <= asteroid2large.getY()+45){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid2large.getX()-20 && enemy2.getCenterX()+20 <= asteroid2large.getX()+80
				&& enemy2.getCenterY()-40 >= asteroid2large.getY()-60 && enemy2.getCenterX()+25 <= asteroid2large.getY()+45){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid2large.getX()-20 && enemy3.getCenterX()+20 <= asteroid2large.getX()+80
				&& enemy3.getCenterY()-40 >= asteroid2large.getY()-60 && enemy3.getCenterX()+25 <= asteroid2large.getY()+45){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid2large.getX()-20 && enemy4.getCenterX()+20 <= asteroid2large.getX()+80
				&& enemy4.getCenterY()-40 >= asteroid2large.getY()-60 && enemy4.getCenterX()+25 <= asteroid2large.getY()+45){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid2large.getX()-20 && enemy5.getCenterX()+20 <= asteroid2large.getX()+80
				&& enemy5.getCenterY()-40 >= asteroid2large.getY()-60 && enemy5.getCenterX()+25 <= asteroid2large.getY()+45){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid3large.getX()-20 && enemy.getCenterX()+20 <= asteroid3large.getX()+80
				&& enemy.getCenterY()-40 >= asteroid3large.getY()-60 && enemy.getCenterX()+25 <= asteroid3large.getY()+45){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid3large.getX()-20 && enemy1.getCenterX()+20 <= asteroid3large.getX()+80
				&& enemy1.getCenterY()-40 >= asteroid3large.getY()-60 && enemy1.getCenterX()+25 <= asteroid3large.getY()+45){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid3large.getX()-20 && enemy2.getCenterX()+20 <= asteroid3large.getX()+80
				&& enemy2.getCenterY()-40 >= asteroid3large.getY()-60 && enemy2.getCenterX()+25 <= asteroid3large.getY()+45){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid3large.getX()-20 && enemy3.getCenterX()+20 <= asteroid3large.getX()+80
				&& enemy3.getCenterY()-40 >= asteroid3large.getY()-60 && enemy3.getCenterX()+25 <= asteroid3large.getY()+45){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid3large.getX()-20 && enemy4.getCenterX()+20 <= asteroid3large.getX()+80
				&& enemy4.getCenterY()-40 >= asteroid3large.getY()-60 && enemy4.getCenterX()+25 <= asteroid3large.getY()+45){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid3large.getX()-20 && enemy5.getCenterX()+20 <= asteroid3large.getX()+80
				&& enemy5.getCenterY()-40 >= asteroid3large.getY()-60 && enemy5.getCenterX()+25 <= asteroid3large.getY()+45){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid4large.getX()-20 && enemy.getCenterX()+20 <= asteroid4large.getX()+80
				&& enemy.getCenterY()-40 >= asteroid4large.getY()-60 && enemy.getCenterX()+25 <= asteroid4large.getY()+45){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid4large.getX()-20 && enemy1.getCenterX()+20 <= asteroid4large.getX()+80
				&& enemy1.getCenterY()-40 >= asteroid4large.getY()-60 && enemy1.getCenterX()+25 <= asteroid4large.getY()+45){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid4large.getX()-20 && enemy2.getCenterX()+20 <= asteroid4large.getX()+80
				&& enemy2.getCenterY()-40 >= asteroid4large.getY()-60 && enemy2.getCenterX()+25 <= asteroid4large.getY()+45){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid4large.getX()-20 && enemy3.getCenterX()+20 <= asteroid4large.getX()+80
				&& enemy3.getCenterY()-40 >= asteroid4large.getY()-60 && enemy3.getCenterX()+25 <= asteroid4large.getY()+45){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid4large.getX()-20 && enemy4.getCenterX()+20 <= asteroid4large.getX()+80
				&& enemy4.getCenterY()-40 >= asteroid4large.getY()-60 && enemy4.getCenterX()+25 <= asteroid4large.getY()+45){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid5large.getX()-20 && enemy5.getCenterX()+20 <= asteroid5large.getX()+80
				&& enemy5.getCenterY()-40 >= asteroid5large.getY()-60 && enemy5.getCenterX()+25 <= asteroid5large.getY()+45){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
		if (enemy.getCenterX()-20 >= asteroid5large.getX()-20 && enemy.getCenterX()+20 <= asteroid5large.getX()+80
				&& enemy.getCenterY()-40 >= asteroid5large.getY()-60 && enemy.getCenterX()+25 <= asteroid5large.getY()+45){
			enemy.explode();
			splitDie(enemy, -600, 600);
		}
		if (enemy1.getCenterX()-20 >= asteroid5large.getX()-20 && enemy1.getCenterX()+20 <= asteroid5large.getX()+80
				&& enemy1.getCenterY()-40 >= asteroid5large.getY()-60 && enemy1.getCenterX()+25 <= asteroid5large.getY()+45){
			enemy1.explode();
			splitDie(enemy1, -600, 600);
		}
		if (enemy2.getCenterX()-20 >= asteroid5large.getX()-20 && enemy2.getCenterX()+20 <= asteroid5large.getX()+80
				&& enemy2.getCenterY()-40 >= asteroid5large.getY()-60 && enemy2.getCenterX()+25 <= asteroid5large.getY()+45){
			enemy2.explode();
			splitDie(enemy2, -600, 600);
		}
		if (enemy3.getCenterX()-20 >= asteroid5large.getX()-20 && enemy3.getCenterX()+20 <= asteroid5large.getX()+80
				&& enemy3.getCenterY()-40 >= asteroid5large.getY()-60 && enemy3.getCenterX()+25 <= asteroid5large.getY()+45){
			enemy3.explode();
			splitDie(enemy3, -600, 600);
		}
		if (enemy4.getCenterX()-20 >= asteroid5large.getX()-20 && enemy4.getCenterX()+20 <= asteroid5large.getX()+80
				&& enemy4.getCenterY()-40 >= asteroid5large.getY()-60 && enemy4.getCenterX()+25 <= asteroid5large.getY()+45){
			enemy4.explode();
			splitDie(enemy4, -600, 600);
		}
		if (enemy5.getCenterX()-20 >= asteroid5large.getX()-20 && enemy5.getCenterX()+20 <= asteroid5large.getX()+80
				&& enemy5.getCenterY()-40 >= asteroid5large.getY()-60 && enemy5.getCenterX()+25 <= asteroid5large.getY()+45){
			enemy5.explode();
			splitDie(enemy5, -600, 600);
		}
	}
	
	private boolean enemypLevels(){
		if(score%4500 == 0 || score%4500 == 10 || score%4500 == 20 ||
				score%4500 == 30 ||	score%4500 == 40 ||	score%4500 == 50 ||
				score%4500 == 60 ||	score%4500 == 70 ||	score%4500 == 80 ||
				score%4500 == 90 ||	score%4500 == 100 || score%4500 == 110 ||
				score%4500 == 120 || score%4500 == 130 || score%4500 == 140 ||
				score%4500 == 150 || score%4500 == 160 || score%4500 == 170 ||
				score%4500 == 180 || score%4500 == 190 || score%4500 == 200 ||
				score%4500 == 210 || score%4500 == 220 || score%4500 == 230 ||
				score%4500 == 240 || score%4500 == 250 || score%4500 == 260 ||
				score%4500 == 270 || score%4500 == 280 || score%4500 == 290 ||
				score%4500 == 300 || score%4500 == 310 || score%4500 == 320 ||
				score%4500 == 330 || score%4500 == 340 || score%4500 == 350 ||
				score%4500 == 360 || score%4500 == 370 || score%4500 == 380 ||
				score%4500 == 390 || score%4500 == 400 || score%4500 == 410 ||
				score%4500 == 420 || score%4500 == 430 || score%4500 == 440 ||
				score%4500 == 450 || score%4500 == 460 || score%4500 == 470 ||
				score%4500 == 480 || score%4500 == 490 || score%4500 == 500){
			return true;
		}
		else{
			return false;
		}
	}
	
	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 240, 280, paint);
		g.drawString("Helpful Hint:", 240, 430, paint);
		
		if(hint == 0){
			g.drawString("Tap anywhere on the screen", 240, 500, paint);
			g.drawString("to teleport instantly.", 240, 535, paint);
		}
		else if(hint == 1){
			g.drawString("More points are gained with", 240, 500, paint);
			g.drawString("higher difficulties.", 240, 535, paint);
		}
		else if(hint == 2){
			g.drawString("Travel downwards to avoid", 240, 500, paint);
			g.drawString("enemy projectiles.", 240, 535, paint);
		}
		else if(hint == 3)
			g.drawString("WINNERS DON'T DO DRUGS.", 240, 500, paint);
		else if(hint == 4){
			g.drawString("More powerful enemies appear", 240, 500, paint);
			g.drawString("after level 10.", 240, 535, paint);
		}
		else if(hint == 5)
			g.drawString("Beware the all mighty cashew.", 240, 500, paint);
		else if(hint == 6)
			g.drawString("Your ship can only sustain 6 hits.", 240, 500, paint);
		else if(hint == 7)
			g.drawString("Certain enemies are invincible.", 240, 500, paint);
		else if(hint == 8){
			g.drawString("You can pause the game by", 240, 500, paint);
			g.drawString("using the button in the upper", 240, 535, paint);
			g.drawString("left corner or the back button", 240, 570, paint);
		}
		else if(hint == 9){
			g.drawString("If in a jam, teleport to the", 240, 500, paint);
			g.drawString("top of the screen", 240, 535, paint);
		}
		else if(hint == 10){
			g.drawString("If your ship is red, you are", 240, 500, paint);
			g.drawString("currently invincible.", 240, 535, paint);
		}
		else{
			g.drawString("Drag on the screen to auto fire.", 240, 500, paint);
		}
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawImage(resume, 40, 265);
		g.drawImage(menu_big, 40, 460);
		if(mutemusic == false){
			g.drawImage(music, 40, 645);
		}else{
			g.drawImage(music_mute, 40, 645);
		}
		if(mutesound == false){
			g.drawImage(sound, 308, 645);
		}else{
			g.drawImage(sound_mute, 308, 645);
		}

	}

	private void drawGameOverUI() {
		Assets.bonus_enemy.pause();
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME", 230, 330, paint2);
		g.drawString("OVER", 230, 440, paint2);
		if(level >= 100)
			g.drawString("You Reached Level: " + Integer.toString(100), 230, 520, paint);
		else
			g.drawString("You Reached Level: " + Integer.toString(level), 230, 520, paint);
		g.drawString("Your Score Was: ", 230, 560, paint);
		g.drawString(Integer.toString(score), 230, 600, paint);
		if(difficulty == 0){
			g.drawString("x 0.8", 230, 635, paint);
		}
		if(difficulty == 1){
			g.drawString("x 1.0", 230, 635, paint);
		}
		if(difficulty == 2){
			g.drawString("x 1.5", 230, 635, paint);
		}
		if(difficulty == 3){
			g.drawString("x 2.0", 230, 635, paint);
		}
		g.drawString(Integer.toString(finalScore), 230, 670, paint);
		g.drawImage(menu_button, 280, 60);
		g.drawImage(retry_button, 0, 60);

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if(start == false){
			state = GameState.Ready;
		}else if(state == GameState.GameOver){
			state = GameState.GameOver;
		}else{
			state = GameState.Paused;
		}
	}
	
	public void unpause() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game, fis, fos));

	}

	public static Player getPlayer() {
		
		return player;
		
	}
	
	public static boolean getMuteSound() {
		
		return mutesound;
		
	}
	
	public static boolean getMuteMusic() {
		
		return mutemusic;
		
	}

}