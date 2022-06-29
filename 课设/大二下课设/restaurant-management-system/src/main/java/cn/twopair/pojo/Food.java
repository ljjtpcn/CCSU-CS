package cn.twopair.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName Food
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:30
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class Food {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String describe;
    private Double price;
    private String status;
    private Integer sum;
    private String filePath;
}
