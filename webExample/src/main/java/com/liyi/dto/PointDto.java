package com.liyi.dto;

import java.util.Date;

public class PointDto {

	private Integer point;  //经验值
	private String pointDese;
	private Date createTime;
	
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	
	public String getPointDese() {
		return pointDese;
	}
	public void setPointDese(String pointDese) {
		this.pointDese = pointDese;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
