package com.zzl.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.zzl.bean.Chat;
import com.zzl.bean.User;
import com.zzl.repository.ChatRepository;
import com.zzl.service.ChatService;
@Service
public class ChatServiceImpl implements ChatService{
	ChatRepository chatRepository;
	@Override
	@CachePut(value="findchatBySenderAndAccepter",key="#id")
	public Chat save(Long id,Chat chat) {
	 chat =chatRepository.save(chat);
	 //更新缓存
	 //覆盖redis -->key值应该保存为分页和id  
		return chat;
	}

	@Override
	public List<Chat> queryChat(User user,Pageable page) {
		return null;
	}
}
