package cn.twopair.entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class BookInfo implements Serializable {
    Integer id;
    String bookNo;
    String bookName;
    String author;
    String publisher;
    Double price;
    String publishTime;
    String ISBN;
    Integer amount;

    public BookInfo() {
    }

    public BookInfo(String bookNo, String bookName, String author) {
        this.bookNo = bookNo;
        this.bookName = bookName;
        this.author = author;
    }
}