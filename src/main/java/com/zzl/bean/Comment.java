package com.zzl.bean;

import javax.persistence.CascadeType;
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
@Table(name="comment")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	@OneToOne
	@JoinColumn(name = "commenter_id")
	private User user;
	@Column(name = "ip")
	private String ip;
	@Column(name = "comment")
	private String comment;
	@Column(name = "create_date")
	private String createDate;
	@ManyToOne(cascade = {CascadeType.REFRESH }, optional = false)  
	@JsonIgnore
	@JoinColumn(name="title_id")
	private Title title;
	@Column(name = "Is_approal")
	private Integer IsApproal;
	@Column(name = "No_approal")
	private Integer NoApproal;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Integer getNoApproal() {
		return NoApproal;
	}
	public void setNoApproal(Integer noApproal) {
		NoApproal = noApproal;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getIsApproal() {
		return IsApproal;
	}
	public void setIsApproal(Integer isApproal) {
		IsApproal = isApproal;
	}
	
}
