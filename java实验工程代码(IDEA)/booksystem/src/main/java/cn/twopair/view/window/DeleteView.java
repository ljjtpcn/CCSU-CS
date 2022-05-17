package cn.twopair.view.window;


import cn.twopair.Main;
import cn.twopair.view.handler.DeleteHandler;
import cn.twopair.view.handler.special.NumberHandler;
import cn.twopair.view.util.SetIconUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 16:28
 */

public class DeleteView extends JDialog {
    private JPanel contentPane;//创建顶层容器
    private JTextField idTextField;//文本框
    private JLabel idLabel;//标签
    private JButton cancelButton;//按钮
    private JButton sureButton;//按钮
    private JLabel topLabel;
    private JLabel imglabel;


    NumberHandler numberHandler = new NumberHandler();
    //构造方法
    public DeleteView(MainView mainView) {
        super(mainView,"删除图书记录", true);
        DeleteHandler deleteHandler = new DeleteHandler(this, mainView);
        setBounds(100, 100, 720, 560);
        contentPane = new JPanel();
        this.setContentPane(contentPane);    //给frame窗口加一个panel
        contentPane.setLayout(null);    //设置布局为null
        //顶部设置标签
        topLabel = new JLabel("删除图书记录");    //创建顶部标签、设置字体大小
        topLabel.setFont(new Font("楷体", Font.BOLD, 31));
        topLabel.setBounds(264, 26, 242, 48);    //设置位置
        contentPane.add(topLabel);
        //设置id标签
        idLabel = new JLabel("请输入id：");    //设置标签，在标签前面加上图片
        SetIconUtil.setIcon(idLabel, "img/edit.png");
        idLabel.setFont(new Font("楷体", Font.BOLD, 35));    //字体 + 边界
        idLabel.setBounds(53, 197, 230, 68);
        contentPane.add(idLabel);
        //设置要输入id的文本框
        idTextField = new JTextField(10);    //设置id文本框、边界、字体大小
        idTextField.setBounds(283, 212, 303, 42);
        idTextField.setFont(new Font("楷体", Font.BOLD, 25));
        contentPane.add(idTextField);

        //设置"确定"按钮
        sureButton = new JButton("确定");
        SetIconUtil.setIcon(sureButton, "img/sure_min.png");
        sureButton.setFont(new Font("楷体", Font.BOLD, 25));
        sureButton.setBounds(115, 420, 125, 34);
        contentPane.add(sureButton);
        //设置"取消"按钮
        cancelButton = new JButton("取消");
        SetIconUtil.setIcon(cancelButton, "img/cancel_max.png");
        cancelButton.setFont(new Font("楷体", Font.BOLD, 25));
        cancelButton.setBounds(486, 420, 125, 34);
        contentPane.add(cancelButton);

        //设置左上角
        URL imgLogo = MainView.class.getClassLoader().getResource("img/deleteLogo.png");
        assert imgLogo != null;
        Image image = new ImageIcon(imgLogo).getImage();
        setIconImage(image);

        //设置背景图片
        imglabel = new JLabel();
        imglabel.setIcon(new ImageIcon(Objects.requireNonNull(DeleteView.class.getClassLoader().getResource("img/deleteBack.jpg"))));
        imglabel.setBounds(0, 0, 720, 560);
        contentPane.add(imglabel);


        //监听
        sureButton.addActionListener(deleteHandler);
        cancelButton.addActionListener(deleteHandler);
        idTextField.addKeyListener(numberHandler);

        getRootPane().setDefaultButton(sureButton);
        //设置窗口不可拉伸
        setResizable(false);

        sureButton.setContentAreaFilled(false);
        sureButton.setBorderPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setBorderPainted(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }




    public JTextField getIdText() {
        return idTextField;
    }
}

