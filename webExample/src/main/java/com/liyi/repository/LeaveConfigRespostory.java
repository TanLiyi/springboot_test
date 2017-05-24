package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.LeaveConfig;

public interface LeaveConfigRespostory extends CrudRepository<LeaveConfig, Integer> {
	
	//最高只能有4个等级
	@Query("select l from LeaveConfig l ")
	List<LeaveConfig> findAllLeaveConfig();
	
	@Query("select l from LeaveConfig l where minPoint<=?1")
	LeaveConfig findByPoint(Integer point);

}
