package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.stats.core.beans.ActionResult;
import org.stats.core.config.Application;
import org.stats.core.managers.AccountManager;
import org.stats.ui.window.EditAccountForm;

public final class SaveAccountActionListener implements ActionListener {
	private EditAccountForm form;
	
	public SaveAccountActionListener(EditAccountForm form) {
		this.form = form;
	}

	public void actionPerformed(ActionEvent event) {
		AccountManager organiaztionManager = Application.getInstance().getBean(AccountManager.class);
		ActionResult result = organiaztionManager.saveAccount(form.getAccountBean());
		if(result.isSuccess()) {
			form.setVisible(false);
			form.dispose();
		} else {
			JOptionPane.showMessageDialog(null, result.getMessage(), "Save error ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}