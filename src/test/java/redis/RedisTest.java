package redis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzl.bean.User;
import com.zzl.redis.UserRedis;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class,UserRedis.class})
public class RedisTest {
	private static Logger logger =LoggerFactory.getLogger(RedisTest.class);
	@Autowired
	UserRedis userRedis; 
	@Before
	public void setup(){
		User user =new User();
		user.setUserName("张振禄");
		user.setPassword("asd6953");
		Long time =(long) (60*60*24);
		userRedis.add("user",time, user);
	}
	@Test
	public void get(){
		User user =userRedis.get("user");
		System.out.println(user.getUserName());
	}
}
