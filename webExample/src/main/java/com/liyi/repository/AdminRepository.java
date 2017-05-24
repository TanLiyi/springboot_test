package com.liyi.repository;

import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
	
	Admin findByNameAndPassword(String name,String password);

}
