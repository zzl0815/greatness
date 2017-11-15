package com.zzl.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice; 
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zzl.util.EmailUtils;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
		
	@Autowired
	EmailUtils util;
	 	@ExceptionHandler(value = Exception.class)  
	    @ResponseBody  
	    public String jsonHandler(HttpServletRequest request, Exception e) throws Exception {  
	        StringWriter sw = new StringWriter();  
	        PrintWriter pw = new PrintWriter(sw);  
	        e.printStackTrace(pw);  
	        System.out.println(sw.toString());
	        //发送邮件  
	        //util.sendSimpleMail("948706409@qq.com", "异常", sw.toString());  
	        return "发生异常";  
	    }  
}
