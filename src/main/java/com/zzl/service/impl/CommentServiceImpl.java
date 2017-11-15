package com.zzl.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.zzl.bean.Comment;
import com.zzl.bean.Title;
import com.zzl.repository.CommentRepository;
import com.zzl.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentRepository commentRepository;
	@Override
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}
	@Override
	public String delComment(Comment comment) {
		commentRepository.delete(comment);
		return "success";
	}
	@Override
	public Page<Comment> Comment(Pageable pageable,final Comment com) {
		System.out.println(pageable.getPageSize()+"PageSize::"+pageable.getPageNumber());
		 Page<Comment> comment =commentRepository.findAll(new Specification<Comment>() {
			@Override
			public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate titleId=cb.equal(root.get("title").as(Title.class),com.getTitle());
				query.where(cb.and(titleId));
				return query.getRestriction();
			}
		},pageable);
		return comment;
	}
	@Override
	public void updateIsApproal(com.zzl.bean.Comment comment) {
		commentRepository.updateIsApproal(comment.getIsApproal(),comment.getId());
	}
	@Override
	public void updateNsApproal(com.zzl.bean.Comment comment) {
		commentRepository.updateNoApproal(comment.getNoApproal(), comment.getId());
	}

}
