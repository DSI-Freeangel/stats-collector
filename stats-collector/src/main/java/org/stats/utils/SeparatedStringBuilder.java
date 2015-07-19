package org.stats.utils;

import org.springframework.util.StringUtils;

public class SeparatedStringBuilder {
	private StringBuilder rep = new StringBuilder();
	protected String separator;

	/**
	 * Separate by space by default
	 */
	public SeparatedStringBuilder() {
		this("");
	}

	/**
	 * Build using 'separator'
	 */
	public SeparatedStringBuilder(String separator) {
		this.separator = separator;
	}

	public SeparatedStringBuilder append(String value) {
		if (!StringUtils.isEmpty(value)) {
			if (rep.length() > 0)
				rep.append(separator);
			rep.append(value);
		}
		return this;
	}

	public SeparatedStringBuilder append(int value) {
		if (rep.length() > 0)
			rep.append(separator);
		rep.append(value);
		return this;
	}

	public String toString() {
		return rep.toString();
	}

	public void reset() {
		rep.setLength(0);
	}
}