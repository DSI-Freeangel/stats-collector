package org.stats.ui.window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.stats.core.beans.CurrencyBean;
import org.stats.core.managers.CurrencyManager;
import org.stats.ui.listeners.ChildWindowCloseListener;
import org.stats.ui.listeners.SaveCurrencyActionListener;
import org.stats.utils.WindowUtils;

@Component
@Scope(value = "prototype")
public class EditCurrencyForm extends JFrame {
	private static final long serialVersionUID = 2535932716410265040L;
	private JTextField currencyIdField;
	private JTextField currencyNameField;
	private JTextField currencyAbbreviationField;
	private JCheckBox isDefaultCurrencyField;
	private JButton saveButton;
	private JButton cancelButton;

	@Autowired
	private CurrencyManager currencyManager;
	@Autowired
	private ChildWindowCloseListener childWindowCloseListener;
	@Autowired
	private SaveCurrencyActionListener saveCurrencyActionListener;

	public EditCurrencyForm() throws HeadlessException {
		super("Edit currency");
	}

	public EditCurrencyForm(Long currencyId) throws HeadlessException {
		this();
		init(currencyId);
	}

	public EditCurrencyForm init(Long currencyId) {
		makeInterface();
		fillFields(currencyId);
		this.setVisible(true);
		return this;
	}

	private void fillFields(Long currencyId) {
		if (null != currencyId) {
			CurrencyBean currencyBean = getCurrencyManager().getCurrencyBean(currencyId);
			if (null != currencyBean) {
				currencyIdField.setText(currencyBean.getId().toString());
				currencyNameField.setText(currencyBean.getName());
				currencyAbbreviationField.setText(currencyBean.getAbbreviation());
				isDefaultCurrencyField.setSelected(Boolean.TRUE.equals(currencyBean.getIsDefault()));
			}
		}
	}

	private void makeInterface() {
		this.setLayout(new BorderLayout(5, 5));
		JPanel panel = new JPanel();
		JLabel labelId = new JLabel("ID");
		labelId.setLabelFor(getCurrencyIdField());
		JLabel labelName = new JLabel("Name");
		labelName.setLabelFor(getCurrencyNameField());
		JLabel labelAbbreviation = new JLabel("Abbreviation");
		labelAbbreviation.setLabelFor(getCurrencyAbbreviationField());
		panel.setLayout(new GridLayout(4, 2, 5, 5));
		panel.add(labelId);
		panel.add(getCurrencyIdField());
		panel.add(labelName);
		panel.add(getCurrencyNameField());
		panel.add(labelAbbreviation);
		panel.add(getCurrencyAbbreviationField());
		panel.add(new JLabel());
		panel.add(getIsDefaultCurrencyField());
		this.add(panel, BorderLayout.CENTER);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout(5, 5));
		buttonsPanel.add(getSaveButton(), BorderLayout.WEST);
		buttonsPanel.add(getCancelButton(), BorderLayout.EAST);
		this.add(buttonsPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(getChildWindowCloseListener());
		WindowUtils.moveToCenter(this);
		this.pack();
	}

	public JTextField getCurrencyIdField() {
		if (null == currencyIdField) {
			currencyIdField = new JTextField(30);
			currencyIdField.setEditable(false);
			currencyIdField.setEnabled(false);
		}
		return currencyIdField;
	}

	public JTextField getCurrencyNameField() {
		if (null == currencyNameField) {
			currencyNameField = new JTextField(30);
		}
		return currencyNameField;
	}

	public JTextField getCurrencyAbbreviationField() {
		if (null == currencyAbbreviationField) {
			currencyAbbreviationField = new JTextField(30);
		}
		return currencyAbbreviationField;
	}

	public JCheckBox getIsDefaultCurrencyField() {
		if (null == isDefaultCurrencyField) {
			isDefaultCurrencyField = new JCheckBox("Is default");
		}
		return isDefaultCurrencyField;
	}

	public JButton getCancelButton() {
		if (null == cancelButton) {
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(getChildWindowCloseListener().setParentWindow(this));
		}
		return cancelButton;
	}

	public JButton getSaveButton() {
		if (null == saveButton) {
			saveButton = new JButton("Save");
			saveButton.addActionListener(getSaveCurrencyActionListener().init(this));
		}
		return saveButton;
	}

	public CurrencyBean getCurrencyBean() {
		CurrencyBean currency = new CurrencyBean();
		String idString = this.currencyIdField.getText();
		if (!StringUtils.isEmpty(idString)) {
			currency.setId(Long.valueOf(idString));
		}
		currency.setName(this.currencyNameField.getText());
		currency.setAbbreviation(this.currencyAbbreviationField.getText());
		currency.setIsDefault(this.isDefaultCurrencyField.isSelected());
		return currency;
	}

	public CurrencyManager getCurrencyManager() {
		return currencyManager;
	}

	public void setCurrencyManager(CurrencyManager currencyManager) {
		this.currencyManager = currencyManager;
	}

	public ChildWindowCloseListener getChildWindowCloseListener() {
		return childWindowCloseListener;
	}

	public void setChildWindowCloseListener(ChildWindowCloseListener childWindowCloseListener) {
		this.childWindowCloseListener = childWindowCloseListener;
	}

	public SaveCurrencyActionListener getSaveCurrencyActionListener() {
		return saveCurrencyActionListener;
	}

	public void setSaveCurrencyActionListener(SaveCurrencyActionListener saveCurrencyActionListener) {
		this.saveCurrencyActionListener = saveCurrencyActionListener;
	}

}
