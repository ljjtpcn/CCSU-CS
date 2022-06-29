package cn.twopair.mappers;

import cn.twopair.pojo.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName OrderItemMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/17 上午9:54
 * @Version 1.0
 **/
public interface OrderItemMapper {

    @Insert("INSERT INTO orderItem VALUES(null, #{item.orderId}, #{item.foodId}, #{item.cnt})")
    int insertOneOrderItem(@Param("item") OrderItem item);

    @Select("SELECT * FROM orderItem WHERE orderId = #{orderItem.orderId}")
    List<OrderItem> getOrderItemData(@Param("orderItem") OrderItem orderItem);
}
