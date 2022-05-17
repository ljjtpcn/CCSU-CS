package cn.twopair.mappers;

import cn.twopair.pojo.Log;

import java.util.List;

/**
 * @ClassName LogMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午10:40
 * @Version 1.0
 **/
public interface LogMapper {

    List<Log> selectAll();
}
