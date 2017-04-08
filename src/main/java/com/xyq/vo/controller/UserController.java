package com.xyq.vo.controller;

import com.xyq.vo.model.User;
import com.xyq.vo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户控制器
 * Created by yqxu2 on 2017/2/28.
 */

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /**
     * 鉴定帐号密码是否正确
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/verify")
    public String verify(HttpServletRequest request, HttpServletResponse response) {
        // 登录状态 默认未登录
        String loginresult = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("loginresult", loginresult);
        if (username == null || password == null || "".equals(username) || "".equals(password)){
            loginresult = "fail";
            session.setAttribute("loginresult", loginresult);
            return "login";
        }
        User user = userService.getUserByLoginname(username);
        if (user == null || !user.getPassword().equals(password)) {
            loginresult = "fail";
            session.setAttribute("loginresult", loginresult);
            return "login";
        }
        loginresult = "success";
        session.setAttribute("username", username);
        session.setAttribute("loginresult", loginresult);
        return "index";
    }

    /**
     * 注册帐号
     * @param request
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView addUser(HttpServletRequest request) {
        String loginname = request.getParameter("user");
        String password = request.getParameter("passwd");
        String nickname = request.getParameter("nickname");
        User user = new User();
        user.setUser_id(String.valueOf(userService.getUserNum() + 1));
        user.setLogin_name(loginname);
        user.setPassword(password);
        if (nickname != null && !"".equals(nickname)) {
            user.setUser_name(nickname);
        }
        user.setRole_id("1");
        user.setLastlogintime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        user.setOrdered("0");
        userService.addUser(user);
        return new ModelAndView("login", "regresult", "success");
    }

    public void getUserByLoginname(){

    }



}
