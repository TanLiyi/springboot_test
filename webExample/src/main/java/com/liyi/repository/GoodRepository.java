package com.liyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.liyi.entity.Goods;

public interface GoodRepository extends CrudRepository<Goods, Integer>,JpaSpecificationExecutor<Goods> {
	@Query("select g from Goods g where isOnShelf=1 and deleted=0 and categoryId=?1")
	List<Goods> findOnShalf(Integer categoryId);
	
	@Query("select g from Goods g where deleted=0 and id=?1")
	Goods findbyId(Integer id);
	
//	@Query("select g from Goods g where categoryId=?1 and deleted=0")
//	List<Goods> findAdminCategoryGoods(Integer categoryId);
	
	@Query("select g from Goods g where isOnShelf=1 and deleted=0 and name like ?1 order by createTime desc")
	List<Goods> finByName(String name);
	
	@Query("select g from Goods g where isOnShelf=1 and deleted=0 and categoryId=?1")
	List<Goods> findByCategoryId(Integer categoryId);
	
	@Query("select g from Goods g where isOnShelf=1 and deleted=0 order by totalSaleQty desc")
	List<Goods> findIndexTopSaleGood();
	
}