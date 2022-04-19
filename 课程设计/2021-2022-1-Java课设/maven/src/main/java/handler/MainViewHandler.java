package handler;

import res.TableDTO;
import service.DeleteService;
import service.impl.DeleteServiceImpl;
import window.*;

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

public class MainViewHandler implements ActionListener {

    private MainView mainView;

    public MainViewHandler(MainView mainView) {
        this.mainView = mainView;
    }


    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        if ("关键字查询".equals(text)) {
            String op = mainView.getSearchTextField().getText();
            if ("exit".equals(op)) {
                int result = JOptionPane.showConfirmDialog(
                        mainView,
                        "确定退出登录?",
                        "警告",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    new LoginView("学生理财系统");
                    mainView.dispose();

                }
            } else {
                mainView.setPageNow(1);
                mainView.reloadTable(mainView.getUser());
                mainView.getSearchTextField().setText("");
            }
        } else if ("总收入查询".equals(text)) {
            new TotalIncomeDialog(mainView, mainView).getDialog().setVisible(true);
        } else if ("总支出查询".equals(text)) {
            new TotalExpendDialog(mainView, mainView).getDialog().setVisible(true);
        } else if ("统计查询".equals(text)) {
            new StatisticsView(mainView);
        } else if ("上一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() - 1);
            mainView.reloadTable(mainView.getUser());
        } else if ("下一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() + 1);
            mainView.reloadTable(mainView.getUser());
        } else if ("增加收支记录".equals(text)) {
            new AddView(mainView);
        } else if ("删除收支记录".equals(text)) {
            new DeleteView(mainView);
        } else if ("修改收支记录".equals(text)) {
            int[] selectedIDs = mainView.getSelectedIDs();
            if (selectedIDs.length > 1) {
                JOptionPane.showMessageDialog(mainView, "一次只能修改一行");
            } else if (selectedIDs.length == 0) {
                JOptionPane.showMessageDialog(mainView, "请选中需要修改的行");
            } else {
                new ChangeView(mainView, selectedIDs[0]);
            }

        }
    }


}
