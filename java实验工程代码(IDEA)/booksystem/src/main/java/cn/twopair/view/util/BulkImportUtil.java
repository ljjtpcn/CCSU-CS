package cn.twopair.view.util;

import javax.swing.*;
import java.awt.*;

/**
 * @description: 个人批量设置控件属性
 * @author: 李佳骏
 * @date: 2021-12-12 11:28
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
public class BulkImportUtil {

    /**
     * componentRoot为被添加面板, components为需要添加的组件参数
     *
     * @param [javax.swing.JComponent, javax.swing.JComponent...]
     * @return [void]
     * @date [2021/12/11 11:14]
     */
    public static void add(JComponent componentRoot, JComponent... components) {
        for (JComponent comp : components) {
            componentRoot.add(comp);
        }
    }


    /**
     * font为字体, <code>components</code>为需要设置为<code>font</code>字体的组件参数
     *
     * @param [java.awt.Font, javax.swing.JComponent...]
     * @return [void]
     * @date [2021/12/11 11:33]
     */
    public static void setFont(Font font, JComponent... components) {
        for (JComponent comp : components) {
            comp.setFont(font);
        }
    }

    /**
     * @Param: color 颜色
     * @Param: Labels JLanel对象
     * @Description: 批量染色
     * @Return: void
     * @Author: 李佳骏
     * @Date: 2022/5/17 下午7:19
     */
    public static void setColor(Color color, JLabel... Labels) {
        for (JLabel label : Labels) {
            label.setForeground(color);
        }
    }

}
