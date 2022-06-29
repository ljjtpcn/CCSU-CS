package cn.twopair.view;

import cn.twopair.handler.AccountsViewHandler;
import cn.twopair.handler.special.MoneyHandler;
import cn.twopair.handler.special.NumberHandler;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

/**
 * @author ljjtpcn
 */
@Getter
@Setter
public class AccountsView extends JDialog {

    private JPanel root;
    private JTextField orderIdTextField;
    private JTextField deskIdTextField;
    private JTextField shouldPayTextField;
    private JTextField rateMoneyTextField;
    private JTextField theAmountActuallyPaidTextField;
    private JTextField orderIdTextFieldDown;
    private JTextField changeMoneyTexField;
    private JTable searchViewTable;
    private JTable orderItemTable;
    private MyTableModel searchTableModel;
    private MyTableModel orderItemTableModel;
    private Vector<Vector<Object>> searchRowData;
    private Vector<Vector<Object>> orderItemRowData;
    private Vector<Object> searchColumnNames;
    private Vector<Object> orderItemColumnNames;
    private JComboBox statusPayCheckBox;
    private AccountsViewHandler accountViewHandler;
    private MoneyHandler moneyHandler;
    private final String searchOrderButtonText = "查询";
    private final String sureButtonText = "结账";
    /**
     * Create the frame.
     */
    public AccountsView(MenuView menuView) {
        super(menuView, "订单管理", true);
        setBounds(100, 100, 1292, 742);
        root = new JPanel();
        root.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(root);
        root.setLayout(null);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        searchPanel.setBounds(0, 0, 1292, 107);
        root.add(searchPanel);

        JLabel orderIdLabel = new JLabel("订单编号:");
        orderIdLabel.setIcon(new ImageIcon("src/main/resources/img/编号.png"));
        orderIdLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        orderIdLabel.setBounds(183, 44, 112, 28);
        searchPanel.add(orderIdLabel);

        orderIdTextField = new JTextField();
        orderIdTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        orderIdTextField.setColumns(10);
        orderIdTextField.setBounds(313, 45, 123, 28);
        searchPanel.add(orderIdTextField);

        JLabel deskIdLabel = new JLabel("餐桌编号:");
        deskIdLabel.setIcon(new ImageIcon("src/main/resources/img/餐桌.png"));
        deskIdLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        deskIdLabel.setBounds(442, 44, 132, 28);
        searchPanel.add(deskIdLabel);

        deskIdTextField = new JTextField();
        deskIdTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        deskIdTextField.setColumns(10);
        deskIdTextField.setBounds(579, 45, 123, 28);
        searchPanel.add(deskIdTextField);

        JLabel statusPayLabel = new JLabel("支付状态:");
        statusPayLabel.setIcon(new ImageIcon("src/main/resources/img/未知状态.png"));
        statusPayLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        statusPayLabel.setBounds(720, 42, 112, 32);
        searchPanel.add(statusPayLabel);

        statusPayCheckBox = new JComboBox();
        statusPayCheckBox.setModel(new DefaultComboBoxModel(new String[]{"", "未支付", "已支付"}));
        statusPayCheckBox.setSelectedIndex(0);
        statusPayCheckBox.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        statusPayCheckBox.setBounds(850, 45, 100, 26);
        searchPanel.add(statusPayCheckBox);

        JButton searchButton = new JButton(searchOrderButtonText);
        searchButton.setIcon(new ImageIcon("src/main/resources/img/查询.png"));
        searchButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchButton.setContentAreaFilled(false);
        searchButton.setBounds(978, 45, 107, 27);
        searchPanel.add(searchButton);

        JPanel orderViewPanel = new JPanel();
        orderViewPanel.setLayout(null);
        orderViewPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u8BA2\u5355\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        orderViewPanel.setBounds(0, 119, 1292, 169);
        root.add(orderViewPanel);

        // 表格所有行数据(默认空)
        searchRowData = new Vector<>();
        // 创建一个表格，指定 表头 和 所有行数据
        searchColumnNames = new Vector<>();
        searchColumnNames.add("订单编号");
        searchColumnNames.add("餐桌编号");
        searchColumnNames.add("下单时间");
        searchColumnNames.add("应付金额");
        searchColumnNames.add("客户编号");
        searchColumnNames.add("订单状态");
        searchColumnNames.add("用餐人数");
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

        orderViewPanel.setLayout(null);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        searchViewTable.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane searchScrollPane = new JScrollPane(searchViewTable);
        searchScrollPane.setBounds(76, 12, 1151, 157);
        orderViewPanel.add(searchScrollPane);

        JPanel orderItemViewPanel = new JPanel();
        orderItemViewPanel.setLayout(null);
        orderItemViewPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u8BA2\u5355\u7EC6\u5219\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        orderItemViewPanel.setBounds(0, 300, 1292, 225);
        root.add(orderItemViewPanel);

        // 表格所有行数据(默认空)
        orderItemRowData = new Vector<>();

        orderItemColumnNames = new Vector<>();
        orderItemColumnNames.add("序号");
        orderItemColumnNames.add("订单编号");
        orderItemColumnNames.add("菜品名称");
        orderItemColumnNames.add("菜品单价");
        orderItemColumnNames.add("下单数量");
        orderItemTableModel = new MyTableModel();
        orderItemTableModel.setDataVector(orderItemRowData, orderItemColumnNames);
        orderItemTable = new JTable(orderItemTableModel);
        // 设置表格内容颜色
        orderItemTable.setForeground(Color.BLACK);                   // 字体颜色
        orderItemTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));      // 字体样式
        orderItemTable.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        orderItemTable.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        orderItemTable.setGridColor(Color.GRAY);                     // 网格颜色
        orderItemTable.setDefaultRenderer(Object.class, new MyCellRender());       // 内容居中显示
        // 设置表头
        orderItemTable.getTableHeader().setFont(new Font("楷体", Font.BOLD, 14));  // 设置表头名称字体样式
        orderItemTable.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
