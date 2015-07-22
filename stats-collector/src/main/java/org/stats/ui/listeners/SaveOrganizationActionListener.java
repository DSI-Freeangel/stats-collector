package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
import org.stats.core.config.Application;
import org.stats.core.exception.ValidationException;
import org.stats.core.managers.OrganizationManager;
import org.stats.ui.window.EditOrganizationForm;
import org.stats.utils.WindowUtils;

public final class SaveOrganizationActionListener implements ActionListener {
	private static final Logger log = Logger.getLogger(SaveOrganizationActionListener.class);
	private EditOrganizationForm form;
	
	public SaveOrganizationActionListener(EditOrganizationForm form) {
		this.form = form;
	}

	public void actionPerformed(ActionEvent event) {
		OrganizationManager organiaztionManager = Application.getInstance().getBean(OrganizationManager.class);
		try {
			organiaztionManager.saveOrganization(form.getOrganizationBean());
			form.setVisible(false);
			form.dispose();
		} catch (ValidationException e) {
			WindowUtils.showMessage("Save error ", e.getMessage());
		} catch (Exception e) {
			WindowUtils.showMessage("Save error ", e.getMessage());
			log.error("Currency saving error: ", e);
		}
	}
}