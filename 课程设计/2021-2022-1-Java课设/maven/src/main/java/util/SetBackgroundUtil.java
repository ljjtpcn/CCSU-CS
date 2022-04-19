package util;

import javax.swing.*;
import java.awt.*;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/23 8:19
 */

public class SetBackgroundUtil extends JPanel {
    ImageIcon icon;
    Image img;

    public  SetBackgroundUtil(String path) {
        //  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片
        icon = new ImageIcon(getClass().getClassLoader().getResource(path));
        img = icon.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}