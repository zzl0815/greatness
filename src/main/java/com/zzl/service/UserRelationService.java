package com.zzl.service;

import java.util.List;

import com.zzl.bean.Chat;
import com.zzl.bean.User;
import com.zzl.bean.UserRelation;

public interface UserRelationService {
	UserRelation saveUserRelation(UserRelation userRelation);
	List<UserRelation> queryUserRelationByUser(User user);
	UserRelation findIdBySenderAndAccepterOr(User sender,User Accepter);
	UserRelation findchatBySenderAndAccepter(UserRelation userRelation);
	String updateIsAgreeById(Integer isAgress,Long id);
}
