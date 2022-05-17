package cn.twopair.view.window;


import cn.twopair.entity.BookInfo;
import cn.twopair.service.BookService;
import cn.twopair.service.impl.BookServiceImpl;
import cn.twopair.view.handler.ChangeHandler;
import cn.twopair.view.util.BulkImportUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 8:16
 */

@ToString
@Setter
@Getter
public class ChangeView extends JDialog {

    //整体
    private JDialog frame;
    private JButton sureButton;
    //id
    private JLabel idLabel;
    private JTextField idTextField;
    //图书
    private JLabel bookNoLabel;
    private JTextField bookNoTextField;
    //书名
    private JLabel bookNameLabel;
    private JTextField bookNameTextField;
    //作者
    private JTextField authorField;
    private JLabel authorLabel;
    //出版社
    private JLabel publisherLabel;
    private JTextField publisherTextField;
    //价格
    private JLabel priceLabel;
    private JTextField priceTextField;
    //出版日期
    private JTextField publishTimeField;
    private JLabel publishTimeLabel;
    //ISBN
    private JTextField ISBNTextField;
    private JLabel ISBNLabel;
    //库存
    private JTextField amountTextField;
    private JLabel amountLabel;
    //背景图片
    private JLabel imgLabel;


    public ChangeView(MainView mainView, int selectedIndex) {
        ChangeHandler changeHandler = new ChangeHandler(this, mainView);
        frame = new JDialog(mainView, "修改图书记录", true);
        frame.setBounds(100, 100, 1440, 560);
        frame.getContentPane().setLayout(null);

        //数据回显
        BookService studentService = new BookServiceImpl();
        BookInfo bookInfo = studentService.getBySelectedId(selectedIndex);
        String id = String.valueOf(bookInfo.getId());
        String bookNo = bookInfo.getBookNo();
        String bookName = bookInfo.getBookName();
        String author = bookInfo.getAuthor();
        String publisher = bookInfo.getPublisher();
        String price = String.valueOf(bookInfo.getPrice());
        String publishTime = String.valueOf(bookInfo.getPublishTime());
        String ISBN = String.valueOf(bookInfo.getISBN());
        String amount = String.valueOf(bookInfo.getAmount());


        Font kFont = new Font("楷体", Font.BOLD, 25);
        Font lFont = new Font("隶书", Font.PLAIN, 20);

        //id标签
        idLabel = new JLabel("id:");
        //idLabel.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/id32.png"))));
        idLabel.setFont(kFont);
        idLabel.setBounds(485, 350, 180, 35);
        frame.getContentPane().add(idLabel);
        //文本框
        idTextField = new JTextField();
        idTextField.setBounds(570, 350, 300, 35);
        frame.getContentPane().add(idTextField);
        idTextField.setColumns(10);
        idTextField.setText(id);
        idTextField.setEditable(false);

        //库存标签
        amountLabel = new JLabel("库存:");
        //amountLabel.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/id32.png"))));
        amountLabel.setFont(kFont);
        amountLabel.setBounds(125, 36, 180, 35);
        frame.getContentPane().add(amountLabel);
        //文本框
        amountTextField = new JTextField();
        amountTextField.setBounds(230, 40, 300, 35);
        frame.getContentPane().add(amountTextField);
        amountTextField.setColumns(10);
        amountTextField.setText(amount);
        amountTextField.setEditable(true);

        //图书
        this.bookNoLabel = new JLabel("图书：");
        //this.bookNo.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/username32.png"))));
        this.bookNoLabel.setFont(kFont);
        this.bookNoLabel.setBounds(120, 106, 180, 35);
        frame.getContentPane().add(this.bookNoLabel);
        //图书文本框
        bookNoTextField = new JTextField();
        bookNoTextField.setColumns(10);
        bookNoTextField.setBounds(230, 110, 300, 35);
        frame.getContentPane().add(bookNoTextField);
        bookNoTextField.setText(bookNo);

        //图书名
        bookNameLabel = new JLabel("图书名：");
        //bookNameLabel.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/money32.png"))));
        bookNameLabel.setFont(kFont);
        bookNameLabel.setBounds(100, 176, 180, 35);
        frame.getContentPane().add(bookNameLabel);
        //文本框
        bookNameTextField = new JTextField();
        bookNameTextField.setColumns(10);
        bookNameTextField.setBounds(230, 180, 300, 35);
        bookNameTextField.setText(bookName);
        frame.getContentPane().add(bookNameTextField);

        //作者
        authorLabel = new JLabel("作者：");
        //authorLabel.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/type32.png"))));
        authorLabel.setFont(kFont);
        authorLabel.setBounds(120, 246, 163, 35);
        frame.getContentPane().add(authorLabel);
        //文本框
        authorField = new JTextField();
        authorField.setColumns(10);
        authorField.setBounds(230, 250, 298, 35);
        authorField.setText(author);
        frame.getContentPane().add(authorField);

//--------------------------------------------------------------------------------**//
        //出版社
        publisherLabel = new JLabel("出版社：");
        //publisherLabel.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/date32.png"))));
        publisherLabel.setFont(kFont);
        publisherLabel.setBounds(793, 36, 180, 35);
        frame.getContentPane().add(publisherLabel);
        //文本框
        publisherTextField = new JTextField();
        publisherTextField.setColumns(10);
        publisherTextField.setBounds(950, 36, 300, 35);
        publisherTextField.setText(publisher);
        frame.getContentPane().add(publisherTextField);
        //价格
        priceLabel = new JLabel("价格：");
        //priceLabel.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/date32.png"))));
        priceLabel.setFont(kFont);
        priceLabel.setBounds(817, 106, 180, 35);
        frame.getContentPane().add(priceLabel);
        //文本框
        priceTextField = new JTextField();
        priceTextField.setColumns(10);
        priceTextField.setBounds(950, 110, 300, 35);
        priceTextField.setText(price);
        frame.getContentPane().add(priceTextField);
        //出版日期
        publishTimeLabel = new JLabel("出版日期：");
        //publishTimeLabel.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/date32.png"))));
        publishTimeLabel.setFont(kFont);
        publishTimeLabel.setBounds(773, 176, 180, 35);
        frame.getContentPane().add(publishTimeLabel);
        //文本框
        publishTimeField = new JTextField();
        publishTimeField.setColumns(10);
        publishTimeField.setBounds(950, 180, 300, 35);
        publishTimeField.setText(publishTime);
        frame.getContentPane().add(publishTimeField);
        //ISBN
        ISBNLabel = new JLabel("ISBN：");
        //ISBNLabel.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/remark32.png"))));
        ISBNLabel.setFont(kFont);
        ISBNLabel.setBounds(800, 246, 180, 35);
        frame.getContentPane().add(ISBNLabel);
        //文本框
        ISBNTextField = new JTextField();
        ISBNTextField.setColumns(10);
        ISBNTextField.setBounds(950, 246, 300, 35);
        ISBNTextField.setText(ISBN);
        frame.getContentPane().add(ISBNTextField);
        //确认
        sureButton = new JButton("确定");

        sureButton.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/paysure.png"))));
        sureButton.setFont(new Font("楷体", Font.BOLD, 20));
        sureButton.setBounds(1275, 455, 120, 46);
        frame.getContentPane().add(sureButton);

        imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon(Objects.requireNonNull(ChangeView.class.getClassLoader().getResource("img/changeview.jpg"))));
        imgLabel.setBounds(0, 0, 1440, 560);
        frame.getContentPane().add(imgLabel);


        //设置监听
        sureButton.addActionListener(changeHandler);
        frame.getRootPane().setDefaultButton(sureButton);

        //设置非法输入(非数字)监听
        //设置窗口不可拉伸
//        setResizable(false);
        BulkImportUtil.setFont(lFont, idTextField, bookNoTextField, bookNameTextField, authorField, publisherTextField, priceTextField, publishTimeField, ISBNTextField, amountTextField);
        BulkImportUtil.setColor(Color.RED, idLabel, bookNoLabel, bookNameLabel, authorLabel, publisherLabel, priceLabel, publishTimeLabel, ISBNLabel, amountLabel);
        sureButton.setContentAreaFilled(false);
        sureButton.setBorderPainted(false);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }




}