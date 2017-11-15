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
		User accepter = new  User();
		accepter.setId(id);
		userrelation.setAccepter(user);
		userrelation.setSender(user);
		userrelation.setCreateDate(new SimpleDateFormat(Common.DATEFORMAT).format(new Date()));
		userrelation.setIsAgree(0);
		userRelationService.saveUserRelation(userrelation);
		//在存入数据库的时候存入redis一条数据
		//用户登录时访问redis数据
		//如果存在则有消息
		//不存在就没有
		
		return Common.SUCCESS;
	}
}
