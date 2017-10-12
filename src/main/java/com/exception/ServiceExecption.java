package com.exception;

public class ServiceExecption extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	public ServiceExecption(String message) {
		super(message);
	}
	public ServiceExecption(String message, String code) {
		super(message);
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
}
