package cn.twopair.test;

import cn.twopair.entity.BookInfo;
import cn.twopair.dao.BookInfoDAO;

public class TestAddBook {
    public static void main(String[] args) {
        BookInfoDAO bookInfoDAO = new BookInfoDAO();
        BookInfo book = new BookInfo(null, null, null);
        book.setBookNo("0203");
        book.setBookName("操作系统");
        book.setAuthor("戴源泉");
        book.setPublisher("清华大学出版社");
        book.setPublishTime("20170809");
        book.setAmount(4);
        book.setPrice(45.05);
        book.setISBN("98745372123");
        if (bookInfoDAO.addBook(book)) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！");
        }
    }
}