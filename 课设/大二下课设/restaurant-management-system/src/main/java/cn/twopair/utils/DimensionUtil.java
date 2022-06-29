package cn.twopair.utils;

import javax.swing.*;
import java.awt.*;

/**
 * @description: TODO 获取屏幕大小
 * @author: 李佳骏
 * @date: 2021-12-12 11:07
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
public class DimensionUtil {
        public static Rectangle getBounds(){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            //保证主界面不会覆盖任务栏
            Insets screenInsets = Toolkit.getDefaultToolkit()
                    .getScreenInsets(new JFrame().getGraphicsConfiguration());

            return new Rectangle(screenInsets.left, screenInsets.top,
                    screenSize.width - screenInsets.left - screenInsets.right,
                    screenSize.height - screenInsets.top - screenInsets.bottom);
        }
}
