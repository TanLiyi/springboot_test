package com.liyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyi.dto.DashBoardGetCountDto;
import com.liyi.repository.OrderRespostory;

@Service
public class DashBoardService {

	@Autowired
	private OrderRespostory orderRespostory;

	
	public DashBoardGetCountDto getCount() {
		Integer deliveryingCount = orderRespostory.deliveryingCount();
		Integer notPaidCount = orderRespostory.notPaidCount();
		Integer undeliveredCount = orderRespostory.undeliveredCount();
		Integer successCount = orderRespostory.successCount();

		DashBoardGetCountDto response = new DashBoardGetCountDto();
		response.setDeliveryingCount(deliveryingCount);
		response.setNotPaidCount(notPaidCount);
		response.setUndeliveredCount(undeliveredCount);
		response.setSuccessCount(successCount);
		return response;
	}
	
	
		
}
