package com.zzl.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="chat")
public class Chat {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	@Column(name="create_date")
	private String createDate;
	@Column(name="content")
	private String content;
	@ManyToOne(optional=false)
	@JsonIgnore
	@JoinColumn(name="user_relation_id")
	private UserRelation userRelation;
	
}
