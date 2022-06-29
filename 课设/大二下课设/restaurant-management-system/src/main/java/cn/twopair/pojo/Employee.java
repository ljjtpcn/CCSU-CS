package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName Employees
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:29
 * @Version 1.0
 **/

@Getter
@Setter
@ToString
public class Employee {
    private Integer id;
    private String name;
    private String sex;
    private String identityId;
    private String tel;
    private String status;
}
