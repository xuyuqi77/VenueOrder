package com.xyq.vo.controller;

import com.xyq.vo.common.Tools.Trans;
import com.xyq.vo.model.*;
import com.xyq.vo.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yqxu2 on 2017/4/6.
 */
@Controller
@RequestMapping("/")
public class OrderController {
    private OrderService orderService;
    private VenueService venueService;
    private UserService userService;
    private SportService sportService;
    private AccountService accountService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSportService(SportService sportService) {
        this.sportService = sportService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 根据选择的场馆和项目显示表格
     *
     * @param request
     * @return
     */
    @RequestMapping("/afterchoose")
    public String toOrderAfterChoose(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        // 获取选择的场馆
        String venue = request.getParameter("c_venue");
        // 获取选择的项目
        String sport = request.getParameter("c_sport");
        if (StringUtils.isNotEmpty(venue)) {
            request.getSession().setAttribute("c_venue", venue);
        }
        if (StringUtils.isNotEmpty(sport))
            request.getSession().setAttribute("c_sport", sport);
        // 根据选项筛选符合条件的结果
        List<Object[]> list = orderService.listOrderTableByVnameSname(
                (String) request.getSession().getAttribute("c_venue"),
                (String) request.getSession().getAttribute("c_sport"));
        if (list.size() == 0) {
            request.getSession().setAttribute("orderTable", null);
        } else {
            request.getSession().setAttribute("orderTable", list);
        }
        // 选择场馆和项目后标红
        List<Venue> venueList = venueService.getAllVenue();
        List<Sport> sportList = sportService.getAllSportName();
        request.getSession().setAttribute("c_v_back", Trans.toOrderChooseVenue(
                (String) request.getSession().getAttribute("c_venue"),
                venueList)
        );
        request.getSession().setAttribute("c_s_back", Trans.toOrderChooseSport(
                (String) request.getSession().getAttribute("c_sport"),
                sportList)
        );
        return "order";
    }

    /**
     * 提交订单
     * @param request
     * @return
     */
    @RequestMapping("/submitOrder")
    public ModelAndView sumbitOrder(HttpServletRequest request) {
        String loginname = (String) request.getSession().getAttribute("loginname");
        String loginresult = (String) request.getSession().getAttribute("loginresult");
        if (loginname == null || "".equals(loginname) || loginresult.equals("fail")) {
            return new ModelAndView("login", "orderreslut", "unlogin");
        }
        User user = userService.getUserByLoginname(loginname);
        Account account = accountService.getAccountByUserid(user.getUser_id());
        // 账户金额不足
        if(account.getMoney() < 5) {
            return new ModelAndView("order", "orderresult", "arrears");
        }

        String optionvalue = request.getParameter("option_value"); // 使用时间
        String venue = (String) request.getSession().getAttribute("c_venue");
        String sport = (String) request.getSession().getAttribute("c_sport");
        String result = orderService.sumbitOrder(loginname, venue, sport, optionvalue);
        if (result.equals("success")) {
            List<Object[]> list = orderService.listOrderTableByVnameSname(
                    (String) request.getSession().getAttribute("c_venue"),
                    (String) request.getSession().getAttribute("c_sport"));
            request.getSession().setAttribute("orderTable", list);
            return new ModelAndView("order", "orderresult", "success");
        } else if (result.equals("下单失败,余量不足")) {
            return new ModelAndView("order", "orderresult", "unenough");
        } else if (result.equals("用户已处于下单状态")) {
            return new ModelAndView("order", "orderresult", "userordered");
        }
        return new ModelAndView("order", "orderresult", "fail");
    }

    @RequestMapping("/orderList")
    public ModelAndView orderList(@RequestParam String loginname) {
        User user = userService.getUserByLoginname(loginname);
        List<Order> orderList = orderService.getOrderByUserId(user.getUser_id());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orderlist");
        mv.addObject("orderList", orderList);
        return mv;
    }

    @RequestMapping("/deleteorder")
    public void delOrder(@RequestParam String orderId, PrintWriter out){
        Order order = orderService.getOrderById(orderId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp ts = Timestamp.valueOf(order.getOrder_date());
        String str1 = sdf.format(new Date());
        String str2 = sdf.format(ts);
        // 判断是否为当日订单
        if (!str1.equals(str2)) {
            out.write("datefalse");
        } else {
            out.write("success");
            orderService.delOrderByOrderId(orderId);
        }
        out.close();
    }
}
