package cn.twopair.handler;

import cn.twopair.pojo.Food;
import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import cn.twopair.utils.CheckUtil;
import cn.twopair.view.FoodView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName FoodViewHandler
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/14 上午9:46
 * @Version 1.0
 **/
public class FoodViewHandler implements ActionListener, MouseListener {

    FoodView foodView;

    public FoodViewHandler(FoodView foodView) {
        this.foodView = foodView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String text = source.getText();
        if (foodView.getChooseFileButtonText().equals(text)) {
            loadingPhoto();

        } else if (foodView.getSearchButtonText().equals(text)) {
            getData();

        } else if (foodView.getAddButtonText().equals(text)) {
            add();

        } else if (foodView.getMergeButtonText().equals(text)) {
            merge();
            
        } else if (foodView.getDeleteButtonText().equals(text)) {
            delete();
        }
    }

    private void delete() {
        //确认是否要删除
        int option = JOptionPane.showConfirmDialog(foodView,
                "请确认你选择删除的菜品项是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            // 获取选中行
            int[] selectedRows = foodView.getTable().getSelectedRows();
            List<Integer> selectedIds = new ArrayList<>();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(foodView, "请先选中行！", "警告信息", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //获取选中行对应id
            for (int selectedRow : selectedRows) {
                selectedIds.add((Integer) foodView.getTable().getValueAt(selectedRow, 0));
            }
            int effectCount = services.deleteFoodById(selectedIds);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(foodView, "删除成功！", "删除菜品信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(foodView, "删除失败！", "删除菜品信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void merge() {
        //确认是否要修改菜品信息
        int option = JOptionPane.showConfirmDialog(foodView,
                "请确认你选择修改的菜品信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Food food = new Food();
            try {
                food.setId(Integer.valueOf(foodView.getFoodIdTextFieldDown().getText()));
                food.setName(foodView.getFoodNameTextFieldDown().getText());
                food.setCategoryId(Integer.valueOf(foodView.getCategoryIdTextFieldDown().getText()));
                food.setStatus(String.valueOf(foodView.getStatusCheckBoxDown().getSelectedItem()));
                food.setPrice(Double.valueOf(foodView.getFoodPriceTextFieldDown().getText()));
                food.setDescribe(foodView.getDescriptionTextArea().getText());
                food.setSum(Integer.valueOf(foodView.getFoodSumTextFieldDown().getText()));
                food.setFilePath(foodView.getFileAbsolutePathTextArea().getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(foodView, "修改失败, 请检查填写信息！", "修改菜品信息", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int effectCount = services.mergeOneFood(food);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(foodView, "修改成功！", "修改菜品信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(foodView, "修改失败！", "修改菜品信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void add() {
        //确认是否要添加菜品
        int option = JOptionPane.showConfirmDialog(foodView,
                "请确认你想添加的菜品信息是否正确?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //确认继续操作
            Services services = new ServicesImpl();
            Food food = new Food();
            try {
                food.setId(null);
                food.setName(foodView.getFoodNameTextFieldDown().getText());
                food.setCategoryId(Integer.valueOf(foodView.getCategoryIdTextFieldDown().getText()));
                food.setPrice(Double.valueOf(foodView.getFoodPriceTextFieldDown().getText()));
                food.setDescribe(foodView.getDescriptionTextArea().getText());
                food.setStatus(String.valueOf(foodView.getStatusCheckBoxDown().getSelectedItem()));
                food.setSum(Integer.valueOf(foodView.getFoodSumTextFieldDown().getText()));
                food.setFilePath(foodView.getFileAbsolutePathTextArea().getText());
            } catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(foodView, "添加失败, 请检查填写信息！", "添加菜品信息", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int effectCount = services.addOneFood(food);
            if (effectCount > 0) {
                JOptionPane.showMessageDialog(foodView, "添加成功！", "添加菜品信息", JOptionPane.INFORMATION_MESSAGE);
                clearDownText();
                getData();
            } else {
                JOptionPane.showMessageDialog(foodView, "添加失败！", "添加菜品信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadingPhoto() {
        String path = foodView.chooseFile();
        foodView.getFileAbsolutePathTextArea().setText(path);
        File file = new File(path);
        if (!file.exists()) {
            // 不知道为什么 这里不能填相对路径img/404.png， 神奇啊
            // 好了 解决了
            foodView.getFoodPhotoLabel().setIcon(new ImageIcon("src/main/resources/img/404.png"));
        } else {
            foodView.getFoodPhotoLabel().setIcon(new ImageIcon(path));
        }
    }

    private void getData() {
        Services services = new ServicesImpl();
        Vector<Vector<Object>> returnData =
                services.getFoodData(foodView.getFoodIdTextField().getText(),
                        foodView.getFoodNameTextField().getText(),
                        String.valueOf(foodView.getStatusCheckBox().getSelectedItem()));
        foodView.reloadData(returnData);
    }

    private void clearDownText() {
        foodView.getFoodIdTextFieldDown().setText("");
        foodView.getFoodNameTextFieldDown().setText("");
        foodView.getCategoryIdTextFieldDown().setText("");
        foodView.getFoodPriceTextFieldDown().setText("");
        foodView.getDescriptionTextArea().setText("");
        foodView.getStatusCheckBoxDown().setSelectedItem("");
        foodView.getFoodSumTextFieldDown().setText("");
        foodView.getFileAbsolutePathTextArea().setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = foodView.getTable();
        if (e.getClickCount() == 2) {
            int selectedRow = table.getSelectedRow();
            foodView.getFoodIdTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 0)));
            foodView.getFoodNameTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 1)));
            foodView.getCategoryIdTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 2)));
            foodView.getFoodPriceTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 3)));
            foodView.getDescriptionTextArea()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 4)));
            foodView.getStatusCheckBoxDown()
                    .setSelectedItem(String.valueOf(table.getValueAt(selectedRow, 5)));
            foodView.getFoodSumTextFieldDown()
                    .setText(String.valueOf(table.getValueAt(selectedRow, 6)));
            Services services = new ServicesImpl();
            String foodIconPath = services.getFoodIconPathById((Integer) table.getValueAt(selectedRow, 0));
            foodView.getFileAbsolutePathTextArea().setText(foodIconPath);

            File file = new File(foodIconPath);
            if (!file.exists()) {
                // 不知道为什么 这里不能填相对路径img/404.png， 神奇啊
                foodView.getFoodPhotoLabel().setIcon(new ImageIcon("src/main/resources/img/404.png"));
            } else {
                foodView.getFoodPhotoLabel().setIcon(new ImageIcon(foodIconPath));
            }
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
