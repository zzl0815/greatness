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
	@OneToOne
	@JoinColumn(name="sender_id")
	private User sender;
	//1发送消息给2
	//非1就是接收者
	@ManyToOne(optional=false)
	@JsonIgnore
	@JoinColumn(name="user_relation_id")
	private UserRelation userRelation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public UserRelation getUserRelation() {
		return userRelation;
	}
	public void setUserRelation(UserRelation userRelation) {
		this.userRelation = userRelation;
	}
	
}
