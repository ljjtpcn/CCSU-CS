package cn.twopair.view;

/**
 * @ClassName FoodView
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/14 上午9:41
 * @Version 1.0
 **/


import cn.twopair.handler.FoodViewHandler;
import cn.twopair.handler.special.MoneyHandler;
import cn.twopair.handler.special.NumberHandler;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.Vector;

@Getter
@Setter
public class FoodView extends JDialog {

    private JPanel root;
    private JTextField foodIdTextField;
    private JTextField foodNameTextField;
    private JTextField foodIdTextFieldDown;
    private JTextField foodNameTextFieldDown;
    private JTextField categoryIdTextFieldDown;
    private JTextField foodPriceTextFieldDown;

    private MyTableModel myTableModel;
    private Vector<Vector<Object>> rowData;
    private Vector<Object> columnNames;
    private JTable table;
    private JTextArea descriptionTextArea;
    JTextArea fileAbsolutePathTextArea;
    private JComboBox statusCheckBoxDown;
    private JComboBox statusCheckBox;
    JLabel foodPhotoLabel;
    private JTextField foodSumTextFieldDown;
    FoodViewHandler foodViewHandler;

    private final String searchButtonText = "查询";
    private final String addButtonText = "添加菜品";
    private final String mergeButtonText = "修改菜品";
    private final String deleteButtonText = "删除菜品";
    private final String chooseFileButtonText = "选择图片";

