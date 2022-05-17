package cn.twopair.req;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName BookRequest
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/16 下午2:47
 * @Version 1.0
 **/

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class BookRequest {
    private Integer id;
    private String bookNo;
    private String bookName;
    private String author;
    private String publisher;
    private Double price;
    private String publishTime;
    private String ISBN;
    private Integer amount;
    private Integer pageNow;
    private Integer pageSize;

}
