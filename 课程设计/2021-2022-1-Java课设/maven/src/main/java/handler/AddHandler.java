package handler;

import entity.AddDO;
import service.AddService;
import service.impl.AddServiceImpl;
import window.AddView;
import window.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.CheckUtil.checkDate;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-11 16:52
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */

public class AddHandler implements ActionListener {

    private AddView addView;
    private MainView mainView;

    public AddHandler(AddView addView, MainView mainView) {
        this.addView = addView;
        this.mainView = mainView;
    }


    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        StringBuilder money = new StringBuilder();
        if (addView.getPayButton().isSelected()) {
            money.append("-");
        }
        money.append(addView.getMoneyText().getText());
        String date = addView.getDateText().getText();
        String type = addView.getTypeText().getText();
        String note = addView.getRemarkText().getText();
        String user = addView.getUser();
        AddDO addDO = new AddDO();
        addDO.setUser(user);
        addDO.setMoney(money.toString());
        addDO.setDate(date);
        addDO.setType(type);
        addDO.setNote(note);

        if ("确定".equals(text)) {
            if (check(addDO)) {
                //发送请求
                AddService addService = new AddServiceImpl();
                boolean flag = addService.validateAdd(addDO);
                if (flag) {
                    mainView.reloadTable(mainView.getUser());
                    JOptionPane.showMessageDialog(addView, "添加成功");
                } else {
                    JOptionPane.showMessageDialog(addView, "添加失败");
                }
            } else {
                JOptionPane.showMessageDialog(addView, "请检查是否有缺项未填或填写格式错误");

            }
        } else if ("取消".equals(text)) {
            addView.dispose();
        }
    }

    private boolean check(AddDO addDO) {
        String money = addDO.getMoney();
        String date = addDO.getDate();
        String type = addDO.getType();
        String note = addDO.getNote();
        try {
            Double.valueOf(money);
        } catch (NumberFormatException e) {
            return false;
        }

        if (money.length() > 8) {
            return false;
        }
        if (money.charAt(0) == '-' && addView.getAddButton().isSelected()) {
            return false;
        }


        if (type == null || "".equals(type.trim()) ||
                note == null || "".equals(note.trim())) {
            return false;
        }

        return checkDate(date);


    }

}
