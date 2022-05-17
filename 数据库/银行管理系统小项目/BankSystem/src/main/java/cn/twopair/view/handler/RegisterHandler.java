package cn.twopair.view.handler;

import cn.twopair.view.window.RegisterView;
import javax.swing.*;
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
        return false;
    }


}
