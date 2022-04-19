package student;

import java.io.Serializable;

public class User implements Serializable {
    private String loginName;
    private String passWord;
    private String userName;

    public User()  {

    }

    public User(String loginName, String passWord, String userName) {
        this.loginName = loginName;
        this.passWord = passWord;
        this.userName = userName;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String toString() {
        return "User [loginName=" + loginName + ", passWord=" + passWord + ", userName=" + userName
                + ", getLoginName()=" + getLoginName() + ", getPassWord()=" + getPassWord() + ", getUserName()="
                + getUserName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }



}
