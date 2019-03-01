package com.mg.view;

import com.mg.bean.Account;
import com.mg.dao.AccountDao;
import com.mg.uitls.UIUtils;
import org.h2.util.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

/**
 * 账号管理UI
 */
public class AccountNumberManagerUI extends JInternalFrame {

    /** 检索组件*/
    private JPanel jPanel1;
    private JLabel userAcountSearchLabel;
    private JTextField userAcountSearchTxt;
    private JButton userAccountSerch;

    /** 列表组件*/
    private JScrollPane jScrollPane1;
    private JTable userAccountTable;

    /** 编辑账号组件*/
    private JPanel jPanel2;
    private JLabel idLabel;
    private JTextField idTxt;
    private JLabel userAccountLabel;
    private JTextField userAccountTxt;
    private JLabel userAccountPwdLabel;
    private JTextField userAccountPwdTxt;
    private JLabel enableLabel;
    private JComboBox enableComboBox;

    private JButton deleteButton;
    private JButton updateButton;
    private JButton addButton;

    private AccountDao accountDao;


    /**
     * 在构造函数中初始化相关组件
     */
    public AccountNumberManagerUI() {
        initComponents();
        accountDao = new AccountDao();
        //居中显示
        int windowWidth = this.getWidth();
        int windowHeight = this.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width - 100;
        int screenHeight = screenSize.height - 100;
        this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
        //填充数据
        this.fillTable(null);
    }

