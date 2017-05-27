package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>,JpaSpecificationExecutor<Order> {

	@Query("select o from Order o where id=?1 and deleted=0")
	Order findById(Integer id);

	@Query("select o from Order o where deleted=0")
	List<Order> findAllOrder();

	@Query("select o from Order o where userId=?1 and deleted=0")
	List<Order> findUserOrder(Integer userId);

	//0-未 1-支付 2-待收 3-完成
	@Query("select count(1) from Order where orderStatus=2 and deleted=0")
	Integer deliveryingCount(); // 待收货

	@Query("select count(1) from Order where orderStatus=0 and deleted=0")
	Integer notPaidCount(); // 代付款

	@Query("select count(1) from Order where orderStatus=1 and deleted=0")
	Integer undeliveredCount(); // 待发货

	@Query("select count(1) from Order where orderStatus=3 and deleted=0")
	Integer successCount(); // 已完成
}
