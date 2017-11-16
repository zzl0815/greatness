package com.zzl.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.zzl.bean.Chat;
import com.zzl.bean.User;
import com.zzl.repository.ChatRepository;
import com.zzl.service.ChatService;

public class ChatServiceImpl implements ChatService{
	ChatRepository chatRepository;
	@Override
	public Chat save(Chat chat) {
	 chat =chatRepository.save(chat);
		return chat;
	}

	@Override
	public List<Chat> queryChat(User user,Pageable page) {
		return null;
	}
}
