package com.oven.controller;

import com.oven.entity.Book;
import com.oven.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 书籍控制层
 */
@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping("/findAll")
    public Object findAll() {
        return bookService.findAll();
    }

    @RequestMapping("/save")
    public Object save(Book book) {
        bookService.save(book);
        return "success";
    }

}
