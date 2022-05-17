package cn.twopair.test;

import cn.twopair.entity.BookInfo;
import cn.twopair.dao.BookInfoDAO;

import java.util.List;

public class TestQueryBybookname {
    public static void main(String[] args) {
        BookInfoDAO bookInfoDAO = new BookInfoDAO();
        List<BookInfo> books = bookInfoDAO.queryByBookName("java高级程序设计");
        for (BookInfo bookInfo : books) {
            System.out.println(bookInfo.getId() + "\t" + bookInfo.getBookNo() + "\t" + bookInfo.getBookName() + "\t" + bookInfo.getAuthor());
        }
    }
}
