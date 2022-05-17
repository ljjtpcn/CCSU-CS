package cn.twopair.view.handler;

import cn.twopair.dao.BookInfoDAO;
import cn.twopair.view.window.AddView;
import cn.twopair.view.window.ChangeView;
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

        if ("查询/刷新".equals(text)) {
            String keyword = mainView.getSearchTextField().getText();
            if ((keyword == null) || "".equals(keyword.trim())) {
                mainView.reloadTable();
            } else {
                mainView.reloadTable(keyword);
            }
            mainView.setPageNow(1);
            mainView.getSearchTextField().setText("");

        } else if ("上一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() - 1);
            mainView.reloadTable();
        } else if ("下一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() + 1);
            mainView.reloadTable();
        } else if ("增加图书信息".equals(text)) {
            new AddView(mainView);
        } else if ("删除图书信息".equals(text)) {
            new DeleteView(mainView);
        } else if ("修改图书信息".equals(text)) {
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
