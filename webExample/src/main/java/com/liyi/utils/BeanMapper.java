package com.liyi.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public class BeanMapper {
	private static DozerBeanMapper dozer=new DozerBeanMapper();

	public static <T>  T map(Object source, Class<T> destinationClass){
		return dozer.map(source,destinationClass);
	}

	public <T> List<T> mapList(Iterable<?> sourceList, Class<T> destinationClass){
		List<T> list= Lists.newArrayList();
		for (Object source : sourceList) {
			T object=dozer.map(source,destinationClass);
			list.add(object);
		}
		return list;
	}

}
