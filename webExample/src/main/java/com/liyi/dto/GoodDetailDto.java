package com.liyi.dto;

import java.io.Serializable;

public class GoodDetailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private CreateGoodInput detail;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CreateGoodInput getDetail() {
		return detail;
	}
	public void setDetail(CreateGoodInput detail) {
		this.detail = detail;
	}
	
}
