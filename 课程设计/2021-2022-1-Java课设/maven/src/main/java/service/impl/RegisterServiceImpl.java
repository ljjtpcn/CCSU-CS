package service.impl;

import entity.RegisterDO;
import service.RegisterService;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/21 17:26
 */

public class RegisterServiceImpl implements RegisterService {
    @Override
    public int validateRegister(RegisterDO registerDO) {
        String username = registerDO.getUsername();
        String password = registerDO.getPassword();
        String sex = registerDO.getSex();

        StringBuilder sql = new StringBuilder();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //检查用户名是否重复
        sql.append("select username from manager where username = ?");
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()){
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRes(rs);
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }

        sql.setLength(0);
        sql.append("INSERT INTO manager(username, pwd, sex) VALUES('").append(username).append("', '")
                .append(password).append("', '").append(sex).append("')");
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.executeUpdate();

            //校验是否创建成功,成功返回true
            sql.setLength(0);
            sql.append("select id from manager where username = '").append(username).append("'");
            ps = conn.prepareStatement(sql.toString());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRes(rs);
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }

        return 2;
    }
}
