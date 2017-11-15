package com.zzl.service;

import java.util.List;

import com.zzl.bean.User;
import com.zzl.bean.UserRelation;

public interface UserRelationService {
	UserRelation saveUserRelation(UserRelation userRelation);
	List<UserRelation> queryUserRelationByUser(User user);
}
