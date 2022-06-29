package cn.twopair.handler;

import cn.twopair.pojo.Customer;
import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import cn.twopair.view.CustomerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName EmployeeViewHandler
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/12 下午1:56
 * @Version 1.0
 **/
public class CustomerViewHandler implements ActionListener, MouseListener {

    CustomerView customerView;

    public CustomerViewHandler(CustomerView customerView) {
        this.customerView = customerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String text = source.getText();
        if (customerView.getSearchButtonText().equals(text)) {
            getData();

        } else if (customerView.getAddButtonText().equals(text)) {
            add();

        } else if (customerView.getMergeButtonText().equals(text)) {
            merge();

        } else if (customerView.getDeleteButtonText().equals(text)) {
            delete();
        }
    }

    private void delete() {
        //确认是否要删除客户
        int option = JOptionPane.showConfirmDialog(customerView,
                "请确认你选择删除的客户项是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            // 获取选中行
            int[] selectedRows = customerView.getTable().getSelectedRows();
            List<Integer> selectedIds = new ArrayList<>();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(customerView, "请先选中行！", "警告信息", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //获取选中行对应id
            for (int selectedRow : selectedRows) {
                selectedIds.add((Integer) customerView.getTable().getValueAt(selectedRow, 0));
            }
            int effectCount = services.deleteCustomerById(selectedIds);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(customerView, "删除成功！", "删除客户信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(customerView, "删除失败！", "删除客户信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void merge() {
        //确认是否要修改客户
        int option = JOptionPane.showConfirmDialog(customerView,
                "请确认你选择修改的客户信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Customer customer = new Customer();
            customer.setName(customerView.getNameTextFieldDown().getText());
            customer.setSex(customerView.getSexTextFieldDown().getText());
            customer.setTel(customerView.getTelphoneTextFieldDown().getText());
            try {
                customer.setId(Integer.valueOf(customerView.getIdTextFieldDown().getText()));
                customer.setBase(Double.valueOf(customerView.getBaseTextFieldDown().getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(customerView, "修改失败！", "修改客户信息", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int effectCount = services.mergeOneCustomer(customer);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(customerView, "修改成功！", "修改客户信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(customerView, "修改失败！", "修改客户信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void add() {
        //确认是否要添加客户
        int option = JOptionPane.showConfirmDialog(customerView,
                "请确认你想添加的客户信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Customer customer = new Customer();
            customer.setId(null);
            customer.setName(customerView.getNameTextFieldDown().getText());
            customer.setSex(customerView.getSexTextFieldDown().getText());
            customer.setTel(customerView.getTelphoneTextFieldDown().getText());
            try {
                customer.setBase(Double.valueOf(customerView.getBaseTextFieldDown().getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(customerView, "添加失败！", "添加客户信息", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int effectCount = services.addOneCustomer(customer);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(customerView, "添加成功！", "添加客户信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(customerView, "添加失败！", "添加客户信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void getData() {
        Services services = new ServicesImpl();
        Vector<Vector<Object>> returnData =
                services.getCustomerData(customerView.getIdTextField().getText(),
                        customerView.getNameTextField().getText(),
                        customerView.getTelphoneTextField().getText());
        customerView.reloadData(returnData);
    }

    private void clearDownText() {
        customerView.getIdTextFieldDown().setText("");
        customerView.getNameTextFieldDown().setText("");
        customerView.getSexTextFieldDown().setText("");
        customerView.getTelphoneTextFieldDown().setText("");
        customerView.getBaseTextFieldDown().setText("");
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = customerView.getTable();
        if (e.getClickCount() == 2) {
            int selectedRow = table.getSelectedRow();
            customerView.getIdTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 0)));
            customerView.getNameTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 1)));
            customerView.getSexTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 2)));
            customerView.getTelphoneTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 3)));
            customerView.getBaseTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 4)));
        }
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
