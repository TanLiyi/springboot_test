package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.LearnResouce;

public interface LearnResouceRepository extends CrudRepository<LearnResouce, Long> {
	
	@Query("select l from LearnResouce l")
	List<LearnResouce> getAll();

}
