package res;

import java.util.Vector;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/19 23:39
 */

public class TableDTO {
    private Vector<Vector<Object>> data;
    private double lastWeekTotal;
    private double thisWeekTotal;
    private double lastMonthTotal;
    private double thisMonthTotal;
    private double lastYear;
    private double thisYear;

    //返回条数
    private int totalCount;


    public Vector<Vector<Object>> getData() {
        return data;
    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getLastWeekTotal() {
        return lastWeekTotal;
    }

    public void setLastWeekTotal(double lastWeekTotal) {
        this.lastWeekTotal = lastWeekTotal;
    }

    public double getThisWeekTotal() {
        return thisWeekTotal;
    }

    public void setThisWeekTotal(double thisWeekTotal) {
        this.thisWeekTotal = thisWeekTotal;
    }

    public double getLastMonthTotal() {
        return lastMonthTotal;
    }

    public void setLastMonthTotal(double lastMonthTotal) {
        this.lastMonthTotal = lastMonthTotal;
    }

    public double getThisMonthTotal() {
        return thisMonthTotal;
    }

    public void setThisMonthTotal(double thisMonthTotal) {
        this.thisMonthTotal = thisMonthTotal;
    }

    public double getLastYear() {
        return lastYear;
    }

    public void setLastYear(double lastYear) {
        this.lastYear = lastYear;
    }

    public double getThisYear() {
        return thisYear;
    }

    public void setThisYear(double thisYear) {
        this.thisYear = thisYear;
    }

    @Override
    public String toString() {
        return "TableDTO{" +
                "data=" + data +
                ", lastWeekTotal=" + lastWeekTotal +
                ", thisWeekTotal=" + thisWeekTotal +
                ", lastMonthTotal=" + lastMonthTotal +
                ", thisMonthTotal=" + thisMonthTotal +
                ", lastYear=" + lastYear +
                ", thisYear=" + thisYear +
                ", totalCount=" + totalCount +
                '}';
    }
}
