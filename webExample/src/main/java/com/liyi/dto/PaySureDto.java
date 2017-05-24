package com.liyi.dto;

import java.io.Serializable;
import java.util.List;

public class PaySureDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	List<OrderInputDto> goods=null;
	private double total;
	private String favourable; //优惠描述
	private double realPrice;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public List<OrderInputDto> getGoods() {
		return goods;
	}
	public void setGoods(List<OrderInputDto> goods) {
		this.goods = goods;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getFavourable() {
		return favourable;
	}
	public void setFavourable(String favourable) {
		this.favourable = favourable;
	}
	public double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}
	
}
