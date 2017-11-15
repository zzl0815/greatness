package com.zzl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.zzl.bean.Title;

public interface TitleRepository extends JpaRepository<Title, Long>{
	@Query("update Title set readPerson =:readPerson where id =:id")
	@Modifying
	void updatereadPerson(@Param("readPerson")Integer readPerson,@Param("id")Long id);
}
