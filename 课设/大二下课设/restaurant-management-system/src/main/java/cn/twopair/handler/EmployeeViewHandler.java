package cn.twopair.handler;

import cn.twopair.pojo.Employee;
import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import cn.twopair.view.EmployeeView;

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
public class EmployeeViewHandler implements ActionListener, MouseListener {

    EmployeeView employeeView;

    public EmployeeViewHandler(EmployeeView employeeView) {
        this.employeeView = employeeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String text = source.getText();
        if (employeeView.getSearchButtonText().equals(text)) {
            getData();

        } else if (employeeView.getAddButtonText().equals(text)) {
            add();

        } else if (employeeView.getMergeButtonText().equals(text)) {
            merge();

        } else if (employeeView.getDeleteButtonText().equals(text)) {
            delete();
        }
    }

    private void delete() {
        //确认是否要删除员工
        int option = JOptionPane.showConfirmDialog(employeeView,
                "请确认你选择删除的员工项是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();

            // 获取选中行
            int[] selectedRows = employeeView.getTable().getSelectedRows();
            List<Integer> selectedIds = new ArrayList<>();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(employeeView, "请先选中行！", "警告信息", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //获取选中行对应id
            for (int selectedRow : selectedRows) {
                selectedIds.add((Integer) employeeView.getTable().getValueAt(selectedRow, 0));
            }
            int effectCount = services.deleteEmployeeById(selectedIds);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(employeeView, "删除成功！", "删除员工信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(employeeView, "删除失败！", "删除员工信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void merge() {
        //确认是否要修改员工
        int option = JOptionPane.showConfirmDialog(employeeView,
                "请确认你选择修改的员工信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Employee employee = new Employee();
            employee.setName(employeeView.getNameTextFieldDown().getText());
            employee.setSex(employeeView.getSexTextFieldDown().getText());
            employee.setIdentityId(employeeView.getIdentityIdTextFieldDown().getText());
            employee.setTel(employeeView.getTelphoneTextFieldDown().getText());
            employee.setStatus(employeeView.getStatusTextFieldDown().getText());
            try {
                employee.setId(Integer.valueOf(employeeView.getIdTextFieldDown().getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(employeeView, "修改失败！", "修改餐台信息", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int effectCount = services.mergeOneEmployee(employee);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(employeeView, "修改成功！", "修改员工信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(employeeView, "修改失败！", "修改员工信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void add() {
        //确认是否要添加员工
        int option = JOptionPane.showConfirmDialog(employeeView,
                "请确认你想添加的员工信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Employee employee = new Employee();
            employee.setId(null);
            employee.setName(employeeView.getNameTextFieldDown().getText());
            employee.setSex(employeeView.getSexTextFieldDown().getText());
            employee.setIdentityId(employeeView.getIdentityIdTextFieldDown().getText());
            employee.setTel(employeeView.getTelphoneTextFieldDown().getText());
            employee.setStatus(employeeView.getStatusTextFieldDown().getText());

            int effectCount = services.addOneEmployee(employee);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(employeeView, "添加成功！", "添加员工信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(employeeView, "添加失败！", "添加员工信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void getData() {
        Services services = new ServicesImpl();
        Vector<Vector<Object>> returnData =
                services.getEmployeeData(employeeView.getIdTextField().getText(),
                        employeeView.getNameTextField().getText(),
                        employeeView.getIdentityIdTextField().getText());
        employeeView.reloadData(returnData);
    }


    private void clearDownText() {
        employeeView.getIdTextFieldDown().setText("");
        employeeView.getNameTextFieldDown().setText("");
        employeeView.getSexTextFieldDown().setText("");
        employeeView.getIdentityIdTextFieldDown().setText("");
        employeeView.getTelphoneTextFieldDown().setText("");
        employeeView.getStatusTextFieldDown().setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = employeeView.getTable();
        if (e.getClickCount() == 2) {
            int selectedRow = table.getSelectedRow();
            employeeView.getIdTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 0)));
            employeeView.getNameTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 1)));
            employeeView.getSexTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 2)));
            employeeView.getIdentityIdTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 3)));
            employeeView.getTelphoneTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 4)));
            employeeView.getStatusTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 5)));
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
