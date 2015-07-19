package org.stats.core.dao;


import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.stats.core.dao.base.BaseDao;
import org.stats.core.entity.Currency;

@Component
@Transactional
public class CurrencyDao extends BaseDao<Currency>{

	protected CurrencyDao() {
		super(Currency.class);
	}

}
