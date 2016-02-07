package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.stats.core.config.Application;
import org.stats.ui.window.EditCurrencyForm;

@Component
@Scope(value = "prototype")
public final class OpenEditCurrencyListener implements ActionListener {

	private Long currencyId;

	public OpenEditCurrencyListener() {
	}

	public OpenEditCurrencyListener(Long currencyId) {
		this();
		init(currencyId);
	}

	public OpenEditCurrencyListener init(Long currencyId) {
		this.currencyId = currencyId;
		return this;
	}

	public void actionPerformed(ActionEvent event) {
		Application.getInstance().getBean(EditCurrencyForm.class).init(currencyId);
	}

}