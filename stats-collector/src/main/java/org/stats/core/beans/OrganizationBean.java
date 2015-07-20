package org.stats.core.beans;

import org.stats.core.entity.Organization;
import org.stats.core.interfaces.IdNameInterface;


public class OrganizationBean  implements IdNameInterface {
	
	public OrganizationBean() {
		this(null, null, null);
	}

	public OrganizationBean(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public OrganizationBean(Organization organization) {
		this(organization.getId(), organization.getName(), organization.getDescription());
	}
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
