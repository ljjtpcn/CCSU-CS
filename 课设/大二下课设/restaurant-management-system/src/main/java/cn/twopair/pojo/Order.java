package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:35
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class Order {
    private String orderNo;
    private Integer deskId;
    private Date createTime;
    private  Double money;
    private Integer customerId;
    private String status;
    private Integer number;
}
