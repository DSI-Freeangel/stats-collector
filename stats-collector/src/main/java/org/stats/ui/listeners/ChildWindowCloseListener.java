package org.stats.ui.listeners;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChildWindowCloseListener extends WindowAdapter {
	 @Override
     public void windowClosing(WindowEvent e) {
        Window window = e.getWindow();
		window.setVisible(false);
		window.dispose();
     }
}
