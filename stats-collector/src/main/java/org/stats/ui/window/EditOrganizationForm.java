package org.stats.ui.window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.util.StringUtils;
import org.stats.core.beans.OrganizationBean;
import org.stats.core.config.Application;
import org.stats.core.managers.OrganizationManager;
import org.stats.ui.listeners.CancelButtonActionListener;
import org.stats.ui.listeners.ChildWindowCloseListener;
import org.stats.ui.listeners.SaveOrganizationActionListener;
import org.stats.utils.WindowUtils;

public class EditOrganizationForm extends JFrame {
	private static final long serialVersionUID = -2719024382425917917L;

	private JTextField organizationIdField;
	private JTextField organizationNameField;
	private JTextArea organizationDescriptionField;
	private JButton saveButton;
	private JButton cancelButton;

	public EditOrganizationForm(Long organizationId) throws HeadlessException {
		super("Edit organization");
		makeInterface();
		fillFields(organizationId);
		this.setVisible(true);
	}

	private void fillFields(Long organizationId) {
		if (null != organizationId) {
			OrganizationManager organiaztionManager = Application.getInstance().getBean(OrganizationManager.class);
			OrganizationBean organization = organiaztionManager.getOrganizationBean(organizationId);
			if (null != organization) {
				organizationIdField.setText(organization.getId().toString());
				organizationNameField.setText(organization.getName());
				organizationDescriptionField.setText(organization.getDescription());
			}
		}
	}

	private void makeInterface() {
		this.setLayout(new BorderLayout(5, 5));
		JPanel panel = new JPanel();
		JLabel labelId = new JLabel("ID");
		labelId.setLabelFor(getOrganizationIdField());
		JLabel labelName = new JLabel("Name");
		labelName.setLabelFor(getOrganizationNameField());
		panel.setLayout(new GridLayout(2, 2, 5, 5));
		panel.add(labelId);
		panel.add(getOrganizationIdField());
		panel.add(labelName);
		panel.add(getOrganizationNameField());
		this.add(panel, BorderLayout.NORTH);
		this.add(getOrganizationDescriptionField(), BorderLayout.CENTER);
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

	public JTextField getOrganizationIdField() {
		if (null == organizationIdField) {
			organizationIdField = new JTextField(30);
			organizationIdField.setEditable(false);
			organizationIdField.setEnabled(false);
		}
		return organizationIdField;
	}

	public JTextField getOrganizationNameField() {
		if (null == organizationNameField) {
			organizationNameField = new JTextField(30);
		}
		return organizationNameField;
	}

	public JTextArea getOrganizationDescriptionField() {
		if (null == organizationDescriptionField) {
			organizationDescriptionField = new JTextArea(3, 30);
		}
		return organizationDescriptionField;
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
			saveButton.addActionListener(new SaveOrganizationActionListener(this));
		}
		return saveButton;
	}
	
	public OrganizationBean getOrganizationBean() {
		OrganizationBean organization = new OrganizationBean();
		String idString = this.organizationIdField.getText();
		if(!StringUtils.isEmpty(idString)) {
			organization.setId(Long.valueOf(idString));
		}
		organization.setName(this.organizationNameField.getText());
		organization.setDescription(this.organizationDescriptionField.getText());
		return organization;
	}

}
