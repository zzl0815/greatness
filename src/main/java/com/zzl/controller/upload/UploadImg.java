package com.zzl.controller.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ActionEnter;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class UploadImg {
	
    private final ResourceLoader resourceLoader;  
    @Autowired
	public UploadImg(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	@ResponseBody
	@RequestMapping(value= "uploadImg.do",method=RequestMethod.POST)
	public String uploadloadImg(@RequestParam("file")MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException{
		if(file.isEmpty()){
			return "上传文件为空";
		}
		//待解决代码优化问题
		String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
		String filePath="";
		String filename=  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+type;
		filePath = request.getSession().getServletContext().getRealPath("/personimg/");
		File dest =new File(filePath+filename);
		file.transferTo(dest);
		System.out.println("执行上传文件");
		return  new ObjectMapper().writeValueAsString("/personimg/"+filename);
	}
	@ResponseBody
	@RequestMapping(value= "ueditorImg.do",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String ueditorImg(@RequestParam("upfile")MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException{
		if(file.isEmpty()){
			return null;
		}
		Map  result = new HashMap();	
		String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
		String filePath="";
		String filename=  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+type;
		filePath = request.getSession().getServletContext().getRealPath("/articleimg/");
		File dest =new File(filePath+filename);
		file.transferTo(dest);
		result.put("status", "SUCCESS");
		result.put("url","http:localhost:8080/articleimg/"+filename);
		result.put("title",filename );
		result.put("original",filename);
		System.out.println("http:localhost:8080/articleimg/"+filename);
		System.out.println(new ObjectMapper().writeValueAsString(result));
		return  new ObjectMapper().writeValueAsString(result);
	}
	
	 @RequestMapping(value = "/ueditor")  
	    public void config(HttpServletRequest request, HttpServletResponse response) {  
	        response.setContentType("application/json");  
	        System.out.println("执行ueditor");
	        String rootPath = request.getSession().getServletContext()  
	                .getRealPath("/");  
	        try {  
	            String exec = new ActionEnter(request, rootPath).exec();  
	            PrintWriter writer = response.getWriter();  
	            writer.write(exec);  
	            writer.flush();  
	            writer.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
}
