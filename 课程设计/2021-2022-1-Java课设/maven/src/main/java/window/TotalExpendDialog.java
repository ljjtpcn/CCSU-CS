package window;

import req.StudentRequest;
import res.TableDTO;
import service.impl.StudentServiceImpl;
import window.ext.TotalTable;
import window.ext.TotalTableModel;

import javax.swing.*;
import java.awt.*;


/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/23 10:20
 */

public class TotalExpendDialog extends JFrame {
    JPanel root = new JPanel(new BorderLayout());

    TableDTO tableDTO;
    final JDialog dialog;
    public TotalExpendDialog(MainView mainView, Component parentComponent) {
        // 创建一个模态对话框
        dialog = new JDialog(mainView, "支出查询", true);
        // 设置对话框的宽高
        dialog.setSize(960, 540);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(parentComponent);

        StudentServiceImpl studentService = new StudentServiceImpl();
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setUser(mainView.getUser());
        studentRequest.setPageNow(1);
        studentRequest.setPageSize(mainView.getPageSize());

        tableDTO = studentService.totalExpendStudents(studentRequest);

        TotalTable totalTable = new TotalTable();
        TotalTableModel totalTableModel = TotalTableModel.assembleModel(tableDTO.getData());
        //JTable关联model
        totalTable.setModel(totalTableModel);
        //设置表格渲染方式
        totalTable.renderRuler();
        JScrollPane jScrollPane = new JScrollPane(totalTable);
//        south.setPreferredSize(new Dimension(0, 120));


        root.add(jScrollPane, BorderLayout.CENTER);
//        root.add(south, BorderLayout.SOUTH);
        dialog.setContentPane(root);
    }


    public TableDTO getTableDTO() {
        return tableDTO;
    }

    public void setTableDTO(TableDTO tableDTO) {
        this.tableDTO = tableDTO;
    }

    public JDialog getDialog() {
        return dialog;
    }
}
