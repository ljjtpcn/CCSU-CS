package cn.twopair.test;

import cn.twopair.entity.BookInfo;
import cn.twopair.dao.BookInfoDAO;

public class TestUpdateBook {
    public static void main(String[] args) {
        BookInfoDAO bdao = new BookInfoDAO();
        BookInfo book = new BookInfo("0203", "操作系统", "戴源泉");
        java.util.List<BookInfo> books;
        books = bdao.queryByBookName(book.getBookName());
        for (BookInfo bookInfo : books) {
            System.out.println(bookInfo.getId() + "\t" + bookInfo.getBookNo() + "\t" + bookInfo.getBookName() + "\t" + bookInfo.getAuthor());
        }
        book.setId(books.get(0).getId());
        book.setBookName("操作系统原理");
        book.setAuthor("王欣");
        book.setPrice(12.1);
        book.setPublisher("华中科技大学出版社");
        book.setISBN("98745372123");
        book.setPublishTime("10170801");
        if (bdao.update(book)) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }
}
