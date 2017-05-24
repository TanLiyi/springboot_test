package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.CartSub;

public interface CartSubRespostory extends CrudRepository<CartSub, Integer> {
	
	@Query("select s from CartSub s where  id=?1 and deleted=0")
	CartSub findById( Integer id);
	
	@Query("select s from CartSub s where  cartId=?1 and goodId=?2 and deleted=0")
	CartSub findByIdAndGoodId( Integer cartId,Integer goodId);
	
	@Modifying
	@Query("update CartSub set deleted=1 where id in?1  and deleted=0")
	void deleteIdIn(List<Integer> collect);
	
	@Query("select s from CartSub s where  id in ?1 and deleted=0")
	List<CartSub> findIdIn(List<Integer> collect);

}
