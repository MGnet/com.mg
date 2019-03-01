package com.mg.service;

import com.mg.uitls.UIUtils;

import javax.swing.*;
import java.io.PrintStream;

/**
 * 爬去线程
 */
public class CrawlerThread implements Runnable {

    private PrintStream ps;

    private JButton crawlerButton;

    public CrawlerThread(PrintStream ps, JButton crawlerButton) {
        this.ps = ps;
        this.crawlerButton = crawlerButton;
    }

    @Override
    public void run() {
        crawlerButton.setEnabled(false);
        System.setOut(ps);
        System.out.println("IT'S ALIVE!! AGAIN");
        UIUtils.sleep(2000);
        System.out.println("comGGG");
        UIUtils.sleep(2000);
        System.out.println("I am back!!!");
        crawlerButton.setEnabled(true);
    }

}
