package com.xyq.vo.service;

import com.xyq.vo.dao.AccountDAO;
import com.xyq.vo.model.Account;
import com.xyq.vo.model.Page;
import com.xyq.vo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 账户服务层
 * Created by yqxu2 on 2017/4/30.
 */
@Service
@Transactional
public class AccountService {

    private AccountDAO accountDAO;

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account getAccountById(String accountid) {
        return accountDAO.getAccountById(accountid);
    }

    public Account getAccountByUserid(String userid) {
        return accountDAO.getAccountByUserid(userid);
    }

    public boolean insertAccount(Account account) {
        return accountDAO.insertAccount(account);
    }

    public boolean updateAccountBaseInfo(Account account) {
        return accountDAO.updateAccountBaseInfo(account);
    }

    public boolean setAccountMoney(String userid, int i) {
        return accountDAO.setAccountMoney(userid, i);
    }

    public List<Account> listAllAccount(User user){
        return accountDAO.listAllAccount(user);
    }

    public List<Account> listPageAccount(User user, Page page) {
        return accountDAO.listPageAccount(user, page);
    }
}
