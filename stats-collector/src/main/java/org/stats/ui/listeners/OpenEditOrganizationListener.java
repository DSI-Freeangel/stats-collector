package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.stereotype.Component;
import org.stats.core.config.Application;
import org.stats.ui.window.EditOrganizationForm;

@Component
public final class OpenEditOrganizationListener implements ActionListener {

	private Long organizationId;

	public OpenEditOrganizationListener() {
	}

	public OpenEditOrganizationListener(Long organizationId) {
		this();
		init(organizationId);
	}

	public OpenEditOrganizationListener init(Long organizationId) {
		this.organizationId = organizationId;
		return this;
	}

	public void actionPerformed(ActionEvent event) {
		Application.getInstance().getBean(EditOrganizationForm.class).init(organizationId);
	}

}