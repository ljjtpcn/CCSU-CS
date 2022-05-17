package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Log
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午10:31
 * @Version 1.0
 **/

@ToString
@Setter
@Getter
public class Log {
    private String cardNumber;
    private Date dealTime;
    private Double money;
    private String type;
    private String comment;
}
