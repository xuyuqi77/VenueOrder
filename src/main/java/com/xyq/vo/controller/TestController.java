package com.xyq.vo.controller;

import com.xyq.vo.common.Result;
import com.xyq.vo.model.User;
import com.xyq.vo.service.OrderService;
import com.xyq.vo.service.SportService;
import com.xyq.vo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 测试控制层
 * Created by yqxu2 on 2017/2/16.
 */
@Controller
@RequestMapping("/")
public class TestController {

    private SportService sportService;

    private UserService userService;

    private OrderService orderService;

    @Autowired
    public void setSportService(SportService sportService) {
        this.sportService = sportService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/index")
    public ModelAndView toIndex() {
        List<Object[]> list2 = sportService.showSportList();
        return new ModelAndView("index", "vp_lists", list2);
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
     * 预定界面
     * @return
     */
    @RequestMapping("/order")
    public ModelAndView toOrder() {
        List<Object[]> list = orderService.listOrderTable("","");
        return new ModelAndView("order", "orderTable", list);
    }

    @RequestMapping("/show")
    public String show() {
        return "ttt1";
    }

    @RequestMapping("/venue")
    public String toVenue() {
        return "venue";
    }

    @RequestMapping("/image")
    public String toImage() {
        return "image";
    }

    @RequestMapping("/about")
    public String toAbout() {
        return "about";
    }

    /**
     * 登录界面
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

}
