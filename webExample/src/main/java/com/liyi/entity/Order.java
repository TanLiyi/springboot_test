package com.liyi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tan_order")
@DynamicInsert
@DynamicUpdate
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "order_code")
	private String orderCode;

	@Column(name = "order_status")
	private Integer orderStatus; // 1.未付款 2.已付款 3.待收货 5 已收货-已完成

	@Column(name = "real_price")
	private double realPrice;
	
	@Column(name = "total_price")
	private double totalPrice;

	private String favourable;  //优惠
	
	@Column(name = "deleted")
	private Integer deleted;
	
	@Column(name = "create_time")
    private Date createTime;
    
    @Column(name = "update_time")
    private Date updateTime;
    
    @Column(name = "address_id")
    private Integer addressId;
    
    @OneToMany(mappedBy = "order")
    private List<OrderSub> orderSub;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", insertable = false, updatable = false,referencedColumnName="id")
    private User user;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="address_id", insertable = false, updatable = false,referencedColumnName="id")
    private OrderAddress address;
    
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public OrderAddress getAddress() {
		return address;
	}

	public void setAddress(OrderAddress address) {
		this.address = address;
	}

	public String getFavourable() {
		return favourable;
	}

	public void setFavourable(String favourable) {
		this.favourable = favourable;
	}

	public List<OrderSub> getOrderSub() {
		return orderSub;
	}

	public void setOrderSub(List<OrderSub> orderSub) {
		this.orderSub = orderSub;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}


	public double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}
