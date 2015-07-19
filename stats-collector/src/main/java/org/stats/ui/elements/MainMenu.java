package org.stats.ui.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.stats.core.config.Application;
import org.stats.ui.listeners.OpenEditAccountListener;
import org.stats.ui.listeners.OpenEditCurrencyListener;
import org.stats.ui.listeners.OpenEditOrganizationListener;

public class MainMenu extends JMenuBar {
	private static final long serialVersionUID = 4661724205948081350L;
	private JFrame parentWindow;
	
	public MainMenu(JFrame window) {
		super();
		parentWindow = window;
	    this.add(createFileMenu());
	    this.add(createCreateMenu());
	}

	private JMenu createFileMenu() {
		JMenu jmFile = new JMenu("File");
	    JMenuItem jmiExit = new JMenuItem("Exit");
	    jmiExit.addActionListener(new CloseItemListener());
	    jmFile.add(jmiExit);
		return jmFile;
	}
	
	private JMenu createCreateMenu() {
		JMenu jmCreate = new JMenu("Create");
	    JMenuItem jmiOperation = new JMenuItem("Operation");
	    jmiOperation.addActionListener(new StubEventListener());
	    JMenuItem jmiOperationName = new JMenuItem("Operation Name");
	    jmiOperationName.addActionListener(new StubEventListener());
	    JMenuItem jmiOperationClass = new JMenuItem("Operation Class");
	    jmiOperationClass.addActionListener(new StubEventListener());
	    JMenuItem jmiAccount = new JMenuItem("Account");
	    jmiAccount.addActionListener(new OpenEditAccountListener(null));
	    JMenuItem jmiOrganization = new JMenuItem("Organization");
	    jmiOrganization.addActionListener(new OpenEditOrganizationListener(null));
	    JMenuItem jmiCurrency = new JMenuItem("Currency");
	    jmiCurrency.addActionListener(new OpenEditCurrencyListener(null));
	    JMenuItem jmiExchangeRate = new JMenuItem("Exchange Rate");
	    jmiExchangeRate.addActionListener(new StubEventListener());
	    jmCreate.add(jmiOperation);
	    jmCreate.add(jmiOperationName);
	    jmCreate.add(jmiOperationClass);
	    jmCreate.addSeparator();
	    jmCreate.add(jmiAccount);
	    jmCreate.add(jmiOrganization);
	    jmCreate.addSeparator();
	    jmCreate.add(jmiCurrency);
	    jmCreate.add(jmiExchangeRate);
		return jmCreate;
	}
	
	private final class StubEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem)e.getSource();
			System.out.println(source.getText() + " clicked");
		}
	}

	private final class CloseItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parentWindow.setVisible(false);
			parentWindow.dispose();
			Application.getInstance().exit();
		}
	}
}
