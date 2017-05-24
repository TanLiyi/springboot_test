package com.liyi.exception;

public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int errorCode = -1;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}
