package com.zzl.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzl.bean.Title;
import com.zzl.bean.User;
import com.zzl.bean.common.Common;
import com.zzl.service.impl.TitleServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

}
