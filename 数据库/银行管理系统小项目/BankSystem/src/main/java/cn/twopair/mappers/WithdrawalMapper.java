package cn.twopair.mappers;

import cn.twopair.pojo.Withdrawal;

import java.util.List;

/**
 * @ClassName WithdrawalMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午10:42
 * @Version 1.0
 **/
public interface WithdrawalMapper {
    List<Withdrawal> selectAll();
}
