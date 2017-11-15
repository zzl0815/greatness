package com.zzl.bean;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reward")
public class Reward {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id ;
	@Column(name="order_no")
	private String OrderNo;
	@Column(name="total")
	private Double total;
	@OneToOne(optional=true)
	@JoinColumn(name="user_id")  //可以为空 匿名捐赠
	private User user ;
	@Column(name="pay_type")
	private Integer payType;//0支付宝 1微信
	@Column(name="create_date")
	private String createDate;
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
