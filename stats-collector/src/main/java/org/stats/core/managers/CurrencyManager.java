package org.stats.core.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.stats.core.beans.ActionResult;
import org.stats.core.beans.CurrencyBean;
import org.stats.core.dao.CurrencyDao;
import org.stats.core.entity.Currency;

@Component
public class CurrencyManager {
	@Autowired
	private CurrencyDao dao;

	public CurrencyDao getDao() {
		return dao;
	}

	public void setDao(CurrencyDao dao) {
		this.dao = dao;
	}

	public Currency getCurrency(Long id) {
		return dao.get(id);
	}
	
	public CurrencyBean getCurrencyBean(Long id) {
		if (null != id) {
			Currency currency = getCurrency(id);
			if (null != currency) {
				return new CurrencyBean(currency);
			}
		}
		return null;
	}

	public ActionResult saveCurrency(CurrencyBean currencyBean) {
		if (null == currencyBean) {
			return ActionResult.error("Nothing to save.");
		}
		if(StringUtils.isEmpty(currencyBean.getName())) {
			return ActionResult.error("Currency name should not be empty.");
		}
		if(StringUtils.isEmpty(currencyBean.getAbbreviation())) {
			return ActionResult.error("Currency abbreviation should not be empty.");
		}
		Currency currency = null;
		if (null != currencyBean.getId()) {
			currency = dao.get(currencyBean.getId());
			if (null == currency) {
				return ActionResult.error("Organization with id #" + currencyBean.getId() + " not found.");
			}
		} else {
			currency = new Currency();
		}
		currency.setName(currencyBean.getName());
		currency.setAbbreviation(currencyBean.getAbbreviation());
		currency.setIsDefault(currencyBean.getIsDefault());
		dao.save(currency);
		return ActionResult.success();
	}
}
