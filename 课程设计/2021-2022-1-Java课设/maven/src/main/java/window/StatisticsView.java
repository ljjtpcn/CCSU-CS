package window;

import handler.StatisticsViewHandler;
import util.BulkImportUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/23 20:39
 */

public class StatisticsView extends JDialog {
    //整体
    private JDialog frame;
    private JLabel imgLabel;
    //单选框
    private JLabel incomeLabel;
    private JRadioButton addButton;
    private JRadioButton payButton;
    private ButtonGroup incomeGroup;
    //本周
    private JLabel thisWeekLabel;
    private JLabel thisWeektextField;
    //上周
    private JLabel lastWeekLabel;
    private JLabel lastWeekTextField;
    //这个月
    private JLabel thisMonthLabel;
    private JLabel thisMonthTextField;
    //上个月
    private JLabel lastMonthTextField;
    private JLabel lastMonthLabel;
    //今年
    private JLabel thisYearLabel;
    private JLabel thisYearTextField;
    //去年
    private JLabel lastYearTextField;
    private JLabel lastYearLabel;
    //折线图,雷达图按钮
    private JButton lineButton;
    private JButton radarButton;
    MainView mainView;
    Font kFont = new Font("楷体", Font.BOLD, 25);
    Font lFont = new Font("隶书", Font.PLAIN, 23);

