package window;

import handler.MainViewHandler;
import req.StudentRequest;
import res.TableDTO;
import service.StudentService;
import service.impl.StudentServiceImpl;
import util.SetIconUtil;
import window.ext.MainViewTable;
import window.ext.MainViewTableModel;
import util.BulkImportUtil;
import util.DimensionUtil;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.net.URL;
import java.util.Vector;



/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-12 9:35
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
public class MainView extends JFrame {

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 25));
    JButton incomeButton = new JButton("总收入查询");
    JButton expendButton = new JButton("总支出查询");
    JButton statisticsButton = new JButton("统计查询");
    JButton searchButton = new JButton("关键字查询");
    JButton addButton = new JButton("增加收支记录");
    JButton deleteButton = new JButton("删除收支记录");
    JButton modifyButton = new JButton("修改收支记录");
    JButton preButton = new JButton("上一页");
    JButton nextButton = new JButton("下一页");



    JTextField searchTextField = new JTextField(20);

    MainViewTable mainViewTable = new MainViewTable();
//    Vector<Vector<Object>> data = new Vector<>();

    //当前页
    private int pageNow = 1;
    //每页显示行数
    private int pageSize = 10;
    //当前用户名,方便查询对应收支记录
    private String user;


    MainViewHandler mainViewHandler;
    public MainView(String title, String user) {
        super(title);
        this.user = user;
        Container root = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height, 50);
        //监听器
        mainViewHandler = new MainViewHandler(this);

        layoutNorth(root);
        layoutCenter(root, user);
        layoutSouth(root);



        //设置图标
        URL imgUrl = LoginView.class.getClassLoader().getResource("img/logo.png");
        assert imgUrl != null;
        setIconImage(new ImageIcon(imgUrl).getImage());
        //根据屏幕大小设置主界面大小
        setBounds(bounds);
        //设置窗体完全充满整个屏幕的可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置回车默认查询按钮
//        getRootPane().setDefaultButton(searchButton);
        extracted();
        setResizable(true);
        setVisible(true);
    }

    private void extracted() {
        preButton.setContentAreaFilled(false);
        preButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        incomeButton.setContentAreaFilled(false);
        incomeButton.setBorderPainted(false);
        expendButton.setContentAreaFilled(false);
        expendButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setBorderPainted(false);
        statisticsButton.setContentAreaFilled(false);
        statisticsButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        modifyButton.setContentAreaFilled(false);
        modifyButton.setBorderPainted(false);
    }

    private void layoutNorth(Container root) {


        searchButton.addActionListener(mainViewHandler);

        BulkImportUtil.add(northPanel, addButton , modifyButton, deleteButton,
                searchTextField, searchButton,
                incomeButton, expendButton, statisticsButton);

        //图标
        SetIconUtil.setIcon(addButton, "img/add.png");
        SetIconUtil.setIcon(modifyButton, "img/modify.png");
        SetIconUtil.setIcon(deleteButton, "img/delete.png");
        SetIconUtil.setIcon(searchButton, "img/search.png");
        SetIconUtil.setIcon(incomeButton, "img/income.png");
        SetIconUtil.setIcon(expendButton, "img/expend.png");
        SetIconUtil.setIcon(statisticsButton, "img/statistics.png");

        //字体
        Font font = new Font("楷体", Font.BOLD, 20);
        BulkImportUtil.setFont(font, addButton , modifyButton, deleteButton,
                 searchButton,
                incomeButton, expendButton, statisticsButton);
        searchTextField.setFont(new Font("楷体", Font.BOLD, 25));

        //增删改查监听
        addButton.addActionListener(mainViewHandler);
        deleteButton.addActionListener(mainViewHandler);
        modifyButton.addActionListener(mainViewHandler);
        incomeButton.addActionListener(mainViewHandler);
        expendButton.addActionListener(mainViewHandler);
        statisticsButton.addActionListener(mainViewHandler);

        //设置回车默认button
        getRootPane().setDefaultButton(searchButton);

        root.add(northPanel, BorderLayout.NORTH);

    }






    private void layoutCenter(Container root, String user) {
//        addElement("1", "张三", "B20190103224", "10", "30", "150", "火星");
//        addElement("2", "李四", "B20190103225", "20", "60", "150", "地球");
//        addElement("3", "王五", "B20190103226", "30", "90", "150", "月球");

        TableDTO tableDTO = getVectors(user);
        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(tableDTO.getData());



        //JTable关联model
        mainViewTable.setModel(mainViewTableModel);
        //设置表格渲染方式
        mainViewTable.renderRuler();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        root.add(jScrollPane, BorderLayout.CENTER);
        showPreNext(tableDTO.getTotalCount());
    }

    /*
     * 设置上一页下一页是否可见
     * @param [int]
     * @return [void]
     * @date [2021/12/23 12:35]
     */
    private void showPreNext(int totalCount){
        if(pageNow == 1){
            preButton.setVisible(false);
        }else{
            preButton.setVisible(true);
        }
        int pageCount = 0;
        if(totalCount % pageSize == 0){
            pageCount = totalCount / pageSize;
        }else{
            pageCount = totalCount / pageSize + 1;
        }
        if(pageNow == pageCount){
            nextButton.setVisible(false);
        }else{
            nextButton.setVisible(true);
        }
    }


    private TableDTO getVectors(String user) {
        StudentService studentService = new StudentServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setUser(user);
        request.setSearchKey(searchTextField.getText().trim());
        TableDTO tableDTO = studentService.retrieveStudents(request);
        return tableDTO;
    }

//    private void addElement(Object... elements) {
//        Vector<Object> row = new Vector<>();
//        for (Object element : elements) {
//            row.addElement(element);
//        }
//        data.add(row);
//    }

    private void layoutSouth(Container root) {
        SetIconUtil.setIcon(preButton, "img/pre.png");
        SetIconUtil.setIcon(nextButton, "img/next.png");

        preButton.setFont(new Font("楷体", Font.BOLD, 20));
        nextButton.setFont(new Font("楷体", Font.BOLD, 20));

        preButton.addActionListener(mainViewHandler);
        nextButton.addActionListener(mainViewHandler);

        BulkImportUtil.add(southPanel, preButton, nextButton);
        root.add(southPanel, BorderLayout.SOUTH);
    }


    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    //刷新查询
    public void reloadTable(String user) {
        TableDTO dto = getVectors(user);
        MainViewTableModel.updateModel(dto.getData());
        mainViewTable.renderRuler();
        showPreNext(dto.getTotalCount());

    }

    public  int[] getSelectedIDs(){
        int[] selectedRows = mainViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for(int i = 0;  i <selectedRows.length; i ++){
            int rowIdx = selectedRows[i];
            Object idObj = mainViewTable.getValueAt(rowIdx, 0);
            ids[i] = Integer.parseInt(idObj.toString());
        }

        return ids;
    }


}
