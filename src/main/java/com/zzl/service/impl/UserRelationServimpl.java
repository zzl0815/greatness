package com.zzl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zzl.bean.UserRelation;
import com.zzl.repository.UserRelationRepository;
import com.zzl.service.UserRelationService;
@Service
public class UserRelationServimpl implements UserRelationService {
	@Autowired
	UserRelationRepository userRelationRepository;
	
	@Override
	@Cacheable()
	public UserRelation saveUserRelation(UserRelation userRelation) {
		userRelation = userRelationRepository.save(userRelation);
		return userRelation;
	}
	//创建数组,用来
}
