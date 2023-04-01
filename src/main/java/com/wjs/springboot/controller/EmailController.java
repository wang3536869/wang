package com.wjs.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjs.springboot.config.EmailService;
import com.wjs.springboot.config.Result;
import com.wjs.springboot.config.ValidCodeUtils;
import com.wjs.springboot.entity.User;
import com.wjs.springboot.mapper.UserMapper;
import com.wjs.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Wjs
 */
@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
  private EmailService emailService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @PostMapping("/send")
    public Result<?>sendMsg(@RequestBody User user,HttpSession session){
        String emailTo=user.getEmail();
        if (emailTo==null){
            return Result.error("0","邮箱号为空!");
        }
        String code=String.valueOf(ValidCodeUtils.generateValidCode(4));
        emailService.sendSimpleMail("1579257489@qq.com",emailTo,"<h1>欢迎登录</h1>",code);
        stringRedisTemplate.opsForValue().set("code",code,1, TimeUnit.MINUTES);
//        stringRedisTemplate.opsForValue().set("email",emailTo);
        session.setAttribute("email",emailTo);
        System.out.println("验证码为"+stringRedisTemplate.opsForValue().get("code"));
        System.out.println("邮箱为"+session.getAttribute("email"));
        return Result.success("验证码发送成功!");
    }
    @PostMapping("/login")
    public Result<?> emailLogin(@RequestBody Map<String ,String>map,HttpSession session){
        String email=map.get("email");
        String code=map.get("code");
        String codeInRedis=stringRedisTemplate.opsForValue().get("code");
//        String emailInRedis=stringRedisTemplate.opsForValue().get("email");
        String emailInSession= (String) session.getAttribute("email");
        System.out.println("验证码为"+codeInRedis);
        System.out.println("邮箱为"+session.getAttribute("email"));
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail,email));
        System.out.println(res);
        if (codeInRedis==null||emailInSession==null||!codeInRedis.equals(code)){
            return Result.error("-1","验证码错误");
        }else if(res==null){
            return Result.error("-1","邮箱未注册或邮箱输入出错");
        }else {
            stringRedisTemplate.opsForValue().set("user",String.valueOf(res.getId()));
            return Result.success(res);
        }
    }
}
