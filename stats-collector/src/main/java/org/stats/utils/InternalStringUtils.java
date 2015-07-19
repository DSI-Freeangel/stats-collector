package org.stats.utils;

public class InternalStringUtils {
	public static final String CUT_MORE_SIGNS = "...";

	public static String truncate(String description, int maxLength) {
		return truncate(description, maxLength, false);
	}

	public static String truncate(String description, int maxLength, boolean addCutMoreSigns) {
		if (null == description) {
			return null;
		}
		String result = description.trim();
		if (result.length() > maxLength) {
			result = result.substring(0, maxLength + 1);
			if (addCutMoreSigns) {
				result += CUT_MORE_SIGNS;
			}
		}
		return result;
	}
}
