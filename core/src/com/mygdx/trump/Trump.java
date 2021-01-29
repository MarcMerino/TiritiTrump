package com.mygdx.trump;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Trump extends Game {

    public static final int WIDTH = 768, HEIGHT = 576;

    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();

        font = new BitmapFont();
        this.setScreen(new TrumpMenuScreen(this));
    }
}