    /**
     * 组件构造器
     */
    private void initComponents() {
        this.setClosable(true);
        this.setIconifiable(true);
        this.setTitle("账号管理");

        //检索框的组件
        jPanel1 = new JPanel();
        jPanel1.setBorder(BorderFactory.createTitledBorder("检索条件"));
        userAcountSearchLabel = new JLabel();
        userAcountSearchTxt = new JTextField();
        userAccountSerch = new JButton();
        userAcountSearchLabel.setText("账号");
        userAccountSerch.setText("检索");
        userAccountSerch.addActionListener((actionListener)->{
            //账号检索监听
            String accountNumber = userAcountSearchTxt.getText();
            fillTable(accountNumber);
        });
        //检索条件的布局
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(userAcountSearchLabel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userAcountSearchTxt, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(userAccountSerch)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(userAcountSearchLabel)
                                        .addComponent(userAcountSearchTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userAccountSerch))
                                .addContainerGap(27, Short.MAX_VALUE))
        );
        //账户列表组件
        jScrollPane1 = new JScrollPane();
        userAccountTable = new JTable();
        userAccountTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"编号", "账号", "密码", "是否可用", "添加日期", "更改日期"}) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false};
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        userAccountTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //鼠标点击行监听
                userAccountTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(userAccountTable);

        //表单操作
        jPanel2 = new JPanel();
        jPanel2.setBorder(BorderFactory.createTitledBorder("账号编辑"));
        idLabel = new JLabel();
        idTxt = new JTextField();
        userAccountLabel = new JLabel();
        userAccountTxt = new JTextField();
        userAccountPwdLabel = new JLabel();
        userAccountPwdTxt = new JTextField();
        enableLabel = new JLabel();
        enableComboBox = new JComboBox();
        enableComboBox.addItem("可用");
        enableComboBox.addItem("不可用");



        idLabel.setText("编号");
        idTxt.setEditable(false);
        userAccountLabel.setText("账号");
        userAccountPwdLabel.setText("密码");
        enableLabel.setText("是否可用");

        deleteButton = new JButton();
        updateButton = new JButton();
        addButton = new JButton();

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(idLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(userAccountLabel)))
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(userAccountTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(userAccountPwdLabel)))
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(userAccountPwdTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(enableLabel)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(enableComboBox, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(deleteButton)
                                                .addGap(30, 30, 30)
                                                .addComponent(updateButton)
                                                .addGap(30, 30, 30)
                                                .addComponent(addButton)))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(idLabel)
                                        .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userAccountLabel)
                                        .addComponent(userAccountTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userAccountPwdLabel)
                                        .addComponent(userAccountPwdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(enableLabel)
                                        .addComponent(enableComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(deleteButton)
                                        .addComponent(updateButton)
                                        .addComponent(addButton)
                                .addGap(25, 25, 25)))
        );

        deleteButton.setText("删除");
        deleteButton.addActionListener((actionListener) -> {
            if(StringUtils.isNullOrEmpty(idTxt.getText())){
                UIUtils.alertError("请选择删除的账号!");
                return;
            }
            accountDao.delete(Integer.parseInt(idTxt.getText()));
            //刷新列表
            String accountNumber = userAcountSearchTxt.getText();
            fillTable(accountNumber);
            UIUtils.alert("删除", "删除成功!");
        });

        updateButton.setText("更新");
        updateButton.addActionListener((actionListener) -> {
            //更新
            Account account = new Account();
            if(StringUtils.isNullOrEmpty(idTxt.getText())){
                UIUtils.alertError("请选择修改的账号!");
                return;
            }
            account.setId(Integer.parseInt(idTxt.getText()));
            account.setAccount(userAccountTxt.getText());
            account.setAccountPwd(userAccountPwdTxt.getText());
            String info = (String) enableComboBox.getSelectedItem();
            if(info.contains("不")){
                account.setUsed(false);
            }else{
                account.setUsed(true);
            }
            accountDao.update(account);
            //刷新列表
            String accountNumber = userAcountSearchTxt.getText();
            fillTable(accountNumber);
            UIUtils.alert("修改", "修改成功!");
        });

        addButton.setText("添加");
        addButton.addActionListener((actionListener) -> {
            //添加
            Account account = new Account();
            account.setId(accountDao.getNextId());
            if(StringUtils.isNullOrEmpty(userAccountTxt.getText())){
                UIUtils.alertError("请输入账号!");
                return;
            }
            account.setAccount(userAccountTxt.getText());
            if(StringUtils.isNullOrEmpty(userAccountPwdTxt.getText())){
                UIUtils.alertError("请输入密码!");
                return;
            }
            account.setAccountPwd(userAccountPwdTxt.getText());
            String info = (String) enableComboBox.getSelectedItem();
            if(info.contains("不")){
                account.setUsed(false);
            }else{
                account.setUsed(true);
            }
            accountDao.add(account);
            //刷新列表
            String accountNumber = userAcountSearchTxt.getText();
            fillTable(accountNumber);
            UIUtils.alert("添加", "添加成功!");
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
                                        .addComponent(jPanel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));
        pack();
    }

    /**
     * 填充表格数据
     */
    private void fillTable(String accountNumber) {
        DefaultTableModel dtm = (DefaultTableModel) userAccountTable.getModel();
        dtm.setRowCount(0);
        List<Account> list = accountDao.queryList(accountNumber);
        for(Account account : list){
            Vector v = new Vector();
            v.add(account.getId());
            v.add(account.getAccount());
            v.add(account.getAccountPwd());
            if(account.isUsed()){
                v.add("可用");
            }else{
                v.add("不可用");
            }
            v.add(account.getCreateTime());
            v.add(account.getUpdateTime());
            dtm.addRow(v);
        }
    }

    /**
     * 点击列表的时候，赋值 编辑数据
     * @param evt
     */
    private void userAccountTableMousePressed(MouseEvent evt) {
        //获取选中行
        int row = userAccountTable.getSelectedRow();
        this.idTxt.setText(String.valueOf(userAccountTable.getValueAt(row, 0)));
        this.userAccountTxt.setText(String.valueOf(userAccountTable.getValueAt(row, 1)));
        this.userAccountPwdTxt.setText(String.valueOf(userAccountTable.getValueAt(row, 2)));
        String infos = (String.valueOf(userAccountTable.getValueAt(row, 3)));
        int n = this.enableComboBox.getItemCount();
        for (int i = 0; i < n; i++) {
            String info = (String) this.enableComboBox.getItemAt(i);
            if (info.equals(infos)) {
                this.enableComboBox.setSelectedIndex(i);
            }
        }
    }
}
