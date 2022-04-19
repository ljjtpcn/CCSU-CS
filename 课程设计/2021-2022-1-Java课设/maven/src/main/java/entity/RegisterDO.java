package entity;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/21 17:24
 */

public class RegisterDO {
    private String username;
    private String password;
    private String sex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
