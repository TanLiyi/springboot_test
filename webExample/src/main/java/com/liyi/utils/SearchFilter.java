/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.liyi.utils;

/**
 * 参考自springside
 */
public class SearchFilter {

	/**
	 * EQ:等于 LIKE:模糊查找 GT:大于 LT:小于 GTE:大于等于 LTE:小于等于
	 */
	public enum Operator {
		EQ, LIKE, GT, LT, GTE, LTE
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	/**
	 * 默认为eq操作,若value为空或空串时会在CustomSpecications.searchFilterSpec()中忽略
	 * 
	 * @param fieldName
	 *            字段名
	 * @param value
	 *            字段值
	 */
	public SearchFilter(String fieldName, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = Operator.EQ;
	}

	/**
	 * 若value为空或空串时会在CustomSpecications.searchFilterSpec()中忽略
	 * 
	 * @param fieldName
	 *            字段名
	 * @param value
	 *            字段值
	 * @param operator
	 *            Operator类型
	 */
	public SearchFilter(String fieldName, Object value, Operator operator) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	// private static Map<String, SearchFilter> parse(Map<String, Object>
	// searchParams) {
	// Map<String, SearchFilter> filters = Maps.newHashMap();
	//
	// for (Entry<String, Object> entry : searchParams.entrySet()) {
	// // 过滤掉空值
	// String key = entry.getKey();
	// Object value = entry.getValue();
	// if (StringUtils.isBlank((String) value)) {
	// continue;
	// }
	//
	// // 拆分operator与filedAttribute
	// String[] names = StringUtils.split(key, "_");
	// if (names.length != 2) {
	// throw new IllegalArgumentException(key + " is not a valid search filter
	// name");
	// }
	// String filedName = names[1];
	// Operator operator = Operator.valueOf(names[0]);
	//
	// // 创建searchFilter
	// SearchFilter filter = new SearchFilter(filedName, value, operator);
	// filters.put(key, filter);
	// }
	//
	// return filters;
	// }
}
