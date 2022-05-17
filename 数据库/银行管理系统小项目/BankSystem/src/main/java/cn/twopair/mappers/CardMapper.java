package cn.twopair.mappers;

import cn.twopair.pojo.Card;

import java.util.List;

/**
 * @ClassName CardMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午10:19
 * @Version 1.0
 **/
public interface CardMapper {
    List<Card> selectAll();
}
