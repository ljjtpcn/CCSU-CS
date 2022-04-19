package service.impl;

import entity.AdminDO;
import service.AdminService;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-12 15:32
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
public class AdminServiceImpl implements AdminService {

    @Override
    public boolean validateAdmin(AdminDO adminDO) {
        String username = adminDO.getUsername();
        String pwd = adminDO.getPwd();


        StringBuilder sql = new StringBuilder();
        sql.append("select pwd from manager where username = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            conn = DBUtil.getConnection();
            if(conn == null){
                return false;
            }
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return pwd.equals(resultSet.getString("pwd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeRes(resultSet);
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }

        return false;
    }
}
