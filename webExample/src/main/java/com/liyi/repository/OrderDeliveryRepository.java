package com.liyi.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.OrderDelivery;

public interface OrderDeliveryRepository extends CrudRepository<OrderDelivery,Integer>, JpaSpecificationExecutor<OrderDelivery> {

	OrderDelivery findById(Integer id);
}
