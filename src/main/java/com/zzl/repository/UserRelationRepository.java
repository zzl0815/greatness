package com.zzl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zzl.bean.User;
import com.zzl.bean.UserRelation;

public interface UserRelationRepository extends JpaRepository<UserRelation, Long>{	
	List<UserRelation> findBySenderOrAccepter(User sender,User accepter);
	
}
