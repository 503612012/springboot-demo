package com.oven.vo;

/**
 * 书籍实体类
 *
 * @author Oven
 */
public class Book {

    private Integer id;
    private String bookName;
    private String fromDb;

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

    public String getFromDb() {
        return fromDb;
    }

    public void setFromDb(String fromDb) {
        this.fromDb = fromDb;
    }

}
