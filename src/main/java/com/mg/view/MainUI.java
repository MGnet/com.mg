package com.mg.view;

import javax.swing.*;
import java.awt.*;

/**
 * 主界面
 */
public class MainUI extends JFrame {

    /** 容器 */
    private JDesktopPane tabal;
    /** 目录的容器 */
    private JMenuBar jMenuBar1;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenuItem jMenuItem1_1;
    private JMenuItem jMenuItem2_1;
    private JMenuItem jMenuItem3_1;

    /**
     * 构造函数
     */
    public MainUI() {
        initComponents();
    }

    /**
     * 初始化所有组件
     */
    private void initComponents() {

        tabal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        jMenu3 = new JMenu();
        jMenuItem1_1 = new JMenuItem();
        jMenuItem2_1 = new JMenuItem();
        jMenuItem3_1 = new JMenuItem();

        jMenu1.setText("数据获取");
        jMenuItem1_1.setText("数据获取操作界面");
        jMenuItem1_1.addActionListener((actionListener)->{
            CrawlerUI crawlerUI = new CrawlerUI();
            crawlerUI.setVisible(true);
            this.tabal.add(crawlerUI);
        });
        jMenu1.add(jMenuItem1_1);

        jMenu2.setText("账号管理");
        jMenuItem2_1.setText("账号列表");
        jMenu2.add(jMenuItem2_1);
        jMenuItem2_1.addActionListener((actionListener)->{
            AccountNumberManagerUI accountNumberManagerUI = new AccountNumberManagerUI();
            accountNumberManagerUI.setVisible(true);
            this.tabal.add(accountNumberManagerUI);
        });

        jMenu3.setText("系统配置");
        jMenuItem3_1.setText("基础信息配置");
        jMenu3.add(jMenuItem3_1);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu3);
        // 设置布局
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("数据爬去");
        this.setJMenuBar(jMenuBar1);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(
                GroupLayout.Alignment.LEADING).addComponent(tabal,
                GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(
                GroupLayout.Alignment.LEADING).addComponent(tabal,
                GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE));
        pack();
    }
}
