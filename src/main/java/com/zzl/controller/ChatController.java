package com.zzl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzl.bean.User;

@RestController
@RequestMapping(value="chat")
public class ChatController extends BaseController{
	//初始化聊天界面
	public String indexChat(
			HttpServletRequest request,
			@RequestParam(value="id")Long id
			){
		User user=(User) request.getSession().getAttribute("User");
		//历史聊天记录
		
		return null;
	}
	
}
