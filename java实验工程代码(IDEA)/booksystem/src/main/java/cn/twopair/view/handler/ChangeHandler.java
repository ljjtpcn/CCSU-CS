package cn.twopair.view.handler;


import cn.twopair.entity.BookInfo;
import cn.twopair.dao.BookInfoDAO;
import cn.twopair.view.window.ChangeView;
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

public class ChangeHandler implements ActionListener {

    private ChangeView changeView;
    private MainView mainView;

    public ChangeHandler(ChangeView changeView, MainView mainView) {
        this.changeView = changeView;
        this.mainView = mainView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(Integer.valueOf(changeView.getIdTextField().getText()));
        bookInfo.setBookNo(changeView.getBookNoTextField().getText());
        bookInfo.setBookName(changeView.getBookNameTextField().getText());
        bookInfo.setAuthor(changeView.getAuthorField().getText());
        bookInfo.setPublisher(changeView.getPublisherTextField().getText());
        bookInfo.setPrice(Double.valueOf(changeView.getPriceTextField().getText()));
        bookInfo.setPublishTime(changeView.getPublishTimeField().getText());
        bookInfo.setISBN(changeView.getISBNTextField().getText());
        bookInfo.setAmount(Integer.valueOf(changeView.getAmountTextField().getText()));


        if ("确定".equals(text)) {
            int result = JOptionPane.showConfirmDialog(
                    changeView,
                    "确认修改？",
                    "提示",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                boolean flag = BookInfoDAO.update(bookInfo);
                if (flag) {
                    //mainView.reloadTable();
                    JOptionPane.showMessageDialog(changeView, "修改成功!请刷新");
                } else {
                    JOptionPane.showMessageDialog(changeView, "修改失败,请尝试重新修改");
                }
            }
        }
    }


}
