package com.zzl.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.zzl.bean.User;

@Repository
public class UserRedis {
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	public void add(String key,Long time ,User user ){
		Gson gson = new Gson();
		redisTemplate.opsForValue().set(key,gson.toJson(user),time,TimeUnit.MINUTES);
	}
	public User get(String key){
		Gson gson =new Gson();
		User user =new User();
		String userJson=redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(userJson)){
			user=gson.fromJson(userJson,User.class);
		}
		return user;
	}
}
