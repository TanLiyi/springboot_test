package com.liyi.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class JsonMapper {

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private ObjectMapper objectMapper;

	public static final ObjectMapper defaultObjectMapper;
	public static final ObjectMapper nonEmptyObjectMapper;
	public static final ObjectMapper nonDefaultObjectMapper;

	static {
		defaultObjectMapper = new ObjectMapper();
		//defaultObjectMapper.setSerializationInclusion(Include.NON_NULL);
		defaultObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		defaultObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		nonEmptyObjectMapper = new ObjectMapper();
		nonEmptyObjectMapper.setSerializationInclusion(Include.NON_EMPTY);
		nonEmptyObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		nonEmptyObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		nonDefaultObjectMapper = new ObjectMapper();
		nonDefaultObjectMapper.setSerializationInclusion(Include.NON_DEFAULT);
		nonDefaultObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		nonDefaultObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	private JsonMapper() {}

	/**
	 * 默认mapper
	 */
	public static JsonMapper defaultMapper() {
		JsonMapper mapper = new JsonMapper();
		mapper.objectMapper = defaultObjectMapper;
		return mapper;
	}

	/**
	 * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
	 */
	public static JsonMapper nonEmptyMapper() {
		JsonMapper mapper = new JsonMapper();
		mapper.objectMapper = nonEmptyObjectMapper;
		return mapper;
	}

	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
	 */
	public static JsonMapper nonDefaultMapper() {
		JsonMapper mapper = new JsonMapper();
		mapper.objectMapper = nonDefaultObjectMapper;
		return mapper;
	}

	public String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String toPrettyJson(Object object) {
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}



	/**
	 * 当JSON里只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
	 */
	public void update(String jsonString, Object object) {
		try {
			objectMapper.readerForUpdating(object).readValue(jsonString);
		} catch (IOException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
	}

	/**
	 * 輸出JSONP格式數據.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 設定是否使用Enum的toString函數來讀寫Enum,
	 * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
	 * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
	 */
	public void enableEnumUseToString() {
		objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
