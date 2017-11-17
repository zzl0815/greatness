package com.zzl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzl.bean.User;
import com.zzl.bean.UserRelation;
import com.zzl.service.UserRelationService;

@RestController
@RequestMapping("/first")
public class FirstClassController extends BaseController{
	public FirstClassController() {
		setViewName("first/");
	}
	@Autowired
	UserRelationService userRelationSerivce;
	@RequestMapping("/queryfriend.do")
	public String queryfirend(
			HttpServletRequest request) throws JsonProcessingException{
		User user =(User)request.getSession().getAttribute("User");
		List<UserRelation>  list = userRelationSerivce.queryUserRelationByUser(user);
		System.out.println(getInstance().writeValueAsString(list));
		return getInstance().writeValueAsString(list);
	}
}
