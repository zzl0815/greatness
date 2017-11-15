package mysql;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzl.bean.User;
import com.zzl.repository.UserRepository;

import junit.framework.Assert;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JpaConfigurationTest.class})
public class MysqlTest {
	private static Logger logger =LoggerFactory.getLogger(MysqlTest.class);
	@Autowired
	UserRepository userRepository;
	@Before
	public void initData(){
		userRepository.deleteAll();
		User user =new User();
		user.setIdentifiesCard("440582199608152673");
		user.setUserName("张振禄");
		user.setPassword("mimamima");
		user.setVipClass(0);
		user.setIntegral(0);
		userRepository.save(user);
		Assert.assertNotNull(user.getId());
		
	}
	@Test
	public void findPage(){
		Pageable pageable =new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
		Page<User> page =userRepository.findAll(pageable);
		Assert.assertNotNull(page);
	}
}
