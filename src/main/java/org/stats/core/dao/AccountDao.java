package org.stats.core.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.stats.core.dao.base.BaseDao;
import org.stats.core.entity.Account;

@Component
@Transactional
public class AccountDao extends BaseDao<Account>{

	protected AccountDao() {
		super(Account.class);
	}

}
