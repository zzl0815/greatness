package com.zzl.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzl.bean.Comment;
import com.zzl.bean.Title;
import com.zzl.bean.User;
import com.zzl.bean.UserRelation;
import com.zzl.bean.common.Common;
import com.zzl.redis.UserRelationRedis;
import com.zzl.service.CommentService;
import com.zzl.service.UserRelationService;
import com.zzl.service.impl.TitleServiceImpl;
import com.zzl.service.impl.UserSerivceImpl;

@RestController
@RequestMapping("index")
public class IndexController extends BaseController{
	@Autowired
	TitleServiceImpl titleService;
	@Autowired
	UserSerivceImpl userService;
	@Autowired
	CommentService commentSerivce;
	@Autowired
	UserRelationRedis userRelationRedis;  //错了  没有经过service层
	@Autowired
	UserRelationService userRelationService ;
	@RequestMapping(value="queryTitle.do",method=RequestMethod.GET)
	public String queryTitle(
			@RequestParam(value="start",defaultValue="0")Integer start,
			@RequestParam(value="length",defaultValue="5")Integer length,
			HttpServletRequest request
			) throws JsonProcessingException{
		Pageable pageable= new PageRequest(start,length,Sort.Direction.DESC,"createDate");
		return 	getInstance().writeValueAsString(titleService.queryTile(pageable));
	}
	//跳转页面...暂未想到如何更好的跳转页面！！！
	//理论上应该是不级联查询comments  当评论人数多时查询太多,造成数据缓慢,
	//应该分页查询即可
	@Transactional
	@RequestMapping(value="/findTitleById.do",method=RequestMethod.GET)
	public  ModelAndView findTitleById(
			@PathParam(value="id")Long id,
			HttpServletRequest request,
			@RequestParam(name="start",defaultValue="0")Integer start,
			@RequestParam(name="length",defaultValue="5")Integer length
			) throws JsonProcessingException{
		ModelAndView  model = new ModelAndView();
		model.setViewName("/article");
		 Title title =titleService.findTitleById(id);
		title.setReadPerson(title.getReadPerson()+1);
		titleService.updateReadPersonById(title,id);
		userService.updateintegral(1, request);
		Pageable pageable = new PageRequest(start, length,Sort.Direction.DESC,"createDate");
		Comment com = new Comment();
		com.setTitle(title);
		Page<Comment>  page = commentSerivce.Comment(pageable, com);
		model.addObject("TitleById",title);
		model.addObject("comment",page.getContent());
		return model ;
	}
	@Transactional
	@RequestMapping(value="/saveComment.do",method=RequestMethod.GET)
	public String saveComment(
			HttpServletRequest  requeset,
			@RequestParam(name="comment")String comment,
			@RequestParam(name="titleId")Long titleId,
			HttpServletResponse response
			) throws IOException{
		Comment  com = new Comment();
		com.setIsApproal(0);
		com.setNoApproal(0);
		Comment comm=null;
		User user = (User) requeset.getSession().getAttribute("User");
		if(user!=null){
		com.setComment(comment);
		com.setIp(requeset.getRemoteAddr());
		Title title = titleService.findTitleById(titleId);
		com.setUser(user);
		com.setTitle(title);
		 comm= commentSerivce.saveComment(com);
		}else{
			response.sendRedirect("index.html");
			return "index.html";
		}
		return getInstance().writeValueAsString(comm);
	}
	@Transactional
	@RequestMapping(value="/updateIsApproal.do",method=RequestMethod.GET)
	public String updateIsApproal(
			HttpServletRequest  requeset,
			@RequestParam(name="Id")Long Id,
			@RequestParam(name="IsApproal")Integer IsApproal
			) throws JsonProcessingException{
		Comment   com = new Comment();
		//bug还未处理,一个人点赞多次 
		//设想利用IP地址和用户来预防刷点赞
		//点赞未专门列表 所以我应该将点赞的IP存入缓存中;
		com.setIsApproal(IsApproal+1);
		com.setId(Id);
		commentSerivce.updateIsApproal(com);
		return getInstance().writeValueAsString(com);
	}
	@Transactional
	@RequestMapping(value="/updateNsApproal.do",method=RequestMethod.GET)
	public String updateNsApproal(
			HttpServletRequest  requeset,
			@RequestParam(name="Id")Long Id,
			@RequestParam(name="NoApproal")Integer NoApproal
			) throws JsonProcessingException{
		Comment   com = new Comment();
		com.setNoApproal(NoApproal+1);
		com.setId(Id);
		commentSerivce.updateNsApproal(com);
		return getInstance().writeValueAsString(com);
	}
	@RequestMapping(value="/Remindersfriends.do",method=RequestMethod.GET)
	public String Remindersfriends(
			HttpServletRequest request
			) throws JsonProcessingException{
		User user =(User)request.getSession().getAttribute("User");
		if(user!=null){
			List<UserRelation> list =userRelationRedis.queryUserRelations(user.getId()+"");
			if(list!=null){
				System.out.println(getInstance().writeValueAsString(list)+"list::");
				return getInstance().writeValueAsString(list);
			}
		}
		return getInstance().writeValueAsString("0");
	}
	@RequestMapping(value="/delRemindersfriends.do",method=RequestMethod.GET)
	public String delRemindersfriends(
			HttpServletRequest request) throws JsonProcessingException{
		User user=((User)request.getSession().getAttribute("User"));
		userRelationRedis.removeUserRelations(user.getId()+"");
		//下次进入就又用数据库取值了,所以可以在方法上cacheable()
		List<UserRelation>  list = userRelationService.queryUserRelationByUser(user);
		System.out.println(getInstance().writeValueAsString(list));
		return getInstance().writeValueAsString(list); 
	}
}

