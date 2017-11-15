package com.zzl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.zzl.bean.Comment;

public interface CommentService {
	Comment saveComment(Comment comment);
	String delComment(Comment comment);
	Page<Comment> Comment(Pageable pageable,Comment com);
	void updateIsApproal(Comment comment);
	void updateNsApproal(Comment comment);
} 
