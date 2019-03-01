package com.mg.dao;

import com.mg.bean.Account;
import com.mg.db.DBManager;
import org.h2.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 账号DAO
 */
public class AccountDao {

    DBManager dbManager = new DBManager();

    /**
     * 查询列表
     * @param accountNumber
     * @return
     */
    public List<Account> queryList(String accountNumber){
        String sql = "select t.ID, t.ACCOUNT, t.ACCOUNT_PWD, t.IS_USED, t.CREATE_TIME, t.UPDATE_TIME from account t ";
        if(!StringUtils.isNullOrEmpty(accountNumber)){
            sql = sql + "where t.ACCOUNT like '%" + accountNumber + "%'";
        }
        ResultSet rs = dbManager.execute(sql);
        List<Account> list = new ArrayList<>();
        try {
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("ID"));
                account.setAccount(rs.getString("ACCOUNT"));
                account.setAccountPwd(rs.getString("ACCOUNT_PWD"));
                Integer isUsed = rs.getInt("IS_USED");
                if(isUsed == 1){
                    account.setUsed(true);
                }else{
                    account.setUsed(false);
                }
                account.setCreateTime(rs.getTimestamp("CREATE_TIME"));
                account.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
                list.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 更新
     * @param account
     */
    public void update(Account account){
        String sql = "UPDATE ACCOUNT t set t.ACCOUNT='{account}', t.ACCOUNT_PWD='{accountPwd}', t.IS_USED={isUsed}, t.UPDATE_TIME=CURRENT_TIMESTAMP where t.ID={ID}";
        Integer isUsed = account.isUsed()?1:0;
        sql = sql.replace("{account}", account.getAccount());
        sql = sql.replace("{accountPwd}", account.getAccountPwd());
        sql = sql.replace("{isUsed}", isUsed.toString());
        sql = sql.replace("{ID}", account.getId().toString());
        dbManager.execute(sql);
    }

    /**
     * 添加
     * @param account
     */
    public void add(Account account){
        String sql = "INSERT INTO ACCOUNT VALUES({ID}, '{account}', '{accountPwd}', {isUsed},  CURRENT_TIMESTAMP, null);";
        Integer isUsed = account.isUsed()?1:0;
        sql = sql.replace("{account}", account.getAccount());
        sql = sql.replace("{accountPwd}", account.getAccountPwd());
        sql = sql.replace("{isUsed}", isUsed.toString());
        sql = sql.replace("{ID}", account.getId().toString());
        dbManager.execute(sql);
    }

    public void delete(Integer id){
        String sql = "delete from ACCOUNT t where t.ID=" + id;
        dbManager.execute(sql);
    }

    /**
     * 获取下一次插入的ID
     * @return
     */
    public Integer getNextId(){
        String sql = "select max(t.ID) as maxId from account t";
        ResultSet rs = dbManager.execute(sql);
        Integer maxId = 1;
        try {
            while(rs.next()){
                maxId = rs.getInt(maxId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId + 1;
    }

}
