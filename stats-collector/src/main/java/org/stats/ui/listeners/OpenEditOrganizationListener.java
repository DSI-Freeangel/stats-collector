package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.stats.ui.window.EditOrganizationForm;

public final class OpenEditOrganizationListener implements ActionListener {

	private Long organizationId;

	public OpenEditOrganizationListener(Long organizationId) {
		this.organizationId = organizationId;
	}

	public void actionPerformed(ActionEvent event) {
		new EditOrganizationForm(organizationId);
	}
}