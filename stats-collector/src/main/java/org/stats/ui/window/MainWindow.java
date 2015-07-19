package org.stats.ui.window;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.stats.ui.elements.MainMenu;
import org.stats.ui.listeners.WindowCloseListener;
import org.stats.utils.WindowUtils;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 55744428503846418L;
	private static final String APPLICATION_TITLE = "Stats collector.";

	public MainWindow() throws HeadlessException {
		super(APPLICATION_TITLE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowCloseListener());
		this.setJMenuBar(new MainMenu(this));
		//TODO remove after adding some content to new window
		this.setMinimumSize(new Dimension(400, 100));
		WindowUtils.moveToCenter(this);
	}

}
