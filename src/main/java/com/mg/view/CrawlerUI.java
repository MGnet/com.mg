package com.mg.view;

import com.mg.service.CrawlerThread;
import com.mg.uitls.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

/**
 * 派去日志
 */
public class CrawlerUI extends JInternalFrame {

    /** 重写输入组件 */
    private static PrintStream ps;

    /** 页面组件 */
    private JPanel panel;
    private JButton crawlerButton;
    private JTextArea logConsole;
    private JScrollPane logConsoleScrollPane;

    public CrawlerUI(){
        this.initComponents();
        //居中显示
        int windowWidth = this.getWidth();
        int windowHeight = this.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width - 100;
        int screenHeight = screenSize.height - 100;
        this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }

    private void initComponents(){
        //重构插件，让 System.out.println 输入到 logConsole 中
        ps = new PrintStream(System.out) {
            @Override
            public void println(String x) {
                logConsole.append(x + "\n");
            }
        };
        this.setClosable(true);
        this.setIconifiable(true);
        this.setTitle("数据获取");

        panel = new JPanel();
        crawlerButton = new JButton();
        logConsole = new JTextArea();
        logConsole.setEditable(false);
        logConsoleScrollPane = new JScrollPane(logConsole);
        logConsoleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        crawlerButton.setText("开始执行数据获取");
        crawlerButton.addActionListener((actionListener)->new Thread(new CrawlerThread(ps, crawlerButton)).start());

        GroupLayout jPanel1Layout = new GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(crawlerButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(logConsoleScrollPane, 900, 900, 900)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(crawlerButton))
                                .addContainerGap(10, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(logConsoleScrollPane, 400, 400, 400))
                                .addContainerGap(10, Short.MAX_VALUE))
        );
        this.add(panel);
        pack();
    }
}
