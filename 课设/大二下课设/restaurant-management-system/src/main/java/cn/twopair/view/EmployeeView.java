package cn.twopair.view;

import cn.twopair.handler.EmployeeViewHandler;
import cn.twopair.handler.special.NumberHandler;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Vector;

/**
 * @author ljjtpcn
 */
@Getter
@Setter
public class EmployeeView extends JDialog {

    private JPanel root;
    private MyTableModel myTableModel;
    private Vector<Vector<Object>> rowData;
    private Vector<Object> columnNames;
    private JTable table;
    private JTextField idTextField;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JTextField nameTextFieldDown;
    private JTextField idTextFieldDown;
    private JTextField sexTextFieldDown;
    private JTextField identityIdTextFieldDown;
    private JTextField telphoneTextFieldDown;
    private JTextField statusTextFieldDown;
    private JTextField identityIdTextField;
    private final String searchButtonText = "查询";
    private final String addButtonText = "添加员工";
    private final String mergeButtonText = "修改员工";
    private final String deleteButtonText = "删除员工";

    EmployeeViewHandler employeeViewHandler;

    /**
     * Create the frame.
     */
    public EmployeeView(MenuView menuView) {
        super(menuView, "员工管理", true);
        setResizable(false);
        setBounds(100, 100, 1292, 747);
        root = new JPanel();
        root.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(root);
        root.setLayout(null);

        JLabel idLabel = new JLabel("员工序号: ");
        idLabel.setIcon(new ImageIcon("src/main/resources/img/编号.png"));
        idLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setBounds(64, 74, 123, 35);
        root.add(idLabel);

        idTextField = new JTextField();
        idTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        idTextField.setBounds(205, 76, 114, 32);
        root.add(idTextField);
        idTextField.setColumns(10);

        nameLabel = new JLabel("员工姓名: ");
        nameLabel.setIcon(new ImageIcon("src/main/resources/img/姓名.png"));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        nameLabel.setBounds(348, 74, 132, 35);
        root.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        nameTextField.setColumns(10);
        nameTextField.setBounds(484, 74, 114, 32);
        root.add(nameTextField);

        JLabel identityIdLabel = new JLabel("身份证号: ");
        identityIdLabel.setIcon(new ImageIcon("src/main/resources/img/身份证.png"));
        identityIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        identityIdLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        identityIdLabel.setBounds(636, 74, 123, 35);
        root.add(identityIdLabel);

        identityIdTextField = new JTextField();
        identityIdTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        identityIdTextField.setColumns(10);
        identityIdTextField.setBounds(772, 76, 175, 32);
        root.add(identityIdTextField);

        JButton searchButton = new JButton("查询");
        searchButton.setIcon(new ImageIcon("src/main/resources/img/查询.png"));
        searchButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchButton.setBounds(1000, 74, 107, 35);
        searchButton.setContentAreaFilled(false);
        root.add(searchButton);

        // 表格所有行数据(默认空)
        rowData = new Vector<>();
        // 创建一个表格，指定 表头 和 所有行数据
        columnNames = new Vector<>();
        columnNames.add("序号");
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("身份证号");
        columnNames.add("手机号");
        columnNames.add("状态");

        myTableModel = new MyTableModel();
        myTableModel.setDataVector(rowData, columnNames);
        table = new JTable(myTableModel);
        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色
        table.setDefaultRenderer(Object.class, new MyCellRender());       // 内容居中显示
        // 设置表头
        table.getTableHeader().setFont(new Font("楷体", Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);                            // 设置不允许双击修改

        // 设置行高
        table.setRowHeight(30);

        // 第一列列宽设置为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(64, 136, 1185, 192);

        // 添加 滚动面板 到 内容面板
        root.add(scrollPane);

        JLabel idLabelDown = new JLabel("序号: ");
        idLabelDown.setIcon(new ImageIcon("src/main/resources/img/编号.png"));
        idLabelDown.setHorizontalAlignment(SwingConstants.CENTER);
        idLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        idLabelDown.setBounds(64, 461, 91, 35);
        root.add(idLabelDown);

        JLabel nameLabelDown = new JLabel("姓名: ");
        nameLabelDown.setIcon(new ImageIcon("src/main/resources/img/姓名.png"));
        nameLabelDown.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        nameLabelDown.setBounds(270, 463, 87, 35);
        root.add(nameLabelDown);

        nameTextFieldDown = new JTextField();
        nameTextFieldDown.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        nameTextFieldDown.setColumns(10);
        nameTextFieldDown.setBounds(361, 465, 121, 32);
        root.add(nameTextFieldDown);

        idTextFieldDown = new JTextField();
        idTextFieldDown.setEditable(false);
        idTextFieldDown.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        idTextFieldDown.setToolTipText("自动分配序号");
        idTextFieldDown.setColumns(10);
        idTextFieldDown.setBounds(159, 465, 106, 32);
        root.add(idTextFieldDown);

        JLabel sexLabelDown = new JLabel("性别:");
        sexLabelDown.setIcon(new ImageIcon("src/main/resources/img/性别.png"));
        sexLabelDown.setHorizontalAlignment(SwingConstants.CENTER);
        sexLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        sexLabelDown.setBounds(484, 463, 91, 35);
        root.add(sexLabelDown);

        sexTextFieldDown = new JTextField();
        sexTextFieldDown.setToolTipText("男 或 女");
        sexTextFieldDown.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        sexTextFieldDown.setColumns(10);
        sexTextFieldDown.setBounds(580, 465, 38, 32);
        root.add(sexTextFieldDown);

        JLabel identityIdLabelDown = new JLabel("身份证号: ");
        identityIdLabelDown.setIcon(new ImageIcon("src/main/resources/img/身份证.png"));
        identityIdLabelDown.setHorizontalAlignment(SwingConstants.CENTER);
        identityIdLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        identityIdLabelDown.setBounds(636, 463, 123, 35);
        root.add(identityIdLabelDown);

        identityIdTextFieldDown = new JTextField();
        identityIdTextFieldDown.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        identityIdTextFieldDown.setColumns(10);
        identityIdTextFieldDown.setBounds(772, 465, 175, 32);
        root.add(identityIdTextFieldDown);

        JLabel telphoneLabelDown = new JLabel("手机号: ");
        telphoneLabelDown.setIcon(new ImageIcon("src/main/resources/img/手机号.png"));
        telphoneLabelDown.setHorizontalAlignment(SwingConstants.CENTER);
        telphoneLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        telphoneLabelDown.setBounds(952, 463, 123, 35);
        root.add(telphoneLabelDown);

        telphoneTextFieldDown = new JTextField();
        telphoneTextFieldDown.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        telphoneTextFieldDown.setColumns(10);
        telphoneTextFieldDown.setBounds(1074, 465, 175, 32);
        root.add(telphoneTextFieldDown);

        JButton addButton = new JButton("添加员工");
        addButton.setIcon(new ImageIcon("src/main/resources/img/添加 .png"));
        addButton.setToolTipText("序号为自动分配，不可指定");
        addButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        addButton.setBounds(170, 605, 151, 35);
        addButton.setContentAreaFilled(false);
        root.add(addButton);

        JButton mergeButton = new JButton("修改员工");
        mergeButton.setIcon(new ImageIcon("src/main/resources/img/修改.png"));
        mergeButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        mergeButton.setBounds(523, 605, 144, 35);
        mergeButton.setContentAreaFilled(false);
        root.add(mergeButton);

        JButton deleteButton = new JButton("删除员工");
        deleteButton.setIcon(new ImageIcon("src/main/resources/img/删除.png"));
        deleteButton.setToolTipText("请先选中要删除的员工所在行，支持多行删除");
        deleteButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        deleteButton.setBounds(868, 605, 144, 35);
        deleteButton.setContentAreaFilled(false);
        root.add(deleteButton);

        JLabel statusLabel = new JLabel("状态: ");
        statusLabel.setIcon(new ImageIcon("src/main/resources/img/未知状态.png"));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        statusLabel.setBounds(488, 531, 87, 35);
        root.add(statusLabel);

        statusTextFieldDown = new JTextField();
        statusTextFieldDown.setToolTipText("在职 或 离职");
        statusTextFieldDown.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        statusTextFieldDown.setColumns(10);
        statusTextFieldDown.setBounds(580, 533, 121, 32);
        root.add(statusTextFieldDown);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("src/main/resources/img/employeeVIew.jpg"));
        backgroundLabel.setBounds(0, 0, 1292, 747);
        root.add(backgroundLabel);


        //添加监听
        employeeViewHandler = new EmployeeViewHandler(this);
        searchButton.addActionListener(employeeViewHandler);
        table.addMouseListener(employeeViewHandler);
        addButton.addActionListener(employeeViewHandler);
        mergeButton.addActionListener(employeeViewHandler);
        deleteButton.addActionListener(employeeViewHandler);
        telphoneTextFieldDown.addKeyListener(new NumberHandler());
        setLocationRelativeTo(null);
    }


    public void reloadData(Vector<Vector<Object>> data) {
        myTableModel.setDataVector(data, columnNames);
    }
}
