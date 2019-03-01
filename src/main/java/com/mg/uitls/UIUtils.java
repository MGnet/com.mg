package com.mg.uitls;

import javax.swing.*;
import java.awt.*;

public class UIUtils {

    /**
     * 提示框
     * @param title 标题
     * @param message 描述
     */
    public static void alert(String title, String message){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, message, title,JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 错误提示框
     * @param message 描述
     */
    public static void alertError(String message){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, message, "错误",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * 休眠
     * @param time
     */
    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
