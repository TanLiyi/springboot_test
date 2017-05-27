package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.Points;


public interface PointsRepository extends CrudRepository<Points, Integer>{
	
	@Query("select p from Points p where p.userId=?1  order by createTime desc")
	List<Points> findByUserId(Integer userId);
	
//	@Query("select p from Points p where p.userId=?1 and date_format(o.createTime, '%Y-%m-%d') = date_format(NOW(), '%Y-%m-%d')")
//	List<Points> findByUser(Integer userId);
}
