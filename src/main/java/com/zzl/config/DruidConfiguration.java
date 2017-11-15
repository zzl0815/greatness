package com.zzl.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfiguration {
	@Bean
	public ServletRegistrationBean statViewServle(){
		//监控后台服务器的连接地址为:/druid/*
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
			//白名单
		servletRegistrationBean.addInitParameter("allow", "192.168.0.119");
		//黑名单
		servletRegistrationBean.addInitParameter("deny", "192.168.0.113");
		//账号密码
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword","admin");
		//是否能够重置数据
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean statFilter(){
		FilterRegistrationBean bean=new FilterRegistrationBean(new WebStatFilter());
		bean.addUrlPatterns("/*");
		bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return bean;
	}
}
