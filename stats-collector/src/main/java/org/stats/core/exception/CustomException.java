package org.stats.core.exception;

public class CustomException extends Exception {
	private static final long serialVersionUID = 5296211493499836061L;

	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message, Throwable exception) {
		super(message, exception);
	}

}
