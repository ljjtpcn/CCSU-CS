package cn.twopair.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:42
 * @Version 1.0
 **/
public interface UserMapper  {
    String getPasswordByUsername(@Param("username") String username);
}
