package com.zzl.util;

import java.util.Properties;

import org.hibernate.validator.constraints.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.zzl.Application;

@Service 
public class EmailUtils {
    @Autowired
    private   JavaMailSender mailSender;
    public  void sendSimpleMail(String to, String subject, String content){  
        SimpleMailMessage message = new SimpleMailMessage();  
        message.setFrom("948706409@qq.com");  
        message.setTo(to);  
        message.setSubject(subject);  
        message.setText(content);  
        System.out.println(to+subject+content);
        mailSender.send(message);  
    }  
}
