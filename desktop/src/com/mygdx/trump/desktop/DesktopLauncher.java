package com.mygdx.trump.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.trump.Trump;
import com.mygdx.trump.TrumpScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Trump.WIDTH;
		config.height = Trump.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new Trump(), config);
	}
}
