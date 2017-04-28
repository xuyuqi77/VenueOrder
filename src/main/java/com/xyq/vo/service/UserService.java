package com.xyq.vo.service;

import com.xyq.vo.dao.UserDAO;
import com.xyq.vo.model.Page;
import com.xyq.vo.model.Role;
import com.xyq.vo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by yqxu2 on 2017/2/16.
 */
@Service("userService")
@Transactional
public class UserService {

    public UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean addUser(User user) {
        return userDAO.add(user);
    }

    public boolean delUser(String userid) {
        return userDAO.del(userid);
    }

    public User getUserByLoginname(String loginname) {
        return userDAO.getUserByLoginname(loginname);
    }

    public User getUserByUserid(String userid) {
        return userDAO.getUserByUserid(userid);
    }

    public User getUserAndRoleById(String userid) {
        return userDAO.getUserAndRoleById(userid);
    }

    public int getUserNum() {
        return userDAO.getUserNum();
    }

    public User getUserByNameAndPwd(String loginname, String password) {
        return userDAO.getUserByNameAndPwd(loginname, password);
    }

    public void updateLastLogin(User user) {
        userDAO.updateLastLogin(user);
    }

    public List<User> listAllUser(User user) {
        return userDAO.listAllUser(user);
    }

    public List<User> listPageUser(User user, Page page) {
        return userDAO.listPageUser(user, page);
    }

    public boolean updateUserBaseInfo(User user) {
        return userDAO.updateUserBaseInfo(user);
    }

    public boolean updateUserRights(User user) {
        return userDAO.updateUserRights(user);
    }
}
