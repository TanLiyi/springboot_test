package com.liyi.dto;

import java.io.Serializable;
import java.util.List;

public class CartResponseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CartDto> cartDto = null;
	private Integer total = null;
	private double totalPrice;
	
	public List<CartDto> getCartDto() {
		return cartDto;
	}
	public void setCartDto(List<CartDto> cartDto) {
		this.cartDto = cartDto;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
