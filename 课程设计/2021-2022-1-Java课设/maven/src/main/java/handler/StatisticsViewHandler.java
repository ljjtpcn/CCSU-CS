package handler;

import res.TableDTO;
import window.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/23 21:53
 */

public class StatisticsViewHandler implements ActionListener {
    private StatisticsView statisticsView;

    public StatisticsViewHandler(StatisticsView statisticsView) {
        this.statisticsView = statisticsView;
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void actionPerformed(ActionEvent e) {
        if (statisticsView.getAddButton().isSelected()) {
            TableDTO tableDTO = new TotalIncomeDialog(statisticsView.getMainView(), statisticsView.getMainView()).getTableDTO();
            showData(tableDTO);

        } else {
            TableDTO tableDTO = new TotalExpendDialog(statisticsView.getMainView(), statisticsView.getMainView()).getTableDTO();
            showData(tableDTO);
        }
    }

    private void showData(TableDTO tableDTO) {

        double thisWeekTotal = tableDTO.getThisWeekTotal();
        double lastWeekTotal = tableDTO.getLastWeekTotal();
        double thisMonthTotal = tableDTO.getThisMonthTotal();
        double lastMonthTotal = tableDTO.getLastMonthTotal();
        double thisYearTotal = tableDTO.getThisYear();
        double lastYearTotal = tableDTO.getLastYear();

        statisticsView.getThisWeektextField().setText(String.valueOf(thisWeekTotal));
        statisticsView.getLastWeekTextField().setText(String.valueOf(lastWeekTotal));
        statisticsView.getThisMonthTextField().setText(String.valueOf(thisMonthTotal));
        statisticsView.getLastMonthTextField().setText(String.valueOf(lastMonthTotal));
        statisticsView.getThisYearTextField().setText(String.valueOf(thisYearTotal));
        statisticsView.getLastYearTextField().setText(String.valueOf(lastYearTotal));


    }
}
