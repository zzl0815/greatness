package com.zzl.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzl.bean.Title;
import com.zzl.bean.TitleType;
import com.zzl.bean.User;
import com.zzl.bean.common.Common;
import com.zzl.service.impl.TitleServiceImpl;
import com.zzl.service.impl.TitleTypeServiceImpl;

@RestController
@RequestMapping("/title")
public class TitleController extends BaseController{
	@Autowired
	TitleServiceImpl  titleservice;
	@Autowired
	TitleTypeServiceImpl titleTypeServiceImpl;
	
	@RequestMapping(value="/publishTopic.do" ,method=RequestMethod.POST)
	public String publishTopic(@RequestParam("title")String titlename,
			@RequestParam("content")String content,
			@RequestParam("titleAbstract")String titleAbstract,
			@RequestParam("titleTypeId")Long titleTypeId,
			HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		Title  title = new Title();
		title.setContent(content);
		title.setTitle(titlename);
		TitleType  type =new TitleType();
		type.setId(titleTypeId);
		title.setType(type);
		type.setTypeName(titlename);
		title.setTitleAbstract(titleAbstract);
		if(user!=null){
			title.setDelflag(0);
			title.setCreateDate(new SimpleDateFormat(Common.DATEFORMAT).format(new Date()));
			title.setReadPerson(0);
			titleservice.saveTitle(title);
		}
		return Common.SUCCESS;
	}
	@RequestMapping(value="querytitleType.do",method=RequestMethod.POST)
	public String querytitleType(
			HttpServletRequest request) throws JsonProcessingException{
		List<TitleType> list = null;
		User user = (User) request.getSession().getAttribute("User");
		if(user!=null){
			 list = titleTypeServiceImpl.findByUserId(user.getId());
		}
		System.out.println(getInstance().writeValueAsString(list));
		return getInstance().writeValueAsString(list);
	}
	@RequestMapping(value="savetitleType.do",method=RequestMethod.POST)
	public String savetitleType(@RequestParam(value="typeName")String typeName,
			HttpServletRequest request){
		TitleType  type = new TitleType();
		User user = (User) request.getSession().getAttribute("User");
		if(user!=null){
			type.setTypeName(typeName);
			type.setUser(user);
			type.setCreateDate(new SimpleDateFormat(Common.DATEFORMAT).format(new Date()));
		}
		return 	titleTypeServiceImpl.saveTitleType(type);
	}
}
