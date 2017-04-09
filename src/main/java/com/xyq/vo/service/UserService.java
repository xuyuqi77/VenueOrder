package com.xyq.vo.service;

import com.xyq.vo.dao.UserDAO;
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

    public void addUser(User user) {
        userDAO.add(user);
    }

    public User getUserByLoginname(String loginname) {
        return userDAO.getUserByLoginname(loginname);
    }

    public int getUserNum() {
        return userDAO.getUserNum();
    }
}
