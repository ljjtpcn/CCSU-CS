package cn.twopair.view;

import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import cn.twopair.handler.OrderViewHandler;
import cn.twopair.handler.special.NumberHandler;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

@Getter
@Setter
public class OrderView extends JDialog {

    private JPanel root;
    private JTextField searchFoodNameTextField;
    private JTextField customerTelTextField;
    private JTextField customerNumberTextField;
    private JTextField foodIdTextFieldAtMinute;
    private JTextField foodNameTextFieldAtMinute;
    private JTextField foodPriceTextFieldAtMinute;
    private JTextField foodSumTextFieldAtMinute;
    private JTextField oneOrderFoodNumberTextField;

    private final String searchEmptySeatButtonText = "查询空座";
    private final String searchFoodButtonText = "查询";
    private final String searchBossButtonText = "老板推荐";
    private final String shoppingButtonText = "加入购物车";
    private final String deleteFoodButtonText = "删除选中菜品";
    private final String sureOrderButtonText = "确认下单";


    private JComboBox emptySeatCheckBox;
    private JComboBox searchCategoryCheckBox;
    private JTextArea descriptionTextArea;
    private JLabel foodPhotoLabel;
    private JTable searchViewTable;
    private JTable alreadyAddFoodTable;
    private MyTableModel searchTableModel;
    private MyTableModel alreadyTableModel;
    private Vector<Vector<Object>> searchRowData;
    private Vector<Vector<Object>> alreadyRowData;
    private Vector<Object> searchColumnNames;
    private Vector<Object> alreadyColumnNames;
    private OrderViewHandler orderViewHandler;



