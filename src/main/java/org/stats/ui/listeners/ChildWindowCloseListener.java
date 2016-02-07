package org.stats.ui.listeners;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class ChildWindowCloseListener extends WindowAdapter implements ActionListener {
	private JFrame parentWindow;

	@Override
	public void windowClosing(WindowEvent e) {
		action(e.getWindow());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		action(parentWindow);
	}

	private void action(Window window) {
		window.setVisible(false);
		window.dispose();
	}

	public JFrame getParentWindow() {
		return parentWindow;
	}

	public ChildWindowCloseListener setParentWindow(JFrame parentWindow) {
		this.parentWindow = parentWindow;
		return this;
	}

}
