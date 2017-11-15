package com.zzl.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzl.bean.UserRelation;

//应该创建个BaseRedis来使用规范接口
@Repository
public class UserRelationRedis {
	static Gson gson ;
	static{
		gson= new Gson();
	}
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	public void saveUserRelation(List<UserRelation> userRelations){
		redisTemplate.opsForValue().set("Accepter:"+userRelations.get(0).getAccepter().getId(),gson.toJson(userRelations));
	}
	public List<UserRelation> queryUserRelations(String key){
		String result =redisTemplate.opsForValue().get("Accepter:"+key);
		return 	gson.fromJson(result, new TypeToken<List<UserRelation>>(){}.getType());
	}
	public void removeUserRelations(String key){
		redisTemplate.opsForValue().getOperations().delete("Accepter:"+key);
	}
}
