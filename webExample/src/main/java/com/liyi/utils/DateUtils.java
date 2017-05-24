package com.liyi.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static String dateToLocalDateTime(Date date){
		Instant in=date.toInstant();
		ZoneId zone=ZoneId.systemDefault();
		LocalDateTime localDate=LocalDateTime.ofInstant(in, zone);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return formatter.format(localDate);
	}

	public static Date getNextDate(Date date){
		Calendar calendar=new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,1);
		date=calendar.getTime();
		return date;
	}
}
