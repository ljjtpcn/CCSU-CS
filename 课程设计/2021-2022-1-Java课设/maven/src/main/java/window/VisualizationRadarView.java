package window;


import com.yujianbo.swing.tools.SwingService;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import req.GraphicaRequest;
import res.GraphicalDTO;
import service.impl.GraphicalServiceimpl;

import javax.swing.*;

import java.awt.*;
import java.net.URL;
import java.util.Vector;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/24 10:35
 */

public class VisualizationRadarView extends JFrame {

    private String username;
    Double maxValue = 0d;

    public VisualizationRadarView(StatisticsView statisticsView, MainView mainView) {
        this.username = mainView.getUser();
        JDialog jf = new JDialog(statisticsView, "雷达统计图", true);
        jf.setSize(1800, 960);

        GraphicalServiceimpl graphicalRequest = new GraphicalServiceimpl();
        GraphicaRequest request = new GraphicaRequest();
        request.setUsername(username);
        GraphicalDTO graphicalDTO = graphicalRequest.retrieveGraphical(request);

        //总收入
        Vector<Vector<String>> dataIncome = graphicalDTO.getDataIncome();
        //总支出
        Vector<Vector<String>> dataExpend = graphicalDTO.getDataExpend();
        //今年收支数据
        if (dataIncome.size() == 0 && dataExpend.size() == 0) {
            JOptionPane.showMessageDialog(statisticsView, "没有数据哦");
            return;
        }
        Vector<String> incomeData = null;
        Vector<String> expendData = null;
        if (dataIncome.size() != 0) {
            incomeData = dataIncome.get(dataIncome.size() - 1);
        }
        if (dataExpend.size() != 0) {
            expendData = dataExpend.get(dataExpend.size() - 1);
        }


        //与图有关          收入
        DefaultCategoryDataset datasetSpider = new DefaultCategoryDataset();
        Vector<Double> arr = new Vector<>();
        double t;
        if (incomeData != null && incomeData.size() != 0) {
            for (int i = 1; i < incomeData.size(); i++) {
                t = Double.parseDouble(incomeData.get(i));
                maxValue = Math.max(maxValue, t);
                arr.add(t);
            }
            String key1 = "收入";
            datasetSpider.addValue(arr.get(0), key1, "一月");
            datasetSpider.addValue(arr.get(1), key1, "二月");
            datasetSpider.addValue(arr.get(2), key1, "三月");
            datasetSpider.addValue(arr.get(3), key1, "四月");
            datasetSpider.addValue(arr.get(4), key1, "五月");
            datasetSpider.addValue(arr.get(5), key1, "六月");
            datasetSpider.addValue(arr.get(6), key1, "七月");
            datasetSpider.addValue(arr.get(7), key1, "八月");
            datasetSpider.addValue(arr.get(8), key1, "九月");
            datasetSpider.addValue(arr.get(9), key1, "十月");
            datasetSpider.addValue(arr.get(10), key1, "十一月");
            datasetSpider.addValue(arr.get(11), key1, "十二月");
        }

/**********************支出************************/

        arr.setSize(0);
        if (expendData != null && expendData.size() != 0) {
            for (int i = 1; i < expendData.size(); i++) {
                t = Double.parseDouble(expendData.get(i));
                maxValue = Math.max(maxValue, -t);
                arr.add(-t);
            }
            String key2 = "支出";
            datasetSpider.addValue(arr.get(0), key2, "一月");
            datasetSpider.addValue(arr.get(1), key2, "二月");
            datasetSpider.addValue(arr.get(2), key2, "三月");
            datasetSpider.addValue(arr.get(3), key2, "四月");
            datasetSpider.addValue(arr.get(4), key2, "五月");
            datasetSpider.addValue(arr.get(5), key2, "六月");
            datasetSpider.addValue(arr.get(6), key2, "七月");
            datasetSpider.addValue(arr.get(7), key2, "八月");
            datasetSpider.addValue(arr.get(8), key2, "九月");
            datasetSpider.addValue(arr.get(9), key2, "十月");
            datasetSpider.addValue(arr.get(10), key2, "十一月");
            datasetSpider.addValue(arr.get(11), key2, "十二月");
        }

        System.out.println(maxValue);
        jf.add(SwingService.getSpiderChart(datasetSpider, maxValue, true, 12, null, null, "用户今年月度收支统计图", new Font("楷体", Font.BOLD, 15), false));

        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

        //设置左上角
        URL imgLogo = LoginView.class.getClassLoader().getResource("img/logo.png");
        assert imgLogo != null;
        Image image = new ImageIcon(imgLogo).getImage();
        setIconImage(image);
    }

    public void addIncomeItem(TimeSeries timeseries, int month, String year, String value, double pre) {
        timeseries.add(new Month(month, Integer.parseInt(year)), Double.parseDouble(value) + pre);
    }

    public void addExpendItem(TimeSeries timeseries, int month, String year, String value, double pre) {
        timeseries.add(new Month(month, Integer.parseInt(year)), (Double.parseDouble(value) + pre) * -1);
    }
}

