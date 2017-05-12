package com.xyq.vo.dao;

import com.xyq.vo.model.Account;
import com.xyq.vo.model.Page;
import com.xyq.vo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 账户处理层
 * Created by yqxu2 on 2017/4/30.
 */
@Repository
public class AccountDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 根据id获取账户
     * @param accountid
     * @return
     */
    public Account getAccountById(String accountid) {
        String sql = "select a.account_id,a.user_id,a.money,b.login_name " +
                " from account a " +
                " left join user b on a.user_id = b.user_id " +
                " where a.account_id = '" + accountid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        if (query != null) {
            List<Object[]> lists = query.list();
            Object[] list = lists.get(0);
            Account account = new Account();
            account.setAccount_id((String) list[0]);
            account.setUser_id((String) list[1]);
            account.setMoney((Integer) list[2]);
            account.getUser().setLogin_name((String) list[3]);
            return account;
        }
        return null;
    }

    /**
     * 根据用户ID获取账户
     * @param userid
     * @return
     */
    public Account getAccountByUserid(String userid) {
        String sql = "select * from account where user_id = '" + userid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            Account account = new Account();
            List<Object[]> lists = query.list();
            Object[] list = lists.get(0);
            account.setAccount_id((String) list[0]);
            account.setUser_id((String) list[1]);
            account.setMoney((Integer) list[2]);
            return account;
        }
        return null;
    }

    /**
     * 获取所有账户
     * @param user
     * @return
     */
    public List<Account> listAllAccount(User user){
        StringBuilder sql = new StringBuilder("select a.account_id,a.user_id,a.money,b.login_name " +
                " from account a " +
                " left join user b on a.user_id = b.user_id " +
                " where 1=1 ");
        if(user != null){
            if(StringUtils.isNotEmpty(user.getLogin_name())) {
                sql.append(" and b.login_name like '%" + user.getLogin_name() + "%' ");
            }
        }
        sql.append(" order by a.account_id;");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Account> accountList = new ArrayList<Account>();
        if (query != null) {
            List<Object[]> lists = query.list();
            for (Object[] list:lists) {
                Account account = new Account();
                account.setAccount_id((String) list[0]);
                account.setUser_id((String) list[1]);
                account.setMoney((Integer) list[2]);
                account.getUser().setLogin_name((String) list[3]);
                accountList.add(account);
            }
        }
        return accountList;
    }

    /**
     * 分页获取账户
     * @param user
     * @param page
     * @return
     */
    public List<Account> listPageAccount(User user, Page page) {
        StringBuilder sql = new StringBuilder("select a.account_id,a.user_id,a.money,b.login_name " +
                " from account a " +
                " left join user b on a.user_id = b.user_id " +
                " where 1=1 ");
        if(user != null){
            if(StringUtils.isNotEmpty(user.getLogin_name())) {
                sql.append(" and b.login_name like '%" + user.getLogin_name() + "%' ");
            }
        }
        sql.append(" order by a.account_id");
        if (page.getCurrentPage() == page.getTotalPage()) {
            sql.append(" limit " + (page.getCurrentPage()-1)*page.getShowCount() + "," + page.getTotalPage()*page.getShowCount());
        } else {
            sql.append(" limit " + (page.getCurrentPage()-1)*page.getShowCount() + "," + page.getShowCount() + " ");
        }
        sql.append(";");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Account> accountList = new ArrayList<Account>();
        if (query != null) {
            List<Object[]> lists = query.list();
            for (Object[] list:lists) {
                Account account = new Account();
                account.setAccount_id((String) list[0]);
                account.setUser_id((String) list[1]);
                account.setMoney((Integer) list[2]);
                account.getUser().setLogin_name((String) list[3]);
                accountList.add(account);
            }
        }
        return accountList;
    }


    /**
     * 更新账户基本信息
     * @param account
     * @return
     */
    public boolean updateAccountBaseInfo(Account account) {
        String sql = "update account set money = " + account.getMoney() + " " +
                "where account_id = '" + account.getAccount_id() + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    public boolean setAccountMoney(String userid, int i){
        String sql = "update account set money = money +" + i +
                " where user_id = '" + userid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    public boolean cutAccountMoney(String userid) {
        String sql = "update account set money = money - 5 where user_id ='" + userid + "' and money > 5;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int i = query.executeUpdate();
        if (i == 1)
            return true;
        return false;
    }

    public boolean insertAccount(Account account) {
        String sql = "insert into account(account_id,user_id,money) " +
                "values('" + String.valueOf(getAccountMaxNum() + 1) + "','" + account.getUser_id() + "'," + account.getMoney() + ");";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    /**
     * 获取最大账户编号
     * @return
     */
    public int getAccountMaxNum() {
        String sql = "select max(account_id) from account;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<String> list = query.list();
        int num = Integer.parseInt(list.get(0));
        return num;
    }
}
