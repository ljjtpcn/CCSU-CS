package cn.twopair.view.handler;

import cn.twopair.view.window.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName LoginHandler
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/5/10 上午11:23
 * @Version 1.0
 **/
public class LoginHandler implements ActionListener {
    private LoginView loginView;
    public LoginHandler(LoginView loginView){
        this.loginView = loginView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
