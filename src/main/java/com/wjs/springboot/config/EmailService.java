package com.wjs.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Wjs
 */
@Component
public class EmailService {
    @Resource
    JavaMailSender javaMailSender;
    public void sendSimpleMail(String from,String to,String subject,String content){
        SimpleMailMessage simpMsg=new SimpleMailMessage();
        simpMsg.setFrom(from);
        simpMsg.setTo(to);
        simpMsg.setSubject(subject);
        simpMsg.setText(content);
        javaMailSender.send(simpMsg);
    }
}