    /**
     * Create the frame.
     */
    public FoodView(MenuView menuView) {
        super(menuView, "菜品管理", true);
        setBounds(100, 100, 1292, 747);
        root = new JPanel();
        root.setBorder(new TitledBorder(null, "\u83DC\u54C1\u7BA1\u7406", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setContentPane(root);
        root.setLayout(null);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        searchPanel.setBounds(0, 27, 1292, 107);
        root.add(searchPanel);

        JLabel foodIdLabel = new JLabel("菜品编号:");
        foodIdLabel.setIcon(new ImageIcon("src/main/resources/img/餐台编号.png"));
        foodIdLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodIdLabel.setBounds(183, 44, 112, 28);
        searchPanel.add(foodIdLabel);

        foodIdTextField = new JTextField();
        foodIdTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodIdTextField.setColumns(10);
        foodIdTextField.setBounds(313, 45, 123, 28);
        searchPanel.add(foodIdTextField);

        JLabel foodNameLabel = new JLabel("菜品名称:");
        foodNameLabel.setIcon(new ImageIcon("src/main/resources/img/菜品分类名称.png"));
        foodNameLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodNameLabel.setBounds(454, 44, 107, 28);
        searchPanel.add(foodNameLabel);

        foodNameTextField = new JTextField();
        foodNameTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodNameTextField.setColumns(10);
        foodNameTextField.setBounds(579, 45, 123, 28);
        searchPanel.add(foodNameTextField);

        JLabel statusLabel = new JLabel("状态:");
        statusLabel.setIcon(new ImageIcon("src/main/resources/img/状态.png"));
        statusLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        statusLabel.setBounds(720, 42, 74, 32);
        searchPanel.add(statusLabel);

        statusCheckBox = new JComboBox();
        statusCheckBox.setModel(new DefaultComboBoxModel(new String[]{"", "上架中", "已下架"}));
        statusCheckBox.setSelectedIndex(0);
        statusCheckBox.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        statusCheckBox.setBounds(812, 45, 84, 26);
        searchPanel.add(statusCheckBox);

        JButton searchButton = new JButton("查询");
        searchButton.setIcon(new ImageIcon("src/main/resources/img/查询.png"));
        searchButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchButton.setContentAreaFilled(false);
        searchButton.setBounds(978, 45, 107, 27);
        searchPanel.add(searchButton);

        // 表格所有行数据(默认空)
        rowData = new Vector<>();
        // 创建一个表格，指定 表头 和 所有行数据
        columnNames = new Vector<>();
        columnNames.add("菜品编号");
        columnNames.add("菜品名称");
        columnNames.add("菜品分类编号");
        columnNames.add("菜品价格");
        columnNames.add("菜品描述");
        columnNames.add("状态");
        columnNames.add("销量");

        myTableModel = new MyTableModel();
        myTableModel.setDataVector(rowData, columnNames);

        JPanel viewPanel = new JPanel();
        viewPanel.setBorder(new TitledBorder(null, "\u663E\u793A\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        viewPanel.setBounds(0, 174, 1292, 313);
        root.add(viewPanel);
        table = new JTable(myTableModel);
        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font("微软雅黑", Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色
        table.setDefaultRenderer(Object.class, new MyCellRender());       // 内容居中显示
        // 设置表头
        table.getTableHeader().setFont(new Font("楷体", Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
//        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);                            // 设置不允许双击修改

        // 设置行高
        table.setRowHeight(30);

        // 列宽设置
        table.getColumnModel().getColumn(0).setMaxWidth(90);
        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setMaxWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setMaxWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setMaxWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setMaxWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(90);
        table.getColumnModel().getColumn(6).setMaxWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        viewPanel.setLayout(null);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(76, 12, 1137, 301);
        viewPanel.add(scrollPane);

        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(null);
        operationPanel.setBorder(new TitledBorder(null, "\u64CD\u4F5C\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        operationPanel.setBounds(0, 499, 1292, 233);
        root.add(operationPanel);

        JLabel foodLabelDown = new JLabel("菜品编号:");
        foodLabelDown.setIcon(new ImageIcon("src/main/resources/img/餐台编号.png"));
        foodLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodLabelDown.setBounds(34, 21, 107, 28);
        operationPanel.add(foodLabelDown);

        foodIdTextFieldDown = new JTextField();
        foodIdTextFieldDown.setToolTipText("菜品编号为自动分配，不可指定");
        foodIdTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodIdTextFieldDown.setEditable(false);
        foodIdTextFieldDown.setColumns(10);
        foodIdTextFieldDown.setBounds(159, 22, 107, 28);
        operationPanel.add(foodIdTextFieldDown);

        JLabel foodNameLabelDown = new JLabel("菜品名称:");
        foodNameLabelDown.setIcon(new ImageIcon("src/main/resources/img/菜品分类名称.png"));
        foodNameLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodNameLabelDown.setBounds(284, 21, 112, 28);
        operationPanel.add(foodNameLabelDown);

        foodNameTextFieldDown = new JTextField();
        foodNameTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodNameTextFieldDown.setColumns(10);
        foodNameTextFieldDown.setBounds(414, 22, 123, 28);
        operationPanel.add(foodNameTextFieldDown);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setToolTipText("菜品描述...");
        descriptionTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        descriptionTextArea.setBounds(549, 21, 261, 158);
        operationPanel.add(descriptionTextArea);

        JButton addButton = new JButton("添加菜品");
        addButton.setIcon(new ImageIcon("src/main/resources/img/添加 .png"));
        addButton.setToolTipText("菜品编号为自动分配，不可指定");
        addButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        addButton.setContentAreaFilled(false);
        addButton.setBounds(1045, 21, 213, 35);
        operationPanel.add(addButton);

        JButton mergeButton = new JButton("修改菜品");
        mergeButton.setIcon(new ImageIcon("src/main/resources/img/修改.png"));
        mergeButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        mergeButton.setContentAreaFilled(false);
        mergeButton.setBounds(1045, 97, 213, 35);
        operationPanel.add(mergeButton);

        JButton deleteButton = new JButton("删除菜品");
        deleteButton.setIcon(new ImageIcon("src/main/resources/img/删除.png"));
        deleteButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBounds(1045, 180, 213, 35);
        operationPanel.add(deleteButton);

        JLabel categoryIdLabelDown = new JLabel("所属分类:");
        categoryIdLabelDown.setIcon(new ImageIcon("src/main/resources/img/外键.png"));
        categoryIdLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        categoryIdLabelDown.setBounds(34, 148, 112, 31);
        operationPanel.add(categoryIdLabelDown);

        categoryIdTextFieldDown = new JTextField();
        categoryIdTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        categoryIdTextFieldDown.setColumns(10);
        categoryIdTextFieldDown.setBounds(159, 151, 107, 28);
        operationPanel.add(categoryIdTextFieldDown);

        JLabel foodPriceLabelDown = new JLabel("菜品价格:");
        foodPriceLabelDown.setIcon(new ImageIcon("src/main/resources/img/价格.png"));
        foodPriceLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodPriceLabelDown.setBounds(34, 84, 107, 28);
        operationPanel.add(foodPriceLabelDown);

        foodPriceTextFieldDown = new JTextField();
        foodPriceTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodPriceTextFieldDown.setColumns(10);
        foodPriceTextFieldDown.setBounds(159, 85, 107, 28);
        operationPanel.add(foodPriceTextFieldDown);

        JLabel statusLabelDown = new JLabel("菜品状态:");
        statusLabelDown.setIcon(new ImageIcon("src/main/resources/img/状态.png"));
        statusLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        statusLabelDown.setBounds(284, 147, 112, 32);
        operationPanel.add(statusLabelDown);

        statusCheckBoxDown = new JComboBox();
        statusCheckBoxDown.setModel(new DefaultComboBoxModel(new String[] {"", "已下架", "上架中"}));
        statusCheckBoxDown.setSelectedIndex(0);
        statusCheckBoxDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        statusCheckBoxDown.setBounds(414, 153, 123, 26);
        operationPanel.add(statusCheckBoxDown);

        JLabel foodSumLabelDown = new JLabel("菜品销量:");
        foodSumLabelDown.setIcon(new ImageIcon("src/main/resources/img/菜品销量.png"));
        foodSumLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        foodSumLabelDown.setBounds(284, 84, 112, 28);
        operationPanel.add(foodSumLabelDown);

        foodSumTextFieldDown = new JTextField();
        foodSumTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        foodSumTextFieldDown.setColumns(10);
        foodSumTextFieldDown.setBounds(414, 85, 123, 28);
        operationPanel.add(foodSumTextFieldDown);

        JButton chooseFileDown = new JButton("选择图片");
        chooseFileDown.setIcon(new ImageIcon("src/main/resources/img/待选择.png"));
        chooseFileDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        chooseFileDown.setContentAreaFilled(false);
        chooseFileDown.setBounds(393, 191, 143, 28);
        operationPanel.add(chooseFileDown);

        fileAbsolutePathTextArea = new JTextArea();
        fileAbsolutePathTextArea.setEditable(false);
        fileAbsolutePathTextArea.setBounds(549, 191, 261, 28);
        operationPanel.add(fileAbsolutePathTextArea);

        foodPhotoLabel = new JLabel();
        foodPhotoLabel.setBounds(828, 21, 200, 200);
        operationPanel.add(foodPhotoLabel);



        // 添加监听
        foodViewHandler = new FoodViewHandler(this);
        searchButton.addActionListener(foodViewHandler);
        table.addMouseListener(foodViewHandler);
        addButton.addActionListener(foodViewHandler);
        mergeButton.addActionListener(foodViewHandler);
        deleteButton.addActionListener(foodViewHandler);
        chooseFileDown.addActionListener(foodViewHandler);

        foodPriceTextFieldDown.addKeyListener(new MoneyHandler());
        foodSumTextFieldDown.addKeyListener(new NumberHandler());
        setLocationRelativeTo(null);
    }


    public void reloadData(Vector<Vector<Object>> data) {
        myTableModel.setDataVector(data, columnNames);
        table.getColumnModel().getColumn(0).setMaxWidth(90);
        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setMaxWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setMaxWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setMaxWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setMaxWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(90);
        table.getColumnModel().getColumn(6).setMaxWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
    }

    public String chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSize(715, 49);
        chooser.setLocation(301, 81);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & png Images", "jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showDialog(this, "选择");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return "No File be chosen";
        }
    }
}


