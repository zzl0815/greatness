package com.zzl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BaseController {
	private  static final ObjectMapper mapper = new ObjectMapper();;
	private ModelAndView  view ;
	private String viewName;
	public static ObjectMapper getInstance(){
		return mapper;
	}
	public BaseController() {
		viewName="";
		view=new ModelAndView();
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	@RequestMapping(value = "/{name}.html")
	public ModelAndView baseHtml(@PathVariable("name")String name){
		view.setViewName("/"+viewName+name);
		System.out.println("/"+viewName+name);
		return view ; 
	}
}
