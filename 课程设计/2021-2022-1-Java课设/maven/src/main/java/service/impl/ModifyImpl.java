package service.impl;

import entity.ModifyIncomeDO;
import service.ModifyService;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 10:09
 */

public class ModifyImpl implements ModifyService {
    @Override
    public boolean validateModifyIncome(ModifyIncomeDO modifyIncomeDO) {


        StringBuilder sql = new StringBuilder();
        sql.append("update students set classify = ?, changeMoney = ?, note = ?, modifyDate = ? where id = ?");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, modifyIncomeDO.getType());
            ps.setString(2, modifyIncomeDO.getMoney());
            ps.setString(3, modifyIncomeDO.getNote());
            ps.setString(4, modifyIncomeDO.getDate());
            ps.setString(5, modifyIncomeDO.getId());

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
