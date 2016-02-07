package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.stats.core.exception.ValidationException;
import org.stats.core.managers.CurrencyManager;
import org.stats.ui.window.EditCurrencyForm;
import org.stats.utils.WindowUtils;

@Component
@Scope(value = "prototype")
public class SaveCurrencyActionListener implements ActionListener {
	private static final Logger log = Logger.getLogger(SaveCurrencyActionListener.class);
	private EditCurrencyForm form;
	@Autowired
	private CurrencyManager currencyManager;

	public SaveCurrencyActionListener() {
	}

	public SaveCurrencyActionListener(EditCurrencyForm form) {
		this();
		init(form);
	}

	public final SaveCurrencyActionListener init(EditCurrencyForm form) {
		this.form = form;
		return this;
	}

	public void actionPerformed(ActionEvent event) {
		try {
			getCurrencyManager().saveCurrency(form.getCurrencyBean());
			form.setVisible(false);
			form.dispose();
		} catch (ValidationException e) {
			WindowUtils.showMessage("Save error ", e.getMessage());
		} catch (Exception e) {
			WindowUtils.showMessage("Save error ", e.getMessage());
			log.error("Currency saving error: ", e);
		}
	}

	public CurrencyManager getCurrencyManager() {
		return currencyManager;
	}

	public void setCurrencyManager(CurrencyManager currencyManager) {
		this.currencyManager = currencyManager;
	}

}