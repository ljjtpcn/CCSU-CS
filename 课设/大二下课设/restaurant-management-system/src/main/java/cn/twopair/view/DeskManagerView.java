package cn.twopair.view;


import cn.twopair.handler.DeskManagerViewHandler;
import cn.twopair.handler.special.NumberHandler;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Vector;

/**
 * @ClassName DeskManagerView
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/13 上午8:20
 * @Version 1.0
 **/
@Setter
@Getter
public class DeskManagerView extends JDialog {

    private JPanel root;
    private JTextField deskNoTextField;
    private JTextField deskSeatingTextField;
    private MyTableModel myTableModel;
    private Vector<Vector<Object>> rowData;
    private Vector<Object> columnNames;
    private JTable table;
    private JTextField idtextFieldDown;
    private JTextField deskNoTextFieldDown;
    private JTextField deskSeatingTextFieldDown;
    private JComboBox statusCheckBox;
    private JComboBox statusCheckBoxDown;
    private final String searchButtonText = "查询";
    private final String addButtonText = "添加餐台";
    private final String mergeButtonText = "修改餐台";
    private final String deleteButtonText = "删除餐台";

    DeskManagerViewHandler deskManagerViewHandler;
    /**
     * Create the frame.
     */
    public DeskManagerView(MenuView menuView) {
        super(menuView, "餐台管理", true);
        setResizable(false);
        setBounds(100, 100, 1292, 747);
        root = new JPanel();
        root.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(root);
        root.setLayout(null);

        statusCheckBox = new JComboBox();
        statusCheckBox.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        statusCheckBox.setModel(new DefaultComboBoxModel(new String[] {"", "空闲", "忙碌", "预订"}));
        statusCheckBox.setSelectedIndex(0);
        statusCheckBox.setBounds(734, 85, 63, 26);
        root.add(statusCheckBox);

        JButton searchButton = new JButton(searchButtonText);
        searchButton.setIcon(new ImageIcon("src/main/resources/img/查询.png"));
        searchButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        searchButton.setContentAreaFilled(false);
        searchButton.setBounds(930, 85, 107, 27);
        root.add(searchButton);

        JLabel deskNoLabel = new JLabel("餐台编号:");
        deskNoLabel.setIcon(new ImageIcon("src/main/resources/img/餐台编号.png"));
        deskNoLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        deskNoLabel.setBounds(170, 81, 123, 35);
        root.add(deskNoLabel);

        JLabel seatingLable = new JLabel("座位人数:");
        seatingLable.setIcon(new ImageIcon("src/main/resources/img/座位数量.png"));
        seatingLable.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        seatingLable.setBounds(452, 81, 123, 35);
        root.add(seatingLable);

        deskNoTextField = new JTextField();
        deskNoTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        deskNoTextField.setBounds(311, 85, 123, 28);
        root.add(deskNoTextField);
        deskNoTextField.setColumns(10);

        deskSeatingTextField = new JTextField();
        deskSeatingTextField.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        deskSeatingTextField.setColumns(10);
        deskSeatingTextField.setBounds(593, 85, 123, 28);
        root.add(deskSeatingTextField);

        // 表格所有行数据(默认空)
        rowData = new Vector<>();
        // 创建一个表格，指定 表头 和 所有行数据
        columnNames = new Vector<>();
        columnNames.add("序号");
        columnNames.add("餐台编号");
        columnNames.add("座位人数");
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
        scrollPane.setBounds(58, 138, 1185, 271);

        // 添加 滚动面板 到 内容面板
        root.add(scrollPane);

        JLabel idLabelDown = new JLabel("序号: ");
        idLabelDown.setIcon(new ImageIcon("src/main/resources/img/编号.png"));
        idLabelDown.setHorizontalAlignment(SwingConstants.CENTER);
        idLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        idLabelDown.setBounds(170, 445, 91, 35);
        root.add(idLabelDown);

        idtextFieldDown = new JTextField();
        idtextFieldDown.setEditable(false);
        idtextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        idtextFieldDown.setColumns(10);
        idtextFieldDown.setBounds(263, 449, 123, 28);
        root.add(idtextFieldDown);

        JLabel deskNoLabelDown = new JLabel("餐台编号:");
        deskNoLabelDown.setIcon(new ImageIcon("src/main/resources/img/餐台编号.png"));
        deskNoLabelDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        deskNoLabelDown.setBounds(416, 445, 123, 35);
        root.add(deskNoLabelDown);

        deskNoTextFieldDown = new JTextField();
        deskNoTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        deskNoTextFieldDown.setColumns(10);
        deskNoTextFieldDown.setBounds(557, 449, 123, 28);
        root.add(deskNoTextFieldDown);

        JLabel seatingLableDown = new JLabel("座位人数:");
        seatingLableDown.setIcon(new ImageIcon("src/main/resources/img/座位数量.png"));
        seatingLableDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        seatingLableDown.setBounds(698, 445, 123, 35);
        root.add(seatingLableDown);

        deskSeatingTextFieldDown = new JTextField();
        deskSeatingTextFieldDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
        deskSeatingTextFieldDown.setColumns(10);
        deskSeatingTextFieldDown.setBounds(839, 449, 123, 28);
        root.add(deskSeatingTextFieldDown);

        statusCheckBoxDown = new JComboBox();
        statusCheckBoxDown.setModel(new DefaultComboBoxModel(new String[] {"", "空闲", "忙碌", "预订"}));
        statusCheckBoxDown.setSelectedIndex(0);
        statusCheckBoxDown.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        statusCheckBoxDown.setBounds(974, 449, 63, 26);
        root.add(statusCheckBoxDown);

        JButton addButton = new JButton(addButtonText);
        addButton.setIcon(new ImageIcon("src/main/resources/img/添加 .png"));
        addButton.setToolTipText("序号为自动分配，不可指定");
        addButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        addButton.setContentAreaFilled(false);
        addButton.setBounds(170, 605, 151, 35);
        root.add(addButton);

        JButton mergeButton = new JButton(mergeButtonText);
        mergeButton.setIcon(new ImageIcon("src/main/resources/img/修改.png"));
        mergeButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        mergeButton.setContentAreaFilled(false);
        mergeButton.setBounds(479, 605, 151, 35);
        root.add(mergeButton);

        JButton deleteButton = new JButton(deleteButtonText);
        deleteButton.setIcon(new ImageIcon("src/main/resources/img/删除.png"));
        deleteButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBounds(839, 605, 151, 35);
        root.add(deleteButton);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("src/main/resources/img/deskManagerView.jpg"));
        backgroundLabel.setBounds(0, 0, 1292, 747);
        root.add(backgroundLabel);

        // 添加监听
        deskManagerViewHandler = new DeskManagerViewHandler(this);
        searchButton.addActionListener(deskManagerViewHandler);
        addButton.addActionListener(deskManagerViewHandler);
        mergeButton.addActionListener(deskManagerViewHandler);
        deleteButton.addActionListener(deskManagerViewHandler);
        table.addMouseListener(deskManagerViewHandler);

        deskSeatingTextFieldDown.addKeyListener(new NumberHandler());
        deskSeatingTextField.addKeyListener(new NumberHandler());

        setLocationRelativeTo(null);
    }

    public void reloadData(Vector<Vector<Object>> data) {
        myTableModel.setDataVector(data, columnNames);
    }

}

