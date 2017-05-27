package com.liyi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "goods")
@DynamicInsert
@DynamicUpdate
public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	private String name;

	private String pic;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "[desc]")
	private String desc;

	@Column(name = "is_on_shelf")
	private Byte isOnShelf=1;   //1-上架 0未上架 2-已售罄

	@Column(name = "sale_price")   //售价
	private Double salePrice;
	
	@Column(name = "total_stock_qty")  //总库存
	private Integer totalStockQty=0;

	@Column(name = "total_sale_qty") //售出数量
	private Integer totalSaleQty=0;

	@Column(name = "visit_count")
	private Integer count=0; //浏览人数
	
	private Byte deleted=0;

	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "update_time")
	private Date updateTime;
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Byte getDeleted() {
		return deleted;
	}

	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}


	public Byte getIsOnShelf() {
		return isOnShelf;
	}

	public void setIsOnShelf(Byte isOnShelf) {
		this.isOnShelf = isOnShelf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getTotalStockQty() {
		return totalStockQty;
	}

	public void setTotalStockQty(Integer totalStockQty) {
		this.totalStockQty = totalStockQty;
	}

	public Integer getTotalSaleQty() {
		return totalSaleQty;
	}

	public void setTotalSaleQty(Integer totalSaleQty) {
		this.totalSaleQty = totalSaleQty;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

   
}
