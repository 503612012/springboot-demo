package com.oven.dao;

import com.oven.vo.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 书籍dao层
 *
 * @author Oven
 */
@Repository
public class BookDao extends BaseDao {

    public List<Book> findAll() {
        String sql = "select * from t_book";
        return super.getJdbcTemplate().query(sql, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setBookName(rs.getString("book_name"));
            book.setFromDb(rs.getString("from_db"));
            return book;
        });
    }

    public void save(Book book) {
        String sql = "insert into t_book (id, book_name, from_db) values (null, ?, ?)";
        super.getJdbcTemplate().update(sql, book.getBookName(), book.getFromDb());
    }

}
