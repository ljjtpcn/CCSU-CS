package handler;

import entity.AdminDO;
import window.LoginView;
import window.MainView;
import service.AdminService;
import service.impl.AdminServiceImpl;
import window.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-11 16:52
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */

public class LoginHandler extends KeyAdapter implements ActionListener {

    private LoginView loginView;

    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }


    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        if ("注册".equals(text)) {
            new RegisterView(loginView);

        } else if ("登录".equals(text)) {
            login();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (KeyEvent.VK_ENTER == e.getKeyCode()) {
            login();
        }
    }


    private void login() {
        String inputUser = loginView.getUserNameField().getText();
        String inputPwd = loginView.getPassWordField().getText();
        String inputCode = loginView.getInputCode().getText();
        String code = loginView.getCode();
        //username.trim()去除首尾空格,判空字符
        if (inputUser == null || inputPwd == null ||
                "".equals(inputUser.trim()) || "".equals(inputPwd.trim())) {
            //弹出一个警告框
            JOptionPane.showMessageDialog(loginView, "用户名或密码不能为空!请重新输入");
            return;
        }
        if (inputCode == null || "".equals(inputCode.trim())) {
            //弹出一个警告框
            JOptionPane.showMessageDialog(loginView, "请输入验证码!");
            return;
        }
        if (!code.equalsIgnoreCase(inputCode)) {
            JOptionPane.showMessageDialog(loginView, "验证码错误");
            loginView.getInputCode().setText("");
            loginView.getPicture(loginView.getAuthCode(), loginView.getCodePanel());
            return;
        }

        //查询db
        AdminService adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUsername(inputUser);
        adminDO.setPwd(inputPwd);

        //返回校验结果做出判断
        if (adminService.validateAdmin(adminDO)) {

            //跳转到主页面并销毁当前登录页面
            JOptionPane.showMessageDialog(loginView, "登录成功");

            //新界面,且传入主界面登陆的用户名以查询对应账号的记录
            new MainView("学生理财系统", adminDO.getUsername());

            //销毁登陆界面
            loginView.dispose();
        } else {
            loginView.setCnt(loginView.getCnt() + 1);
            if (loginView.getCnt() >= 3) {
                JOptionPane.showMessageDialog(loginView, "忘记密码?请联系李佳骏或戴博管理员");
                return;
            }
            JOptionPane.showMessageDialog(loginView, "账号不存在或密码错误");
            loginView.getPicture(loginView.getAuthCode(), loginView.getCodePanel());
            loginView.getInputCode().setText("");
        }

    }
}
