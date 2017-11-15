package com.zzl.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="v_user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "identifiescard")
	private String identifiesCard;
	@Column(name = "vipclass",columnDefinition="0")
	private Integer vipClass;
	@Column(name = "integral",columnDefinition="0")
	private Integer integral;
	@Column(name="img",length=100)
	private String img ; 
	@Column(name="nickname",length=20)
	private String nickname;
	@Column(name="create_date",length=30)
	private String createDate;
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdentifiesCard() {
		return identifiesCard;
	}
	public void setIdentifiesCard(String identifiesCard) {
		this.identifiesCard = identifiesCard;
	}
	public Integer getVipClass() {
		return vipClass;
	}
	public void setVipClass(Integer vipClass) {
		this.vipClass = vipClass;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	
}
