package util;

import org.junit.Test;

import java.sql.*;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-12 14:15
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
public  class DBUtil  {

    private static final String URL = "jdbc:mysql://118.31.7.116:3306/b20190103224";
    private static final String USERNAME = "ljj";
    private static final String PWD = "rootroot";
    private static final String DRIVER = "com.mysql.jdbc.Driver";


     static {
         try {
             Class.forName(DRIVER);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
     }


     public static Connection getConnection(){
         try {
             return DriverManager.getConnection(URL, USERNAME, PWD);
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return null;
     }

    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePre(PreparedStatement ps){
         if(ps != null){
             try {
                 ps.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
    }

    public static void closeRes(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkUpdateTrue(StringBuffer sql) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            int cnt = ps.executeUpdate();
            return cnt > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }

        return false;
    }

}
