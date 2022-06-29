package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName OrderItem
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:38
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class OrderItem {
    private Integer id;
    private String orderId;
    private Integer foodId;
    private Integer cnt;
}
