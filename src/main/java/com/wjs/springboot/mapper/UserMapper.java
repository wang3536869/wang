package com.wjs.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjs.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Wjs
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
