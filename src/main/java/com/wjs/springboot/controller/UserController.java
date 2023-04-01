package com.wjs.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjs.springboot.config.Result;
import com.wjs.springboot.entity.User;
import com.wjs.springboot.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Wjs
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping
    public Result<?> save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @GetMapping
    public Result<?> findAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
        IPage<User> users = userService.findAll(pageNum, pageSize, search);
        return Result.success(users);
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> login(User user, HttpSession session) {
        User res = userService.login(user);
        if (res == null) {
            return Result.error("-405", "用户名或密码错误");
        }
        session.setAttribute("user",user.getId());
        session.setMaxInactiveInterval(6*60*60);
        stringRedisTemplate.delete("code");
        return Result.success(res);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res = userService.register(user);
        if (res == null) {
            userService.save(user);
            return Result.success();
        }
        return Result.error("-1", "用户名重复");
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @PostMapping("/loginout")
    public Result<?> loginout() {
        stringRedisTemplate.delete("user");
        return Result.success("success");
    }
}
