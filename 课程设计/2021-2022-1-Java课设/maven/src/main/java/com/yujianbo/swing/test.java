//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yujianbo.swing;


import java.awt.Font;

import org.jfree.chart.ChartPanel;
import org.jfree.data.time.TimeSeries;

public class test {

    public test() {
    }


    public static class chart {
        public chart() {
        }

        public static ChartPanel getChartSeriesTime(TimeSeries[] timeSeries, int width, int height, String title, String xTitle, String yTitle, boolean tooltips, Font xTitleFont, Font xCategoryFont, Font yTitleFont, Font topTitleFont, Font bottomTitleFont) {
            return SwingCharts.getSeriesChartTime(timeSeries, width, height, title, xTitle, yTitle, tooltips, xTitleFont, xCategoryFont, yTitleFont, topTitleFont, bottomTitleFont);
        }

    }


}
