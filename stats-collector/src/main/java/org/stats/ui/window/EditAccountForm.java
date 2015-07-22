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
import org.stats.core.exception.CustomException;
import org.stats.core.managers.AccountManager;
import org.stats.core.managers.CurrencyManager;
import org.stats.core.managers.OrganizationManager;
import org.stats.ui.elements.IdNameComboBox;
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
	private IdNameComboBox accountCurrencySelectBox;
	private IdNameComboBox accountOrganizationSelectBox;
	
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
				accountCurrencySelectBox.setSelectedId(account.getCurrencyId());
				accountOrganizationSelectBox.setSelectedId(account.getOrganizationId());
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
		JLabel labelOrganization = new JLabel("Organization");
		labelOrganization.setLabelFor(getAccountOrganizationSelectBox());
		JLabel labelCurrency = new JLabel("Currency");
		labelCurrency.setLabelFor(getAccountCurrencySelectBox());
		panel.setLayout(new GridLayout(8, 2, 5, 5));
		panel.add(labelId);
		panel.add(getAccountIdField());
		panel.add(labelCustomId);
		panel.add(getAccountCustomIdField());
		panel.add(labelName);
		panel.add(getAccountNameField());
		panel.add(labelCurrency);
		panel.add(getAccountCurrencySelectBox());
		panel.add(labelOrganization);
		panel.add(getAccountOrganizationSelectBox());
		panel.add(labelStartDate);
		panel.add(getAccountStartDateField());
		panel.add(labelEndDate);
		panel.add(getAccountEndDateField());
		panel.add(getIsOwnCheckBox());
		panel.add(getIsOnHandCheckBox());
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
	
	public IdNameComboBox getAccountCurrencySelectBox() {
		if(null == accountCurrencySelectBox) {
			CurrencyManager currencyManager = Application.getInstance().getBean(CurrencyManager.class);
			accountCurrencySelectBox = new IdNameComboBox(currencyManager.getAvailableCurrencyList());
		}
		return accountCurrencySelectBox;
	}

	public IdNameComboBox getAccountOrganizationSelectBox() {
		if(null == accountOrganizationSelectBox) {
			OrganizationManager organizationManager = Application.getInstance().getBean(OrganizationManager.class);
			accountOrganizationSelectBox = new IdNameComboBox(organizationManager.getAvailableOrganizations());
		}
		return accountOrganizationSelectBox;
	}

	public AccountBean getAccountBean() throws CustomException {
		AccountBean accountBean = new AccountBean();
		String idString = this.accountIdField.getText();
		if(!StringUtils.isEmpty(idString)) {
			accountBean.setId(Long.valueOf(idString));
		}
		accountBean.setCustomId(this.accountCustomIdField.getText());
		accountBean.setName(this.accountNameField.getText());
		accountBean.setDescription(this.accountDescriptionField.getText());
		Date startDate = null;
		String startDateStr = this.accountStartDateField.getText();
		try {
			if (!StringUtils.isEmpty(startDateStr)) {
				startDate = Constants.DATE_FORMAT.parse(startDateStr);
			}
		} catch (ParseException e) {
			throw new CustomException("Unparseable date:" + startDateStr, e);
		}
		accountBean.setStartDate(startDate);
		Date endDate = null;
		String endDateStr = this.accountEndDateField.getText();
		try {
			if (!StringUtils.isEmpty(endDateStr)) {
				endDate = Constants.DATE_FORMAT.parse(endDateStr);
			}
		} catch (ParseException e) {
			throw new CustomException("Unparseable date:" + endDateStr, e);
		}
		accountBean.setEndDate(endDate);
		accountBean.setIsOnHand(this.isOnHandCheckBox.isSelected());
		accountBean.setIsOwn(this.isOwnCheckBox.isSelected());
		accountBean.setEnabled(true);
		accountBean.setOrganizationId(this.accountOrganizationSelectBox.getSelectedId());
		accountBean.setCurrencyId(this.accountCurrencySelectBox.getSelectedId());
		return accountBean;
	}

}
