package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Deposit
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午10:21
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class Deposit {
    private String cardNumber;
    private Date time;
    private Double money;

}
