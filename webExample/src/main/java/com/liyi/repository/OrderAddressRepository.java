package com.liyi.repository;

import com.liyi.entity.Address;
import com.liyi.entity.OrderAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderAddressRepository extends CrudRepository<OrderAddress, Integer>{
	
}
