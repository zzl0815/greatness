package com.zzl.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zzl.bean.User;
import com.zzl.bean.common.Common;
import com.zzl.service.UserService;
import com.zzl.util.EmailUtils;
import com.zzl.util.MD5Utils;


@RestController
@RequestMapping("login")
public class LoginController extends BaseController{
	@Autowired
	UserService userSerivce;
	@Autowired
	EmailUtils util;
	@ResponseBody
	@RequestMapping(value = "/login.do",method=RequestMethod.POST)
	public boolean Login(@RequestParam("userName")String userName,
			@RequestParam("password")String password,
			HttpServletRequest request){
				 return userSerivce.checkNameandPassword(userName,MD5Utils.GetMD5Code(password),request);
	}
	@ResponseBody
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	public String Register(
			@RequestParam("userName")String userName,
			@RequestParam("password")String password,
			@RequestParam("identifiesCard")String identifiesCard,
			@RequestParam("img")String img,
			@RequestParam("nickname")String nickname,
			HttpServletRequest request
			){
		System.out.println(img);
		User  user = new User();
		user.setUserName(userName);
		user.setPassword(MD5Utils.GetMD5Code(password));
		user.setIdentifiesCard(identifiesCard);
		user.setIntegral(0);
		user.setVipClass(0);
		user.setNickname(nickname);
		user.setCreateDate(new SimpleDateFormat(Common.DATEFORMAT).format(new Date()));
		if(img.equals("")){
			img = "/personimg/img.jpg";
		}
		user.setImg(img);
		String result = userSerivce.saveUser(user, request);
		return  result;
	}
	@ResponseBody
	@RequestMapping(value="/userExit.do",method=RequestMethod.POST)
	public void Register(HttpServletRequest request){
		request.getSession().removeAttribute("User");
	}
	@ResponseBody
	@RequestMapping(value="sendEmail.do",method=RequestMethod.POST)
	public String sendEmail(@RequestParam("EmailAddress")String EmailAddress){
		Random  random = new Random();
		StringBuilder  builder =new StringBuilder();
		for(int i=0;i<6;i++){
			builder.append(random.nextInt(9));
		}
		util.sendSimpleMail(EmailAddress, Common.EMIAL_CODE_SUBJECT, builder.toString());
		return builder.toString();  
	}
}
