package com.liyi.repository;

import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.Stat;

public interface StatRepository extends CrudRepository<Stat, Integer> {
	
}
