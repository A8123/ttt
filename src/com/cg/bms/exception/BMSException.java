package com.cg.bms.exception;

public class BMSException extends Exception{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public BMSException(String message) {
		super();
		this.message = message;
	}

}
