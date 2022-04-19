package window;

import com.yujianbo.swing.SwingService;

import com.yujianbo.swing.test;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import req.GraphicaRequest;
import res.GraphicalDTO;
import service.impl.GraphicalServiceimpl;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/24 10:35
 */

public class VisualizationLineView extends JFrame {

    private String username;
    double totalIncome;
    double totalExpend;
    //判断是否有数据
    boolean flag = false;

    public VisualizationLineView(StatisticsView statisticsView, MainView mainView) {
        this.username = mainView.getUser();
        JDialog jf = new JDialog(statisticsView, "折线统计图", true);
        jf.setSize(1800, 960);

        GraphicalServiceimpl graphicalRequest = new GraphicalServiceimpl();
        GraphicaRequest request = new GraphicaRequest();
        request.setUsername(username);
        GraphicalDTO graphicalDTO = graphicalRequest.retrieveGraphical(request);

        //收入
        Vector<Vector<String>> dataIncome = graphicalDTO.getDataIncome();
        TimeSeries timeserversIncome = new TimeSeries("收入统计");
        double pre = 0d;
        for (Vector<String> data : dataIncome) {
            Vector<String> copy = data;
            for (int i = 2; i < copy.size(); i++) {
                Double t = Double.parseDouble(copy.get(i));
                t += Double.parseDouble(copy.get(i - 1));
                copy.set(i, String.valueOf(t));
            }
            String year = data.get(0);
            for (int i = 1; i < copy.size(); i++) {
                flag = true;
                addIncomeItem(timeserversIncome, i, year, copy.get(i), pre);
            }
            pre += Double.parseDouble(copy.get(12));

        }
        totalIncome = pre;

        //支出
        Vector<Vector<String>> dataExpend = graphicalDTO.getDataExpend();
        TimeSeries timeserversExpend = new TimeSeries("支出统计");
        pre = 0d;
        for (Vector<String> data : dataExpend) {
            Vector<String> copy = data;
            for (int i = 2; i < copy.size(); i++) {
                Double t = Double.parseDouble(copy.get(i));
                t += Double.parseDouble(copy.get(i - 1));
                copy.set(i, String.valueOf(t));
            }
            String year = data.get(0);
            for (int i = 1; i < copy.size(); i++) {
                flag = true;
                addExpendItem(timeserversExpend, i, year, copy.get(i), pre);
            }
            pre += Double.parseDouble(copy.get(12));
        }
        totalExpend = pre;

        ChartPanel chartSeriesTime = test.chart.getChartSeriesTime(new TimeSeries[]{timeserversIncome, timeserversExpend},
                1800,
                960,
                "总收入支出折线图",
                "时间",
                "金额",
                false,
                new Font("楷体", 1, 15),//时间
                new Font("楷体", 1, 15),//横坐标
                new Font("楷体", 1, 15),//纵坐标
                new Font("我在星野深处等你", Font.BOLD, 20),
                new Font("楷体", 1, 12)
        );
        jf.add(chartSeriesTime);
        if (totalIncome < totalExpend * -1) {
            // 设置按钮显示效果
            UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("楷体", Font.BOLD, 16)));
            // 设置文本显示效果
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("隶书", Font.BOLD, 22)));
            JOptionPane.showMessageDialog(null, "兄台我看你骨骼惊奇,但你为什么入不敷出啊??");
        }
        setLocationRelativeTo(null);
        jf.setLocationRelativeTo(null);
        if (flag) {
            jf.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(statisticsView, "没有数据哦");
        }

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

