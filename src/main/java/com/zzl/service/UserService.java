package com.zzl.service;
import javax.servlet.http.HttpServletRequest;

import com.zzl.bean.User;
public interface UserService {
	public User getUserById(Long id);
	//根据用户名查出Id和密码,根据密码
	boolean checkNameandPassword(String name, String password, HttpServletRequest request);
	Integer checkVipClass(HttpServletRequest request);
	String saveUser(User user,HttpServletRequest request);
	User updateVipClass(User user);
	User updateintegral(Integer integral,HttpServletRequest request);
}
