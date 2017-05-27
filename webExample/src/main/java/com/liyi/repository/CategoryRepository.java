package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>,JpaSpecificationExecutor<Category>{
	
	List<Category> findByDeleted(Integer deleted);
	
	@Query("select c from Category c where id=?1 and deleted=0")
	Category findById(Integer id);
	
	@Modifying
	@Query("update Category set deleted=1 where id in?1")
	void deleteIdIn(List<Integer> collect);
}
