package com.wjs.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjs.springboot.config.Result;
import com.wjs.springboot.entity.Book;
import com.wjs.springboot.entity.User;
import com.wjs.springboot.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Wjs
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;
    @PostMapping
    public Result<?> save(@RequestBody Book book) {
        bookService.save(book);
        return Result.success();
    }

    @GetMapping
    public Result<?> findAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
        IPage<Book> books=bookService.findAll(pageNum, pageSize, search);
        return Result.success(books);
    }

    @PutMapping
    public Result<?> update(@RequestBody Book book) {
        bookService.update(book);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        bookService.delete(id);
        return Result.success();
    }
}