    /**
     * Create the frame.
     */
    public OrderView(MenuView menuView) {
        super(menuView, "点单", true);
        setBounds(0, 0, 1292, 1050);
        root = new JPanel();
        root.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(root);
        root.setLayout(null);

        JPanel InfoPanel = new JPanel();
        InfoPanel.setLayout(null);
        InfoPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u5C31\u9910\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        InfoPanel.setBounds(0, 0, 1292, 107);
        root.add(InfoPanel);

        JLabel customerTelLabel = new JLabel("客户手机号:");
        customerTelLabel.setBounds(214, 39, 136, 39);
        InfoPanel.add(customerTelLabel);
        customerTelLabel.setIcon(new ImageIcon("src/main/resources/img/手机号.png"));
        customerTelLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));

        customerTelTextField = new JTextField();
        customerTelTextField.setBounds(368, 45, 123, 28);
        InfoPanel.add(customerTelTextField);
        customerTelTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        customerTelTextField.setColumns(10);

        JLabel customerNumberLabel = new JLabel("就餐人数:");
        customerNumberLabel.setBounds(509, 44, 115, 28);
        InfoPanel.add(customerNumberLabel);
        customerNumberLabel.setIcon(new ImageIcon("src/main/resources/img/座位数量.png"));
        customerNumberLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));

        customerNumberTextField = new JTextField();
        customerNumberTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        customerNumberTextField.setColumns(10);
        customerNumberTextField.setBounds(642, 45, 123, 28);
        InfoPanel.add(customerNumberTextField);

        JButton searchEmptySeatButton = new JButton(searchEmptySeatButtonText);
        searchEmptySeatButton.setIcon(new ImageIcon("src/main/resources/img/查询.png"));
        searchEmptySeatButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchEmptySeatButton.setContentAreaFilled(false);
        searchEmptySeatButton.setBounds(777, 44, 136, 28);
        InfoPanel.add(searchEmptySeatButton);

        emptySeatCheckBox = new JComboBox();
        emptySeatCheckBox.setModel(new DefaultComboBoxModel(new String[]{""}));
        emptySeatCheckBox.setSelectedIndex(0);
        emptySeatCheckBox.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        emptySeatCheckBox.setBounds(942, 45, 107, 26);
        InfoPanel.add(emptySeatCheckBox);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u67E5\u8BE2\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        searchPanel.setBounds(0, 113, 1292, 107);
        root.add(searchPanel);

        JLabel searchFoodNameLabel = new JLabel("菜品名:");
        searchFoodNameLabel.setIcon(new ImageIcon("src/main/resources/img/菜品分类名称.png"));
        searchFoodNameLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchFoodNameLabel.setBounds(254, 44, 96, 28);
        searchPanel.add(searchFoodNameLabel);

        searchFoodNameTextField = new JTextField();
        searchFoodNameTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        searchFoodNameTextField.setColumns(10);
        searchFoodNameTextField.setBounds(368, 45, 123, 28);
        searchPanel.add(searchFoodNameTextField);

        JLabel searchCategoryLabel = new JLabel("类别:");
        searchCategoryLabel.setIcon(new ImageIcon("src/main/resources/img/分类.png"));
        searchCategoryLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchCategoryLabel.setBounds(509, 42, 74, 32);
        searchPanel.add(searchCategoryLabel);

        searchCategoryCheckBox = new JComboBox();
        searchCategoryCheckBox.setModel(new DefaultComboBoxModel(new String[]{""}));
        String[] allCategoryName = getAllCategoryName();
        for (int i = 0; i < allCategoryName.length; i++) {
            searchCategoryCheckBox.addItem(allCategoryName[i]);
        }
        searchCategoryCheckBox.setSelectedIndex(0);
        searchCategoryCheckBox.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchCategoryCheckBox.setBounds(601, 45, 107, 26);
        searchPanel.add(searchCategoryCheckBox);

        JButton searchFoodButton = new JButton(searchFoodButtonText);
        searchFoodButton.setIcon(new ImageIcon("src/main/resources/img/查询.png"));
        searchFoodButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchFoodButton.setContentAreaFilled(false);
        searchFoodButton.setBounds(720, 45, 107, 27);
        searchPanel.add(searchFoodButton);

        JButton searchBossButton = new JButton(searchBossButtonText);
        searchBossButton.setIcon(new ImageIcon("src/main/resources/img/推荐.png"));
        searchBossButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchBossButton.setContentAreaFilled(false);
        searchBossButton.setBounds(839, 43, 146, 30);
        searchPanel.add(searchBossButton);

        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(null);
        viewPanel.setBorder(new TitledBorder(null, "\u663E\u793A\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        viewPanel.setBounds(0, 232, 1292, 185);
        root.add(viewPanel);


        // 表格所有行数据(默认空)
        searchRowData = new Vector<>();
        // 创建一个表格，指定 表头 和 所有行数据
        searchColumnNames = new Vector<>();
        searchColumnNames.add("菜品编号");
        searchColumnNames.add("菜品名称");
        searchColumnNames.add("菜品价格");
        searchColumnNames.add("菜品描述");
        searchColumnNames.add("销量");
        searchTableModel = new MyTableModel();
        searchTableModel.setDataVector(searchRowData, searchColumnNames);
        searchViewTable = new JTable(searchTableModel);
        // 设置表格内容颜色
        searchViewTable.setForeground(Color.BLACK);                   // 字体颜色
        searchViewTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));      // 字体样式
        searchViewTable.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        searchViewTable.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        searchViewTable.setGridColor(Color.GRAY);                     // 网格颜色
        searchViewTable.setDefaultRenderer(Object.class, new MyCellRender());       // 内容居中显示
        // 设置表头
        searchViewTable.getTableHeader().setFont(new Font("楷体", Font.BOLD, 14));  // 设置表头名称字体样式
        searchViewTable.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
