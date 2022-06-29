package cn.twopair.dao;

import cn.twopair.mappers.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName DaoUtil
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午8:06
 * @Version 1.0
 **/

@Getter
@Setter
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

    public static  SqlSession session = getSqlSession();
    public static UserMapper userMapper = session.getMapper(UserMapper.class);
    public static CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
    public static DeskMapper deskMapper = session.getMapper(DeskMapper.class);
    public static EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
    public static FoodMapper foodMapper = session.getMapper(FoodMapper.class);
    public static OrderMapper orderMapper = session.getMapper(OrderMapper.class);

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

}
