package cn.twopair.view.window.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author 李佳骏 ljjtpcn@163.com
 * @date [2021/12/12 9:16]
 * @description TODO 自定义表格渲染方式
 */
public class MainViewCellRender extends DefaultTableCellRenderer {
    /**
     * 在每一行的每一列显示之前都会调用
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int styleNumber = 2;
        if (row % styleNumber == 0) {
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }

        //内容居中
        setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
