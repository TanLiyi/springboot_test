package com.liyi.utils;

import java.util.Date;
import java.util.UUID;

public class CodeUtils {

	
	public String productCode(){
		Date date=new Date();
		Long dateTime=date.getTime();
		StringBuffer buffer=new StringBuffer();
		buffer.append("G").append(dateTime);
		String uuid = UUID.randomUUID().toString();
		buffer.append(uuid);
		return buffer.toString();
	}
	
	public static String orderCode(){
		Date date=new Date();
		Long dateTime=date.getTime();
		StringBuffer buffer=new StringBuffer();
		int random=(int)(1+Math.random()*10);
		buffer.append("O").append(dateTime).append(random);
		
		return buffer.toString();
	}
}
