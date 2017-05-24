package com.liyi.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.liyi.entity.Address;
import com.liyi.entity.User;

public class OrderDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private User user;
	private String orderCode;
	private Date createTime;
	List<OrderGoodsDto> goods;
	private double realPice;
	private double total;
	private Integer count;
	private Integer status;
	private String desc;
	private Address address;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public List<OrderGoodsDto> getGoods() {
		return goods;
	}
	public void setGoods(List<OrderGoodsDto> goods) {
		this.goods = goods;
	}
	public double getRealPice() {
		return realPice;
	}
	public void setRealPice(double realPice) {
		this.realPice = realPice;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
