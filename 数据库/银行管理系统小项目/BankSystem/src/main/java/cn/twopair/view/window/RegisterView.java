package cn.twopair.view.window;

import cn.twopair.view.handler.RegisterHandler;
import cn.twopair.view.handler.special.AlphabetNumberHandler;
import cn.twopair.view.util.SetIconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

/**
 * @ClassName RegisterView
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午10:47
 * @Version 1.0
 **/
public class RegisterView extends JDialog {
        private JLabel usernameLabel;    //用户名标签。
        private JLabel passwordLabel;    //密码标签。
        private JLabel repasswordLabel;    //确认密码标签。
        private JTextField usernameText;    //用户名文本域。
        private JPasswordField passwordText;    //密码文本域。
        private JPasswordField repasswordText;    //确认密码文本域。
        private JButton registerButton;    //注册按钮。
        private JButton cancelButton;    //取消按钮。
        private JLabel sexLabel;
        private JPanel sexJPanel;
        private JRadioButton maleButton;

        public JTextField getUsernameText() {
            return usernameText;
        }

        public JPasswordField getPasswordText() {
            return passwordText;
        }

        public JPasswordField getRepasswordText() {
            return repasswordText;
        }

        public JRadioButton getMaleButton() {
            return maleButton;
        }

        public JRadioButton getFemaleButton() {
            return femaleButton;
        }

        private JRadioButton femaleButton;
        private ButtonGroup group;


        //监听器
        RegisterHandler registerHandler = new RegisterHandler(this);
        AlphabetNumberHandler alphabetNumberHandler = new AlphabetNumberHandler();
        public RegisterView() {

            this.setLayout(null);//设置为空布局。
            this.setSize(500, 500);//设置大小。
            Container c = this.getContentPane();
            c.setBackground(Color.WHITE);//设置背景颜色。

            //创建"用户名"标签。
            usernameLabel = new JLabel("账号");
            usernameLabel.setBounds(110, 110, 60, 20);    //设置"用户名"标签位置。
            usernameLabel.setFont(new Font("隶书", Font.BOLD, 18));
            c.add(usernameLabel);    //添加"用户名"标签。
            //创建"用户名"文本域
            usernameText = new JTextField();
            usernameText.setBounds(210, 110, 160, 25);    //设置"用户名"文本域位置。
            usernameText.grabFocus();//获得光标。
            usernameText.setToolTipText("请输入英文和数字的组合");
            usernameText.addKeyListener(alphabetNumberHandler);
            c.add(usernameText);    //添加"用户名"文本域。

            //创建"密码"标签
            passwordLabel = new JLabel("密码");
            passwordLabel.setBounds(110, 160, 60, 20);
            passwordLabel.setFont(new Font("隶书", Font.BOLD, 18));
            c.add(passwordLabel);    //添加"密码"标签。
            //创建"密码"文本域
            passwordText = new JPasswordField();
            passwordText.setBounds(210, 160, 160, 25);    //设置"密码"文本域位置。
            passwordText.addKeyListener(alphabetNumberHandler);
            c.add(passwordText);    //添加"密码"文本域。

            //创建"确认密码"标签
            repasswordLabel = new JLabel("确认密码");
            repasswordLabel.setBounds(110, 210, 80, 20);    //设置"确认密码"标签位置。
            repasswordLabel.setFont(new Font("隶书", Font.BOLD, 18));
            c.add(repasswordLabel);    //添加"确认密码"标签。
            //创建"确认密码"文本域
            repasswordText = new JPasswordField();
            repasswordText.setBounds(210, 210, 160, 25);    //设置"确认密码"文本域位置。
            repasswordText.addKeyListener(alphabetNumberHandler);
            c.add(repasswordText); //添加"确认密码"文本域。

            //性别单选框
            sexLabel = new JLabel("性别");
            sexLabel.setBounds(110, 260, 60, 20);
            sexLabel.setFont(new Font("隶书", Font.BOLD, 18));
            c.add(sexLabel);
            //创建性别文本域
            group = new ButtonGroup();
            maleButton = new JRadioButton("男", true);
            maleButton.setFont(new Font("隶书", Font.BOLD, 16));
            femaleButton = new JRadioButton("女");
            femaleButton.setFont(new Font("隶书", Font.BOLD, 16));
            //定义容器
            sexJPanel = new JPanel();
            sexJPanel.setBackground(Color.WHITE);
            sexJPanel.add(maleButton);
            sexJPanel.add(femaleButton);
            //组别
            group.add(maleButton);
            group.add(femaleButton);
            //设置"性别"文本域位置。
            sexJPanel.setBounds(210, 250, 160, 80);
            c.add(sexJPanel); //添加"性别"文本域

            //创建注册按钮
            registerButton = new JButton("注册");    //创建"注册"按钮。
            registerButton.setBounds(140, 340, 130, 50);    //设置"注册"按钮位置。
            registerButton.setFont(new Font("楷体", Font.PLAIN, 18));
            getRootPane().setDefaultButton(registerButton);
            c.add(registerButton);
            //创建取消按钮
            cancelButton = new JButton("取消");    //创建"取消"按钮。
            cancelButton.setBounds(300, 340, 110, 50);    //设置"取消"按钮位置。
            cancelButton.setFont(new Font("楷体", Font.PLAIN, 16));
            c.add(cancelButton);

            //设置背景图片
            //设置图标
            URL imgLogo = RegisterView.class.getClassLoader().getResource("img/logo.png");
            assert imgLogo != null;
            Image image = new ImageIcon(imgLogo).getImage();
            setIconImage(image);
            //设置确定取消图标
            SetIconUtil.setIcon(registerButton, "img/sure.png");
            SetIconUtil.setIcon(cancelButton, "img/cancel.png");
            //设置用户名图标
            SetIconUtil.setIcon(usernameLabel, "img/username.png");
            //设置密码图标
            SetIconUtil.setIcon(passwordLabel, "img/password.png");
            //设置性别图标
            SetIconUtil.setIcon(sexLabel, "img/sex.png");
            //设置性别选项图标及距离
            SetIconUtil.setIcon(maleButton, "img/male.png");
            SetIconUtil.setIcon(femaleButton, "img/female.png");
            maleButton.setIconTextGap(20);
            femaleButton.setIconTextGap(20);

            //设置监听
            registerButton.addActionListener(registerHandler);
            cancelButton.addActionListener(registerHandler);

            //窗体关闭按钮监听
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    new LoginView("学生理财系统");
                }
            });

            registerButton.setContentAreaFilled(false);
            registerButton.setBorderPainted(false);
            cancelButton.setContentAreaFilled(false);
            cancelButton.setBorderPainted(false);



            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);
        }

    }