//        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        searchViewTable.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        searchViewTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);                            // 设置不允许双击修改
        // 设置行高
        searchViewTable.setRowHeight(30);

        viewPanel.setLayout(null);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        searchViewTable.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane searchScrollPane = new JScrollPane(searchViewTable);
        searchScrollPane.setBounds(76, 12, 1151, 173);
        viewPanel.add(searchScrollPane);

        JPanel minuteInfoPanel = new JPanel();
        minuteInfoPanel.setLayout(null);
        minuteInfoPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u8BE6\u7EC6\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        minuteInfoPanel.setBounds(0, 429, 1292, 274);
        root.add(minuteInfoPanel);

        JLabel foodIdLabelAtMinute = new JLabel("菜品编号:");
        foodIdLabelAtMinute.setIcon(new ImageIcon("src/main/resources/img/餐台编号.png"));
        foodIdLabelAtMinute.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodIdLabelAtMinute.setBounds(34, 40, 107, 28);
        minuteInfoPanel.add(foodIdLabelAtMinute);

        foodIdTextFieldAtMinute = new JTextField();
        foodIdTextFieldAtMinute.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodIdTextFieldAtMinute.setEditable(false);
        foodIdTextFieldAtMinute.setColumns(10);
        foodIdTextFieldAtMinute.setBounds(159, 41, 107, 28);
        minuteInfoPanel.add(foodIdTextFieldAtMinute);

        JLabel foodNameLabelAtMinute = new JLabel("菜品名称:");
        foodNameLabelAtMinute.setIcon(new ImageIcon("src/main/resources/img/菜品分类名称.png"));
        foodNameLabelAtMinute.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodNameLabelAtMinute.setBounds(284, 40, 112, 28);
        minuteInfoPanel.add(foodNameLabelAtMinute);

        foodNameTextFieldAtMinute = new JTextField();
        foodNameTextFieldAtMinute.setEditable(false);
        foodNameTextFieldAtMinute.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodNameTextFieldAtMinute.setColumns(10);
        foodNameTextFieldAtMinute.setBounds(414, 41, 123, 28);
        minuteInfoPanel.add(foodNameTextFieldAtMinute);

        JLabel foodPriceLabelAtMinute = new JLabel("菜品价格:");
        foodPriceLabelAtMinute.setIcon(new ImageIcon("src/main/resources/img/价格.png"));
        foodPriceLabelAtMinute.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodPriceLabelAtMinute.setBounds(34, 100, 107, 28);
        minuteInfoPanel.add(foodPriceLabelAtMinute);

        foodPriceTextFieldAtMinute = new JTextField();
        foodPriceTextFieldAtMinute.setEditable(false);
        foodPriceTextFieldAtMinute.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodPriceTextFieldAtMinute.setColumns(10);
        foodPriceTextFieldAtMinute.setBounds(159, 101, 107, 28);
        minuteInfoPanel.add(foodPriceTextFieldAtMinute);

        JLabel foodSumLabelAtMinute = new JLabel("菜品销量:");
        foodSumLabelAtMinute.setIcon(new ImageIcon("src/main/resources/img/菜品销量.png"));
        foodSumLabelAtMinute.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodSumLabelAtMinute.setBounds(284, 100, 112, 28);
        minuteInfoPanel.add(foodSumLabelAtMinute);

        foodSumTextFieldAtMinute = new JTextField();
        foodSumTextFieldAtMinute.setEditable(false);
        foodSumTextFieldAtMinute.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodSumTextFieldAtMinute.setColumns(10);
        foodSumTextFieldAtMinute.setBounds(414, 101, 123, 28);
        minuteInfoPanel.add(foodSumTextFieldAtMinute);

        JButton shoppingButton = new JButton(shoppingButtonText);
        shoppingButton.setIcon(new ImageIcon("src/main/resources/img/购物车.png"));
        shoppingButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        shoppingButton.setContentAreaFilled(false);
        shoppingButton.setBounds(344, 180, 193, 59);
        minuteInfoPanel.add(shoppingButton);

        JLabel oneOrderFoodNumberLabel = new JLabel("下单数量:");
        oneOrderFoodNumberLabel.setIcon(new ImageIcon("src/main/resources/img/已购数量.png"));
        oneOrderFoodNumberLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        oneOrderFoodNumberLabel.setBounds(34, 200, 112, 39);
        minuteInfoPanel.add(oneOrderFoodNumberLabel);

        oneOrderFoodNumberTextField = new JTextField();
        oneOrderFoodNumberTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        oneOrderFoodNumberTextField.setColumns(10);
        oneOrderFoodNumberTextField.setBounds(159, 200, 107, 39);
        minuteInfoPanel.add(oneOrderFoodNumberTextField);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        descriptionTextArea.setBounds(571, 40, 401, 200);
        minuteInfoPanel.add(descriptionTextArea);

        foodPhotoLabel = new JLabel();
        foodPhotoLabel.setBounds(1027, 40, 200, 200);
        minuteInfoPanel.add(foodPhotoLabel);

        JPanel alreadyAddFoodPanel = new JPanel();
        alreadyAddFoodPanel.setLayout(null);
        alreadyAddFoodPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u5DF2\u9009\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        alreadyAddFoodPanel.setBounds(0, 715, 1292, 185);
        root.add(alreadyAddFoodPanel);


        // 表格所有行数据(默认空)
        alreadyRowData = new Vector<>();

        alreadyColumnNames = new Vector<>();
        alreadyColumnNames.add("菜品编号");
        alreadyColumnNames.add("菜品名称");
        alreadyColumnNames.add("菜品价格");
        alreadyColumnNames.add("下单数量");
        alreadyTableModel = new MyTableModel();
        alreadyTableModel.setDataVector(alreadyRowData, alreadyColumnNames);
        alreadyAddFoodTable = new JTable(alreadyTableModel);
        // 设置表格内容颜色
        alreadyAddFoodTable.setForeground(Color.BLACK);                   // 字体颜色
        alreadyAddFoodTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));      // 字体样式
        alreadyAddFoodTable.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        alreadyAddFoodTable.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        alreadyAddFoodTable.setGridColor(Color.GRAY);                     // 网格颜色
        alreadyAddFoodTable.setDefaultRenderer(Object.class, new MyCellRender());       // 内容居中显示
        // 设置表头
        alreadyAddFoodTable.getTableHeader().setFont(new Font("楷体", Font.BOLD, 14));  // 设置表头名称字体样式
        alreadyAddFoodTable.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
