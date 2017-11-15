package com.zzl.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzl.bean.Reward;
import com.zzl.bean.User;
import com.zzl.bean.common.AlibabaPay;
import com.zzl.bean.common.Common;
import com.zzl.service.RewardService;
import com.zzl.service.UserService;
import com.zzl.service.impl.RewardServiceImpl;

@RestController
@RequestMapping("pay")
public class AlibabaPayController extends BaseController {
	@Autowired
	RewardService rewardService;
	@Autowired
	UserService userSerivce;
	
	String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu0SQCSsRbFGYoVDyTaiRQaC8Nl6qJ0d1u6iXZgEUgWajFjVIWKfRBzmGAzyIVpC2kijsSGHUbphZgic5ew1dXiKV78sklO4mw5hoq60X2jGUKhSFQz9WKoggNmw90c4X18rOfRJKQiHnOeH/3HtTq6gEMZnmXPOWSE7uIq0cELTxi1EvOImcSqgVQnX2eGL0LJpuSSbObvoaSCWtIgcMI+2sQxh0OGjLlY0RMzoTHO4mNkaFwBFLRdVCzT3VjeLsohSFS5TmV5s5u7jL0CYhwayHbnL/Yyty8g182tgbU5YmAbHTHLqPxjWBoI9caIcgNGnpqoNqTV8BbF3nCD38pwIDAQAB";
	String APP_PRIVATE_KEY="MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCqpz67mRCNVn7PmxDDaYdqfS9V1JaPJZRkZYOTVRFN2FBg7bW7nPwD+A3s7Po3QCLtejRNuebd4TlSmL3xt1ZE4OvWVxdwSWNCj/Z12auE8DBSZqpj6lhhysB3lV2t2I9pRi0gsE2NiZJth2UPeDGirJh75pcXX3Z1RTT+X+I/Tsmtxmmtx/u5gB/OirFkY6sbB4qFLDI2BGN4JljpKq76CYxvxeqsuAWYHojzpCbcuM51FRY1dFxHEUzk9iuApGq4hdvGwb0B8Pm8ThyFGUi3QYRP75HX2ELljjK72kS/J2nhme414i1zCxnW18QNq16Tu+HAOXEmRJk5DmraHbv1AgMBAAECggEBAJ3XjU2lGMsx6p8JVSr5i7fyS/r+Y7fJUYS+5EE82IGBB29vzrzWSb8+C1tOIW1DFEtE0eXmgeRN8mA0SNOu+MtCosAg09wW9QlW8OY2S8/s/TTqIsXBtrfU+cphzEEE4jjEIO9wQnsceOR37ACv8nD9ZRiMwslqkb2MIqT5eOGF8pSnp1iKNOg70vgbZJrl3fyEGxg5m/yQW35IIN3R/CMkcWPO8uBBjjOOgXyyNaEObdgAsj0h/nJ0lYHJpMUcD90fUV2z793/oIvN5c5nZgqTSkmdROKzFfZ6JGvBY13FF4XxvvRYQY7Mq6k7Y9z2kzh7fJpeg+oCY/3lqbdHTI0CgYEA03TfKUo88NZGrMtLxfQGNdmDAbkbzzdocGlI7anDBQqrgn1Xeur3Js5VMuRJEH7vr3LmKQPWAD0QK5JAgyTTdJ42BJRgEAYHnvnG7zWtf2L5zC+3ZYBWnvykMosNYLavQPHb4Qxt/bMs4D586845788Z1RkpdSGg6HNyW/GbNDsCgYEAzpn+nDjqaOhdf3rfJomlySLhx+dQR3d+Yd+FEXQS1PGmUCctnqAfNyi0eSMo7CDttAcih8LFMagwm6OfOcYzSFumGSJWEEodiNijl7NlmgvECPjBUQd6+02ZjpPYMzfW6UZ3JvHFAuGuvKjVbqcYrSUjsr+4r0itwBE4PUrUvY8CgYEAgn/4UcUtQv0a9P0qMGrfglBQ17lj/kiVvfpcNcC1SOFC4+9zE7r6+QoR7qyQowuT5NWmUd26Lr+AwUMraURK9AfIEp93gawW+/fu3CZmgt2UiH1SU+QWkVweActrYCqekhgxuPwwzYfMxEAMXIMTonpP2QIEYfN1GMBW7YN4Y40CgYEAjhY6lRZPoHauVs4aSlbAPeRPgzwCaiQ0NvuSeL5rdSf8SYOoTD+zOCw2cllL+5lp4TZG+Tdqhww04dVV3FYDTvA4+3Ax4GIYjdTwLZSje5475C+wRBnw6oQj53qmZhMxKp3btFzyMVkk43r5zm/8+JdiRby1N1e0pzMwina8eOECgYEAl/PkZ3YgK0AnGYI175XqemG7+XIpLuhA8RLZoICB7Gcc7fAoH3lHql/TM30FvRA7RKFIhH6F71G6WFOlXuhL9RyNyg4WgJrt1gExwNxRgIg8c3k/B7aBvKG/2Ux3PFqS8OnyaGjV0dpIWppNM7uIpC8yo1Hya4kbwFwGKzz9XMQ=";
	String APP_ID="2016082100304500";
	String URL="https://openapi.alipaydev.com/gateway.do";
	String CODEURL="http://abdexf.natappfree.cc";
	public String replacePay(AlibabaPay pay) {
		JSONObject json =new JSONObject();
		json.put("out_trade_no",pay.getOut_trade_no());
		json.put("product_code", pay.getProduct_code());
		json.put("subject", pay.getSubject());
		json.put("total_amount", pay.getTotal_amount()+"");
		return json.toString();
	}
	@Transactional
	@RequestMapping(value="alipay.do")
	public String payalibaba(
			HttpServletRequest request,
			@RequestParam(name="total" ,required=true)Double total,
			HttpServletResponse response
			) throws IOException, AlipayApiException{
		AlipayClient apAlipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY,"json","utf-8",ALIPAY_PUBLIC_KEY,"RSA2");
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(CODEURL+"/pay/callback.do");
		alipayRequest.setNotifyUrl(CODEURL+"/pay/callback.do");
		String orderNo = new StringBuffer("DD").append(new SimpleDateFormat("yyyymmddhhmmss").format(new Date())).toString();
		AlibabaPay pay = new AlibabaPay(orderNo, Common.PRODUCTCODE, total, "打赏给小二的钱:"+total);
		Reward  reward = new Reward();
		reward.setOrderNo(orderNo);
		reward.setPayType(0);
		reward.setCreateDate(new SimpleDateFormat(Common.DATEFORMAT).format(new Date()));
		User user = (User) request.getSession().getAttribute("User");
		reward.setTotal(total);
		if(user!=null){
			reward.setUser(user);
			userSerivce.updateintegral(total.intValue(), request);
		}
		rewardService.saveReward(reward);
		String  result =replacePay(pay);
		alipayRequest.setBizContent(result);
		result = apAlipayClient.pageExecute(alipayRequest).getBody();
		return result;
	};

	@RequestMapping("callback.do")
	public ModelAndView PayCallBack(HttpServletRequest request,
			@RequestParam("total_amount")String total_amount){
		Map  map =  request.getParameterMap();
		Iterator iter =map.entrySet().iterator();
		System.out.println("进入");
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		System.out.println(map.get("total_amount")+"totalamount");
		request.setAttribute("total", total_amount);
		ModelAndView view = new ModelAndView("forward:index.html",null);
		return view;
	}
}
