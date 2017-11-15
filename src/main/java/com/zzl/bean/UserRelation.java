package com.zzl.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_relation")
public class UserRelation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	@OneToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	@OneToOne
	@JoinColumn(name = "accepter_id")
	private User accepter;
	@Column(name="create_date")
	private String createDate;
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE },mappedBy ="userRelation")
	//更改type
	private Set<Chat> chats;
	@Column(name="is_agree")
	private Integer isAgree;//0是未处理 1是同意 2是拒绝 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getAccepter() {
		return accepter;
	}
	public void setAccepter(User accepter) {
		this.accepter = accepter;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Set<Chat> getChats() {
		return chats;
	}
	public void setChats(Set<Chat> chats) {
		this.chats = chats;
	}
	public Integer getIsAgree() {
		return isAgree;
	}
	public void setIsAgree(Integer isAgree) {
		this.isAgree = isAgree;
	}
	
}
