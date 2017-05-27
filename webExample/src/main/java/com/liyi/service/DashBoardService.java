package com.liyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyi.dto.DashBoardGetCountDto;
import com.liyi.repository.OrderRepository;
import com.liyi.repository.UserRepository;

@Service
public class DashBoardService {

	@Autowired
	private OrderRepository orderRespostory;
	
	@Autowired
	private UserRepository userRespostory;

	
	public DashBoardGetCountDto getCount() {
		Integer deliveryingCount = orderRespostory.deliveryingCount();
		Integer notPaidCount = orderRespostory.notPaidCount();
		Integer undeliveredCount = orderRespostory.undeliveredCount();
		Integer successCount = orderRespostory.successCount();
		Integer userCount=userRespostory.countByDeleted();
		
		DashBoardGetCountDto response = new DashBoardGetCountDto();
		response.setDeliveryingCount(deliveryingCount);
		response.setNotPaidCount(notPaidCount);
		response.setUndeliveredCount(undeliveredCount);
		response.setSuccessCount(successCount);
		response.setUserCount(userCount);
		return response;
	}
	
	
		
}
