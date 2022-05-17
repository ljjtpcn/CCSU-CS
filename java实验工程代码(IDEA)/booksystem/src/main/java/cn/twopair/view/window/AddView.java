package cn.twopair.view.window;

/**
 * @ClassName AddView
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/16 下午3:31
 * @Version 1.0
 **/

import cn.twopair.view.handler.AddHandler;
import cn.twopair.view.util.BulkImportUtil;
import cn.twopair.view.util.CheckUtil;
import cn.twopair.view.util.SetIconUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

@Setter
@Getter
@ToString
public class AddView extends JDialog {
    //顶部组件
    private JLabel titleLabel;    //创建标题文本域
    private JPanel northJPanel;
    //中间部件
    //类型组件
    private JLabel typeLabel;    //图书标签
    private JTextField typeText;    //图书文本框
    //金额组件
    private JLabel bookNameLabel;    //书名标签。
    private JTextField bookNameText;    //书名文本框
    //日期组件
    private JLabel autorLabel;    //作者标签。
    private JTextField autorText;    //作者文本框
    //备注组件
    private JLabel publisherLabel;    //出版社标签
    private JTextField publisherText;    //出版社文本域
    private JLabel priceLabel;    //单价标签
    private JTextField priceText;    //单价文本框
    //金额组件
    private JLabel dateLabel;    //出版日期标签。
    private JTextField dateText;    //出版日期文本框
    //日期组件
    private JLabel iSBNLabel;    //iSBN标签。
    private JTextField iSBNText;    //iSBN文本框
    //备注组件
    private JLabel amountLabel;    //库存标签
    private JTextField amountText;    //库存文本域
    //底部
    private JButton sureButton;    //确定按钮。
    private JButton cancelButton;    //取消按钮。
    private JPanel centerJPanel;
    private JPanel southJPanel;
    AddHandler addHandler;

    String nowDateString = CheckUtil.getNowDateString();
    public AddView(MainView mainView) {

        super(mainView, "添加图书信息", true);
        Container c = this.getContentPane();

        addHandler = new AddHandler(this, mainView);
        this.setLayout(new BorderLayout());
        this.setSize(1200, 600);//设置大小。
        c.setBackground(Color.WHITE);//设置背景颜色。

        //设置顶部
        northJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 35));
        titleLabel = new JLabel("图书增加器");
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

        //创建"图书"标签
        typeLabel = new JLabel("图书：");
        SetIconUtil.setIcon(typeLabel, "img/type.png");
        typeLabel.setBounds(90, 109, 140, 20);    //设置"类型"标签位置。
        typeLabel.setFont(new Font("隶书", Font.BOLD, 20));
        //typeLabel.setForeground(Color.RED);
        centerJPanel.add(typeLabel);    //添加"类型"标签。
        //创建"图书"文本域
        typeText = new JTextField(26);
        typeText.setBounds(175, 105, 260, 30);    //设置"类型"文本域位置。
        typeText.grabFocus();//获得光标。
        centerJPanel.add(typeText);    //添加"类型"文本域。

        //创建"书名"标签
        bookNameLabel = new JLabel("书名：");
        SetIconUtil.setIcon(bookNameLabel, "img/money.png");
        bookNameLabel.setBounds(90, 164, 150, 20);
        bookNameLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(bookNameLabel);    //添加"金额"标签。
        //创建"书名"文本域
        bookNameText = new JTextField(30);
        bookNameText.setBounds(175, 160, 260, 30);
        centerJPanel.add(bookNameText);

        //创建"作者"标签
        autorLabel = new JLabel("作者：");
        SetIconUtil.setIcon(autorLabel, "img/date.png");
        autorLabel.setBounds(90, 229, 150, 20);    //设置"日期"标签位置。
        autorLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(autorLabel);    //添加"日期"标签。
        //创建"作者"文本域
        autorText = new JTextField(25);
        autorText.setBounds(175, 225, 260, 30);    //设置"日期"文本域位置。
        centerJPanel.add(autorText); //添加"日期"文本域。

        //创建"出版社"标签
        publisherLabel = new JLabel("出版社：");
        SetIconUtil.setIcon(publisherLabel, "img/remark.png");
        publisherLabel.setBounds(70, 284, 160, 20);    //设置"备注"标签位置。
        publisherLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(publisherLabel);    //添加"备注"标签。
        //创建"出版社"文本域
        publisherText = new JTextField(20);
        publisherText.setBounds(175, 280, 260, 30);    //设置"备注"文本域位置。
        centerJPanel.add(publisherText); //添加"备注"文本域
        c.add(centerJPanel, BorderLayout.CENTER);

        //------------------------------------------------------------//
        //创建"单价"标签
        priceLabel = new JLabel("单价：");
        SetIconUtil.setIcon(priceLabel, "img/type.png");
        priceLabel.setBounds(690, 109, 140, 20);    //设置"类型"标签位置。
        priceLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(priceLabel);    //添加"类型"标签。
        //创建"单价"文本域
        priceText = new JTextField(26);
        priceText.setBounds(775, 105, 260, 30);    //设置"类型"文本域位置。
        priceText.grabFocus();//获得光标。
        centerJPanel.add(priceText);    //添加"类型"文本域。

        //创建"出版日期"标签
        dateLabel = new JLabel("出版日期：");
        SetIconUtil.setIcon(dateLabel, "img/money.png");
        dateLabel.setBounds(650, 164, 150, 20);
        dateLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(dateLabel);    //添加"金额"标签。
        //创建"出版日期"文本域
        dateText = new JTextField(30);
        dateText.setBounds(775, 160, 260, 30);
        centerJPanel.add(dateText);

        //创建"ISBN"标签
        iSBNLabel = new JLabel("ISBN：");
        SetIconUtil.setIcon(iSBNLabel, "img/date.png");
        iSBNLabel.setBounds(680, 229, 150, 20);    //设置"日期"标签位置。
        iSBNLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(iSBNLabel);    //添加"日期"标签。
        //创建"ISBN"文本域
        iSBNText = new JTextField(25);
        iSBNText.setBounds(775, 225, 260, 30);    //设置"日期"文本域位置。
        centerJPanel.add(iSBNText); //添加"日期"文本域。

        //创建"库存数量"标签
        amountLabel = new JLabel("库存数量：");
        SetIconUtil.setIcon(amountLabel, "img/remark.png");
        amountLabel.setBounds(650, 284, 160, 20);    //设置"备注"标签位置。
        amountLabel.setFont(new Font("隶书", Font.BOLD, 20));
        centerJPanel.add(amountLabel);    //添加"备注"标签。
        //创建"库存数量"文本域
        amountText = new JTextField(20);
        amountText.setBounds(775, 280, 260, 30);    //设置"备注"文本域位置。
        centerJPanel.add(amountText); //添加"备注"文本域
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
        BulkImportUtil.setFont(font, typeText, priceText , iSBNText, amountText,dateText ,bookNameText, autorText, publisherText, sureButton, cancelButton);

        //设置左上角
        URL imgLogo = AddView.class.getClassLoader().getResource("img/logo2.png");
        assert imgLogo != null;
        Image image = new ImageIcon(imgLogo).getImage();
        setIconImage(image);

        //设置窗口不可拉伸
        setResizable(false);
        getRootPane().setDefaultButton(sureButton);
        //设置监听
        sureButton.addActionListener(addHandler);
        cancelButton.addActionListener(addHandler);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}

