package org.stats.ui.window;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stats.ui.elements.MainMenu;
import org.stats.ui.listeners.WindowCloseListener;
import org.stats.utils.WindowUtils;

@Component
public class MainWindow extends JFrame {
	private static final long serialVersionUID = 55744428503846418L;
	private static final String APPLICATION_TITLE = "Stats collector.";
	@Autowired
	private MainMenu mainMenu;
	@Autowired
	private WindowCloseListener windowCloseListener;

	public MainWindow() throws HeadlessException {
		super(APPLICATION_TITLE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// TODO remove after adding some content to new window
		this.setMinimumSize(new Dimension(400, 100));
		WindowUtils.moveToCenter(this);
	}

	public void init() {
		this.addWindowListener(getWindowCloseListener());
		this.setJMenuBar(getMainMenu().init(this));
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public WindowCloseListener getWindowCloseListener() {
		return windowCloseListener;
	}

	public void setWindowCloseListener(WindowCloseListener windowCloseListener) {
		this.windowCloseListener = windowCloseListener;
	}

}
