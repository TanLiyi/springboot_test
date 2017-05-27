package com.liyi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyi.entity.Goods;
import com.liyi.repository.GoodRepository;


@Service
public class StatService {

	@Autowired
	private GoodRepository goodRepository;
	
	@Transactional
	public void statGoodvisit(Integer goodId){
		Goods good=goodRepository.findbyId(goodId);
		if(good!=null){
			good.setCount(good.getCount()+1);
		}
	}
	
}
