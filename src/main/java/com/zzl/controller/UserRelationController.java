package com.zzl.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzl.bean.User;
import com.zzl.bean.UserRelation;
import com.zzl.bean.common.Common;
import com.zzl.service.UserRelationService;

@RestController
@RequestMapping("/friend")
public class UserRelationController extends BaseController {
	@Autowired
	UserRelationService userRelationService;
	@Transactional
	@RequestMapping(value="saveRelation.do")
	public String saveRelation(
			@RequestParam(value="id",required=true,defaultValue="32")Long id,
			HttpServletRequest request
			){
		UserRelation userrelation = new UserRelation();
		User user =(User)request.getSession().getAttribute("User");
		System.out.println(user.getId()+"userId"); 
		User accepter = new  User();
		accepter.setId(id);
		userrelation.setAccepter(accepter);
		userrelation.setSender(user);
		userrelation.setCreateDate(new SimpleDateFormat(Common.DATEFORMAT).format(new Date()));
		userrelation.setIsAgree(0);
		UserRelation  userRelation = userRelationService.saveUserRelation(userrelation);
		//在存入数据库的时候存入redis一条数据
		//用户登录时访问redis数据
		//如果存在则有消息
		//不存在就没有
		if(userRelation==null){
			return "该值为空";
		}
		//在设计中最好是设计一个返回类 result  status  Object   Comment
		return Common.SUCCESS;
	}
	@Transactional
	@RequestMapping(value="updateIsAgreeById.do")
	public String updateIsAgreeById(
			@RequestParam("Id")Long id,
			HttpServletRequest request
			){
		userRelationService.updateIsAgreeById(1, id);
		return Common.SUCCESS;
	}
}
