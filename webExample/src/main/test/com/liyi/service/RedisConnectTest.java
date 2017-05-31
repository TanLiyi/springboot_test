package com.liyi.service;
 
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
		stringRedisTemplate.opsForValue().set("aaa", "111");
	/*	Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));*/
		System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
    }

}
