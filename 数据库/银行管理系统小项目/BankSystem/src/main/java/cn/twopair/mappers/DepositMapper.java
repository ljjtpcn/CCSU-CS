package cn.twopair.mappers;

import cn.twopair.pojo.Deposit;

import java.util.List;

/**
 * @ClassName DepositMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午10:24
 * @Version 1.0
 **/
public interface DepositMapper {
    /**
     * @Param: null
     * @Description: 查询所有存款记录
     * @Return: java.util.List<cn.twopair.pojo.Deposit>
     * @Author: 李佳骏d
     * @Date: 2022/5/10 上午10:26
     */
    List<Deposit> selectAll();
}
