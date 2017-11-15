package com.zzl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zzl.interceptor.FirstInterceptor;
import com.zzl.interceptor.LoginInterceptor;
import com.zzl.interceptor.SecondInterceptor;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter{
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/index/**").excludePathPatterns("/login/**");
			registry.addInterceptor(new FirstInterceptor()).addPathPatterns("/first/**");
			registry.addInterceptor(new SecondInterceptor()).addPathPatterns("/second/**");
			super.addInterceptors(registry);
		}
}
