package org.stats.ui.listeners;

import java.awt.event.WindowEvent;

import org.stats.core.config.Application;

public class WindowCloseListener extends ChildWindowCloseListener {
	 @Override
     public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
		Application.getInstance().exit();
     }
}
