package org.stats.core.exception;

import org.springframework.util.StringUtils;

public class ValidationException extends CustomException {
	private static final long serialVersionUID = 4387133050186755199L;
	
	private ValidationException(String message) {
		super(message);
	}

	public static void assertTrue(boolean value, String message) throws ValidationException {
		if(!value) {
			throw new ValidationException(message);
		}
	}
	
	public static void assertNotNull(Object value, String message) throws ValidationException {
		assertTrue(null != value, message);
	}
	
	public static void assertNotEmpty(String value, String message) throws ValidationException {
		assertTrue(!StringUtils.isEmpty(value), message);
	}
}
