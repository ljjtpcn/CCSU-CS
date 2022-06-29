package cn.twopair.handler;

import cn.twopair.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName MenuViewHandler
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/12 下午1:22
 * @Version 1.0
 **/
public class MenuViewHandler implements ActionListener {

    MenuView menuView;

    public MenuViewHandler(MenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem jMenuItem = (JMenuItem) e.getSource();
        String text = jMenuItem.getText();
        if (menuView.getEmployeeManagerText().equals(text)) {
            new EmployeeView(menuView).setVisible(true);
        } else if (menuView.getCustomerManagerText().equals(text)) {
            new CustomerView(menuView).setVisible(true);
        } else if (menuView.getDesktopManagerText().equals(text)) {
            new DeskManagerView(menuView).setVisible(true);
        } else if (menuView.getCategoryManagerText().equals(text)) {
            new CategoryView(menuView).setVisible(true);
        } else if (menuView.getFoodManagerText().equals(text)) {
            new FoodView(menuView).setVisible(true);
        } else if (menuView.getOrderText().equals(text)) {
            new OrderView(menuView).setVisible(true);
        } else if (menuView.getAccountsText().equals(text)) {
            new AccountsView(menuView).setVisible(true);
        } else if(menuView.getSellStatisticsText().equals(text)){
            new SellStatisticsView().getFrame().setVisible(true);
        }
    }
}
