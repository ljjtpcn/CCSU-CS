package window;

import handler.ChangeHandler;
import handler.special.DateHandler;
import handler.special.MoneyHandler;
import res.RowDO;
import service.StudentService;
import service.impl.StudentServiceImpl;
import util.BulkImportUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 8:16
 */

public class ChangeView extends JDialog {

    //整体
    private JDialog frame;
    private JButton sureButton;
    //id
    private JLabel idLabel;
    private JTextField idTextField;
    //用户名
    private JLabel userLabel;
    private JTextField userTextField;
    //变动金额
    private JLabel changeMoneyLabel;
    private JTextField changeMoneyTextField;
    //分类
    private JTextField typeTextField;
    private JLabel typeLabel;
    //日期
    private JLabel dateLabel;
    private JTextField dateTextField;
    //备注
    private JTextField remarkTextField;
    private JLabel remarkLabel;
    //背景图片
    private JLabel imgLabel;


    public ChangeView(MainView mainView, int selectedIndex) {
        ChangeHandler changeIncomeHandler = new ChangeHandler(this, mainView);
        frame = new JDialog(mainView,"修改收支记录",true);
        frame.setBounds(100, 100, 720, 560);
        frame.getContentPane().setLayout(null);

        //数据回显
        StudentService studentService = new StudentServiceImpl();
        RowDO request = new RowDO();
        request.setUsername(mainView.getUser());
        request.setId(String.valueOf(selectedIndex));
        RowDO bySelectedId = studentService.getBySelectedId(request);
        String id = bySelectedId.getId();
        String username = bySelectedId.getUsername();
        String money = bySelectedId.getMoney();
        String remark = bySelectedId.getRemark();
        String date = bySelectedId.getDate();
        String type = bySelectedId.getType();


        Font kFont = new Font("楷体", Font.BOLD, 25);
        Font lFont = new Font("隶书", Font.PLAIN, 20);

        //id标签
        idLabel = new JLabel("id:");
        idLabel.setIcon(new ImageIcon(ChangeView.class.getClassLoader().getResource("img/id32.png")));
        idLabel.setFont(kFont);
        idLabel.setBounds(125, 36, 180, 35);
        frame.getContentPane().add(idLabel);
        //文本框
        idTextField = new JTextField();
        idTextField.setBounds(230, 40, 300, 35);
        frame.getContentPane().add(idTextField);
        idTextField.setColumns(10);
        idTextField.setText(id);
        idTextField.setEditable(false);

        //用户名
        userLabel = new JLabel("用户名：");
        userLabel.setIcon(new ImageIcon(ChangeView.class.getClassLoader().getResource("img/username32.png")));
        userLabel.setFont(kFont);
        userLabel.setBounds(73, 106, 180, 35);
        frame.getContentPane().add(userLabel);
        //用户名文本框
        userTextField = new JTextField();
        userTextField.setColumns(10);
        userTextField.setBounds(230, 110, 300, 35);
        frame.getContentPane().add(userTextField);
        userTextField.setText(username);
        userTextField.setEditable(false);

        //变动金额
        changeMoneyLabel = new JLabel("变动金额：");
        changeMoneyLabel.setIcon(new ImageIcon(ChangeView.class.getClassLoader().getResource("img/money32.png")));
        changeMoneyLabel.setFont(kFont);
        changeMoneyLabel.setBounds(45, 176, 180, 35);
        frame.getContentPane().add(changeMoneyLabel);
        //文本框
        changeMoneyTextField = new JTextField();
        changeMoneyTextField.setColumns(10);
        changeMoneyTextField.setBounds(230, 180, 300, 35);
        changeMoneyTextField.setText(money);
        frame.getContentPane().add(changeMoneyTextField);

        //分类
        typeLabel = new JLabel("分类：");
        typeLabel.setIcon(new ImageIcon(ChangeView.class.getClassLoader().getResource("img/type32.png")));
        typeLabel.setFont(kFont);
        typeLabel.setBounds(97, 246, 163, 35);
        frame.getContentPane().add(typeLabel);
        //文本框
        typeTextField = new JTextField();
        typeTextField.setColumns(10);
        typeTextField.setBounds(230, 250, 298, 35);
        typeTextField.setText(type);
        frame.getContentPane().add(typeTextField);

        //日期
        dateLabel = new JLabel("日期：");
        dateLabel.setIcon(new ImageIcon(ChangeView.class.getClassLoader().getResource("img/date32.png")));
        dateLabel.setFont(kFont);
        dateLabel.setBounds(97, 316, 180, 35);
        frame.getContentPane().add(dateLabel);
        //文本框
        dateTextField = new JTextField();
        dateTextField.setColumns(10);
        dateTextField.setBounds(230, 320, 300, 35);
        dateTextField.setText(date);
        frame.getContentPane().add(dateTextField);

        //备注
        remarkLabel = new JLabel("备注：");
        remarkLabel.setIcon(new ImageIcon(ChangeView.class.getClassLoader().getResource("img/remark32.png")));
        remarkLabel.setFont(kFont);
        remarkLabel.setBounds(89, 386, 180, 35);
        frame.getContentPane().add(remarkLabel);
        //文本框
        remarkTextField = new JTextField();
        remarkTextField.setColumns(10);
        remarkTextField.setBounds(230, 390, 300, 35);
        remarkTextField.setText(remark);
        frame.getContentPane().add(remarkTextField);
        //确认
        sureButton = new JButton("确定");

        sureButton.setIcon(new ImageIcon(ChangeView.class.getClassLoader().getResource("img/paysure.png")));
        sureButton.setFont(new Font("楷体", Font.BOLD, 20));
        sureButton.setBounds(555, 455, 120, 46);
        frame.getContentPane().add(sureButton);

        imgLabel = new JLabel("New label");
        imgLabel.setIcon(new ImageIcon(ChangeView.class.getClassLoader().getResource("img/changeback.jpg")));
        imgLabel.setBounds(0, 0, 720, 560);
        frame.getContentPane().add(imgLabel);


        //设置监听
        sureButton.addActionListener(changeIncomeHandler);
        frame.getRootPane().setDefaultButton(sureButton);

        //设置非法输入(非数字)监听
        MoneyHandler moneyHandler = new MoneyHandler();
        changeMoneyTextField.addKeyListener(moneyHandler);
        DateHandler dateHandler = new DateHandler();
        dateTextField.addKeyListener(dateHandler);
        //设置窗口不可拉伸
//        setResizable(false);
        BulkImportUtil.setFont(lFont, idTextField, userTextField, changeMoneyTextField, dateTextField, typeTextField, remarkTextField);
        sureButton.setContentAreaFilled(false);
        sureButton.setBorderPainted(false);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }





    public JTextField getTypeText() {
        return typeTextField;
    }

    public JTextField getMoneyText() {
        return changeMoneyTextField;
    }

    public JTextField getRemarkText() {
        return remarkTextField;
    }

    public JTextField getIdFiled() {
        return idTextField;
    }

    public JTextField getDateTextField() {
        return dateTextField;
    }

    public void setDateTextField(JTextField dateTextField) {
        this.dateTextField = dateTextField;
    }
}