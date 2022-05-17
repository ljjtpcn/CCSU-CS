package cn.twopair.mappers;

import cn.twopair.pojo.Transfer;

import java.util.List;

/**
 * @ClassName TransferMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午10:41
 * @Version 1.0
 **/
public interface TransferMapper {
    List<Transfer> selectAll();
}
