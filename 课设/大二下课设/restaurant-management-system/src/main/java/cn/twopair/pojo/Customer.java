package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName Customer
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:26
 * @Version 1.0
 **/
@Setter
@Getter
@ToString
public class Customer {
    private Integer id;
    private String name;
    private String sex;
    private String tel;
    private Double base;
}
