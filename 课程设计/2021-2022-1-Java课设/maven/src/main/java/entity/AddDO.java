package entity;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 19:30
 */

public class AddDO {
    private String money;
    private String date;
    private String type;
    private String note;
    private String user;

    public String getMoney() {

        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
