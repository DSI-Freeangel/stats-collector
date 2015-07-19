package org.stats.ui.window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.util.StringUtils;
import org.stats.core.beans.AccountBean;
import org.stats.core.config.Application;
import org.stats.core.managers.AccountManager;
import org.stats.ui.listeners.CancelButtonActionListener;
import org.stats.ui.listeners.ChildWindowCloseListener;
import org.stats.ui.listeners.SaveAccountActionListener;
import org.stats.utils.Constants;
import org.stats.utils.WindowUtils;

public class EditAccountForm extends JFrame {
	private static final long serialVersionUID = -2719024382425917917L;

	private JTextField accountIdField;
	private JTextField accountCustomIdField;
	private JTextField accountNameField;
	private JTextArea accountDescriptionField;
	private JTextField accountStartDateField;
	private JTextField accountEndDateField;
	private JCheckBox isOwnCheckBox;
	private JCheckBox isOnHandCheckBox;
	
	private JButton saveButton;
	private JButton cancelButton;

	public EditAccountForm(Long accountId) throws HeadlessException {
		super("Edit account");
		makeInterface();
		fillFields(accountId);
		this.setVisible(true);
	}

	private void fillFields(Long accountId) {
		if (null != accountId) {
			AccountManager accountManager = Application.getInstance().getBean(AccountManager.class);
			AccountBean account = accountManager.getAccountBean(accountId);
			if (null != account) {
				accountIdField.setText(account.getId().toString());
				accountNameField.setText(account.getName());
				accountDescriptionField.setText(account.getDescription());
				accountCustomIdField.setText(account.getCustomId());
				accountStartDateField.setText(account.getStartDateString());
				accountEndDateField.setText(account.getEndDateString());
				isOwnCheckBox.setEnabled(account.getIsOwn());
				isOnHandCheckBox.setEnabled(account.getIsOnHand());
				//TODO: Add organization select
				//TODO: Add currency select
			}
		}
	}

	private void makeInterface() {
		this.setLayout(new BorderLayout(5, 5));
		JPanel panel = new JPanel();
		JLabel labelId = new JLabel("ID");
		labelId.setLabelFor(getAccountIdField());
		JLabel labelName = new JLabel("Name");
		labelName.setLabelFor(getAccountNameField());
		JLabel labelCustomId = new JLabel("CustomId");
		labelCustomId.setLabelFor(getAccountCustomIdField());
		JLabel labelStartDate = new JLabel("StartDate");
		labelStartDate.setLabelFor(getAccountStartDateField());
		JLabel labelEndDate = new JLabel("EndDate");
		labelEndDate.setLabelFor(getAccountEndDateField());
		panel.setLayout(new GridLayout(6, 2, 5, 5));
		panel.add(labelId);
		panel.add(getAccountIdField());
		panel.add(labelCustomId);
		panel.add(getAccountCustomIdField());
		panel.add(labelName);
		panel.add(getAccountNameField());
		panel.add(labelStartDate);
		panel.add(getAccountStartDateField());
		panel.add(labelEndDate);
		panel.add(getAccountEndDateField());
		panel.add(getIsOwnCheckBox());
		panel.add(getIsOnHandCheckBox());
		//TODO: Add organization select
		//TODO: Add currency select
		this.add(panel, BorderLayout.NORTH);
		this.add(getAccountDescriptionField(), BorderLayout.CENTER);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout(5, 5));
		buttonsPanel.add(getSaveButton(), BorderLayout.WEST);
		buttonsPanel.add(getCancelButton(), BorderLayout.EAST);
		this.add(buttonsPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new ChildWindowCloseListener());
		WindowUtils.moveToCenter(this);
		this.pack();
	}

	public JTextField getAccountIdField() {
		if (null == accountIdField) {
			accountIdField = new JTextField(30);
			accountIdField.setEditable(false);
			accountIdField.setEnabled(false);
		}
		return accountIdField;
	}

	public JTextField getAccountNameField() {
		if (null == accountNameField) {
			accountNameField = new JTextField(30);
		}
		return accountNameField;
	}

	public JTextArea getAccountDescriptionField() {
		if (null == accountDescriptionField) {
			accountDescriptionField = new JTextArea(3, 30);
		}
		return accountDescriptionField;
	}

	public JButton getCancelButton() {
		if (null == cancelButton) {
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new CancelButtonActionListener(this));
		}
		return cancelButton;
	}

	public JButton getSaveButton() {
		if (null == saveButton) {
			saveButton = new JButton("Save");
			saveButton.addActionListener(new SaveAccountActionListener(this));
		}
		return saveButton;
	}
	
	public JTextField getAccountCustomIdField() {
		if (null == accountCustomIdField) {
			accountCustomIdField = new JTextField(30);
		}
		return accountCustomIdField;
	}

	public JTextField getAccountStartDateField() {
		if (null == accountStartDateField) {
			accountStartDateField = new JTextField(30);
		}
		return accountStartDateField;
	}

	public JTextField getAccountEndDateField() {
		if (null == accountEndDateField) {
			accountEndDateField = new JTextField(30);
		}
		return accountEndDateField;
	}

	public JCheckBox getIsOwnCheckBox() {
		if(null == isOwnCheckBox) {
			isOwnCheckBox = new JCheckBox("Is own");
		}
		return isOwnCheckBox;
	}

	public JCheckBox getIsOnHandCheckBox() {
		if(null == isOnHandCheckBox) {
			isOnHandCheckBox = new JCheckBox("Is on hand");
		}
		return isOnHandCheckBox;
	}

	public AccountBean getAccountBean() {
		AccountBean organization = new AccountBean();
		String idString = this.accountIdField.getText();
		if(!StringUtils.isEmpty(idString)) {
			organization.setId(Long.valueOf(idString));
		}
		organization.setCustomId(this.accountCustomIdField.getText());
		organization.setName(this.accountNameField.getText());
		organization.setDescription(this.accountDescriptionField.getText());
		Date startDate = null;
		try {
			startDate = Constants.DATE_FORMAT.parse(this.accountStartDateField.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		organization.setStartDate(startDate);
		Date endDate = null;
		try {
			endDate = Constants.DATE_FORMAT.parse(this.accountEndDateField.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		organization.setEndDate(endDate);
		organization.setIsOnHand(this.isOnHandCheckBox.isSelected());
		organization.setIsOwn(this.isOwnCheckBox.isSelected());
		organization.setEnabled(true);
		//TODO: Add organization select
		//TODO: Add currency select
		return organization;
	}

}