//        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        orderItemTable.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        orderItemTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);                            // 设置不允许双击修改

        // 设置行高
        orderItemTable.setRowHeight(30);
        orderItemViewPanel.setLayout(null);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        orderItemTable.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane orderItemScrollPane = new JScrollPane(orderItemTable);
        orderItemScrollPane.setBounds(76, 12, 1151, 213);
        orderItemViewPanel.add(orderItemScrollPane);

        JLabel shouldPayLabel = new JLabel("应付金额:");
        shouldPayLabel.setIcon(new ImageIcon("src/main/resources/img/待收金额.png"));
        shouldPayLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        shouldPayLabel.setBounds(76, 576, 112, 28);
        root.add(shouldPayLabel);

        shouldPayTextField = new JTextField();
        shouldPayTextField.setEditable(false);
        shouldPayTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        shouldPayTextField.setColumns(10);
        shouldPayTextField.setBounds(192, 577, 123, 28);
        root.add(shouldPayTextField);

        JLabel rateMoneyLabel = new JLabel("优惠金额:");
        rateMoneyLabel.setIcon(new ImageIcon("src/main/resources/img/优惠.png"));
        rateMoneyLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        rateMoneyLabel.setBounds(343, 576, 112, 28);
        root.add(rateMoneyLabel);

        rateMoneyTextField = new JTextField();
        rateMoneyTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        rateMoneyTextField.setColumns(10);
        rateMoneyTextField.setBounds(467, 577, 123, 28);
        root.add(rateMoneyTextField);

        JLabel theAmountActuallyPaidLabel = new JLabel("实付金额:");
        theAmountActuallyPaidLabel.setIcon(new ImageIcon("src/main/resources/img/实付金额.png"));
        theAmountActuallyPaidLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        theAmountActuallyPaidLabel.setBounds(608, 576, 112, 28);
        root.add(theAmountActuallyPaidLabel);

        theAmountActuallyPaidTextField = new JTextField();
        theAmountActuallyPaidTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        theAmountActuallyPaidTextField.setColumns(10);
        theAmountActuallyPaidTextField.setBounds(723, 577, 123, 28);
        root.add(theAmountActuallyPaidTextField);

        JLabel changeMoneyLabel = new JLabel("找零金额:");
        changeMoneyLabel.setIcon(new ImageIcon("src/main/resources/img/找零金钱.png"));
        changeMoneyLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        changeMoneyLabel.setBounds(864, 576, 112, 28);
        root.add(changeMoneyLabel);

        changeMoneyTexField = new JTextField();
        changeMoneyTexField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        changeMoneyTexField.setEditable(false);
        changeMoneyTexField.setColumns(10);
        changeMoneyTexField.setBounds(994, 577, 123, 28);
        root.add(changeMoneyTexField);

        JLabel orderIdLabelDown = new JLabel("订单编号:");
        orderIdLabelDown.setIcon(new ImageIcon("src/main/resources/img/编号.png"));
        orderIdLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        orderIdLabelDown.setBounds(317, 658, 112, 28);
        root.add(orderIdLabelDown);

        orderIdTextFieldDown = new JTextField();
        orderIdTextFieldDown.setEditable(false);
        orderIdTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        orderIdTextFieldDown.setColumns(10);
        orderIdTextFieldDown.setBounds(434, 659, 208, 28);
        root.add(orderIdTextFieldDown);

        JButton sureButton = new JButton(sureButtonText);
        sureButton.setIcon(new ImageIcon("src/main/resources/img/结账.png"));
        sureButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        sureButton.setContentAreaFilled(false);
        sureButton.setBounds(654, 654, 131, 37);
        root.add(sureButton);

        accountViewHandler = new AccountsViewHandler(this);
        searchButton.addActionListener(accountViewHandler);
        sureButton.addActionListener(accountViewHandler);
        moneyHandler = new MoneyHandler();
        rateMoneyTextField.addKeyListener(moneyHandler);
        theAmountActuallyPaidTextField.addKeyListener(moneyHandler);
        searchViewTable.addMouseListener(accountViewHandler);
        deskIdTextField.addKeyListener(new NumberHandler());
        setLocationRelativeTo(null);

    }

    public void reloadOrderItemTableData(Vector<Vector<Object>> data) {
        orderItemTableModel.setDataVector(data, orderItemColumnNames);
    }

    public void reloadSearchTableData(Vector<Vector<Object>> data) {
        searchTableModel.setDataVector(data, searchColumnNames);
    }

}
