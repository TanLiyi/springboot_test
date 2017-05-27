package com.liyi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.Cart;


public interface CartRepository extends CrudRepository<Cart, Integer>{
	
	@Query("select c from Cart c where c.userId=?1 and deleted=0")
	Cart findByUserId(Integer userId);
}
	

	