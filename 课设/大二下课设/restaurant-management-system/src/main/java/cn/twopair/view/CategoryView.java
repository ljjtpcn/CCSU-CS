package cn.twopair.view;

/**
 * @ClassName CategoryView
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/13 下午8:45
 * @Version 1.0
 **/

import cn.twopair.handler.CategoryViewHandler;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.util.Vector;


@Getter
@Setter
public class CategoryView extends JDialog {

    private JPanel root;
    private JTextField categoryIdTextField;
    private JTextField categoryNameTextField;
    private MyTableModel myTableModel;
    private Vector<Vector<Object>> rowData;
    private Vector<Object> columnNames;
    private JTable table;
    private JTextField categoryIdTextFieldDown;
    private JTextField categoryNameTextFieldDown;
    private JTextArea descriptionTextArea;

    private final String searchButtonText = "查询";
    private final String addButtonText = "添加菜品分类";
    private final String mergeButtonText = "修改菜品分类";
    private final String deleteButtonText = "删除菜品分类";

    CategoryViewHandler categoryViewHandler;

    /**
     * Create the frame.
     */
    public CategoryView(MenuView menuView) {
        super(menuView, "菜品分类管理", true);
        setBounds(100, 100, 1292, 747);
        root = new JPanel();
        root.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(root);
        root.setLayout(null);

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        searchPanel.setBounds(0, 0, 1292, 162);
        root.add(searchPanel);
        searchPanel.setLayout(null);

        JLabel categoryIdLabel = new JLabel("菜品分类编号:");
        categoryIdLabel.setIcon(new ImageIcon("src/main/resources/img/餐台编号.png"));
        categoryIdLabel.setBounds(236, 67, 151, 28);
        categoryIdLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchPanel.add(categoryIdLabel);

        categoryIdTextField = new JTextField();
        categoryIdTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        categoryIdTextField.setColumns(10);
        categoryIdTextField.setBounds(405, 68, 123, 28);
        searchPanel.add(categoryIdTextField);

        JLabel categoryNameLabel = new JLabel("菜品分类名称:");
        categoryNameLabel.setIcon(new ImageIcon("src/main/resources/img/菜品分类名称.png"));
        categoryNameLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        categoryNameLabel.setBounds(546, 67, 151, 28);
        searchPanel.add(categoryNameLabel);

        categoryNameTextField = new JTextField();
        categoryNameTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        categoryNameTextField.setColumns(10);
        categoryNameTextField.setBounds(715, 68, 123, 28);
        searchPanel.add(categoryNameTextField);

        JButton searchButton = new JButton("查询");
        searchButton.setIcon(new ImageIcon("src/main/resources/img/查询.png"));
        searchButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchButton.setContentAreaFilled(false);
        searchButton.setBounds(919, 68, 107, 27);
        searchPanel.add(searchButton);

        // 表格所有行数据(默认空)
        rowData = new Vector<>();
        // 创建一个表格，指定 表头 和 所有行数据
        columnNames = new Vector<>();
        columnNames.add("菜品分类编号");
        columnNames.add("菜品分类名称");
        columnNames.add("菜品分类描述");

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

        // 第一列列宽设置为160
        table.getColumnModel().getColumn(0).setMaxWidth(160);
        table.getColumnModel().getColumn(0).setPreferredWidth(160);
        table.getColumnModel().getColumn(1).setMaxWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        viewPanel.setLayout(null);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(76, 12, 1137, 301);
        viewPanel.add(scrollPane);

        JPanel operationPanel = new JPanel();
        operationPanel.setBorder(new TitledBorder(null, "\u64CD\u4F5C\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        operationPanel.setBounds(0, 499, 1292, 233);
        root.add(operationPanel);
        operationPanel.setLayout(null);

        JLabel categoryIdLabelDown = new JLabel("菜品分类编号:");
        categoryIdLabelDown.setIcon(new ImageIcon("src/main/resources/img/餐台编号.png"));
        categoryIdLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        categoryIdLabelDown.setBounds(55, 40, 151, 28);
        operationPanel.add(categoryIdLabelDown);

        categoryIdTextFieldDown = new JTextField();
        categoryIdTextFieldDown.setEditable(false);
        categoryIdTextFieldDown.setToolTipText("菜品分类编号为自动分配，不可指定");
        categoryIdTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        categoryIdTextFieldDown.setColumns(10);
        categoryIdTextFieldDown.setBounds(218, 41, 123, 28);
        operationPanel.add(categoryIdTextFieldDown);

        JLabel categoryNameLabelDown = new JLabel("菜品分类名称:");
        categoryNameLabelDown.setIcon(new ImageIcon("src/main/resources/img/菜品分类名称.png"));
        categoryNameLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        categoryNameLabelDown.setBounds(430, 40, 151, 28);
        operationPanel.add(categoryNameLabelDown);

        categoryNameTextFieldDown = new JTextField();
        categoryNameTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        categoryNameTextFieldDown.setColumns(10);
        categoryNameTextFieldDown.setBounds(599, 41, 123, 28);
        operationPanel.add(categoryNameTextFieldDown);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        descriptionTextArea.setToolTipText("菜品分类描述...");
        descriptionTextArea.setBounds(55, 80, 667, 141);
        operationPanel.add(descriptionTextArea);

        JButton addButton = new JButton("添加菜品分类");
        addButton.setIcon(new ImageIcon("src/main/resources/img/添加 .png"));
        addButton.setToolTipText("菜品分类编号为自动分配，不可指定");
        addButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        addButton.setContentAreaFilled(false);
        addButton.setBounds(919, 37, 213, 35);
        operationPanel.add(addButton);

        JButton mergeButton = new JButton("修改菜品分类");
        mergeButton.setIcon(new ImageIcon("src/main/resources/img/修改.png"));
        mergeButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        mergeButton.setContentAreaFilled(false);
        mergeButton.setBounds(919, 111, 213, 35);
        operationPanel.add(mergeButton);

        JButton deleteButton = new JButton("删除菜品分类");
        deleteButton.setIcon(new ImageIcon("src/main/resources/img/删除.png"));
        deleteButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBounds(919, 186, 213, 35);
        operationPanel.add(deleteButton);

        // 添加监听
        categoryViewHandler = new CategoryViewHandler(this);
        searchButton.addActionListener(categoryViewHandler);
        table.addMouseListener(categoryViewHandler);
        addButton.addActionListener(categoryViewHandler);
        mergeButton.addActionListener(categoryViewHandler);
        deleteButton.addActionListener(categoryViewHandler);
        setLocationRelativeTo(null);

    }

    public void reloadData(Vector<Vector<Object>> data) {
        myTableModel.setDataVector(data, columnNames);
        table.getColumnModel().getColumn(0).setMaxWidth(160);
        table.getColumnModel().getColumn(0).setPreferredWidth(160);
        table.getColumnModel().getColumn(1).setMaxWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
    }

}

