package com.mg;

import com.mg.view.MainUI;

import java.awt.*;

/**
 * 主函数入口
 */
public class App {

    public static void main( String[] args ) {
        //启动主窗口
        EventQueue.invokeLater(()->new MainUI().setVisible(true));
        System.out.println( "Hello World!" );
    }
}
