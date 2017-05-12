package com.xyq.vo.controller;

import com.xyq.vo.common.Const;
import com.xyq.vo.model.Account;
import com.xyq.vo.model.Order;
import com.xyq.vo.model.User;
import com.xyq.vo.service.AccountService;
import com.xyq.vo.service.OrderService;
import com.xyq.vo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 用户控制器
 * Created by yqxu2 on 2017/2/28.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private OrderService orderService;
    private AccountService accountService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 鉴定帐号密码是否正确
     *
     * @param request
     * @return
     */
    @RequestMapping("/verify")
    public String verify(HttpServletRequest request, @RequestParam String loginname,
                         @RequestParam String password, @RequestParam String code) {
        // 登录状态 默认未登录
        String loginresult = null;
        HttpSession session = request.getSession();
        session.setAttribute("loginresult", loginresult);
        String sessionCode = (String) session.getAttribute(Const.SESSION_SECURITY_CODE);
        if (StringUtils.isNotEmpty(code) && !code.equalsIgnoreCase(sessionCode)) {
            loginresult = "wrongcode";
            session.setAttribute("loginresult", loginresult);
            return "redirect:/login";
        }
        if (loginname == null || password == null || "".equals(loginname) || "".equals(password)) {
            loginresult = "fail";
            session.setAttribute("loginresult", loginresult);
            return "redirect:/login";
        }
        User user = userService.getUserByLoginname(loginname);
        if (user == null || !user.getPassword().equals(password)) {
            loginresult = "fail";
            session.setAttribute("loginresult", loginresult);
            return "redirect:/login";
        }
        user.setLastlogintime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        userService.updateLastLogin(user);
        loginresult = "success";
        session.setAttribute("loginname", loginname);
        session.setAttribute("loginresult", loginresult);
        return "redirect:/index";
    }

    /**
     * 注册帐号
     *
     * @return
     */
    @RequestMapping("/add")
    public String addUser(@RequestParam String lgname, @RequestParam String passwd,
                          @RequestParam String nickname, HttpServletRequest request) {
        User user = new User();
        user.setUser_id(String.valueOf(userService.getUserNum() + 1));
        user.setLogin_name(lgname);
        user.setPassword(passwd);
        user.setRole_id("0");
        if (StringUtils.isNotEmpty(nickname)) {
            user.setUser_name(nickname);
        }
        boolean a = userService.addUser(user);
        if (a) {
            request.getSession().setAttribute("regresult", "success");
            return "redirect:/login";
        } else {
            request.getSession().setAttribute("regresult", "loginnameexit");
            return "redirect:/login";
        }

    }

    @RequestMapping("/loginout")
    public void loginout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginname");
        session.removeAttribute("loginresult");
    }


    @RequestMapping("/loginname")
    public void exitLoginname(@RequestParam String loginname, PrintWriter out) {
        User u = userService.getUserByLoginname(loginname);
        if (u != null) {
            out.write("t");
            out.flush();
        }
        out.close();
    }

    @RequestMapping(value = "/lastmoney")
    public void searchLastMoney(@RequestParam String loginname, PrintWriter out) {
        User user = userService.getUserByLoginname(loginname);
        Account account = accountService.getAccountByUserid(user.getUser_id());
        String money = String.valueOf(account.getMoney());
        out.write(money);
        out.close();
    }

    @RequestMapping("/searchorder")
    public String searchOrder(HttpServletRequest request, @RequestParam String from) {
        String viewname = "redirect:/" + from;
        String loginname = (String) request.getSession().getAttribute("loginname");
        User user = userService.getUserByLoginname(loginname);
        List<Order> orderList = orderService.getOrderByUserId(user.getUser_id());
        for (Order order : orderList) {
            Timestamp ts = Timestamp.valueOf(order.getOrder_date());
            Calendar cal = Calendar.getInstance();
            cal.setTime(ts);
            // 判断是否是今天的订单
            if (cal.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)) {
                request.getSession().setAttribute("searchorder", order);
                return viewname;
            }
        }
        request.getSession().setAttribute("searchorder", null);
        return viewname;
    }

    @RequestMapping("/editpassword")
    public ModelAndView editPassword(@RequestParam String loginname) {
        User user = userService.getUserByLoginname(loginname);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user_info");
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(User user) {
        userService.updateUserPassword(user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("login");
        return mv;
    }
}
