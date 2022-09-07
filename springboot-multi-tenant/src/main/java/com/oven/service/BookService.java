package com.oven.service;

import com.oven.dao.BookDao;
import com.oven.vo.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍服务层
 *
 * @author Oven
 */
@Service
public class BookService {

    @Resource
    private BookDao bookDao;

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public void save(Book book) {
        bookDao.save(book);
    }

}
