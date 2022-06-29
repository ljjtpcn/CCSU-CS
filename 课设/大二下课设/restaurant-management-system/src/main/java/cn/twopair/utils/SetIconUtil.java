package cn.twopair.utils;


import javax.swing.*;
import java.net.URL;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 14:13
 */

public class SetIconUtil {

    public static void setIcon(JLabel jLabel, String path){
        URL url = SetIconUtil.class.getClassLoader().getResource(path);
        Icon icon = new ImageIcon(url);
        jLabel.setIcon(icon);
    }

    public static void setIcon(JButton jButton, String path){
        URL url = SetIconUtil.class.getClassLoader().getResource(path);
        Icon icon = new ImageIcon(url);
        jButton.setIcon(icon);
    }

    public static void setIcon(JRadioButton jButton, String path){
        URL url = SetIconUtil.class.getClassLoader().getResource(path);
        Icon icon = new ImageIcon(url);
        jButton.setIcon(icon);
    }
}
