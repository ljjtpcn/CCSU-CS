package cn.twopair.view.window;

import cn.twopair.view.handler.LoginHandler;
import cn.twopair.view.handler.special.AlphabetNumberHandler;
import cn.twopair.view.util.BulkImportUtil;
import cn.twopair.view.util.CodePictureUtil;
import cn.twopair.view.util.SetIconUtil;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * @ClassName LoginView
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午11:16
 * @Version 1.0
 **/
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
    private int cnt; //尝试登录次数
    SpringLayout springLayout = new SpringLayout();
    LoginHandler loginHandler = new LoginHandler(this);

    //背景图片
    Container root = getContentPane();
//    BackgoundPan root;
//    {
//        try {
//            root = new BackgoundPan(ImageIO.read(new File(LoginView.class.getClassLoader().getResource("img/login.png").getPath())));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    JPanel centerPanel = new JPanel(springLayout);
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, FLOW_HAP, FLOW_VAP));
    JPanel codePanel = new JPanel();

    JLabel nameLabel = new JLabel("小型银行系统", JLabel.CENTER);
    JLabel userNameLabel = new JLabel("账号:");
    JLabel passWordLabel = new JLabel("密码:");
    JLabel authCode = new JLabel();

    JTextField inputCode = new JTextField();
    JTextField userNameField = new JTextField();
    JPasswordField passWordField = new JPasswordField();


    JButton loginButton = new JButton("登录");
    JButton registerButton = new JButton("注册");

    public LoginView(String title) {
        super(title);

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
        inputCode.setPreferredSize(new Dimension(200, 40));
        passWordField.setEchoChar('*');
        this.setFont(namePanelFont);
        BulkImportUtil.setFont(centerFont, userNameLabel, userNameField, passWordLabel, passWordField, loginButton, registerButton, inputCode);
        layoutInit();

        //设置左上角
        URL imgLogo = LoginView.class.getClassLoader().getResource("img/logo.png");
        assert imgLogo != null;
        Image image = new ImageIcon(imgLogo).getImage();
        setIconImage(image);

        //设置标题图标
        SetIconUtil.setIcon(nameLabel, "img/name.png");
        //设置用户名图标
        SetIconUtil.setIcon(userNameLabel, "img/username.png");
        //设置密码图标
        SetIconUtil.setIcon(passWordLabel, "img/password.png");
        //设置登录图标

        SetIconUtil.setIcon(loginButton, "img/ok.png");
        //设置注册图标
        SetIconUtil.setIcon(registerButton, "img/register.png");


        AlphabetNumberHandler alphabetNumberHandler = new AlphabetNumberHandler();
        userNameField.addKeyListener(alphabetNumberHandler);
        passWordField.addKeyListener(alphabetNumberHandler);

        //设置loginButton为默认按钮
        getRootPane().setDefaultButton(loginButton);
        // 设置窗口的宽高（包括标题栏）
        setSize(700, 500);
        // 设置窗口坐标，此方法是间接设置窗口坐标，把窗口设置到指定组件的中心，null 表示屏幕
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口不可拉伸
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("楷体", Font.BOLD, 16)));
        // 设置文本显示效果
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("隶书", Font.BOLD, 22)));
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
            springLayout.putConstraint(SpringLayout.SOUTH, inputCode, 0, SpringLayout.SOUTH, codePanel);
        }


        loginButton.addActionListener(loginHandler);
        registerButton.addActionListener(loginHandler);
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
        BulkImportUtil.add(southPanel, loginButton, registerButton);

        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);

        root.add(nameLabel, BorderLayout.NORTH);
        root.add(centerPanel, BorderLayout.CENTER);
        root.add(southPanel, BorderLayout.SOUTH);


    }


    //添加图片，获取验证码
    public void getPicture(JLabel label, JPanel panel) {
        Object[] obj = CodePictureUtil.createImage();
        code = obj[0].toString();
        //太小了看不清,打印一下
//        System.out.println("您的验证码为:" + code);
        ImageIcon img = new ImageIcon((BufferedImage) obj[1]);//创建图片对象
        label.setIcon(img);
        panel.add(label);
    }


    // 见LoginHandler login功能
    public JTextField getUserNameField() {
        return userNameField;
    }

    public JTextField getPassWordField() {
        return passWordField;
    }

//    public static void main(String[] args) {
//        new LoginView("dada");
//    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JLabel getAuthCode() {
        return authCode;
    }

    public void setAuthCode(JLabel authCode) {
        this.authCode = authCode;
    }

    public JTextField getInputCode() {
        return inputCode;
    }

    public void setInputCode(JTextField inputCode) {
        this.inputCode = inputCode;
    }

    public JPanel getCodePanel() {
        return codePanel;
    }

    public void setCodePanel(JPanel codePanel) {
        this.codePanel = codePanel;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}

