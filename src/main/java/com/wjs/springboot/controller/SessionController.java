package com.wjs.springboot.controller;

import com.wjs.springboot.config.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Wjs
 */
@RestController
public class SessionController {
//    @RequestMapping(value = "/send",method = RequestMethod.POST)
    @PostMapping("/send")
    public Result<?> send(HttpSession session){
        String name="你好";
//        request.getSession().setAttribute("name",name);
        session.setAttribute("name",name);
        System.out.println(session.getAttribute("name"));
        return Result.success("成功"+session.getAttribute("name"));
    }
//    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @PostMapping("get")
    public Result<?>get(HttpSession session){
        System.out.println(session.getAttribute("name"));
        return Result.success("成功"+session.getAttribute("name"));
    }
}
