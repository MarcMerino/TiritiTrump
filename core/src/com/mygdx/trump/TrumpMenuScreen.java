package com.mygdx.trump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class TrumpMenuScreen implements Screen {

    final Trump game;

    OrthographicCamera camera;

    public TrumpMenuScreen(final Trump game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Trump.WIDTH, Trump.HEIGHT);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(new Texture(Gdx.files.internal("capitolioV2.jpg")), 0, 0);

        game.font.setColor(Color.BLACK);
        game.font.draw(game.batch, "Welcome to America! ", 50, 550);
        game.font.draw(game.batch, "Press any key to start a revolution!", 50, 525);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new TrumpScreen(game));
            dispose();
        }

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

    }
}