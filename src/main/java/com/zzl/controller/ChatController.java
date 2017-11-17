package com.zzl.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzl.bean.Chat;
import com.zzl.bean.User;
import com.zzl.bean.UserRelation;
import com.zzl.bean.common.Common;
import com.zzl.service.ChatService;
import com.zzl.service.UserRelationService;

@RestController
@RequestMapping(value="chat")
public class ChatController extends BaseController{
	@Autowired
	UserRelationService userRelationService;
	@Autowired
	ChatService chatService;
	//初始化聊天界面
	@RequestMapping(value="chats.do")
	public String indexChat(
			HttpServletRequest request,
			@RequestParam(value="id")Long id
			) throws JsonProcessingException{
		User sender=(User) request.getSession().getAttribute("User");
		//历史聊天记录
		User  accepter= new User();
		accepter.setId(id);
		UserRelation userRelation = userRelationService.findIdBySenderAndAccepterOr(sender, accepter);
		userRelation = userRelationService.findchatBySenderAndAccepter(userRelation);
		//其实更好的操作是分页查询,但是为了开发初期实现功能所以不做分页处理,已经做了懒加载
		//先查出历史纪录多少条,然后根据开发实际情况,选择使用查询所有,还是分页查询,
		if(userRelation==null){
			return "";
		}
		return getInstance().writeValueAsString(userRelation);
	}
	
	@RequestMapping(value="savechat.do")
	public String savechat(
			@RequestParam(value="id")Long id,
			@RequestParam(value="content")String content,
			HttpServletRequest request
			) throws JsonProcessingException
	{
		User user =(User) request.getSession().getAttribute("User");
		Chat  chat = new Chat();
		UserRelation userRelation = new UserRelation();
		userRelation.setId(id);
		chat.setContent(content);
		chat.setSender(user);
		chat.setUserRelation(userRelation);
		chat.setCreateDate(new SimpleDateFormat(Common.DATEFORMAT).format(new Date()));
		return getInstance().writeValueAsString(chatService.save(id,chat));
	}
}
