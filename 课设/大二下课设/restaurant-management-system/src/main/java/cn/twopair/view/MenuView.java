package cn.twopair.view;

import cn.twopair.handler.MenuViewHandler;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 * @author ljjtpcn
 */
@Getter
@Setter
public class MenuView extends JFrame {

    private JPanel root;

    /**
     * Create the frame.
     */

    MenuViewHandler menuViewHandler;
    private final String systemManagerText = "系统管理";
    private final String employeeManagerText = "员工管理";
    private final String customerManagerText = "客户管理";
    private final String sellStatisticsText = "销售统计";
    private final String desktopManagerText = "餐台管理";
    private final String categoryManagerText = "菜品分类管理";
    private final String foodManagerText = "菜品管理";
    private final String orderText = "点单";
    private final String accountsText = "结账";

    public MenuView() {
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("/home/ljjtpcn/Desktop/课设/restaurant_management_system/src/main/resources/img/food.png"));
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 961, 590);
        root = new JPanel();
        root.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(root);
        root.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setFont(new Font("微软雅黑", Font.BOLD, 14));
        menuBar.setBounds(0, 0, 974, 40);
        root.add(menuBar);

        JMenu systemMenu = new JMenu(systemManagerText);
        systemMenu.setIcon(new ImageIcon("src/main/resources/img/系统.png"));
        systemMenu.setHorizontalAlignment(SwingConstants.CENTER);
        systemMenu.setFont(new Font("微软雅黑", Font.BOLD, 20));
        menuBar.add(systemMenu);

        JMenuItem employeeItem = new JMenuItem(employeeManagerText);
        employeeItem.setIcon(new ImageIcon("src/main/resources/img/员工管理.png"));
        employeeItem.setBackground(Color.WHITE);
        employeeItem.setHorizontalAlignment(SwingConstants.CENTER);
        employeeItem.setFont(new Font("微软雅黑", Font.BOLD, 18));
        systemMenu.add(employeeItem);

        JMenuItem customerItem = new JMenuItem(customerManagerText);
        customerItem.setIcon(new ImageIcon("src/main/resources/img/客户管理.png"));
        customerItem.setBackground(Color.WHITE);
        customerItem.setHorizontalAlignment(SwingConstants.CENTER);
        customerItem.setFont(new Font("微软雅黑", Font.BOLD, 18));
        systemMenu.add(customerItem);

        JMenuItem sellStatisticsItem = new JMenuItem(sellStatisticsText);
        sellStatisticsItem.setIcon(new ImageIcon("src/main/resources/img/统计.png"));
        sellStatisticsItem.setBackground(Color.WHITE);
        sellStatisticsItem.setHorizontalAlignment(SwingConstants.CENTER);
        sellStatisticsItem.setFont(new Font("微软雅黑", Font.BOLD, 18));
        systemMenu.add(sellStatisticsItem);

        JMenuItem deskItem = new JMenuItem(desktopManagerText);
        deskItem.setIcon(new ImageIcon("src/main/resources/img/餐桌.png"));
        deskItem.setHorizontalAlignment(SwingConstants.LEFT);
        deskItem.setBackground(Color.WHITE);
        deskItem.setFont(new Font("微软雅黑", Font.BOLD, 20));
        menuBar.add(deskItem);

        JMenuItem categoryItem = new JMenuItem(categoryManagerText);
        categoryItem.setIcon(new ImageIcon("src/main/resources/img/分类.png"));
        categoryItem.setHorizontalAlignment(SwingConstants.CENTER);
        categoryItem.setBackground(Color.WHITE);
        categoryItem.setFont(new Font("微软雅黑", Font.BOLD, 20));
        menuBar.add(categoryItem);

        JMenuItem foodItem = new JMenuItem(foodManagerText);
        foodItem.setIcon(new ImageIcon("src/main/resources/img/菜品计划.png"));
        foodItem.setHorizontalAlignment(SwingConstants.CENTER);
        foodItem.setBackground(Color.WHITE);
        foodItem.setFont(new Font("微软雅黑", Font.BOLD, 20));
        menuBar.add(foodItem);

        JMenuItem orderItem = new JMenuItem(orderText);
        orderItem.setIcon(new ImageIcon("src/main/resources/img/点单.png"));
        orderItem.setHorizontalAlignment(SwingConstants.CENTER);
        orderItem.setBackground(Color.WHITE);
        orderItem.setFont(new Font("微软雅黑", Font.BOLD, 20));
        menuBar.add(orderItem);

        JMenuItem accountItem = new JMenuItem(accountsText);
        accountItem.setIcon(new ImageIcon("src/main/resources/img/收银结账.png"));
        accountItem.setHorizontalAlignment(SwingConstants.CENTER);
        accountItem.setBackground(Color.WHITE);
        accountItem.setFont(new Font("微软雅黑", Font.BOLD, 20));
        menuBar.add(accountItem);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("src/main/resources/img/background.png"));
        lblNewLabel.setBounds(0, 24, 961, 551);
        root.add(lblNewLabel);

        URL imgLogo = LoginView.class.getClassLoader().getResource("img/logo.png");
        assert imgLogo != null;
        Image image = new ImageIcon(imgLogo).getImage();
        setIconImage(image);

        menuViewHandler = new MenuViewHandler(this);
        employeeItem.addActionListener(menuViewHandler);
        customerItem.addActionListener(menuViewHandler);
        deskItem.addActionListener(menuViewHandler);
        categoryItem.addActionListener(menuViewHandler);
        foodItem.addActionListener(menuViewHandler);
        orderItem.addActionListener(menuViewHandler);
        accountItem.addActionListener(menuViewHandler);
        sellStatisticsItem.addActionListener(menuViewHandler);

        setLocationRelativeTo(null);

    }
}
class MyTableModel extends DefaultTableModel {
    // 禁止编辑表格
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}


/**
 * @author 李佳骏 ljjtpcn@163.com
 * @date [2021/12/12 9:16]
 * @description TODO 自定义表格渲染方式
 */
class MyCellRender extends DefaultTableCellRenderer {
    /**
     * 在每一行的每一列显示之前都会调用
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int styleNumber = 2;
        if (row % styleNumber == 0) {
            setBackground(Color.CYAN);
        } else {
            setBackground(Color.WHITE);
        }

        //内容居中
        setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}