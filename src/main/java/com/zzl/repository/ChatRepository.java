package com.zzl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zzl.bean.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long>,JpaSpecificationExecutor<Chat>{
	
}
