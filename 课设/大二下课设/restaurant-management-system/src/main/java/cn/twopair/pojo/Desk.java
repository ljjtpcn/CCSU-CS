package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName Desk
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:27
 * @Version 1.0
 **/

@Getter
@Setter
@ToString
public class Desk {
    private Integer id;
    private String no; // 餐台编号
    private Integer seating;
    private  String status;
}
