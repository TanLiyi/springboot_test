package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.Address;


public interface AddressRepository extends CrudRepository<Address, Integer>{
	
	@Query("select a from Address a where a.userId=?1  and deleted=0 order by a.isDefault desc")
	List<Address> findByUserId(Integer userId);
	
	@Query("select a from Address a where a.id=?1 and deleted=0")
	Address findById(Integer id);
	
	@Query("select a from Address a where a.userId=?1 and isDefault=0 and deleted=0")
	Address findDefault(Integer userId);
	
	@Query("select a from Address a where a.userId=?1 and a.isDefault=1")
	Address findByUserIdAndIsDefault(Integer userId);
	
}
