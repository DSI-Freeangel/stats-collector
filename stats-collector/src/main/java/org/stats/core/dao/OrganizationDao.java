package org.stats.core.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.stats.core.dao.base.BaseDao;
import org.stats.core.entity.Organization;

@Component
@Transactional
public class OrganizationDao extends BaseDao<Organization> {

	public OrganizationDao() {
		super(Organization.class);
	}
	
}
