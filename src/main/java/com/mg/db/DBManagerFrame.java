package com.mg.db;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class DBManagerFrame extends JFrame{

    private JTextArea sqlExecutor = new JTextArea();

    private JButton execute = new JButton("执行");

    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable table = new JTable(tableModel);

    public DBManagerFrame(){

        JToolBar toolbar = new JToolBar();
        toolbar.add(execute);

        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tableModel.setColumnCount(0);
                tableModel.setRowCount(0);

                DBManager db = new DBManager();
                ResultSet rs = db.execute(sqlExecutor.getText());

                ResultSetMetaData meta;
                try {
                    if(rs!=null){
                        meta = rs.getMetaData();
                        for(int i=1;i<=meta.getColumnCount();i++){
                            tableModel.addColumn(meta.getColumnName(i));
                        }
                        while(rs.next()){
                            Vector<String> rowData = new Vector<String>();
                            for(int i=1;i<=meta.getColumnCount();i++){
                                rowData.add(rs.getString(i));
                            }
                            tableModel.addRow(rowData);
                        }
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainPane.setDividerLocation(0.3);

        sqlExecutor.setLineWrap(true);
        JScrollPane topPane = new JScrollPane(sqlExecutor);
        topPane.setPreferredSize(new Dimension(200,400));
        topPane.setMinimumSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),100));
        mainPane.setTopComponent(topPane);

        this.getContentPane().add(mainPane);
        this.getContentPane().add(toolbar,BorderLayout.PAGE_START);

        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0, 220, 400, 200);

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setSize(500, 420);
        this.center();
        this.setVisible(true);

        mainPane.setBottomComponent(scrollPane);
    }

    /**
     *
     */
    private static final long serialVersionUID = -3086325456476318446L;

    protected void center() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        this.setLocation(x, y);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new DBManagerFrame();

    }

}
