package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
import org.stats.core.config.Application;
import org.stats.core.exception.ValidationException;
import org.stats.core.managers.CurrencyManager;
import org.stats.ui.window.EditCurrencyForm;
import org.stats.utils.WindowUtils;

public final class SaveCurrencyActionListener implements ActionListener {
	private static final Logger log = Logger.getLogger(SaveCurrencyActionListener.class);
	private EditCurrencyForm form;
	
	public SaveCurrencyActionListener(EditCurrencyForm form) {
		this.form = form;
	}

	public void actionPerformed(ActionEvent event) {
		CurrencyManager currencyManager = Application.getInstance().getBean(CurrencyManager.class);
		try {
			currencyManager.saveCurrency(form.getCurrencyBean());
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