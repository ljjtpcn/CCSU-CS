package cn.twopair.test;

import cn.twopair.entity.BookInfo;
import cn.twopair.dao.BookInfoDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class TestShowOnTextArea {
    JFrame jf;
    JPanel jpanel;
    JTextArea jta = null;
    JScrollPane jscrollpane;
    BookInfoDAO bdao = new BookInfoDAO();
    Connection conn;
    private String m_TableName;

    public TestShowOnTextArea() {
        jf = new JFrame("显示结果");
        Container contentPane = jf.getContentPane();
        jta = new JTextArea(10, 15);
        jta.setEditable(false);
        jscrollpane = new JScrollPane(jta);
        contentPane.add(jscrollpane);
        jta.append("序号\t图书编号\t书名\t作者\t出版社\t单价\t出版日期\tISBN\t库存数量\n");
        List<BookInfo> books = bdao.getList();
        for (BookInfo book : books) {
            jta.append(Integer.toString(book.getId()));
            jta.append("\t" + book.getBookNo());
            jta.append("\t" + book.getBookName());
            jta.append("\t" + book.getAuthor());
            jta.append("\t" + book.getPublisher());
            jta.append("\t" + book.getPrice());
            jta.append("\t" + book.getPublishTime());
            jta.append("\t" + book.getISBN());
            jta.append("\t" + book.getAmount());
            jta.append("\n");
        }
        jf.setSize(500, 400);
        jf.setLocation(400, 200);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TestShowOnTextArea();
    }
}