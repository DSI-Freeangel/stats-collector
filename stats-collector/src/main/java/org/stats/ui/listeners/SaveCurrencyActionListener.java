package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.stats.core.beans.ActionResult;
import org.stats.core.config.Application;
import org.stats.core.managers.CurrencyManager;
import org.stats.ui.window.EditCurrencyForm;

public final class SaveCurrencyActionListener implements ActionListener {
	private EditCurrencyForm form;
	
	public SaveCurrencyActionListener(EditCurrencyForm form) {
		this.form = form;
	}

	public void actionPerformed(ActionEvent event) {
		CurrencyManager currencyManager = Application.getInstance().getBean(CurrencyManager.class);
		ActionResult result = currencyManager.saveCurrency(form.getCurrencyBean());
		if(result.isSuccess()) {
			form.setVisible(false);
			form.dispose();
		} else {
			JOptionPane.showMessageDialog(null, result.getMessage(), "Save error ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}