package com.zzl.bean;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "title")
public class Title {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	@Column(name= "title")
	private String title ;
	@Column(name= "content")
	private String content;
	@Column(name= "create_date")
	private String createDate;
	@Column(name= "delflag")
	private Integer delflag;
	@Column(name="title_abstract")
	private String TitleAbstract;
	@Column(name="read_person")
	private Integer readPerson;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)  
	@JoinColumn(name="type_id")
	private TitleType type;
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE },mappedBy ="title")
	private Set<Comment> comments;
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public TitleType getType() {
		return type;
	}
	public void setType(TitleType type) {
		this.type = type;
	}
	public Integer getReadPerson() {
		return readPerson;
	}
	public void setReadPerson(Integer readPerson) {
		this.readPerson = readPerson;
	}
	public String getTitleAbstract() {
		return TitleAbstract;
	}
	public void setTitleAbstract(String titleAbstract) {
		TitleAbstract = titleAbstract;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getDelflag() {
		return delflag;
	}
	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
}
