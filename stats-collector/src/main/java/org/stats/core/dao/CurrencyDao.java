package org.stats.core.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.stats.core.dao.base.BaseDao;
import org.stats.core.entity.Currency;

@Component
@Transactional
public class CurrencyDao extends BaseDao<Currency>{

	protected CurrencyDao() {
		super(Currency.class);
	}

	@SuppressWarnings("unchecked")
	public List<Currency> getAvailableCurrencyList() {
		return getSession().createCriteria(Currency.class)
				 .add(Restrictions.eq("active", true))
				 .list();
	}

}
