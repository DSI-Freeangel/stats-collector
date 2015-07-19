package org.stats.core.beans;

public class ActionResult {
	private boolean success = true;
	private String message = "success";
	
	private ActionResult(boolean success, String message){
		this.success = success;
		this.message = message;
	}
	
	public static ActionResult success() {
		return new ActionResult(true, "success");
	}
	
	public static ActionResult error(String message) {
		return new ActionResult(false, message);
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
	
}
