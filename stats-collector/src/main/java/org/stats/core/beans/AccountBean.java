package org.stats.core.beans;

import java.util.Date;

import org.stats.core.entity.Account;

public class AccountBean {
	
	public AccountBean() {
	}
	
	public AccountBean(Account account) {
		this();
		this.id = account.getId();
		this.currencyId = account.getCurrency().getId();
		this.organizationId = account.getOrganization().getId();
		this.customId = account.getCustomId();
		this.name = account.getName();
		this.description = account.getDescription();
		this.value = account.getValue();
		this.startDate = account.getStartDate();
		this.endDate = account.getEndDate();
		this.lastUpdateDate = account.getLastUpdateDate();
		this.isOnHand = account.getIsOnHand();
		this.isOwn = account.getIsOwn();
		this.enabled = account.getEnabled();
	}
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Long currencyId;

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	private Long organizationId;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	private String customId;

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
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

	private Double value;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	private Date startDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	private Date endDate;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	private Date lastUpdateDate;

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	private Boolean isOnHand;

	public Boolean getIsOnHand() {
		return isOnHand;
	}

	public void setIsOnHand(Boolean isOnHand) {
		this.isOnHand = isOnHand;
	}
	
	private Boolean isOwn;

	public Boolean getIsOwn() {
		return isOwn;
	}

	public void setIsOwn(Boolean isOwn) {
		this.isOwn = isOwn;
	}
	
	private Boolean enabled;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
