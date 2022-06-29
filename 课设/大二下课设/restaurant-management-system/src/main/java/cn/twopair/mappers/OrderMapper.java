package cn.twopair.mappers;

import cn.twopair.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName OrderMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/17 上午12:31
 * @Version 1.0
 **/
public interface OrderMapper {

    @Select("SELECT * FROM `order` WHERE orderNo = #{orderId}")
    List<Order> selectOrderById(@Param("orderId") String orderId);


    int insertOrder(@Param("order") Order order);


    List<Order> getOrderData(@Param("order") Order order);

    @Update("UPDATE `order` SET status = #{order.status} WHERE orderNo = #{order.orderNo}")
    int mergeOrderStatus(@Param("order") Order order);

    void getStatisticsData(HashMap<String, Double> params);
}
