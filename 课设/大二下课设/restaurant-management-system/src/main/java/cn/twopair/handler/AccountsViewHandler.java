package cn.twopair.handler;

import cn.twopair.pojo.Desk;
import cn.twopair.pojo.Order;
import cn.twopair.pojo.OrderItem;
import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import cn.twopair.view.AccountsView;
import org.apache.commons.validator.routines.DateValidator;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Vector;

/**
 * @ClassName AccountsViewHandler
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/17 下午4:36
 * @Version 1.0
 **/
public class AccountsViewHandler implements ActionListener, MouseListener {
    AccountsView accountsView;

    public AccountsViewHandler(AccountsView accountsView) {
        this.accountsView = accountsView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        String text = sourceButton.getText();
        if (text.equals(accountsView.getSearchOrderButtonText())) {
            Order order = new Order();
            order.setOrderNo(accountsView.getOrderIdTextField().getText());
            try {
                order.setDeskId(Integer.valueOf(accountsView.getDeskIdTextField().getText()));
            } catch (Exception ignored) {
            }
            order.setStatus(String.valueOf(accountsView.getStatusPayCheckBox().getSelectedItem()));
            Services services = new ServicesImpl();
            Vector<Vector<Object>> data = services.getOrderData(order);
            accountsView.reloadSearchTableData(data);
        } else if (text.equals(accountsView.getSureButtonText())) {

            try {
                double theAmountActuallyPaidMoney = Double.parseDouble(accountsView.getTheAmountActuallyPaidTextField().getText());
                double rateMoney = Double.parseDouble(accountsView.getRateMoneyTextField().getText());
                double getShouldPayMoney = Double.parseDouble(accountsView.getShouldPayTextField().getText());
                if (theAmountActuallyPaidMoney < getShouldPayMoney - rateMoney) {
                    JOptionPane.showMessageDialog(accountsView, "想在我这吃霸王餐！？我数学可不是体育老师教的", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(rateMoney > getShouldPayMoney){
                    JOptionPane.showMessageDialog(accountsView, "老板没这么多钱优惠啦！！！", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Services services = new ServicesImpl();
                Order order = new Order();
                order.setOrderNo(accountsView.getOrderIdTextFieldDown().getText());
                Vector<Vector<Object>> orderData = services.getOrderData(order);
                int deskId = Integer.parseInt(String.valueOf(orderData.get(0).get(1)));
                Desk desk = new Desk();
                desk.setId(deskId);
                desk.setStatus("空闲");
                order.setStatus("已支付");
                int flagOrder = services.mergeOrderStatus(order);
                int flagDesk = services.mergeOneDeskStatus(desk);
                if (flagDesk > 0 && flagOrder > 0) {
                    JOptionPane.showMessageDialog(accountsView, "结账成功！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
                    accountsView.getChangeMoneyTexField().setText(String.valueOf(theAmountActuallyPaidMoney - (getShouldPayMoney - rateMoney)));
                } else {
                    JOptionPane.showMessageDialog(accountsView, "结账失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(accountsView, "请正确输入金额！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable searchViewTable = accountsView.getSearchViewTable();
        int selectedRow = searchViewTable.getSelectedRow();
        if (searchViewTable.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(accountsView, "请先选择一行订单", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(String.valueOf(searchViewTable.getValueAt(selectedRow, 0)));
        Services services = new ServicesImpl();
        Vector<Vector<Object>> data = services.getOrderItemData(orderItem);
        accountsView.reloadOrderItemTableData(data);

        //操作区
        accountsView.getShouldPayTextField().setText(String.valueOf(searchViewTable.getValueAt(selectedRow, 3)));
        double base = services.getRateMoney(String.valueOf(searchViewTable.getValueAt(selectedRow, 4)));
        double rateMoney = (1 - base) * Double.parseDouble(String.valueOf(searchViewTable.getValueAt(selectedRow, 3)));
        accountsView.getRateMoneyTextField().setText(String.valueOf(rateMoney));
        accountsView.getOrderIdTextFieldDown().setText(orderItem.getOrderId());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
