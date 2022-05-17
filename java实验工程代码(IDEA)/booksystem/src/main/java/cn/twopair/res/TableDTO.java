package cn.twopair.res;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Vector;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/19 23:39
 */
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class TableDTO {
    private Vector<Vector<Object>> data;
    //返回条数
    private int totalCount;


}
