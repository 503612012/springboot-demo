package com.oven.dao;

import com.oven.vo.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return super.getJdbcTemplate().query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                return book;
            }
        });
    }

    public void save(Book book) {
        String sql = "insert into t_book (id, book_name, author) values (null, ?, ?)";
        super.getJdbcTemplate().update(sql, book.getBookName(), book.getAuthor());
    }

}
