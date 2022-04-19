package window;

import handler.AddHandler;
import handler.special.DateHandler;
import handler.special.MoneyHandler;
import util.BulkImportUtil;
import util.CheckUtil;
import util.SetIconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.net.URL;

/**
 * @description:
 * @author: 戴博
 * @time: 2021/12/21 17:28
 */


public class AddView extends JDialog {
    //顶部组件
    private JLabel titleLabel;    //创建标题文本域
    private JPanel northJPanel;
    //中间部件
    //收入与支出单选框
    private ButtonGroup group; // 收支按钮
    private JLabel incomeLabel;
    private JPanel incomeJPanel;
    private JRadioButton addButton;
    private JRadioButton payButton;
    //类型组件
    private JLabel typeLabel;    //类型标签
    private JTextField typeText;    //类型文本框
    //金额组件
    private JLabel moneyLabel;    //金钱标签。
    private JTextField moneyText;    //金钱文本框
    //日期组件
    private JLabel dateLabel;    //日期标签。
    private JTextField dateText;    //日期文本框
    //备注组件
    private JLabel remarkLabel;    //备注标签
    private JTextField remarkText;    //备注文本域
    //底部
    private JButton sureButton;    //确定按钮。
    private JButton cancelButton;    //取消按钮。
    private JPanel centerJPanel;
    private JPanel southJPanel;


    MoneyHandler moneyHandler = new MoneyHandler();
    DateHandler dateHandler = new DateHandler();

    private String user;
    String nowDateString = CheckUtil.getNowDateString();
    public AddView(MainView mainView) {

        super(mainView, "添加收支记录", true);
        AddHandler addHandler = new AddHandler(this, mainView);
        this.user = mainView.getUser();
        Container c = this.getContentPane();

        this.setLayout(new BorderLayout());
        this.setSize(600, 600);//设置大小。
        c.setBackground(Color.WHITE);//设置背景颜色。

        //设置顶部
        northJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 35));
        titleLabel = new JLabel("我的存钱罐");
        titleLabel.setFont(new Font("楷书", Font.BOLD, 28));
        northJPanel.setBackground(new Color(81,171,211));
        northJPanel.add(titleLabel);
        c.add(northJPanel, BorderLayout.NORTH);

        //设置中间
        //设置JPanel容器
        centerJPanel = new JPanel(null);
        centerJPanel.setBackground(new Color(135,206,235));
        //设置单选框
        //设置文字标签

        incomeLabel = new JLabel("方式：",JLabel.LEFT);
        SetIconUtil.setIcon(incomeLabel, "img/select.png");
        incomeLabel.setBounds(80, 30, 140, 40);	//确定文本的位置
        incomeLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(incomeLabel);
        //设置收支单选框
        addButton = new JRadioButton("收入", true);	//设置单选框选项
        SetIconUtil.setIcon(addButton, "img/pig.png");
        addButton.setOpaque(false);
        payButton = new JRadioButton("支出");
        payButton.setOpaque(false);
        Font liFont = new Font("隶书", Font.BOLD, 18);
        addButton.setFont(liFont);   //设置字体颜色
        payButton.setFont(liFont);
        //组别
        group = new ButtonGroup();
        group.add(addButton);
        group.add(payButton);
        //设置布局
        incomeJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 5));	//创建容器及布局方式
        incomeJPanel.setBounds(175, 30, 260, 40);	//单选框的位置
        incomeJPanel.setBackground(new Color(135,206,235));		//容器背景颜色
        incomeJPanel.add(addButton);	//将选项按钮放到容器里面
        incomeJPanel.add(payButton);
        centerJPanel.add(incomeJPanel);
        //创建"类型"标签
        typeLabel = new JLabel("分类：");
        SetIconUtil.setIcon(typeLabel, "img/type.png");
        typeLabel.setBounds(90, 109, 140, 20);    //设置"类型"标签位置。
        typeLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(typeLabel);    //添加"类型"标签。
        //创建"类型"文本域
        typeText = new JTextField(26);
        typeText.setBounds(175, 105, 260, 30);    //设置"类型"文本域位置。
        typeText.grabFocus();//获得光标。
        centerJPanel.add(typeText);    //添加"类型"文本域。

        //创建"金额"标签
        moneyLabel = new JLabel("金额：");
        SetIconUtil.setIcon(moneyLabel, "img/money.png");
        moneyLabel.setBounds(90, 164, 150, 20);
        moneyLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(moneyLabel);    //添加"金额"标签。
        //创建"金额"文本域
        moneyText = new JTextField(30);
        moneyText.setBounds(175, 160, 260, 30);
        centerJPanel.add(moneyText);

        //创建"日期"标签
        dateLabel = new JLabel("日期：");
        SetIconUtil.setIcon(dateLabel, "img/date.png");
        dateLabel.setBounds(90, 229, 150, 20);    //设置"日期"标签位置。
        dateLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(dateLabel);    //添加"日期"标签。
        //创建"日期"文本域
        dateText = new JTextField(25);
        dateText.setBounds(175, 225, 260, 30);    //设置"日期"文本域位置。
        dateText.setText(nowDateString);
        centerJPanel.add(dateText); //添加"日期"文本域。

        //创建"备注"标签
        remarkLabel = new JLabel("备注：");
        SetIconUtil.setIcon(remarkLabel, "img/remark.png");
        remarkLabel.setBounds(90, 284, 160, 20);    //设置"备注"标签位置。
        remarkLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(remarkLabel);    //添加"备注"标签。
        //创建"备注"文本域
        remarkText = new JTextField(20);
        remarkText.setBounds(175, 280, 260, 30);    //设置"备注"文本域位置。
        centerJPanel.add(remarkText); //添加"备注"文本域
        c.add(centerJPanel, BorderLayout.CENTER);

        //底部、创建确定按钮
        southJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 180, 35));
        sureButton = new JButton("确定");    //创建"注册"按钮
        SetIconUtil.setIcon(sureButton, "img/sure32.png");
        southJPanel.setBackground(new Color(81,171,211));
        southJPanel.add(sureButton);
        //创建取消按钮
        cancelButton = new JButton("取消");    //创建"取消"按钮。
        SetIconUtil.setIcon(cancelButton, "img/cancel.png");
        southJPanel.add(cancelButton);
        c.add(southJPanel, BorderLayout.SOUTH);
        sureButton.setContentAreaFilled(false);
        sureButton.setBorderPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setBorderPainted(false);
        //统一设置字体
        Font font = new Font("楷体", Font.PLAIN, 20);
        BulkImportUtil.setFont(font, typeText, moneyText, dateText, remarkText, sureButton, cancelButton);

        //设置左上角
        URL imgLogo = LoginView.class.getClassLoader().getResource("img/logo2.png");
        assert imgLogo != null;
        Image image = new ImageIcon(imgLogo).getImage();
        setIconImage(image);

        //设置窗口不可拉伸
        setResizable(false);
        getRootPane().setDefaultButton(sureButton);
        //设置监听
        sureButton.addActionListener(addHandler);
        cancelButton.addActionListener(addHandler);
        moneyText.addKeyListener(moneyHandler);
        dateText.addKeyListener(dateHandler);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }



    public JTextField getTypeText() {
        return typeText;
    }

    public JTextField getMoneyText() {
        return moneyText;
    }

    public JTextField getDateText() {
        return dateText;
    }

    public JTextField getRemarkText() {
        return remarkText;
    }

    public JRadioButton getAddButton() {
        return addButton;
    }

    public JRadioButton getPayButton() {
        return payButton;
    }

    public String getUser() {
        return user;
    }
}

