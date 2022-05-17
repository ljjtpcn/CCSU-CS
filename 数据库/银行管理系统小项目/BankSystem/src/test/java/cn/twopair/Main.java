package cn.twopair;

import cn.twopair.dao.DaoUtil;
import cn.twopair.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SqlSession sqlSqlSession = DaoUtil.getSqlSqlSession();

        List<User> users = sqlSqlSession.selectList("USER.selectAll");
        System.out.println(users);

        DaoUtil.close(sqlSqlSession);
    }

}