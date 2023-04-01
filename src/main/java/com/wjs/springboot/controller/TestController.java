package com.wjs.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjs.springboot.config.Result;
import com.wjs.springboot.entity.User;
import com.wjs.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Wjs
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private UserService userService;
   @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<?> login(@RequestBody User user, HttpSession session) {
        User res = userService.login(user);
//        session.setAttribute("user", res.getUsername());
        return Result.success(res);
    }
    @GetMapping("/findAll")
    public Result<?> findAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search, HttpSession session) {
        Object flag = session.getAttribute("user");
            IPage<User> users = userService.findAll(pageNum, pageSize, search);
            return Result.success(users);
    }
}
