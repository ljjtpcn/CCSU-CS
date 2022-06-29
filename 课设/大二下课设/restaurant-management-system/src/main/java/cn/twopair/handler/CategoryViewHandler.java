package cn.twopair.handler;

import cn.twopair.pojo.Category;
import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import cn.twopair.view.CategoryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName CategoryViewHandler
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/13 下午9:01
 * @Version 1.0
 **/
public class CategoryViewHandler implements MouseListener, ActionListener {

    CategoryView categoryView;

    public CategoryViewHandler(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String text = source.getText();
        if (categoryView.getSearchButtonText().equals(text)) {
            getData();

        } else if (categoryView.getAddButtonText().equals(text)) {
            add();

        } else if (categoryView.getMergeButtonText().equals(text)) {
            merge();

        } else if (categoryView.getDeleteButtonText().equals(text)) {
            delete();
        }
    }

    private void delete() {
        //确认是否要删除
        int option = JOptionPane.showConfirmDialog(categoryView,
                "请确认你选择删除的菜品分类项是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            // 获取选中行
            int[] selectedRows = categoryView.getTable().getSelectedRows();
            List<Integer> selectedIds = new ArrayList<>();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(categoryView, "请先选中行！", "警告信息", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //获取选中行对应id
            for (int selectedRow : selectedRows) {
                selectedIds.add((Integer) categoryView.getTable().getValueAt(selectedRow, 0));
            }
            int effectCount = services.deleteCategoryById(selectedIds);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(categoryView, "删除成功！", "删除菜品分类信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(categoryView, "删除失败！", "删除菜品分类信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void merge() {
        //确认是否要修改菜品分类
        int option = JOptionPane.showConfirmDialog(categoryView,
                "请确认你选择修改的菜品分类信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Category category = new Category();
            try {
                category.setName(categoryView.getCategoryNameTextFieldDown().getText());
                category.setDescribe(categoryView.getDescriptionTextArea().getText());
                category.setId(Integer.valueOf(categoryView.getCategoryIdTextFieldDown().getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(categoryView, "修改失败, 请检查填写信息！", "修改菜品信息", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int effectCount = services.mergeOneCategory(category);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(categoryView, "修改成功！", "修改菜品分类信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(categoryView, "修改失败！", "修改菜品分类信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void add() {
        //确认是否要添加菜品分类
        int option = JOptionPane.showConfirmDialog(categoryView,
                "请确认你想添加的菜品分类信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Category category = new Category();
            try {
                category.setId(null);
                category.setName(categoryView.getCategoryNameTextFieldDown().getText());
                category.setDescribe(categoryView.getDescriptionTextArea().getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(categoryView, "添加失败, 请检查填写信息！", "修改菜品信息", JOptionPane.ERROR_MESSAGE);
                return;

            }
            int effectCount = services.addOneCategory(category);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(categoryView, "添加成功！", "添加菜品分类信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(categoryView, "添加失败！", "添加菜品分类信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void getData() {
        Services services = new ServicesImpl();
        Vector<Vector<Object>> returnData =
                services.getCategoryData(categoryView.getCategoryIdTextField().getText(),
                        categoryView.getCategoryNameTextField().getText());
        categoryView.reloadData(returnData);
    }

    private void clearDownText() {
        categoryView.getCategoryIdTextFieldDown().setText("");
        categoryView.getCategoryNameTextFieldDown().setText("");
        categoryView.getDescriptionTextArea().setText("");
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = categoryView.getTable();
        if (e.getClickCount() == 2) {
            int selectedRow = table.getSelectedRow();
            categoryView.getCategoryIdTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 0)));
            categoryView.getCategoryNameTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 1)));
            categoryView.getDescriptionTextArea()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 2)));
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
