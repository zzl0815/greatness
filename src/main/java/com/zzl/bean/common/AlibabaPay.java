package com.zzl.bean.common;

public class AlibabaPay {
	private String out_trade_no; //yes
	private String product_code="FAST_INSTANT_TRADE_PAY";//yes
	private Double total_amount;//yes
	private String subject;//yes
	private String goods_detail;
	private String goods_type;
	private String timeout_express;
	private Integer qr_pay_mode;
	public AlibabaPay(String out_trade_no,String product_code,Double total_amount,String subject){
		this.out_trade_no=out_trade_no;
		this.product_code=product_code;
		this.total_amount=total_amount;
		this.subject = subject;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getGoods_detail() {
		return goods_detail;
	}
	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	public String getTimeout_express() {
		return timeout_express;
	}
	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}
	public Integer getQr_pay_mode() {
		return qr_pay_mode;
	}
	public void setQr_pay_mode(Integer qr_pay_mode) {
		this.qr_pay_mode = qr_pay_mode;
	}

	
}
