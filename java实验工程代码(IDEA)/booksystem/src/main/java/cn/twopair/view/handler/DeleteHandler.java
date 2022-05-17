package cn.twopair.view.handler;


import cn.twopair.dao.BookInfoDAO;
import cn.twopair.view.window.DeleteView;
import cn.twopair.view.window.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-11 16:52
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */

public class DeleteHandler implements ActionListener {

    private DeleteView deleteView;
    private MainView mainView;

    public DeleteHandler(DeleteView deleteView, MainView mainView) {
        this.deleteView = deleteView;
        this.mainView = mainView;
    }


    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();

        if ("确定".equals(text)) {
            if (check(deleteView.getIdText().getText())) {
                int result = JOptionPane.showConfirmDialog(
                        deleteView,
                        "亲,三思而后行哦!!!",
                        "警告",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    boolean flag = BookInfoDAO.delBookById(Integer.parseInt(deleteView.getIdText().getText()));
                    if (flag) {
                        //mainView.reloadTable();
                        JOptionPane.showMessageDialog(deleteView, "删除成功!请刷新");
                    } else {
                        JOptionPane.showMessageDialog(deleteView, "删除失败!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(deleteView, "id未填写,请检查后再提交!!!");
            }
        } else if ("取消".equals(text)) {
            deleteView.dispose();
        }
    }

    private boolean check(String text) {
        return text != null && !"".equals(text.trim());
    }


}
