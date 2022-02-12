package com.fuadmuradov.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture bird;
	Texture bee1;
	Texture bee2;
	Texture bee3;
	float birdY = 0;
	float birdX = 0;
	int gamestate = 0;
	float velocity = 0;
	float gravity = 0.3f;
	float[] enemyX = new float[4];
	float[] enemyOfset1= new float[4];
	float[] enemyOfset2= new float[4];
	float[] enemyOfset3= new float[4];
	float distance =0;
	float enemyvelocity = 5;
	Random random;
	Circle birdcircle;
	Circle[] enemyCircles1;
	Circle[] enemyCircles2;
	Circle[] enemyCircles3;
	int score =0;
	int scoredEnemy = 0;
	BitmapFont font;
	BitmapFont font2;
	ShapeRenderer shapeRenderer;


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("background1.png");
			bird = new Texture("redbee.png");
				bee1 = new Texture("ufo.png");
					bee2 = new Texture("ufo.png");
						bee3 = new Texture("ufo.png");
							birdX = Gdx.graphics.getWidth()/2 - Gdx.graphics.getHeight()*0.6f;
								birdY = Gdx.graphics.getHeight()/3;
									distance = Gdx.graphics.getWidth()/2;
								random = new Random();
							birdcircle = new Circle();
						enemyCircles1 = new Circle[4];
					enemyCircles2 = new Circle[4];
				enemyCircles3 = new Circle[4];
			shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		font.setColor(Color.CORAL);
		font.getData().setScale(4);

		font2 = new BitmapFont();
		font2.setColor(Color.CORAL);
		font2.getData().setScale(7);

		for(int i=0; i<4; i++){
			enemyX[i] = Gdx.graphics.getWidth()+ i * distance;
				enemyOfset1[i] = (random.nextFloat()) * (Gdx.graphics.getHeight()-100);
					enemyOfset2[i] = (random.nextFloat()) * (Gdx.graphics.getHeight()-100);
						enemyOfset3[i] =  (random.nextFloat()) * (Gdx.graphics.getHeight()-100);

					enemyCircles1[i] = new Circle();
				enemyCircles2[i] = new Circle();
			enemyCircles3[i] = new Circle();
		}
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if(gamestate == 1){
			if(enemyX[scoredEnemy] < Gdx.graphics.getWidth()/2-bird.getHeight()){
				score++;
				if(scoredEnemy<3){
					scoredEnemy++;

				}else{
					scoredEnemy=0;
				}
			}


			if(Gdx.input.justTouched()){
				velocity=-8;
			}

			for(int i=0; i<4; i++){
				if(enemyX[i] < 0){
					enemyX[i] = enemyX[i] + 4 * distance;
					enemyOfset1[i] = (random.nextFloat()) * (Gdx.graphics.getHeight()-100);
					enemyOfset2[i] = (random.nextFloat()) * (Gdx.graphics.getHeight()-100);
					enemyOfset3[i] =  (random.nextFloat()) * (Gdx.graphics.getHeight()-100);
				}else{
					enemyX[i] = enemyX[i] - enemyvelocity;
				}

				batch.draw(bee1, enemyX[i],      enemyOfset1[i], Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/9);
				batch.draw(bee2, enemyX[i]-80, enemyOfset2[i], Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/9);
				batch.draw(bee2, enemyX[i]+80, enemyOfset3[i], Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/9);

				enemyCircles1[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth()/24, enemyOfset1[i] +
						Gdx.graphics.getHeight()/18, Gdx.graphics.getWidth()/24);

				enemyCircles2[i] = new Circle(enemyX[i ]- 80 +Gdx.graphics.getWidth()/24, enemyOfset2[i]
						+ Gdx.graphics.getHeight()/18, Gdx.graphics.getWidth()/24);

				enemyCircles3[i] = new Circle(enemyX[i]+ 80 +Gdx.graphics.getWidth()/24, enemyOfset3[i]
						+ Gdx.graphics.getHeight()/18, Gdx.graphics.getWidth()/24);
			}

			if(birdY>=0){
				velocity = velocity+gravity;
				birdY = birdY-velocity;
			}else{
				gamestate = 2;
			}
		}
		else if(gamestate==0){
			if(Gdx.input.justTouched()){
				gamestate = 1;
			}
		} else if(gamestate==2){
			font2.draw(batch, "GAME OVER! Tap To Play Again:)",120, Gdx.graphics.getHeight()/2);
			if(Gdx.input.justTouched()){
				gamestate = 1;
				birdY = Gdx.graphics.getHeight()/3;
				for(int i=0; i<4; i++){
					enemyX[i] = Gdx.graphics.getWidth()+ i * distance;
					enemyOfset1[i] = (random.nextFloat()) * (Gdx.graphics.getHeight()-100);
					enemyOfset2[i] = (random.nextFloat()) * (Gdx.graphics.getHeight()-100);
					enemyOfset3[i] =  (random.nextFloat()) * (Gdx.graphics.getHeight()-100);

					enemyCircles1[i] = new Circle();
					enemyCircles2[i] = new Circle();
					enemyCircles3[i] = new Circle();
				}
				score = 0;
			}
			velocity = 0;

			scoredEnemy = 0;
		}



		batch.draw(bird, birdX, birdY, Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/9);
		font.draw(batch,"Score: " + String.valueOf(score), 100,200);

		batch.end();
		birdcircle.set(birdX+Gdx.graphics.getWidth()/24, birdY+Gdx.graphics.getHeight()/18, Gdx.graphics.getWidth()/24);
	//	shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
	//	shapeRenderer.setColor(Color.BLACK);
	//	shapeRenderer.circle(birdcircle.x, birdcircle.y, Gdx.graphics.getWidth()/24);


		for(int i=0; i< 4; i++){
		/*	shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/24, enemyOfset1[i] +
					Gdx.graphics.getHeight()/18, Gdx.graphics.getWidth()/24);

			shapeRenderer.circle(enemyX[i] - 80 + Gdx.graphics.getWidth()/24, enemyOfset2[i] +
					Gdx.graphics.getHeight()/18, Gdx.graphics.getWidth()/24);

			shapeRenderer.circle(enemyX[i] + 80 + Gdx.graphics.getWidth()/24, enemyOfset3[i] +
					Gdx.graphics.getHeight()/18, Gdx.graphics.getWidth()/24);

		 */
			if(Intersector.overlaps(birdcircle, enemyCircles1[i]) || Intersector.overlaps(birdcircle, enemyCircles2[i]) || Intersector.overlaps(birdcircle, enemyCircles3[i])){
				gamestate = 2;
			}
		}
		shapeRenderer.end();


	}

	
	@Override
	public void dispose () {
	//	batch.dispose();
	//	img.dispose();
	}
}
