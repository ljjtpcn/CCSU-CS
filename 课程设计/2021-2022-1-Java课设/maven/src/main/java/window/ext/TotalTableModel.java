package window.ext;


import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * @author 李佳骏 ljjtpcn@163.com
 * @date [2021/12/12 8:48]
 * @description TODO 自定义tableModel,单例饿汉模式
 */
public class TotalTableModel extends DefaultTableModel {
    static Vector<Object> columns = new Vector<>();

    static {
        creatCol(columns, "id", "用户名", "变动金额", "分类","日期", "备注");
    }

    private TotalTableModel() {
        //数据, 列头名
        super(null, columns);
    }

    private static TotalTableModel totalTableModel = new TotalTableModel();


    public static TotalTableModel assembleModel(Vector<Vector<Object>> data) {
        totalTableModel.setDataVector(data, columns);
        return totalTableModel;
    }
    public static void updateModel(Vector<Vector<Object>> data) {
        totalTableModel.setDataVector(data, columns);
    }

    public static Vector<Object> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        //设置不可编辑
        return false;
    }

    private static void creatCol(Vector<Object> vector, Object... elements) {
        for (Object element : elements) {
            vector.addElement(element);
        }
    }
}
