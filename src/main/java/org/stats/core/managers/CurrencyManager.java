package org.stats.core.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.stats.core.beans.CurrencyBean;
import org.stats.core.dao.CurrencyDao;
import org.stats.core.entity.Currency;
import org.stats.core.exception.ValidationException;

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

	public Currency saveCurrency(CurrencyBean currencyBean) throws ValidationException {
		ValidationException.assertNotNull(currencyBean, "Nothing to save");
		ValidationException.assertNotEmpty(currencyBean.getName(), "Currency name should not be empty");
		ValidationException.assertNotEmpty(currencyBean.getAbbreviation(), "Currency abbreviation should not be empty");
		Currency currency = null;
		if (null != currencyBean.getId()) {
			currency = dao.get(currencyBean.getId());
			ValidationException.assertNotNull(currency, "Organization with id #" + currencyBean.getId() + " not found");
		} else {
			currency = new Currency();
		}
		currency.setName(currencyBean.getName());
		currency.setAbbreviation(currencyBean.getAbbreviation());
		currency.setIsDefault(currencyBean.getIsDefault());
		return dao.save(currency);
	}

	public List<CurrencyBean> getAvailableCurrencyList() {
		List<Currency> availableCurrencyList = dao.getAvailableCurrencyList();
		if(!CollectionUtils.isEmpty(availableCurrencyList)) {
			return availableCurrencyList.stream().map(currency -> new CurrencyBean(currency)).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
}
