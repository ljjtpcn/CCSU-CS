package cn.twopair.view;

import cn.twopair.utils.BulkImportUtil;
import cn.twopair.utils.CodePictureUtil;
import cn.twopair.utils.SetIconUtil;
import cn.twopair.handler.LoginViewHandler;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * @ClassName LoginView
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/10 下午5:27
 * @Version 1.0
 **/
@Setter
@Getter
public class LoginView extends JFrame {
    /**
     * <p>FLOW_HAP 流布局hgap</p>
     * <p>FLOW_VAP 流布局vgap</p>
     * <p>LINE_HEIGHT 两行垂直上的距离</p>
     * <p>CONTENT_WIDTH 两个控件水平之间的距离</p>
     */
    private final int FLOW_HAP = 150;
    private final int FLOW_VAP = 50;
    private final int LINE_HEIGHT = 20;
    private final int CONTENT_WIDTH = 20;
    private String code;
    SpringLayout springLayout = new SpringLayout();
    LoginViewHandler loginHandler = new LoginViewHandler(this);

    Container root = getContentPane();


    JPanel centerPanel = new JPanel(springLayout);
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, FLOW_HAP, FLOW_VAP));
    JPanel codePanel = new JPanel();

    JLabel nameLabel = new JLabel("饱了么餐饮管理系统", JLabel.CENTER);
    JLabel userNameLabel = new JLabel("账号:");
    JLabel passWordLabel = new JLabel("密码:");
    JLabel authCode = new JLabel();

    JTextField inputCode = new JTextField();
    JTextField userNameField = new JTextField();
    JPasswordField passWordField = new JPasswordField();
    JButton loginButton = new JButton("登录");

    public LoginView() {
        super("饱了么餐饮管理系统");

        //将root设为根容器
        setContentPane(root);
        root.setLayout(new BorderLayout());
        //透明
        codePanel.setOpaque(false);
        centerPanel.setOpaque(false);
        southPanel.setOpaque(false);

        Font namePanelFont = new Font("我在星野深处等你", Font.PLAIN, 40);
        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        nameLabel.setFont(namePanelFont);
        userNameField.setPreferredSize(new Dimension(200, 30));
        passWordField.setPreferredSize(new Dimension(200, 30));
        inputCode.setPreferredSize(new Dimension(200, 30));
        passWordField.setEchoChar('*');
        this.setFont(namePanelFont);
        BulkImportUtil.setFont(centerFont, userNameLabel, userNameField, passWordLabel, passWordField, loginButton, inputCode);


        layoutInit();


        //设置左上角
        URL imgLogo = LoginView.class.getClassLoader().getResource("img/logo.png");
        assert imgLogo != null;
        Image image = new ImageIcon(imgLogo).getImage();
        setIconImage(image);
        //设置标题图标
        SetIconUtil.setIcon(nameLabel, "img/food.png");
        //设置用户名图标
        SetIconUtil.setIcon(userNameLabel, "img/username.png");
        //设置密码图标
        SetIconUtil.setIcon(passWordLabel, "img/password.png");
        //设置登录图标
        SetIconUtil.setIcon(loginButton, "img/ok.png");


        //设置loginButton为默认按钮
        getRootPane().setDefaultButton(loginButton);
        // 设置窗口的宽高（包括标题栏）
        setSize(700, 500);
        // 设置窗口坐标，此方法是间接设置窗口坐标，把窗口设置到指定组件的中心，null 表示屏幕
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }


    private void layoutInit() {
        //userNameLabel参考位置
        if (true) {
            //设置中心
            Spring sumWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userNameField)), Spring.constant(20));
            int offsetX = sumWidth.getValue() / 2;
            //水平居中
            springLayout.putConstraint(SpringLayout.WEST, userNameLabel, -offsetX, SpringLayout.HORIZONTAL_CENTER, centerPanel);
            //垂直居中不会不会不会不会不会
            int offsetY = 80;
            springLayout.putConstraint(SpringLayout.NORTH, userNameLabel, offsetY, SpringLayout.NORTH, centerPanel);
        }
        //userNameField
        if (true) {
            springLayout.putConstraint(SpringLayout.WEST, userNameField, CONTENT_WIDTH, SpringLayout.EAST, userNameLabel);
            springLayout.putConstraint(SpringLayout.NORTH, userNameField, 0, SpringLayout.NORTH, userNameLabel);
        }
        //passWordLabel
        if (true) {
            springLayout.putConstraint(SpringLayout.NORTH, passWordLabel, LINE_HEIGHT, SpringLayout.SOUTH, userNameLabel);
            springLayout.putConstraint(SpringLayout.EAST, passWordLabel, 0, SpringLayout.EAST, userNameLabel);
        }
        //passWordField
        if (true) {
            springLayout.putConstraint(SpringLayout.WEST, passWordField, CONTENT_WIDTH, SpringLayout.EAST, passWordLabel);
            springLayout.putConstraint(SpringLayout.NORTH, passWordField, 0, SpringLayout.NORTH, passWordLabel);
        }
        //authCode
        if (true) {
            springLayout.putConstraint(SpringLayout.NORTH, codePanel, LINE_HEIGHT, SpringLayout.SOUTH, passWordLabel);
            springLayout.putConstraint(SpringLayout.EAST, codePanel, 0, SpringLayout.EAST, passWordLabel);
            getPicture(authCode, codePanel);
        }
        //inputCode
        if (true) {
            springLayout.putConstraint(SpringLayout.WEST, inputCode, CONTENT_WIDTH, SpringLayout.EAST, codePanel);
            springLayout.putConstraint(SpringLayout.SOUTH, inputCode, -20, SpringLayout.SOUTH, codePanel);
        }


        loginButton.addActionListener(loginHandler);
        codePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    //重新获取验证码
                    getPicture(authCode, codePanel);
                }

            }
        });


        BulkImportUtil.add(centerPanel, userNameLabel, userNameField, passWordLabel, passWordField, codePanel, inputCode);
        BulkImportUtil.add(southPanel, loginButton);

        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);

        root.add(nameLabel, BorderLayout.NORTH);
        root.add(centerPanel, BorderLayout.CENTER);
        root.add(southPanel, BorderLayout.SOUTH);

    }

    //添加图片，获取验证码
    public void getPicture(JLabel label, JPanel panel) {
        Object[] obj = CodePictureUtil.createImage();
        code = obj[0].toString();

        //太小了看不清,打印一下
        System.out.println("验证码:" + code);
        ImageIcon img = new ImageIcon((BufferedImage) obj[1]);
        label.setIcon(img);
        panel.add(label);
    }

    public void reloadCode() {
        getInputCode().setText("");
        getPicture(authCode, codePanel);
    }
}
