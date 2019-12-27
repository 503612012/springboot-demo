package com.oven.vo;

import javax.persistence.*;

/**
 * 书籍实体类
 *
 * @author Oven
 */
@Entity
@Table(name = "t_book")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "book_name")
    private String bookName;
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
