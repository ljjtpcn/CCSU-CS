package handler;

import entity.AdminDO;
import entity.RegisterDO;
import service.AdminService;
import service.RegisterService;
import service.impl.AdminServiceImpl;
import service.impl.RegisterServiceImpl;
import window.LoginView;
import window.MainView;
import window.RegisterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-11 16:52
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */

public class RegisterHandler implements ActionListener {

    private RegisterView registerView;

    public RegisterHandler(RegisterView registerView) {
        this.registerView = registerView;
    }


    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        if ("注册".equals(text)) {
            if (regist()) {
                registerView.dispose();
            }
        } else if ("取消".equals(text)) {

            registerView.dispose();
        }
    }

    private boolean regist() {
        //获取注册信息
        String username = registerView.getUsernameText().getText();
        char[] s = registerView.getPasswordText().getPassword();
        String password = String.valueOf(s);
        char[] reS = registerView.getRepasswordText().getPassword();
        String rePassword = String.valueOf(reS);
        String sex = null;
        if (registerView.getMaleButton().isSelected()) {
            sex = "男";
        } else if (registerView.getFemaleButton().isSelected()) {
            sex = "女";
        }


        //开始处理数据
        if (username != null && password != null && rePassword != null && sex != null &&
                !"".equals(username.trim()) && !"".equals(password.trim()) && !"".equals(rePassword.trim())
                && password.equals(rePassword)) {

            //发送注册请求
            RegisterService registerService = new RegisterServiceImpl();
            RegisterDO registerDO = new RegisterDO();
            registerDO.setUsername(username);
            registerDO.setPassword(password);
            registerDO.setSex(sex);
            if (registerService.validateRegister(registerDO) == 1) {
                JOptionPane.showMessageDialog(registerView, "注册成功,请返回登录!");
                return true;
            } else if (registerService.validateRegister(registerDO) == 0) {
                JOptionPane.showMessageDialog(registerView, "用户名重复,请重新注册!");
            }
        } else {
            JOptionPane.showMessageDialog(registerView, "请检查信息是否填写正确!!!");

        }

        return false;
    }


}
