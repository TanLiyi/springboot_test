package com.liyi.dto;

import java.io.Serializable;

import com.liyi.entity.Goods;

public class OrderGoodsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Goods goods=null;
	Integer buyCount;
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	
	
}
