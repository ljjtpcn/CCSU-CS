package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName Category
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:24
 * @Version 1.0
 **/

@Getter
@Setter
@ToString
public class Category {
    private Integer id;
    private String name;
    private String describe;
}
