// table2pojo 0.0.1-SNAPSHOT. Generated on Thu Sep 22 14:51:16 CST 2016.

package com.liyi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table(name = "order_address")
@Entity
@DynamicInsert
@DynamicUpdate
public class OrderAddress implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
    @Column(name = "order_id")
    private Integer orderId;

	private String consignor;   //收货人姓名

	@Column(name="tel")
	private String tel;

	@Column(name="pri")
	private String pri;//省

	@Column(name="city")
	private String ciyt;//市

	@Column(name="town")
	private String town;//区

	@Column(name="street")
	private String street;//街道

	@Column(name="create_time")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getConsignor() {
		return consignor;
	}

	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPri() {
		return pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}

	public String getCiyt() {
		return ciyt;
	}

	public void setCiyt(String ciyt) {
		this.ciyt = ciyt;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
