package com.zzl.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zzl.bean.User;
import com.zzl.bean.UserRelation;

public interface UserRelationRepository extends JpaRepository<UserRelation, Long>{	
	List<UserRelation> findBySenderOrAccepter(User sender,User accepter);
	@Query("select id from UserRelation where (Sender =:sender and Accepter =:accepter) or (Sender =:accepter and Accepter =:sender)")
	UserRelation findIdBySenderAndAccepterOr(User sender,User accepter);
	//查询条件不能查询自身
	@Query("select id,chats from UserRelation where id=:id")
	UserRelation findchatBySenderAndAccepter(Long id);
	@Query("update  UserRelation set isAgree=:isAgree  where id =:id")
	@Modifying
	void updateIsAgreeById(@Param("isAgree")Integer isAgress,@Param("id")Long id);
}
