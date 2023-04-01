package com.wjs.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjs.springboot.entity.User;

/**
 * @author Wjs
 */
public interface UserService {
    /**
     * 增加方法
     * @param user;
     * @return true
    * */
    boolean save(User user);
    /**
     * 删除方法
     * @param id ;
     * @return true
    * */
    boolean delete(Long id);
    /**
     * 更行方法
     * @param user;
     * @return true
    * */
    boolean update(User user);
    /**
     * 查询方法
     * @param pageNum;
     * @param pageSize ;
     * @param search;
     * @return true
     * */
    IPage<User>findAll(Integer pageNum,Integer pageSize,String search);
    /**
     * 登陆方法
     * @param user ;
     * @return true;
    * */
    User login(User user);
    /**
     * 注册方法
     * @param user ;
     * @return true
     * */
    User register(User user);
    /*

    * */
    User getById(Long id);
    /*
    * */
}
