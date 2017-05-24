package com.liyi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liyi.entity.Order;
import com.liyi.entity.OrderDelivery;
import com.liyi.exception.CommonCode;
import com.liyi.exception.ServiceException;
import com.liyi.repository.OrderDeliveryRepository;
import com.liyi.repository.OrderRespostory;

@Service
public class OrderDeliveryService {

	@Autowired
	private OrderDeliveryRepository orderDeliveryRepository;
	
	@Autowired
	private OrderRespostory orderRespostory;
	
	@Transactional
	public Integer create(Integer orderId,String delivererName,String deliveryCode){
		Order order=orderRespostory.findById(orderId);
		if(order==null){
			throw new ServiceException(CommonCode.NOT_FOUND,"not found!");
		}
		OrderDelivery delivery=new OrderDelivery();
		delivery.setOrderId(orderId);
		delivery.setDelivererName(delivererName);
		delivery.setDeliveryCode(deliveryCode);
		
		orderDeliveryRepository.save(delivery);
		return delivery.getId();
	}
}
