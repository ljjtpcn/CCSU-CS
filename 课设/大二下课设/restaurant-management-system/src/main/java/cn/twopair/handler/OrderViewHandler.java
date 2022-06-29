package cn.twopair.handler;

import cn.twopair.pojo.Desk;
import cn.twopair.pojo.Food;
import cn.twopair.pojo.Order;
import cn.twopair.pojo.OrderItem;
import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import cn.twopair.utils.CheckUtil;
import cn.twopair.view.OrderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 * @ClassName OrderViewHandler
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/16 下午12:58
 * @Version 1.0
 **/
public class OrderViewHandler implements ActionListener, MouseListener {

    OrderView orderView;

    public OrderViewHandler(OrderView orderView) {
        this.orderView = orderView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        String sourceButtonText = sourceButton.getText();
        if (sourceButtonText.equals(orderView.getSearchEmptySeatButtonText())) {
            searchEmptySeat();

        } else if (sourceButtonText.equals(orderView.getSearchFoodButtonText())) {
            searchFoodsBycustomer();

        } else if (sourceButtonText.equals(orderView.getSearchBossButtonText())) {
            boosRecommendFood();

        } else if (sourceButtonText.equals(orderView.getShoppingButtonText())) {
            addToShopping();

        } else if (sourceButtonText.equals(orderView.getDeleteFoodButtonText())) {
            deleteSelectedFoods();

        } else if (sourceButtonText.equals(orderView.getSureOrderButtonText())) {
            sureOrder();
        }
    }

