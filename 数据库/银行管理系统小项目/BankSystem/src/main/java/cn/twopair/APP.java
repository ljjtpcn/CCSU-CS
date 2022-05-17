package cn.twopair;

import cn.twopair.dao.DaoUtil;
import cn.twopair.mappers.UserMapper;
import cn.twopair.view.window.RegisterView;
import org.apache.ibatis.session.SqlSession;

public class APP {
    public static void main(String[] args) {
        SqlSession sqlSqlSession = DaoUtil.getSqlSqlSession();

//        List<User> users = sqlSqlSession.selectList("USER.selectAll");
//        System.out.println(users);

        // 代理开发
        UserMapper userMapper = sqlSqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.selectAll());

        DaoUtil.close(sqlSqlSession);
        //new LoginView("test");
        new RegisterView();


    }


}