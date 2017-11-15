package com.zzl.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zzl.bean.User;
import com.zzl.bean.VipClassScore;
import com.zzl.repository.UserRepository;
import com.zzl.repository.VipClassScoreRepository;
import com.zzl.service.UserService;

@Service
public class UserSerivceImpl implements UserService{
	@Autowired
	UserRepository  userrepository;
	@Autowired
	VipClassScoreRepository  scoreRepository;
	@Override
	@Cacheable(value="user_info",keyGenerator="demoKeyGenerator")
	public User getUserById(Long id) {
		return null;
	}
	@Override
	@Transactional
	public boolean checkNameandPassword(String name, String password, HttpServletRequest request) {
		User user = userrepository.findByuserName(name);
		if(user!=null){
			if(user.getPassword().equals(password)){
				userrepository.UserUpdateIntegral(user.getIntegral()+1, user.getId());
				user.setIntegral(user.getIntegral()+1);
				user = updateVipClass(user);
				request.getSession().setAttribute("User", user);
				return true;
			}
		}
		return false;
	}
	@Override
	public Integer checkVipClass(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("User");
		return user.getVipClass();
	}
	@Override
	@Transactional
	public String saveUser(User user, HttpServletRequest request) {
		User checkuserName = userrepository.findByuserName(user.getUserName());
		User checkUseridentifiesCard= userrepository.findByidentifiesCard(user.getIdentifiesCard());
		if(checkuserName!=null){
			return "你的账号已经注册过"; 
		}if(checkUseridentifiesCard!=null){
			return "你的身份证账号已经注册过"; 
		}
		userrepository.save(user);
		request.getSession().setAttribute("User", user);
		return "注册成功";
	}
	@Override
	public User updateVipClass(User user) {
		List<VipClassScore> list = scoreRepository.findAll();
		for(VipClassScore vipClassScore : list){
			if(vipClassScore.getScore().equals(user.getIntegral())){
				userrepository.UserUpdatevipClass(vipClassScore.getVipClass(),user.getId());
				user.setVipClass(vipClassScore.getVipClass());
			}
		}
		return user;
	}
	@Transactional
	@Override
	public User updateintegral(Integer integral, HttpServletRequest request) {
	User user =(User) request.getSession().getAttribute("User");	
	if(user!=null){
		userrepository.UserUpdateIntegral(user.getIntegral()+integral, user.getId());
		user.setIntegral(user.getIntegral()+integral);
		user = updateVipClass(user);
		request.getSession().setAttribute("User", user);
	}
		return user;
	}
}
