package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.stats.ui.window.EditAccountForm;

public final class OpenEditAccountListener implements ActionListener {

	private Long accountId;

	public OpenEditAccountListener(Long organizationId) {
		this.accountId = organizationId;
	}

	public void actionPerformed(ActionEvent event) {
		new EditAccountForm(accountId);
	}
}