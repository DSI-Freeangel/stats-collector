package org.stats.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class WindowUtils {
	public static void moveToCenter(Window window) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = window.getPreferredSize();
		window.setLocation(screenSize.width / 2 - (labelSize.width / 2),
				screenSize.height / 2 - (labelSize.height / 2));
	}
}
