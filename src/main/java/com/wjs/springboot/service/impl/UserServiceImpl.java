package com.wjs.springboot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjs.springboot.entity.User;
import com.wjs.springboot.mapper.UserMapper;
import com.wjs.springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wjs
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean save(User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res == null&&user.getPassword()==null) {
            //设置默认密码
            user.setPassword("123456");
            userMapper.insert(user);
            return true;
        }
        return false;
    }

    /**
     * 删除方法
     *
     * @param id ;
     * @return true
     */
    @Override
    public boolean delete(Long id) {
        userMapper.deleteById(id);
        return true;
    }

    /**
     * 更行方法
     *
     * @param user ;
     * @return true
     */
    @Override
    public boolean update(User user) {
        userMapper.updateById(user);
        return true;
    }

    /**
     * 查询方法
     *
     * @param pageNum  ;
     * @param pageSize ;
     * @param search   ;
     * @return true
     */
    @Override
    public IPage<User> findAll(Integer pageNum, Integer pageSize, String search) {
        return userMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<User>lambdaQuery().like(User::getUsername,search));
    }

    /**
     * 登陆方法
     *
     * @param user ;
     * @return true;
     */
    @Override
    public User login(User user) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));
    }

    @Override
    public User register(User user) {
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if(res==null){
            userMapper.insert(user);
            return user;
        }
        return null;
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }
}
