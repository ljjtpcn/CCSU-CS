package service.impl;

import req.GraphicaRequest;
import res.GraphicalDTO;
import service.GraphicalService;
import util.DBUtil;

import java.sql.*;
import java.util.Vector;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/24 11:26
 */

public class GraphicalServiceimpl implements GraphicalService {
    @Override
    public GraphicalDTO retrieveGraphical(GraphicaRequest request) {
        String username = request.getUsername();

        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT SUM(CASE WHEN MONTH(modifyDate) =1 THEN changeMoney ELSE 0 END) `one`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =2 THEN changeMoney ELSE 0 END) `two`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =3 THEN changeMoney ELSE 0 END) `three`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =4 THEN changeMoney ELSE 0 END) `four`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =5 THEN changeMoney ELSE 0 END) `five`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =6 THEN changeMoney ELSE 0 END) `six`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =7 THEN changeMoney ELSE 0 END) `seven`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =8 THEN changeMoney ELSE 0 END) `eight`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =9 THEN changeMoney ELSE 0 END) `nine`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =10 THEN changeMoney ELSE 0 END) `ten`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =11 THEN changeMoney ELSE 0 END) `eleven`, " +
                        "SUM(CASE WHEN MONTH(modifyDate) =12 THEN changeMoney ELSE 0 END) `twelve`, " +
                        "YEAR(modifyDate) AS yyyy " +
                        "FROM students " +
                        "WHERE changeMoney > 0 AND username = ? " +
                        "GROUP BY YEAR(modifyDate)");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        GraphicalDTO returnDTO = new GraphicalDTO();
        Vector<Vector<String>> dataIncome = new Vector<>();
        Vector<Vector<String>> dataExpend = new Vector<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                String year = String.valueOf(rs.getObject("yyyy"));
                String one = String.valueOf(rs.getDouble("one"));
                String two = String.valueOf(rs.getDouble("two"));
                String three = String.valueOf(rs.getDouble("three"));
                String four = String.valueOf(rs.getDouble("four"));
                String five = String.valueOf(rs.getDouble("five"));
                String six = String.valueOf(rs.getDouble("six"));
                String seven = String.valueOf(rs.getDouble("seven"));
                String eight = String.valueOf(rs.getDouble("eight"));
                String nine = String.valueOf(rs.getDouble("nine"));
                String ten = String.valueOf(rs.getDouble("ten"));
                String eleven = String.valueOf(rs.getDouble("eleven"));
                String twelve = String.valueOf(rs.getDouble("twelve"));
                row.add(year);
                row.add(one);
                row.add(two);
                row.add(three);
                row.add(four);
                row.add(five);
                row.add(six);
                row.add(seven);
                row.add(eight);
                row.add(nine);
                row.add(ten);
                row.add(eleven);
                row.add(twelve);
                dataIncome.add(row);
            }
            returnDTO.setDataIncome(dataIncome);

            sql.setLength(0);
            sql.append(
                    "SELECT SUM(CASE WHEN MONTH(modifyDate) =1 THEN changeMoney ELSE 0 END) `one`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =2 THEN changeMoney ELSE 0 END) `two`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =3 THEN changeMoney ELSE 0 END) `three`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =4 THEN changeMoney ELSE 0 END) `four`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =5 THEN changeMoney ELSE 0 END) `five`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =6 THEN changeMoney ELSE 0 END) `six`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =7 THEN changeMoney ELSE 0 END) `seven`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =8 THEN changeMoney ELSE 0 END) `eight`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =9 THEN changeMoney ELSE 0 END) `nine`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =10 THEN changeMoney ELSE 0 END) `ten`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =11 THEN changeMoney ELSE 0 END) `eleven`, " +
                            "SUM(CASE WHEN MONTH(modifyDate) =12 THEN changeMoney ELSE 0 END) `twelve`, " +
                            "YEAR(modifyDate) AS yyyy " +
                            "FROM students " +
                            "WHERE changeMoney < 0 AND username = ? " +
                            "GROUP BY YEAR(modifyDate)");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                String year = String.valueOf(rs.getObject("yyyy"));
                String one = String.valueOf(rs.getDouble("one"));
                String two = String.valueOf(rs.getDouble("two"));
                String three = String.valueOf(rs.getDouble("three"));
                String four = String.valueOf(rs.getDouble("four"));
                String five = String.valueOf(rs.getDouble("five"));
                String six = String.valueOf(rs.getDouble("six"));
                String seven = String.valueOf(rs.getDouble("seven"));
                String eight = String.valueOf(rs.getDouble("eight"));
                String nine = String.valueOf(rs.getDouble("nine"));
                String ten = String.valueOf(rs.getDouble("ten"));
                String eleven = String.valueOf(rs.getDouble("eleven"));
                String twelve = String.valueOf(rs.getDouble("twelve"));
                row.add(year);
                row.add(one);
                row.add(two);
                row.add(three);
                row.add(four);
                row.add(five);
                row.add(six);
                row.add(seven);
                row.add(eight);
                row.add(nine);
                row.add(ten);
                row.add(eleven);
                row.add(twelve);
                dataExpend.add(row);
            }
            returnDTO.setDataExpend(dataExpend);

            return returnDTO;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeRes(rs);
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }


        return null;
    }
}
