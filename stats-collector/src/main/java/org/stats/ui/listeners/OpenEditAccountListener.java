package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.stereotype.Component;
import org.stats.core.config.Application;
import org.stats.ui.window.EditAccountForm;

@Component
public final class OpenEditAccountListener implements ActionListener {
	private Long accountId;

	public OpenEditAccountListener() {
	}

	public OpenEditAccountListener(Long accountId) {
		this();
		init(accountId);
	}

	public OpenEditAccountListener init(Long accountId) {
		this.accountId = accountId;
		return this;
	}

	public void actionPerformed(ActionEvent event) {
		Application.getInstance().getBean(EditAccountForm.class).init(accountId);
	}

}