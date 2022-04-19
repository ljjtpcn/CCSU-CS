package res;

import java.util.Vector;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/24 11:22
 */

public class GraphicalDTO {
    private Vector<Vector<String>> dataIncome;
    private Vector<Vector<String>> dataExpend;


    public Vector<Vector<String>> getDataIncome() {
        return dataIncome;
    }

    public void setDataIncome(Vector<Vector<String>> dataIncome) {
        this.dataIncome = dataIncome;
    }

    public Vector<Vector<String>> getDataExpend() {
        return dataExpend;
    }

    public void setDataExpend(Vector<Vector<String>> dataExpend) {
        this.dataExpend = dataExpend;
    }
}