    private void sureOrder() {
        if (orderView.getAlreadyRowData().size() <= 0) {
            JOptionPane.showMessageDialog(orderView, "请至少选择一项菜品下单", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String customerTel = orderView.getCustomerTelTextField().getText();
            Integer customerNumber = Integer.valueOf(orderView.getCustomerNumberTextField().getText());
            Integer selectedDeskSeat = Integer.valueOf(String.valueOf(orderView.getEmptySeatCheckBox().getSelectedItem()));
            if (CheckUtil.isEmpty(customerTel, String.valueOf(customerNumber))) {
                JOptionPane.showMessageDialog(orderView, "请正确填写手机号和就餐人数", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (selectedDeskSeat < customerNumber) {
                JOptionPane.showMessageDialog(orderView, "请重新选择合适的餐桌", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(orderView, "情检查客户信息是否正确", "错误", JOptionPane.ERROR_MESSAGE);
        }

        // 生成订单编号， 将点单菜品传入数据库;
        Services services = new ServicesImpl();
        Order order = new Order();
        order.setOrderNo(services.getOrderNumber());
        order.setDeskId(Integer.valueOf(String.valueOf(orderView.getEmptySeatCheckBox().getSelectedItem())));
        order.setCreateTime(new Date());
        order.setStatus("未支付");
        order.setNumber(Integer.valueOf(orderView.getCustomerNumberTextField().getText()));

            int orderCount = orderView.getAlreadyAddFoodTable().getRowCount();
        double sumMoney = 0.0;
        for (int i = 0; i < orderCount; i++) {
            Double money = Double.valueOf(String.valueOf(orderView.getAlreadyAddFoodTable().getValueAt(i, 2)));
            Integer number = Integer.valueOf(String.valueOf(orderView.getAlreadyAddFoodTable().getValueAt(i, 3)));
            sumMoney += money * number;
        }
        order.setMoney(sumMoney);


        services = new ServicesImpl();
        Vector<Vector<Object>> customerData = services.getCustomerData("", "", orderView.getCustomerTelTextField().getText());
        if (customerData.size() <= 0) {
            order.setCustomerId(-1); // 表示客户表无他
        } else {
            order.setCustomerId(Integer.valueOf(String.valueOf(customerData.get(0).get(0))));
        }

        int flag = services.addOneOrder(order);
        if (flag > 0) {
            Desk desk = new Desk(); //修改餐桌状态
            desk.setId(Integer.valueOf(String.valueOf(orderView.getEmptySeatCheckBox().getSelectedItem())));
            desk.setStatus("忙碌");
            int statusFlag = services.mergeOneDeskStatus(desk);
            if (statusFlag > 0) {
                //餐桌锁定完成 开始插入订单明细项
                // 获取所以表单项
                ArrayList<OrderItem> orderItemList = new ArrayList<>();
                for (int i = 0; i < orderCount; i++) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(order.getOrderNo());
                    orderItem.setFoodId(Integer.valueOf(String.valueOf(orderView.getAlreadyAddFoodTable().getValueAt(i, 0))));
                    orderItem.setCnt(Integer.valueOf(String.valueOf(orderView.getAlreadyAddFoodTable().getValueAt(i, 3))));
                    orderItemList.add(orderItem);
                }
                //插入
                int count = services.insertMoreOrderItem(orderItemList);
                if (count < orderItemList.size()) {
                    JOptionPane.showMessageDialog(orderView, "下单失败，请重新下单", "失败", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(orderView, "下单成功，请用餐", "成功", JOptionPane.INFORMATION_MESSAGE);
                    clearAll();
                }
            } else {
                JOptionPane.showMessageDialog(orderView, "下单失败，请重新选择餐桌", "失败", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(orderView, "下单失败，请重新下单", "失败", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedFoods() {
        int[] selectedRows = orderView.getAlreadyAddFoodTable().getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(orderView, "请先选中行", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //确认是否要添加菜品分类
        int option = JOptionPane.showConfirmDialog(orderView,
                "请确认是否删除购物车选择的菜品信息?", "确认信息",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {

            ArrayList<String> ids = new ArrayList<>();
            for (int selectedRow : selectedRows) {
                String id = String.valueOf(orderView.getAlreadyRowData().get(selectedRow).get(0));
                ids.add(id);
            }

            for (String id : ids) {
                Iterator<Vector<Object>> iterator = orderView.getAlreadyRowData().iterator();
                while (iterator.hasNext()) {
                    String current = String.valueOf(iterator.next().get(0));
                    if (current.equals(id)) {
                        iterator.remove();
                        break;
                    }
                }
            }
            orderView.reloadAlreadyTableData(orderView.getAlreadyRowData());
            JOptionPane.showMessageDialog(orderView, "删除成功", "消息", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addToShopping() {
        try {
            if (Integer.parseInt(orderView.getOneOrderFoodNumberTextField().getText()) == 0) {
                JOptionPane.showMessageDialog(orderView, "请正确填写下单数量", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(orderView, "请正确填写下单数量", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String foodId = orderView.getFoodIdTextFieldAtMinute().getText();
        String foodName = orderView.getFoodNameTextFieldAtMinute().getText();
        String foodPrice = orderView.getFoodPriceTextFieldAtMinute().getText();
        String foodNumber = orderView.getOneOrderFoodNumberTextField().getText();

        if (CheckUtil.isEmpty(foodId, foodName, foodPrice)) {
            JOptionPane.showMessageDialog(orderView, "请先选择选择要加入购物车的菜品", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (Vector<Object> data : orderView.getAlreadyRowData()) {
            if (String.valueOf(data.get(0)).equals(foodId)) {
                String updateNumber = String.valueOf(Integer.parseInt(String.valueOf(data.get(3))) +
                        Integer.parseInt(String.valueOf(foodNumber)));
                data.set(3, updateNumber);
                orderView.reloadAlreadyTableData(orderView.getAlreadyRowData());
                return;
            }
        }
        Vector<Object> selectedRowData = new Vector<>();
        selectedRowData.add(foodId);
        selectedRowData.add(foodName);
        selectedRowData.add(foodPrice);
        selectedRowData.add(foodNumber);
        orderView.getAlreadyRowData().add(selectedRowData);
        orderView.reloadAlreadyTableData(orderView.getAlreadyRowData());
    }

    private void boosRecommendFood() {
        Services services = new ServicesImpl();
        Vector<Vector<Object>> returnData = services.getBossRecommendFoodData();
        orderView.reloadSearchTableData(returnData);
        JOptionPane.showMessageDialog(orderView, "老板为你精心挑选了本店热销的菜品，赶紧看看吧！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchFoodsBycustomer() {
        Services services = new ServicesImpl();
        Food food = new Food();
        food.setName(orderView.getSearchFoodNameTextField().getText());
        String selectedCategoryName = String.valueOf(orderView.getSearchCategoryCheckBox().getSelectedItem());
        if ("".equals(selectedCategoryName)) {
            food.setCategoryId(null);
            System.out.println(food.getCategoryId());
        } else {
            Integer categoryId = services.getCategoryIdByName(selectedCategoryName);
            food.setCategoryId(categoryId);
        }
        Vector<Vector<Object>> returnData = services.getCustomerSelectedSearchFoodData(food);
        orderView.reloadSearchTableData(returnData);
    }

    private void searchEmptySeat() {
        Services services = new ServicesImpl();
        try {
            String[] emptySeatData = services.getEmptySeatGreaterThanNumber(Integer.valueOf(orderView.getCustomerNumberTextField().getText()));
            if (emptySeatData.length == 0) {
                JOptionPane.showMessageDialog(orderView, "暂无满足条件的餐桌", "警告", JOptionPane.WARNING_MESSAGE);
            } else {
                orderView.getEmptySeatCheckBox().setModel(new DefaultComboBoxModel(emptySeatData));
            }

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(orderView, "请输入就餐人数", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearAll() {
        orderView.getCustomerTelTextField().setText("");
        orderView.getCustomerNumberTextField().setText("");
        orderView.getSearchFoodNameTextField().setText("");

        orderView.reloadSearchTableData(new Vector<>());
        orderView.reloadAlreadyTableData(new Vector<>());

        orderView.getFoodIdTextFieldAtMinute().setText("");
        orderView.getFoodNameTextFieldAtMinute().setText("");
        orderView.getFoodPriceTextFieldAtMinute().setText("");
        orderView.getFoodSumTextFieldAtMinute().setText("");
        orderView.getOneOrderFoodNumberTextField().setText("");
        orderView.getDescriptionTextArea().setText("");
        orderView.getFoodPhotoLabel().setIcon(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int selectedRow = orderView.getSearchViewTable().getSelectedRow();
            orderView.getFoodIdTextFieldAtMinute()
                    .setText(String.valueOf(orderView.getSearchViewTable().getValueAt(selectedRow, 0)));
            orderView.getFoodNameTextFieldAtMinute()
                    .setText(String.valueOf(orderView.getSearchViewTable().getValueAt(selectedRow, 1)));
            orderView.getFoodPriceTextFieldAtMinute()
                    .setText(String.valueOf(orderView.getSearchViewTable().getValueAt(selectedRow, 2)));
            orderView.getDescriptionTextArea()
                    .setText(String.valueOf(orderView.getSearchViewTable().getValueAt(selectedRow, 3)));
            orderView.getFoodSumTextFieldAtMinute()
                    .setText(String.valueOf(orderView.getSearchViewTable().getValueAt(selectedRow, 4)));
            orderView.getOneOrderFoodNumberTextField()
                    .setText("1"); //默认为1

            Services services = new ServicesImpl();
            Integer foodId = (Integer) orderView.getSearchViewTable().getValueAt(selectedRow, 0);
            String foodIconPath = services.getFoodIconPathById(foodId);

            File file = new File(foodIconPath);
            if (!file.exists()) {
                // 不知道为什么 这里不能填相对路径img/404.png， 神奇啊
                // 好了 解决了
                orderView.getFoodPhotoLabel().setIcon(new ImageIcon("src/main/resources/img/404.png"));
            } else {
                orderView.getFoodPhotoLabel().setIcon(new ImageIcon(foodIconPath));
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
