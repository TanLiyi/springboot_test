package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.User;

public interface UserReprositry extends CrudRepository<User, Integer>,JpaSpecificationExecutor<User>{
	
	@Query("select u from User u where username=?1  and deleted=0")
	User findByName(String username);
	
	@Query("select u from User u where username=?1 and id=?2 and deleted=0")
	User findByIdAndName(String username,Integer id);
	
	User findByTel(String tel);
	@Query("select u from User u where tel=?1 and id=?2 and deleted=0")
	User findUserTel(String tel,Integer id);
	
	@Query("select u from User u where username=?1 and password=?2 and deleted=0")
	User findByNameAndPassword(String username,String password);
	
	@Query("select u from User u where id=?1 and deleted=0")
	User findById(Integer id );
	
	@Query("select u from User u where deleted=0")
	List<User> findAllUser();
	
	@Modifying
	@Query("update User set deleted=1 where id in?1")
	void deleteIdIn(List<Integer> collect);
}
