package com.zzl.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
@EnableJpaRepositories(basePackages="com.zzl.repository")
@EntityScan(basePackages="com.zzl.bean")
public class JpaConfiguration {
	@Bean
		PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor(){
		System.out.println("加载Jpa");
		return new PersistenceExceptionTranslationPostProcessor();
	}
	 @Bean
	    public PlatformTransactionManager transactionManager() {
	        return new JpaTransactionManager();
	    }
	    @Bean
	    public TransactionTemplate transactionTemplate() {
	        return new TransactionTemplate(transactionManager());
	    }
}