//        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        alreadyAddFoodTable.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        alreadyAddFoodTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);                            // 设置不允许双击修改

        // 设置行高
        alreadyAddFoodTable.setRowHeight(30);
        viewPanel.setLayout(null);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        alreadyAddFoodTable.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane alreadyScrollPane = new JScrollPane(alreadyAddFoodTable);
        alreadyScrollPane.setBounds(76, 12, 1151, 173);
        alreadyAddFoodPanel.add(alreadyScrollPane);

        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(null);
        operationPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u64CD\u4F5C\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        operationPanel.setBounds(0, 920, 1292, 115);
        root.add(operationPanel);

        JButton deleteFoodButton = new JButton(deleteFoodButtonText);
        deleteFoodButton.setIcon(new ImageIcon("src/main/resources/img/删除(选餐).png"));
        deleteFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        deleteFoodButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        deleteFoodButton.setContentAreaFilled(false);
        deleteFoodButton.setBounds(255, 33, 202, 52);
        operationPanel.add(deleteFoodButton);

        JButton sureOrderButton = new JButton(sureOrderButtonText);
        sureOrderButton.setIcon(new ImageIcon("src/main/resources/img/工单确认.png"));
        sureOrderButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        sureOrderButton.setContentAreaFilled(false);
        sureOrderButton.setBounds(840, 32, 154, 52);
        operationPanel.add(sureOrderButton);


        //添加监听
        orderViewHandler = new OrderViewHandler(this);
        NumberHandler numberHandler = new NumberHandler();
        searchEmptySeatButton.addActionListener(orderViewHandler);
        searchFoodButton.addActionListener(orderViewHandler);
        searchBossButton.addActionListener(orderViewHandler);
        shoppingButton.addActionListener(orderViewHandler);
        deleteFoodButton.addActionListener(orderViewHandler);
        sureOrderButton.addActionListener(orderViewHandler);
        searchViewTable.addMouseListener(orderViewHandler);

        oneOrderFoodNumberTextField.addKeyListener(numberHandler);
        customerTelTextField.addKeyListener(numberHandler);
        customerNumberTextField.addKeyListener(numberHandler);
        setLocationRelativeTo(null);


    }

    public void reloadAlreadyTableData(Vector<Vector<Object>> data) {
        alreadyTableModel.setDataVector(data, alreadyColumnNames);
    }

    public void reloadSearchTableData(Vector<Vector<Object>> data) {
        searchTableModel.setDataVector(data, searchColumnNames);
    }

    public String[] getAllCategoryName() {
        Services services = new ServicesImpl();
        return services.getAllCategoryName();
    }
}
