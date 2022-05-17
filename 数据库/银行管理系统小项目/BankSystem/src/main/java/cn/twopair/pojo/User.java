package cn.twopair.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ljjtpcn
 */
@Getter
@Setter
@ToString
public class User {
    private String name;
    private String idNumber;
    private String password;
    private String phone;
}
