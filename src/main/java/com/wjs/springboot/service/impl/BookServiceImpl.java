package com.wjs.springboot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjs.springboot.entity.Book;
import com.wjs.springboot.entity.User;
import com.wjs.springboot.mapper.BookMapper;
import com.wjs.springboot.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wjs
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    /**
     * 增加方法
     *
     * @param book ;
     * @return true
     */
    @Override
    public boolean save(Book book) {
        Book bookRes=bookMapper.selectOne(Wrappers.<Book>lambdaQuery().eq(Book::getName,book.getName()));
        if (bookRes==null){
            bookMapper.insert(book);
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
        bookMapper.deleteById(id);
        return true;
    }

    /**
     * 更行方法
     *
     * @param book ;
     * @return true
     */
    @Override
    public boolean update(Book book) {
        bookMapper.updateById(book);
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
    public IPage<Book> findAll(Integer pageNum, Integer pageSize, String search) {
        return bookMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Book>lambdaQuery().like(Book::getName,search));

    }
}
