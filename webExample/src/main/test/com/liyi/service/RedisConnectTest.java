package com.liyi.service;
 
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyi.StartApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={StartApplication.class})
public class RedisConnectTest {

	@Autowired
    private RedisTemplate<String, String> stringRedisTemplate;
	
	@Test
	public void test(){
		// 保存字符串
		/*stringRedisTemplate.opsForValue().set("bfe", "111");*/
//		stringRedisTemplate.opsForList().rightPush("list1", "1");
//		stringRedisTemplate.opsForList().rightPush("list1", "2");
		List<String> list=stringRedisTemplate.opsForList().range("list1" ,0, -1l);
		System.out.println(list);
//		list.stream().count();
		System.out.println(list.stream().count());
		
		Map<Object,Object> map=stringRedisTemplate.opsForHash().entries("hash-key");
		System.out.println(map);
    }

}
