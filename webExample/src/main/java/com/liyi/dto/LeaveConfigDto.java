package com.liyi.dto;

import java.io.Serializable;

public class LeaveConfigDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private double baifen;
	private String leaveName;
	private Integer minPoint;
	
	public double getBaifen() {
		return baifen;
	}

	public void setBaifen(double baifen) {
		this.baifen = baifen;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public Integer getMinPoint() {
		return minPoint;
	}

	public void setMinPoint(Integer minPoint) {
		this.minPoint = minPoint;
	}

}
