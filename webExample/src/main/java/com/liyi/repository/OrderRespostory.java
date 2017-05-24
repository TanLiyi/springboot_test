package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.Order;

public interface OrderRespostory extends CrudRepository<Order, Integer>,JpaSpecificationExecutor<Order> {

	@Query("select o from Order o where id=?1 and deleted=0")
	Order findById(Integer id);
	
	@Query("select o from Order o where deleted=0")
	List<Order> findAllOrder();
	
	@Query("select o from Order o where userId=?1 and deleted=0")
	List<Order>  findUserOrder(Integer userId);
}
