package alibaba;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;

@RunWith(SpringJUnit4ClassRunner.class)
public class PayInterface {
		
//		String APP_PRIVATE_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAruaeomU4m0pZNZw4HYwmESz4y/L7/7B3bQ0cx7RQOIZ2JnfbBXTQaPo2BrFfxR/xN99OUBxHOFHDiVVDLeXV/kHTSPCY7j4lCRJlCkvjAIN/UqIPhXaSrh+KlH873w1PEFGXmVpuRHVzTExhHtv7B1aRfYecxk0bYS3jBD5rMABkqtjUP0xpRvkuwj4/QP73/xrwEA7y8R4iwLF9PeXYExpMyfYKtDcc155r9eS84afegZrzNe9CbG9KGPU8JmhNEs3HpnQM2yS7yylWT2zeMgXpv7IxSXI0qvThnodvTyMDrn82+me9Adl31Fzl2Ipp5iG0v99X5AnFlVnb8JU67QIDAQAB";
//		//实例化客户端
//		String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu0SQCSsRbFGYoVDyTaiRQaC8Nl6qJ0d1u6iXZgEUgWajFjVIWKfRBzmGAzyIVpC2kijsSGHUbphZgic5ew1dXiKV78sklO4mw5hoq60X2jGUKhSFQz9WKoggNmw90c4X18rOfRJKQiHnOeH/3HtTq6gEMZnmXPOWSE7uIq0cELTxi1EvOImcSqgVQnX2eGL0LJpuSSbObvoaSCWtIgcMI+2sQxh0OGjLlY0RMzoTHO4mNkaFwBFLRdVCzT3VjeLsohSFS5TmV5s5u7jL0CYhwayHbnL/Yyty8g182tgbU5YmAbHTHLqPxjWBoI9caIcgNGnpqoNqTV8BbF3nCD38pwIDAQAB";
//		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2016082100304500", APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");
//		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify 
//		AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
//		//SDK已经封装掉了公共参数，这里只需要传入业务参数
//		//此次只是参数展示，未进行字符串转义，实际情况下请转义
//		request.setBizContent("  {" +
//		"    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
//		"    \"primary_industry_code\":\"10001/20102\"," +
//		"    \"secondary_industry_code\":\"10001/20102\"," +
//		"    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
//		" }");
//		AlipayOpenPublicTemplateMessageIndustryModifyResponse response = alipayClient.execute(request); 
//		//调用成功，则处理业务逻辑
//		if(response.isSuccess()){
//		    //.....
//		}
//	}
}
