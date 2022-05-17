package cn.twopair.mappers;

import cn.twopair.pojo.User;
import java.util.List;

/**
 * @author ljjtpcn
 */
public interface UserMapper {
    List<User> selectAll();
}
