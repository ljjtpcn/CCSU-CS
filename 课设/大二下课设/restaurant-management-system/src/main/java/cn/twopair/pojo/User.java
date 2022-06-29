package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName User
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:41
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String username;
    private String password;
}
