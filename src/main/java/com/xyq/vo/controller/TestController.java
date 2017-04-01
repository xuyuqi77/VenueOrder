package com.xyq.vo.controller;

import com.xyq.vo.common.Result;
import com.xyq.vo.model.User;
import com.xyq.vo.service.SportService;
import com.xyq.vo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    public void setSportService(SportService sportService) {
        this.sportService = sportService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/index")
    public ModelAndView toIndex() {
        List<Object[]> list2 = sportService.showSportList();
        return new ModelAndView("index", "vp_lists", list2);
    }

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/verify")
    public ModelAndView verify(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login","loginresult","fail");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null || "".equals(username) || "".equals(password))
            return mav;
        User user = userService.getUserByLoginname(username);
        if (user == null || !user.getPassword().equals(password))
            return mav;
        mav = new ModelAndView("order","loginresult","success");
        return mav;
    }

    @RequestMapping("/show")
    public String show() {
        return "ttt1";
    }

    @RequestMapping("/venue")
    public String toVenue() {
        return "venue";
    }

    @RequestMapping("/order")
    public String toOrder() {
        return "order";
    }

    @RequestMapping("/image")
    public String toImage() {
        return "image";
    }

    @RequestMapping("/about")
    public String toAbout() {
        return "about";
    }

}
