package cn.twopair.utils;

import java.sql.*;

/**
 * @ClassName DBManager
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/16 上午10:17
 * @Version 1.0
 **/
public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/books";
    private static final String USERNAME = "root";
    private static final String PWD = "rootroot";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConn() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePre(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRes(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkUpdateTrue(StringBuilder sql) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBManager.getConn();
            ps = conn.prepareStatement(sql.toString());
            int cnt = ps.executeUpdate();
            return cnt > 0;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBManager.closePre(ps);
            DBManager.closeConnection(conn);
        }

        return false;
    }

}
