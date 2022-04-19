package service.impl;

import entity.AddDO;
import entity.DeleteDO;
import service.AddService;
import service.DeleteService;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 18:26
 */

public class AddServiceImpl implements AddService {
    @Override
    public boolean validateAdd(AddDO addDO) {


        StringBuilder sql = new StringBuilder();
        sql.append("insert into students(username, changeMoney, classify, modifyDate, note) values(?, ?, ?, ?, ?)");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, addDO.getUser());
            ps.setString(2, addDO.getMoney());
            ps.setString(3, addDO.getType());
            ps.setString(4, addDO.getDate());
            ps.setString(5, addDO.getNote());
            return ps.executeUpdate() == 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }

        return false;
    }
}
