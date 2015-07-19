package org.stats;

import java.awt.Frame;

import org.stats.core.config.Application;
import org.stats.ui.window.MainWindow;
import org.stats.ui.window.SplashWindow;

public class AppStarter {
	public static void main(String[] args) {
		SplashWindow splash = new SplashWindow(new Frame());
		Application.getInstance().init();
		MainWindow mainWindow = Application.getInstance().getBean(MainWindow.class);
		splash.setVisible(false);
		mainWindow.setVisible(true);
	}
}
