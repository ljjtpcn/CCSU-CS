package service.impl;

import entity.DeleteDO;
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

public class DeleteServiceImpl implements DeleteService {
    @Override
    public boolean validateDelete(DeleteDO deleteDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from students where id = ?");
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, deleteDo.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }

        return false;
    }
}
