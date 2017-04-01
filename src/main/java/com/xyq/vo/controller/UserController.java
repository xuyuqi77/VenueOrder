package com.xyq.vo.controller;

import com.xyq.vo.model.User;
import com.xyq.vo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户控制器
 * Created by yqxu2 on 2017/2/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/add")
    public String addUser() {
        User user = new User();
        user.setLogin_name("e21314000");
        user.setPassword("123456");
        user.setUser_name("aaa");
        Timestamp ts = new Timestamp(new Date().getTime());
        user.setLastlogintime(ts);
        user.setOrdered("0");
        userService.addUser(user);
        return "ttt1";
    }

    public void getUserByLoginname(){

    }



}
