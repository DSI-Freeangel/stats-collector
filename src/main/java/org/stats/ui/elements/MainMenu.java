package org.stats.ui.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stats.ui.listeners.OpenEditAccountListener;
import org.stats.ui.listeners.OpenEditCurrencyListener;
import org.stats.ui.listeners.OpenEditOrganizationListener;
import org.stats.ui.listeners.WindowCloseListener;

@Component
public class MainMenu extends JMenuBar {
	private static final long serialVersionUID = 4661724205948081350L;
	private JFrame parentWindow;
	@Autowired
	private WindowCloseListener windowCloseListener;
	@Autowired
	private OpenEditAccountListener openEditAccountListener;
	@Autowired
	private OpenEditOrganizationListener openEditOrganizationListener;
	@Autowired
	private OpenEditCurrencyListener openEditCurrencyListener;

	public MainMenu() {
		super();
	}

	public MainMenu(JFrame window) {
		this();
		init(window);
	}

	public MainMenu init(JFrame window) {
		this.parentWindow = window;
		this.add(createFileMenu());
		this.add(createCreateMenu());
		return this;
	}

	private JMenu createFileMenu() {
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiExit = new JMenuItem("Exit");
		jmiExit.addActionListener(getWindowCloseListener().setParentWindow(parentWindow));
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
		jmiAccount.addActionListener(getOpenEditAccountListener());
		JMenuItem jmiOrganization = new JMenuItem("Organization");
		jmiOrganization.addActionListener(getOpenEditOrganizationListener());
		JMenuItem jmiCurrency = new JMenuItem("Currency");
		jmiCurrency.addActionListener(getOpenEditCurrencyListener());
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
			JMenuItem source = (JMenuItem) e.getSource();
			System.out.println(source.getText() + " clicked");
		}
	}

	public WindowCloseListener getWindowCloseListener() {
		return windowCloseListener;
	}

	public void setWindowCloseListener(WindowCloseListener windowCloseListener) {
		this.windowCloseListener = windowCloseListener;
	}

	public OpenEditAccountListener getOpenEditAccountListener() {
		return openEditAccountListener;
	}

	public void setOpenEditAccountListener(OpenEditAccountListener openEditAccountListener) {
		this.openEditAccountListener = openEditAccountListener;
	}

	public OpenEditOrganizationListener getOpenEditOrganizationListener() {
		return openEditOrganizationListener;
	}

	public void setOpenEditOrganizationListener(OpenEditOrganizationListener openEditOrganizationListener) {
		this.openEditOrganizationListener = openEditOrganizationListener;
	}

	public OpenEditCurrencyListener getOpenEditCurrencyListener() {
		return openEditCurrencyListener;
	}

	public void setOpenEditCurrencyListener(OpenEditCurrencyListener openEditCurrencyListener) {
		this.openEditCurrencyListener = openEditCurrencyListener;
	}

}
