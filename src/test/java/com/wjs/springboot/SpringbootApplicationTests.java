package com.wjs.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import java.util.Random;

@SpringBootTest
class SpringbootApplicationTests {
@Resource
    JavaMailSender javaMailSender;
    @Test
    void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("");
        simpleMailMessage.setText(String.valueOf(new Random().nextInt(9999)));
        simpleMailMessage.setFrom("1579257489@qq.com");
        simpleMailMessage.setTo("459593160@qq.com");
        javaMailSender.send(simpleMailMessage);
    }

}
