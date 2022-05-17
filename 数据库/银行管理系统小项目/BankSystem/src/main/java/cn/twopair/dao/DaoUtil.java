package cn.twopair.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DaoUtil {
    static String resource = "mybatis-config.xml";
    static InputStream inputStream;

    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    public static SqlSession getSqlSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public static void close(SqlSession sqlSession) {
        sqlSession.close();
    }

}
