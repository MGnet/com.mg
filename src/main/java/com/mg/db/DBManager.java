package com.mg.db;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBManager {

    /** 数据保存文件 */
    private static String dir;
    /** 用户名 */
    private static String user;
    /** 密码 */
    private static String password;

    private static Properties properties;

    static{
        try {
            //从配置文件中读取相关配置
            properties = new Properties();
            InputStream is = DBManager.class.getClassLoader().getResource("jdbc.properties").openStream();
            properties.load(is);
            dir = getString("dir", "");
            user = getString("user", "");
            password = getString("password", "");
            Class.forName("org.h2.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库
     * @return
     */
    public Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:h2:" + dir, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet query(String sql){
        Connection conn = getConn();
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet execute(String sql){
        Connection conn = getConn();
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            boolean r = stat.execute(sql);
            if(r){
                rs = stat.getResultSet();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private static String getString(String key, String defaultValue) {
        String value = properties.getProperty(key);
        if (null != value && !"".endsWith(value)) {
            return value.trim();
        } else {
            return defaultValue;
        }
    }

    private static int getInt(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (null != value) {
            return Integer.parseInt(value);
        } else {
            return defaultValue;
        }
    }
}
