package cn.twopair.view.handler;


import cn.twopair.entity.BookInfo;
import cn.twopair.dao.BookInfoDAO;
import cn.twopair.view.window.AddView;
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

public class AddHandler implements ActionListener {

    private MainView mainView;
    private AddView addView;
    public AddHandler(AddView addView,  MainView mainView) {
        this.mainView = mainView;
        this.addView = addView;
    }


    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();

        //money.append(addView.getMoneyText().getText());
        //String date = addView.getDateText().getText();
        //String type = addView.getTypeText().getText();
        //String note = addView.getRemarkText().getText();
        //String user = addView.getUser();
        //AddDO addDO = new AddDO();
        //addDO.setUser(user);
        //addDO.setMoney(money.toString());
        //addDO.setDate(date);
        //addDO.setType(type);
        //addDO.setNote(note);

        if ("确定".equals(text)) {
            if(addBook()){
                JOptionPane.showMessageDialog(addView, "添加成功！请刷新");
            }else{
                JOptionPane.showMessageDialog(addView, "添加失败！");
            }
        } else if ("取消".equals(text)) {
            addView.dispose();
        }
    }

    private Boolean addBook() {
        BookInfo bookInfo = new BookInfo();
        try {
            bookInfo.setBookNo(addView.getTypeText().getText());
            bookInfo.setBookName(addView.getBookNameText().getText());
            bookInfo.setAuthor(addView.getAutorText().getText());
            bookInfo.setPublisher(addView.getPublisherText().getText());
            bookInfo.setPrice(Double.valueOf(addView.getPriceText().getText()));
            bookInfo.setPublishTime(addView.getDateText().getText());
            bookInfo.setISBN(addView.getISBNText().getText());
            bookInfo.setAmount(Integer.valueOf(addView.getAmountText().getText()));
        }catch (Exception exception){
            return false;
        }
        return BookInfoDAO.addBook(bookInfo);
    }
}
