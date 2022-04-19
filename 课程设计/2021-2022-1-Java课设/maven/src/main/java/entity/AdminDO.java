package entity;/**
 *
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-12 15:16
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
public class AdminDO {
    private Integer id;
    private String username;
    private String pwd;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
