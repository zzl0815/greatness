package com.zzl.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
/**
 * 研究初始化了些什么
 * @param connectionFactory
 * @return
 */
	@Bean
	public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory connectionFactory){
		System.out.println("加载redis自定义管理器");
		StringRedisTemplate template=new StringRedisTemplate(connectionFactory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper mapper =new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(mapper);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
		@Bean
		public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
			System.out.println("加载redis注解管理器");
			CacheManager cacheManager= new RedisCacheManager(redisTemplate);
		return cacheManager;
		}
}
