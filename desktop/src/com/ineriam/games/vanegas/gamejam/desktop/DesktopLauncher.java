package com.ineriam.games.vanegas.gamejam.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ineriam.games.vanegas.gamejam.GdxGame;

public class DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		int id = 4;

		int nokiaScreenWidth = 88;
		int nokiaScreenHeight = 50; // 48px, but the interface needs more height to display the text
		int windowWidth = 0;
		int windowHeight = 0;

		switch (id) {
			case 0:
				// 88x50
				windowWidth = nokiaScreenWidth;
				break;

			case 1:
				// 480x272
				windowWidth = 480;
				break;

			case 2:
				// 800x454
				windowWidth = 800;
				break;

			case 3:
				// 960x545
				windowWidth = 960;
				break;

			case 4:
				// 1080x613
				windowWidth = 1080;
				break;
		}

		windowHeight = (nokiaScreenHeight * windowWidth) / nokiaScreenWidth;
		config.width = windowWidth;
		config.height = windowHeight;
		config.resizable = false;
		config.title = "Vanegas, v1.0 2019, INERIAM www.ineriam.com, Nokia 3310 JAM (https://itch.io/jam/3310jam)";

		// gradlew desktop:run
		// gradlew desktop:dist build jar

		new LwjglApplication(new GdxGame(), config);
	}
}
