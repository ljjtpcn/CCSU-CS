package cn.twopair.handler;

import cn.twopair.pojo.Desk;
import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import cn.twopair.view.DeskManagerView;

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
public class DeskManagerViewHandler implements ActionListener, MouseListener {

    DeskManagerView deskManagerView;

    public DeskManagerViewHandler(DeskManagerView deskManagerView) {
        this.deskManagerView = deskManagerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String text = source.getText();
        if (deskManagerView.getSearchButtonText().equals(text)) {
            getData();
        } else if (deskManagerView.getAddButtonText().equals(text)) {
            add();

        } else if (deskManagerView.getMergeButtonText().equals(text)) {
            merge();

        } else if (deskManagerView.getDeleteButtonText().equals(text)) {
            delete();
        }
    }

    private void delete() {
        //确认是否要删除餐台
        int option = JOptionPane.showConfirmDialog(deskManagerView,
                "请确认你选择删除的餐台是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();

            // 获取选中行
            int[] selectedRows = deskManagerView.getTable().getSelectedRows();
            List<Integer> selectedIds = new ArrayList<>();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(deskManagerView, "请先选中行！", "警告信息", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //获取选中行对应id
            for (int selectedRow : selectedRows) {
                selectedIds.add((Integer) deskManagerView.getTable().getValueAt(selectedRow, 0));
            }
            int effectCount = services.deleteDeskById(selectedIds);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(deskManagerView, "删除成功！", "删除餐台信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(deskManagerView, "删除失败！", "删除餐台信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void merge() {
        //确认是否要修改餐台
        int option = JOptionPane.showConfirmDialog(deskManagerView,
                "请确认你选择修改的餐台信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Desk desk = new Desk();
            desk.setNo(deskManagerView.getDeskNoTextFieldDown().getText());
            desk.setStatus(String.valueOf(deskManagerView.getStatusCheckBoxDown().getSelectedItem()));
            try {
                desk.setId(Integer.valueOf(deskManagerView.getIdtextFieldDown().getText()));
                desk.setSeating(Integer.valueOf(deskManagerView.getDeskSeatingTextFieldDown().getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(deskManagerView, "修改失败！", "修改餐台信息", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int effectCount = services.mergeOneDesk(desk);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(deskManagerView, "修改成功！", "修改餐台信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(deskManagerView, "修改失败！", "修改餐台信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void add() {
        //确认是否要添加餐台
        int option = JOptionPane.showConfirmDialog(deskManagerView,
                "请确认你想添加的餐台信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Desk desk = new Desk();
            desk.setId(null);
            desk.setNo(deskManagerView.getDeskNoTextFieldDown().getText());
            desk.setStatus(String.valueOf(deskManagerView.getStatusCheckBoxDown().getSelectedItem()));
            try {
                desk.setSeating(Integer.valueOf(deskManagerView.getDeskSeatingTextFieldDown().getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(deskManagerView, "添加失败！", "添加餐台信息", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int effectCount = services.addOneDesk(desk);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(deskManagerView, "添加成功！", "添加餐台信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(deskManagerView, "添加失败！", "添加餐台信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void getData() {
        Services services = new ServicesImpl();
        Vector<Vector<Object>> returnData;

        if("".equals(deskManagerView.getDeskSeatingTextField().getText().trim()) ||
                deskManagerView.getDeskSeatingTextField().getText() == null){
            JOptionPane.showMessageDialog(deskManagerView, "请先输入餐桌人数", "查询餐台信息", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            returnData = services.getDeskData(deskManagerView.getDeskNoTextField().getText(),
                    Integer.valueOf(deskManagerView.getDeskSeatingTextField().getText()),
                    String.valueOf(deskManagerView.getStatusCheckBox().getSelectedItem()));
            deskManagerView.reloadData(returnData);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(deskManagerView, "查询失败！", "查询餐台信息", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearDownText() {
        deskManagerView.getIdtextFieldDown().setText("");
        deskManagerView.getDeskNoTextFieldDown().setText("");
        deskManagerView.getDeskSeatingTextFieldDown().setText("");
        deskManagerView.getStatusCheckBoxDown().setSelectedIndex(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = deskManagerView.getTable();
        if (e.getClickCount() == 2) {
            int selectedRow = table.getSelectedRow();
            deskManagerView.getIdtextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 0)));
            deskManagerView.getDeskNoTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 1)));
            deskManagerView.getDeskSeatingTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 2)));
            deskManagerView.getStatusCheckBoxDown()
                    .setSelectedItem(table.getValueAt(selectedRow, 3));
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
