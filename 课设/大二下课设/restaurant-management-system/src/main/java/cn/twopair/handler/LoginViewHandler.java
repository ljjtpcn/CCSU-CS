package cn.twopair.handler;

import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import cn.twopair.utils.CheckUtil;
import cn.twopair.view.LoginView;
import cn.twopair.view.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName LoginHandler
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/10 下午5:33
 * @Version 1.0
 **/
public class LoginViewHandler implements ActionListener {

    LoginView loginView;

    public LoginViewHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        String ok = "登录";
        if (ok.equals(text)) {
            String inputUsername = loginView.getUserNameField().getText().trim();
            String inputPassword = String.valueOf(loginView.getPassWordField().getPassword()).trim();
            String code = loginView.getCode().toLowerCase();
            String inputCode = loginView.getInputCode().getText().toLowerCase();

            if (CheckUtil.isEmpty(inputUsername, inputPassword)) {
                JOptionPane.showMessageDialog(loginView, "用户名或密码不能为空",
                        "错误", JOptionPane.ERROR_MESSAGE);
                loginView.reloadCode();

            } else if (!code.equals(inputCode)) {
                JOptionPane.showMessageDialog(loginView, "验证码错误",
                        "错误", JOptionPane.ERROR_MESSAGE);
                loginView.reloadCode();

            } else {
                Services services = new ServicesImpl();
                boolean flag = services.login(inputUsername, inputPassword);
                if (flag) {
                    new MenuView().setVisible(true);
                    loginView.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginView, "密码错误",
                            "错误", JOptionPane.ERROR_MESSAGE);
                    loginView.reloadCode();
                }
            }

        }

    }
}
