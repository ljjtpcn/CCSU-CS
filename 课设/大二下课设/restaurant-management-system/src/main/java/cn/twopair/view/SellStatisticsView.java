package cn.twopair.view;

import cn.twopair.services.Services;
import cn.twopair.services.impl.ServicesImpl;
import lombok.Getter;
import lombok.Setter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.ArrayList;

/**
 * @ClassName sellStatisticsView
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/19 下午4:58
 * @Version 1.0
 **/


@Getter
@Setter
public class SellStatisticsView {
    DefaultCategoryDataset dataset;
    ChartFrame frame;

    public SellStatisticsView() {
        /* 1、数据集对象 DefaultCategoryDataset */
        dataset = new DefaultCategoryDataset();
        /* 2、数据集对象 DefaultCategoryDataset 中添加数据 */
        setData();

        /* 3、创建图形对象 JFreeChart：主标题的名称，图标显示的数据集合，是否显示子标题，是否生成提示的标签，是否生成URL链接 */
        JFreeChart chart = ChartFactory.createBarChart("销售额统计表", null, "销售额", dataset, PlotOrientation.VERTICAL, true,
                true, true);
        /* 4、处理乱码 */
        // 处理主标题的乱码
        chart.getTitle().setFont(new Font("楷体", Font.BOLD, 18));
        // 处理子标题乱码
        chart.getLegend().setItemFont(new Font("楷体", Font.BOLD, 18));
        /* 5、获取图表区域对象 CategoryPlot */
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        // 获取X轴的对象
        CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
        // 获取Y轴的对象
        NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
        // 处理X轴上的乱码
        categoryAxis.setTickLabelFont(new Font("楷体", Font.BOLD, 15));
        // 处理X轴外的乱码
        categoryAxis.setLabelFont(new Font("楷体", Font.BOLD, 15));
        // 处理Y轴上的乱码
        numberAxis.setTickLabelFont(new Font("楷体", Font.BOLD, 15));
        // 处理Y轴外的乱码
        numberAxis.setLabelFont(new Font("楷体", Font.BOLD, 15));
        // 处理Y轴上显示的刻度，以10作为1格
        numberAxis.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(10);
        numberAxis.setTickUnit(unit);
        // 获取绘图区域对象
        BarRenderer barRenderer = (BarRenderer) categoryPlot.getRenderer();
        // 设置柱形图的宽度
        barRenderer.setMaximumBarWidth(0.07);

        // 使用ChartFrame对象显示图像
        frame = new ChartFrame("本年度销售额统计", chart);
        frame.pack();
    }

    protected void setData() {
        Services services = new ServicesImpl();
        ArrayList<Double> data = services.getStatisticsData();

        dataset.addValue(data.get(0), "一季度销售额", "一季度");
        dataset.addValue(data.get(1), "二季度销售额", "二季度");
        dataset.addValue(data.get(2), "三季度销售额", "三季度");
        dataset.addValue(data.get(3), "四季度销售额", "四季度");
    }
}
