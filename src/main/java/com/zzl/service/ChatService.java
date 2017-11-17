package com.zzl.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.zzl.bean.Chat;
import com.zzl.bean.User;

public interface ChatService {
	Chat save(Long id,Chat chat);
	List<Chat> queryChat(User user,Pageable page);
}
