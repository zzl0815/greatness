package com.zzl.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zzl.bean.User;
import com.zzl.bean.UserRelation;
import com.zzl.redis.UserRelationRedis;
import com.zzl.repository.UserRelationRepository;
import com.zzl.service.UserRelationService;
@Service
public class UserRelationServimpl implements UserRelationService {
	@Autowired
	UserRelationRepository userRelationRepository;
	//创建单列数组
	@Autowired
	UserRelationRedis  userRelationRedis;
	@Override
	public UserRelation saveUserRelation(UserRelation userRelation) {
		userRelation = userRelationRepository.save(userRelation);
		List<UserRelation> list = userRelationRedis.queryUserRelations(userRelation.getAccepter().getId()+"");
		if(list==null){
			list = new ArrayList();
			list.add(userRelation);
		}
		//判断是否重复添加;
		for(UserRelation userrelation:list){
			if(userrelation.getSender().getId().equals(userRelation.getSender().getId())||userrelation.getAccepter().getId().equals(userRelation.getAccepter().getId())){
				if(userrelation.getAccepter().getId().equals(userRelation.getAccepter().getId())||userrelation.getSender().getId().equals(userRelation.getSender().getId())){
					return null;
				}
			}
		}
		userRelationRedis.saveUserRelation(list);
		return userRelation;
	}
	@Override
	public List<UserRelation> queryUserRelationByUser(User user) {
		return userRelationRepository.findBySenderOrAccepter(user, user);
	}
}
