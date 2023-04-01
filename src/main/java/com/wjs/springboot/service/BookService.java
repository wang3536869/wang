package com.wjs.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjs.springboot.entity.Book;
import com.wjs.springboot.entity.User;

/**
 * @author Wjs
 */
public interface BookService {
    /**
     * 增加方法
     * @param book;
     * @return true
     * */
    boolean save(Book book);
    /**
     * 删除方法
     * @param id ;
     * @return true
     * */
    boolean delete(Long id);
    /**
     * 更行方法
     * @param book;
     * @return true
     * */
    boolean update(Book book);
    /**
     * 查询方法
     * @param pageNum;
     * @param pageSize ;
     * @param search;
     * @return true
     * */
    IPage<Book> findAll(Integer pageNum, Integer pageSize, String search);
}
