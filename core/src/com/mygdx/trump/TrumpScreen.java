package com.mygdx.trump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Rectangle;

public class TrumpScreen implements Screen {
	public BitmapFont font;

	private static final int FRAME_COLS = 6, FRAME_ROWS = 4;
	Trump game;

	// Objects used
	Animation<TextureRegion> walkDownAnimation;
	Animation<TextureRegion> walkLeftAnimation;
	Animation<TextureRegion> walkUpAnimation;
	Animation<TextureRegion> walkRightAnimation;

	OrthographicCamera camera;

	Rectangle trump;
	Texture trumpSprite;

	Texture walkSheet;

	// A variable for tracking elapsed time for the animation
	float stateTime;

	public TrumpScreen(Trump game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Trump.WIDTH, Trump.HEIGHT);

		// Load the sprite sheet as a Texture
		walkSheet = new Texture(Gdx.files.internal("TrumpSpritesheet.png"));

		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);

		// Place the regions into a 1D array in the correct order, starting from the top
		// left, going across first. The Animation constructor requires a 1D array.
		TextureRegion[] walkDownFrames = new TextureRegion[FRAME_COLS];
		TextureRegion[] walkLeftFrames = new TextureRegion[FRAME_COLS];
		TextureRegion[] walkUpFrames = new TextureRegion[FRAME_COLS];
		TextureRegion[] walkRightFrames = new TextureRegion[FRAME_COLS];

		int index = 0;

		for (int i = 0; i < FRAME_ROWS; i++) {
			index = 0;
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i == 0)
					walkDownFrames[index++] = tmp[i][j];
				if (i == 1)
					walkRightFrames[index++] = tmp[i][j];
				if (i == 2)
					walkUpFrames[index++] = tmp[i][j];
				if (i == 3)
					walkLeftFrames[index++] = tmp[i][j];
			}
		}

		// Initialize the Animation with the frame interval and array of frames
		walkDownAnimation = new Animation<TextureRegion>(0.076f, walkDownFrames);
		walkLeftAnimation = new Animation<TextureRegion>(0.076f, walkLeftFrames);
		walkUpAnimation = new Animation<TextureRegion>(0.076f, walkUpFrames);
		walkRightAnimation = new Animation<TextureRegion>(0.076f, walkRightFrames);

		// Crear el personatge
		trump = new Rectangle();
		trump.x = 400;
		trump.y = 400;
		trump.height = 100;
		trump.width = 100;

		stateTime = 0f;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		// Get current frame of animation for the current stateTime
		TextureRegion currentDownFrame = walkDownAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentRightFrame = walkRightAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentUpFrame = walkUpAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentLeftFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
		game.batch.begin();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			game.batch.draw(walkLeftAnimation.getKeyFrame(stateTime, true), trump.x, trump.y);
			trump.x -= 100 * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			game.batch.draw(walkRightAnimation.getKeyFrame(stateTime, true), trump.x, trump.y);
			trump.x += 120 * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			game.batch.draw(walkUpAnimation.getKeyFrame(stateTime, true), trump.x, trump.y);
			trump.y += 120 * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			game.batch.draw(walkDownAnimation.getKeyFrame(stateTime, true), trump.x, trump.y);
			trump.y -= 100 * Gdx.graphics.getDeltaTime();
		} else {
			game.batch.draw(walkDownAnimation.getKeyFrames()[1], trump.x, trump.y, trump.width, trump.height);
		}

		game.batch.end();


	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		game.batch.dispose();

	}
}
