package org.stats.core.managers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.stats.core.beans.AccountBean;
import org.stats.core.beans.ActionResult;
import org.stats.core.dao.AccountDao;
import org.stats.core.entity.Account;
import org.stats.core.entity.Currency;
import org.stats.core.entity.Organization;

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

	public ActionResult saveAccount(AccountBean accountBean) {
		if (null == accountBean) {
			return ActionResult.error("Nothing to save.");
		}
		if (StringUtils.isEmpty(accountBean.getName())) {
			return ActionResult.error("Account name should not be empty.");
		}
		if (null == accountBean.getCurrencyId()) {
			return ActionResult.error("Currency should not be empty.");
		}
		Currency currency = currencyManager.getCurrency(accountBean.getCurrencyId());
		if(null == currency) {
			return ActionResult.error("Currency with id #" + accountBean.getCurrencyId() + " not found.");
		}
		if (null == accountBean.getOrganizationId()) {
			return ActionResult.error("Organization should not be empty.");
		}
		Organization organization = organizationManager.getOrganization(accountBean.getOrganizationId());
		if(null == organization) {
			return ActionResult.error("Organization with id #" + accountBean.getOrganizationId() + " not found.");
		}
		Account account = null;
		if (null != accountBean.getId()) {
			account = dao.get(accountBean.getId());
			if (null == account) {
				return ActionResult.error("Account with id #" + accountBean.getId() + " not found.");
			}
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
		dao.save(account);
		return ActionResult.success();
	}
}
