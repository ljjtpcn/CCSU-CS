package cn.twopair.service.impl;


import cn.twopair.entity.BookInfo;
import cn.twopair.req.BookRequest;
import cn.twopair.res.TableDTO;
import cn.twopair.service.BookService;
import cn.twopair.utils.DBManager;

import java.sql.*;
import java.util.Vector;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-12 15:33
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
public class BookServiceImpl implements BookService {
    @Override
    public TableDTO selectAll(BookRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from bookinfo");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();

        try {
            conn = DBManager.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            returnDTO.setData(getVectors(rs));
            returnDTO.setTotalCount(returnDTO.getData().size());

            return returnDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeRes(rs);
            DBManager.closePre(ps);
            DBManager.closeConnection(conn);
        }
        return null;
    }

    @Override
    public BookInfo getBySelectedId(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from bookinfo where ID = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BookInfo returnBookInfo = new BookInfo();

        try {
            conn = DBManager.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                returnBookInfo.setId(rs.getInt("ID"));
                returnBookInfo.setBookNo(rs.getString("bookNo"));
                returnBookInfo.setBookName(rs.getString("bookName"));
                returnBookInfo.setAuthor(rs.getString("author"));
                returnBookInfo.setPublisher(rs.getString("publisher"));
                returnBookInfo.setPrice(rs.getDouble("price"));
                returnBookInfo.setPublishTime(rs.getString("publishTime"));
                returnBookInfo.setISBN(rs.getString("ISBN"));
                returnBookInfo.setAmount(rs.getInt("amount"));
            }
            return returnBookInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeRes(rs);
            DBManager.closePre(ps);
            DBManager.closeConnection(conn);
        }
        return null;
    }


    private Vector<Vector<Object>> getVectors(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
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
        return data;
    }

}


