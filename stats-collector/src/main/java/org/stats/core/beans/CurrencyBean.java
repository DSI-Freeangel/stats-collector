package org.stats.core.beans;

import org.stats.core.entity.Currency;
import org.stats.core.interfaces.IdNameInterface;

public class CurrencyBean implements IdNameInterface {

	public CurrencyBean() {
		this(null, null, null, null);
	}

	public CurrencyBean(Long id, String name, String abbreviation, Boolean isDefault) {
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.isDefault = isDefault;
	}

	public CurrencyBean(Currency currency) {
		this(currency.getId(), currency.getName(), currency.getAbbreviation(), currency.getIsDefault());
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

	private String abbreviation;

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	private Boolean isDefault;

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}