    public StatisticsView(MainView mainView) {
        this.mainView = mainView;
        frame = new JDialog(mainView, "统计", true);
        frame.setBounds(100, 100, 720, 560);
        frame.getContentPane().setLayout(null);
        //统计方式
        incomeLabel = new JLabel("统计方式：");
        incomeLabel.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/statistic.png")));
        incomeLabel.setBounds(74, 30, 180, 35);
        incomeLabel.setFont(kFont);
        frame.getContentPane().add(incomeLabel);
        //单选框
        //设置整体
        addButton = new JRadioButton("收入");
        addButton.setBackground(Color.WHITE);
        payButton = new JRadioButton("支出");
        payButton.setBackground(Color.WHITE);
        incomeGroup = new ButtonGroup();
        incomeGroup.add(addButton);
        incomeGroup.add(payButton);
        //设置收入和支出的大小和位置
        addButton.setBounds(260, 30, 105, 35);
        addButton.setFont(kFont);
        payButton.setBounds(421, 30, 96, 35);
        payButton.setFont(kFont);

        //添加
        frame.getContentPane().add(addButton);
        frame.getContentPane().add(payButton);

        //本周帐单
        thisWeekLabel = new JLabel("本周账单:");
        thisWeekLabel.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/thisWeek.png")));
        thisWeekLabel.setFont(kFont);
        thisWeekLabel.setBounds(74, 90, 180, 35);
        frame.getContentPane().add(thisWeekLabel);
        //文本框
        thisWeektextField = new JLabel();
        thisWeektextField.setBounds(230, 90, 300, 35);
        frame.getContentPane().add(thisWeektextField);
//        thisWeektextField.setColumns(10);

        //上周账单
        lastWeekLabel = new JLabel("上周账单：");
        lastWeekLabel.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/lastWeek.png")));
        lastWeekLabel.setFont(kFont);
        lastWeekLabel.setBounds(74, 150, 180, 35);
        frame.getContentPane().add(lastWeekLabel);
        //用户名文本框
        lastWeekTextField = new JLabel();
//        lastWeekTextField.setColumns(10);
        lastWeekTextField.setBounds(230, 150, 300, 35);
        frame.getContentPane().add(lastWeekTextField);

        //本月账单
        thisMonthLabel = new JLabel("本月账单：");
        thisMonthLabel.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/thisMonth.png")));
        thisMonthLabel.setFont(kFont);
        thisMonthLabel.setBounds(74, 210, 180, 35);
        frame.getContentPane().add(thisMonthLabel);
        //文本框
        thisMonthTextField = new JLabel();
//        thisMonthTextField.setColumns(10);
        thisMonthTextField.setBounds(230, 210, 300, 35);
        frame.getContentPane().add(thisMonthTextField);

        //上个月账单
        lastMonthLabel = new JLabel("上月账单：");
        lastMonthLabel.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/lastMonth.png")));
        lastMonthLabel.setFont(kFont);
        lastMonthLabel.setBounds(74, 270, 163, 35);
        frame.getContentPane().add(lastMonthLabel);
        //文本框
        lastMonthTextField = new JLabel();
//        lastMonthTextField.setColumns(10);
        lastMonthTextField.setBounds(230, 270, 298, 35);
        frame.getContentPane().add(lastMonthTextField);

        //今年账单
        thisYearLabel = new JLabel("今年账单：");
        thisYearLabel.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/thisYear.png")));
        thisYearLabel.setFont(kFont);
        thisYearLabel.setBounds(74, 330, 180, 35);
        frame.getContentPane().add(thisYearLabel);
        //文本框
        thisYearTextField = new JLabel();
//        thisYearTextField.setColumns(10);
        thisYearTextField.setBounds(230, 330, 300, 35);
        frame.getContentPane().add(thisYearTextField);

        //去年账单
        lastYearLabel = new JLabel("去年账单：");
        lastYearLabel.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/lastYear.png")));
        lastYearLabel.setFont(kFont);
        lastYearLabel.setBounds(74, 390, 180, 35);
        frame.getContentPane().add(lastYearLabel);
        //文本框
        lastYearTextField = new JLabel();
//        lastYearTextField.setColumns(10);
        lastYearTextField.setBounds(230, 390, 300, 35);
        frame.getContentPane().add(lastYearTextField);
        //折线图
        lineButton = new JButton("折线图");
        lineButton.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/brokenLine.png")));
        lineButton.setFont(new Font("楷体", Font.BOLD, 14));
        lineButton.setBounds(555, 455, 120, 46);
        frame.getContentPane().add(lineButton);
        //雷达图
        radarButton = new JButton("雷达图");
        radarButton.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/radar.png")));
        radarButton.setFont(new Font("楷体", Font.BOLD, 14));
        radarButton.setBounds(20, 455, 120, 46);
        frame.getContentPane().add(radarButton);
        //背景
        imgLabel = new JLabel("New label");
        imgLabel.setIcon(new ImageIcon(StatisticsView.class.getClassLoader().getResource("img/StatisticsViewbk.jpg")));
        imgLabel.setBounds(0, 0, 720, 560);
        frame.getContentPane().add(imgLabel);

        //边框透明
        thisWeektextField.setBorder(new LineBorder(Color.WHITE));
        lastWeekTextField.setBorder(new LineBorder(Color.WHITE));
        thisMonthTextField.setBorder(new LineBorder(Color.WHITE));
        lastMonthTextField.setBorder(new LineBorder(Color.WHITE));
        thisYearTextField.setBorder(new LineBorder(Color.WHITE));
        lastYearTextField.setBorder(new LineBorder(Color.WHITE));

        //监听
        StatisticsViewHandler statisticsViewHandler = new StatisticsViewHandler(this);
        lineButton.addActionListener(e -> new VisualizationLineView(this, this.getMainView()));
        radarButton.addActionListener(e -> new VisualizationRadarView(this, this.getMainView()));
        addButton.addActionListener(statisticsViewHandler);
        payButton.addActionListener(statisticsViewHandler);

        BulkImportUtil.setFont(lFont, thisWeektextField, lastWeekTextField,
                thisMonthTextField, lastMonthTextField, thisYearTextField, lastYearTextField);
        lineButton.setContentAreaFilled(false);
        lineButton.setBorderPainted(false);
        radarButton.setContentAreaFilled(false);
        radarButton.setBorderPainted(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JRadioButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JRadioButton addButton) {
        this.addButton = addButton;
    }

    public JRadioButton getPayButton() {
        return payButton;
    }

    public void setPayButton(JRadioButton payButton) {
        this.payButton = payButton;
    }

    public ButtonGroup getIncomeGroup() {
        return incomeGroup;
    }

    public void setIncomeGroup(ButtonGroup incomeGroup) {
        this.incomeGroup = incomeGroup;
    }

    public JLabel getThisWeektextField() {
        return thisWeektextField;
    }

    public void setThisWeektextField(JLabel thisWeektextField) {
        this.thisWeektextField = thisWeektextField;
    }

    public JLabel getLastWeekTextField() {
        return lastWeekTextField;
    }

    public void setLastWeekTextField(JLabel lastWeekTextField) {
        this.lastWeekTextField = lastWeekTextField;
    }

    public JLabel getThisMonthTextField() {
        return thisMonthTextField;
    }

    public void setThisMonthTextField(JLabel thisMonthTextField) {
        this.thisMonthTextField = thisMonthTextField;
    }

    public JLabel getLastMonthTextField() {
        return lastMonthTextField;
    }

    public void setLastMonthTextField(JLabel lastMonthTextField) {
        this.lastMonthTextField = lastMonthTextField;
    }

    public JLabel getThisYearTextField() {
        return thisYearTextField;
    }

    public void setThisYearTextField(JLabel thisYearTextField) {
        this.thisYearTextField = thisYearTextField;
    }

    public JLabel getLastYearTextField() {
        return lastYearTextField;
    }

    public void setLastYearTextField(JLabel lastYearTextField) {
        this.lastYearTextField = lastYearTextField;
    }

    public JButton getLineButton() {
        return lineButton;
    }

    public void setLineButton(JButton lineButton) {
        this.lineButton = lineButton;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }


}
