package com.javadocmd.interstellar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.javadocmd.interstellar.InterstellarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Interstellar, Inc.";
		config.width = 1024;
		config.height = 768;
		config.resizable = false;
		
//		config.x = 2000;
//		config.y = 200;
		
		new LwjglApplication(new InterstellarGame(), config);
	}
}
