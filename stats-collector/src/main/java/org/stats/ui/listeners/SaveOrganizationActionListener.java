package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.stats.core.beans.ActionResult;
import org.stats.core.config.Application;
import org.stats.core.managers.OrganizationManager;
import org.stats.ui.window.EditOrganizationForm;

public final class SaveOrganizationActionListener implements ActionListener {
	private EditOrganizationForm form;
	
	public SaveOrganizationActionListener(EditOrganizationForm form) {
		this.form = form;
	}

	public void actionPerformed(ActionEvent event) {
		OrganizationManager organiaztionManager = Application.getInstance().getBean(OrganizationManager.class);
		ActionResult result = organiaztionManager.saveOrganization(form.getOrganizationBean());
		if(result.isSuccess()) {
			form.setVisible(false);
			form.dispose();
		} else {
			JOptionPane.showMessageDialog(null, result.getMessage(), "Save error ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}