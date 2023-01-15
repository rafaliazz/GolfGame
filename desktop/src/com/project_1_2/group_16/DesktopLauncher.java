package com.project_1_2.group_16;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * Helper class for launching the game.
 */
public class DesktopLauncher {

	public static void main(String[] arg) {
		// launch the game
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(Lwjgl3ApplicationConfiguration.getDisplayMode().width,
				Lwjgl3ApplicationConfiguration.getDisplayMode().height);
		config.setForegroundFPS(30);
		config.useVsync(false);
		new Lwjgl3Application(new App(), config);
	}
}
