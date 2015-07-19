package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.stats.ui.window.EditCurrencyForm;

public final class OpenEditCurrencyListener implements ActionListener {

	private Long currencyId;

	public OpenEditCurrencyListener(Long currencyId) {
		this.currencyId = currencyId;
	}

	public void actionPerformed(ActionEvent event) {
		new EditCurrencyForm(currencyId);
	}
}