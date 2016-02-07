package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.stats.core.config.Application;

@Component
@Scope(value = "prototype")
public class WindowCloseListener extends ChildWindowCloseListener {
	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		Application.getInstance().exit();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Application.getInstance().exit();
	}
}
