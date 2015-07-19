package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public final class CancelButtonActionListener implements ActionListener {
	private JFrame parentWindow;

	public CancelButtonActionListener(JFrame parentWindow) {
		this.parentWindow = parentWindow;
	}

	public void actionPerformed(ActionEvent event) {
		parentWindow.setVisible(false);
		parentWindow.dispose();
	}
}