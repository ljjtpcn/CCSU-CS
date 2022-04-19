package res;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/23 10:39
 */

public class TotalIncomeDTO {
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "TotalIncomeDTO{" +
                "total=" + total +
                '}';
    }
}
