package org.stats.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.stats.core.config.Application;
import org.stats.core.exception.ValidationException;
import org.stats.core.managers.AccountManager;
import org.stats.ui.window.EditAccountForm;
import org.stats.utils.WindowUtils;

@Component
@Scope(value = "prototype")
public class SaveAccountActionListener implements ActionListener {
	private static final Logger log = Logger.getLogger(SaveAccountActionListener.class);
	private EditAccountForm form;

	public SaveAccountActionListener() {
	}

	public SaveAccountActionListener(EditAccountForm form) {
		this();
		init(form);
	}

	public final SaveAccountActionListener init(EditAccountForm form) {
		this.form = form;
		return this;
	}

	public void actionPerformed(ActionEvent event) {
		AccountManager organiaztionManager = Application.getInstance().getBean(AccountManager.class);
		try {
			organiaztionManager.saveAccount(form.getAccountBean());
			form.setVisible(false);
			form.dispose();
		} catch (ValidationException e) {
			WindowUtils.showMessage("Save error ", e.getMessage());
		} catch (Exception e) {
			WindowUtils.showMessage("Save error ", e.getMessage());
			log.error("Account saving error: ", e);
		}
	}
}