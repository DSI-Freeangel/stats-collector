package org.stats.core.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.stats.core.beans.ActionResult;
import org.stats.core.beans.OrganizationBean;
import org.stats.core.dao.OrganizationDao;
import org.stats.core.entity.Organization;

@Component
public class OrganizationManager {
	@Autowired
	private OrganizationDao dao;

	public OrganizationDao getDao() {
		return dao;
	}

	public void setDao(OrganizationDao dao) {
		this.dao = dao;
	}

	public Organization getOrganization(Long id) {
		return dao.get(id);
	}
	
	public OrganizationBean getOrganizationBean(Long id) {
		if (null != id) {
			Organization organization = getOrganization(id);
			if (null != organization) {
				return new OrganizationBean(organization);
			}
		}
		return null;
	}

	public ActionResult saveOrganization(OrganizationBean organizationBean) {
		if (null == organizationBean) {
			return ActionResult.error("Nothing to save.");
		}
		if(StringUtils.isEmpty(organizationBean.getName())) {
			return ActionResult.error("Organization name should not be empty.");
		}
		Organization organization = null;
		if (null != organizationBean.getId()) {
			organization = dao.get(organizationBean.getId());
			if (null == organization) {
				return ActionResult.error("Organization with id #" + organizationBean.getId() + " not found.");
			}
		} else {
			organization = new Organization();
		}
		organization.setName(organizationBean.getName());
		organization.setDescription(organizationBean.getDescription());
		dao.save(organization);
		return ActionResult.success();
	}

	public List<OrganizationBean> getAvailableOrganizations() {
		List<Organization> allOrganizations = dao.getAll();
		if(!CollectionUtils.isEmpty(allOrganizations)) {
			return allOrganizations.stream().map(organization -> new OrganizationBean(organization)).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
}
