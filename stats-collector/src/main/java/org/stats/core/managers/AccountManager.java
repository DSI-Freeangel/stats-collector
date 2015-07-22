package org.stats.core.managers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stats.core.beans.AccountBean;
import org.stats.core.dao.AccountDao;
import org.stats.core.entity.Account;
import org.stats.core.entity.Currency;
import org.stats.core.entity.Organization;
import org.stats.core.exception.ValidationException;

@Component
public class AccountManager {
	@Autowired
	private AccountDao dao;

	public AccountDao getDao() {
		return dao;
	}

	public void setDao(AccountDao dao) {
		this.dao = dao;
	}

	@Autowired
	private CurrencyManager currencyManager;

	public CurrencyManager getCurrencyManager() {
		return currencyManager;
	}

	public void setCurrencyManager(CurrencyManager currencyManager) {
		this.currencyManager = currencyManager;
	}

	@Autowired
	private OrganizationManager organizationManager;
	
	public OrganizationManager getOrganizationManager() {
		return organizationManager;
	}

	public void setOrganizationManager(OrganizationManager organizationManager) {
		this.organizationManager = organizationManager;
	}

	public AccountBean getAccountBean(Long id) {
		if (null != id) {
			Account currency = dao.get(id);
			if (null != currency) {
				return new AccountBean(currency);
			}
		}
		return null;
	}

	public Account saveAccount(AccountBean accountBean) throws ValidationException {
		ValidationException.assertNotNull(accountBean, "Nothing to save");
		ValidationException.assertNotEmpty(accountBean.getName(), "Account name should not be empty");
		ValidationException.assertNotNull(accountBean.getCurrencyId(), "Currency should not be empty");
		Currency currency = currencyManager.getCurrency(accountBean.getCurrencyId());
		ValidationException.assertNotNull(currency, "Currency with id #" + accountBean.getCurrencyId() + " not found");
		ValidationException.assertNotNull(accountBean.getOrganizationId(), "Organization should not be empty");
		Organization organization = organizationManager.getOrganization(accountBean.getOrganizationId());
		ValidationException.assertNotNull(null == organization, "Organization with id #" + accountBean.getOrganizationId() + " not found");
		Account account = null;
		if (null != accountBean.getId()) {
			account = dao.get(accountBean.getId());
		} else {
			account = new Account();
		}
		account.setCurrency(currency);
		account.setOrganization(organization);
		account.setName(accountBean.getName());
		account.setCustomId(accountBean.getCustomId());
		account.setDescription(accountBean.getDescription());
		account.setValue(null != accountBean.getValue() ? accountBean.getValue() : 0);
		account.setStartDate(accountBean.getStartDate());
		account.setEndDate(accountBean.getEndDate());
		account.setLastUpdateDate(new Date());
		account.setIsOnHand(Boolean.TRUE.equals(accountBean.getIsOnHand()));
		account.setIsOwn(Boolean.TRUE.equals(accountBean.getIsOwn()));
		account.setEnabled(Boolean.TRUE.equals(accountBean.getEnabled()));
		return dao.save(account);
	}
}
