package cn.twopair.dao;

import cn.twopair.entity.BookInfo;
import cn.twopair.res.TableDTO;
import cn.twopair.utils.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class BookInfoDAO {
    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement ps;

    public static boolean addBook(BookInfo book) {
        conn = DBManager.getConn();
        String sqlString = "INSERT INTO bookinfo (bookNo,bookname,author,publisher,price,publishtime,ISBN,amount) VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sqlString);
            ps.setString(1, book.getBookNo());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getPublisher());
            ps.setDouble(5, book.getPrice());
            ps.setString(6, book.getPublishTime());
            ps.setString(7, book.getISBN());
            ps.setInt(8, book.getAmount());
            return ps.executeUpdate() != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<BookInfo> queryByBookName(String bname) {
        List<BookInfo> books = new ArrayList<>();
        conn = DBManager.getConn();
        String sqlString = "SELECT * FROM bookinfo WHERE bookName LIKE ?";
        try {
            ps = conn.prepareStatement(sqlString);
            ps.setString(1, bname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt("ID");
                String bookNo = rs.getString("bookNo");
                String bookname = rs.getString("bookName");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String publishtime = rs.getString("publishTime");
                double price = rs.getFloat("price");
                String ISBN = rs.getString("ISBN");
                int amount = rs.getInt("amount");
                BookInfo book = new BookInfo(bookNo, bookname, author);
                book.setId(bookId);
                book.setPublisher(publisher);
                book.setPublishTime(publishtime);
                book.setISBN(ISBN);
                book.setPrice(price);
                book.setAmount(amount);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static List<BookInfo> getList() {
        List<BookInfo> books = new ArrayList<>();
        conn = DBManager.getConn();
        String sql = "SELECT * FROM bookinfo";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BookInfo book = new BookInfo();
                book.setId(rs.getInt(1));
                book.setBookNo(rs.getString(2));
                book.setBookName(rs.getString(3));
                book.setAuthor(rs.getString(4));
                book.setPublisher(rs.getString(5));
                book.setPrice(rs.getDouble(6));
                book.setPublishTime(rs.getString(7));
                book.setISBN(rs.getString(8));
                book.setAmount(rs.getInt(9));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static boolean delBookById(int id) {
        conn = DBManager.getConn();
        String sql = "DELETE FROM bookinfo WHERE ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(BookInfo book) {
        conn = DBManager.getConn();
        String sql = "UPDATE bookinfo SET bookNo = ?,bookName = ?,author = ?,publisher = ?,price = ?,publishTime = ?,ISBN = ?,amount = ? WHERE ID=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getBookNo());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getPublisher());
            ps.setDouble(5, book.getPrice());
            ps.setString(6, book.getPublishTime());
            ps.setString(7, book.getISBN());
            ps.setInt(8, book.getAmount());
            ps.setInt(9, book.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static TableDTO selectByKeyword(String keyword) {
        TableDTO returnDTO = new TableDTO();
        Vector<Vector<Object>> data = new Vector<>();
        conn = DBManager.getConn();
        String sqlString = "SELECT * FROM bookinfo WHERE bookName LIKE ?";
        try {
            ps = conn.prepareStatement(sqlString);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String bookNo = rs.getString("bookNo");
                String bookName = rs.getString("bookName");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                Double price = rs.getDouble("price");
                String publishTime = rs.getString("publishTime");
                String ISBN = rs.getString("ISBN");
                Integer amount = rs.getInt("amount");

                Vector<Object> rows = new Vector<>();
                rows.addElement(id);
                rows.addElement(bookNo);
                rows.addElement(bookName);
                rows.addElement(author);
                rows.addElement(publisher);
                rows.addElement(price);
                rows.addElement(publishTime);
                rows.addElement(ISBN);
                rows.addElement(amount);

                data.add(rows);
            }
            returnDTO.setData(data);
            returnDTO.setTotalCount(data.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnDTO;
    }
}
