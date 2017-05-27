package com.liyi.dto;

import java.io.Serializable;

public class DashBoardGetCountDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer deliveryingCount; //待收货
	private Integer notPaidCount; //代付款
	private Integer undeliveredCount; //待发货
	private Integer successCount; //已完成
	private Integer userCount; //商城会员数
	
	public Integer getDeliveryingCount() {
		return deliveryingCount;
	}
	public void setDeliveryingCount(Integer deliveryingCount) {
		this.deliveryingCount = deliveryingCount;
	}
	public Integer getNotPaidCount() {
		return notPaidCount;
	}
	public void setNotPaidCount(Integer notPaidCount) {
		this.notPaidCount = notPaidCount;
	}
	public Integer getUndeliveredCount() {
		return undeliveredCount;
	}
	public void setUndeliveredCount(Integer undeliveredCount) {
		this.undeliveredCount = undeliveredCount;
	}
	public Integer getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	
}
