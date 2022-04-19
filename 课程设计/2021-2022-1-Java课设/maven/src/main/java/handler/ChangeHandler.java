package handler;

import entity.ModifyIncomeDO;
import service.ModifyService;
import service.impl.ModifyImpl;
import window.ChangeView;
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

public class ChangeHandler implements ActionListener {

    private ChangeView changeView;
    private MainView mainView;

    public ChangeHandler(ChangeView changeView, MainView mainView) {
        this.changeView = changeView;
        this.mainView = mainView;
    }


    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        String id = changeView.getIdFiled().getText();
        String type = changeView.getTypeText().getText();
        String money = changeView.getMoneyText().getText();
        String note = changeView.getRemarkText().getText();
        String date = changeView.getDateTextField().getText();
        ModifyIncomeDO modifyIncomeDO = new ModifyIncomeDO();
        modifyIncomeDO.setId(id);
        modifyIncomeDO.setMoney(money);
        modifyIncomeDO.setType(type);
        modifyIncomeDO.setNote(note);
        modifyIncomeDO.setDate(date);
        if ("确定".equals(text)) {
            if (check(modifyIncomeDO)) {
                int result = JOptionPane.showConfirmDialog(
                        changeView,
                        "确认修改？",
                        "提示",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {

                    ModifyService modifyService = new ModifyImpl();
                    boolean flag = modifyService.validateModifyIncome(modifyIncomeDO);
                    if (flag) {
                        mainView.reloadTable(mainView.getUser());
                        JOptionPane.showMessageDialog(changeView, "修改成功!");
                    } else {
                        JOptionPane.showMessageDialog(changeView, "修改失败,请尝试重新修改");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(changeView, "请检查是否未填或金额,日期格式是否正确");
            }
        } else if ("取消".equals(text)) {

        }
    }

    private boolean check(ModifyIncomeDO modifyIncomeDO) {

        if (modifyIncomeDO.getMoney().length() > 8) {
            return false;
        }
        try {
            Double.valueOf(modifyIncomeDO.getMoney());
        } catch (NumberFormatException e) {
            return false;
        }
        if ((modifyIncomeDO.getType() == null || "".equals(modifyIncomeDO.getType().trim())) ||
                (modifyIncomeDO.getMoney() == null || "".equals(modifyIncomeDO.getMoney().trim())) ||
                (modifyIncomeDO.getNote() == null || "".equals(modifyIncomeDO.getNote().trim()))
        ) {
            return false;
        }

        String date = modifyIncomeDO.getDate();
        return checkDate(date);
    }


}
