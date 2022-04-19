package window.ext;


import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-12 13:18
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
public class TotalTable extends JTable {

    public TotalTable(){
        JComboBox boc = new JComboBox();
        //设置表头
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font("隶书", Font.BOLD, 20));
        tableHeader.setForeground(Color.RED);
        //设置表格体
        setFont(new Font("楷体", Font.PLAIN, 18));
        setForeground(Color.BLACK);
        setGridColor(Color.BLACK);
        setRowHeight(30);
        //设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public void renderRuler() {
        //设置每表格列的渲染方式
        Vector<Object> columns = TotalTableModel.getColumns();

        MainViewCellRender render = new MainViewCellRender();
        for (Object column : columns) {
            TableColumn tableColumn = getColumn(column);
            tableColumn.setCellRenderer(render);
            if("id".equals(column)){
                tableColumn.setPreferredWidth(30);
                tableColumn.setMaxWidth(30);
                tableColumn.setResizable(false);
            }
        }
    }
}
