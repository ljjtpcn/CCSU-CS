package cn.twopair.test;


import cn.twopair.entity.BookInfo;
import cn.twopair.dao.BookInfoDAO;

import java.util.List;

public class TestDeleteBook {
    public static void main(String[] args) {
        BookInfo book = new BookInfo();
        book.setBookName("操作系统");
        System.out.println("--------------");
        BookInfoDAO bookInfoDAO = new BookInfoDAO();
        List<BookInfo> books = bookInfoDAO.queryByBookName(book.getBookName());
        if (books.size() >= 1) {
            int bookid = books.get(0).getId();
            if (!bookInfoDAO.delBookById(bookid)) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败");
            }
        }
    }
}