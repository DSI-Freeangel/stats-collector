package org.stats.ui.window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.OverlayLayout;
import javax.swing.border.EmptyBorder;

import org.stats.core.config.Application;
import org.stats.ui.logger.SplashScreenLogAppender;
import org.stats.utils.WindowUtils;

public class SplashWindow extends JWindow {
	private static final long serialVersionUID = 5903118505565126254L;
	private static final String IMAGE_URL = "./images/splash.jpg";

	public SplashWindow(Frame f) {
		super(f);
		JLabel image = new JLabel(new ImageIcon(IMAGE_URL));
		image.setLayout(new OverlayLayout(image));
		JLabel log = new JLabel("");
		log.setBorder(new EmptyBorder(10, 10, 10, 10));
		log.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
		image.add(log);
		getContentPane().add(image, BorderLayout.CENTER);
		pack();
		SplashScreenLogAppender.setLoggingArea(log);
		WindowUtils.moveToCenter(this);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				dispose();
				Application.getInstance().exit();
			}
		});
		setVisible(true);
	}
}