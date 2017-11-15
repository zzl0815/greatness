package com.zzl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zzl.bean.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>,JpaSpecificationExecutor<Comment>{
	
	@Query("update Comment set IsApproal=:isApproal where id =:id")
	@Modifying
	void updateIsApproal(@Param("isApproal")Integer isApproal,@Param("id")Long id);

	@Query("update Comment set NoApproal=:noApproal where id =:id")
	@Modifying
	void updateNoApproal(@Param("noApproal")Integer noApproal,@Param("id")Long id);

}
