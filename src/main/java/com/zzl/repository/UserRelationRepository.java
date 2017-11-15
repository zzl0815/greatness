package com.zzl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zzl.bean.UserRelation;

public interface UserRelationRepository extends JpaRepository<UserRelation, Long>{	
	
}
