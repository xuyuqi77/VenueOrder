package com.xyq.vo.controller;

import com.xyq.vo.common.Trans;
import com.xyq.vo.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by yqxu2 on 2017/4/6.
 */
@Controller
@RequestMapping("/")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
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
        if (null != venue && !"".equals(venue)) {
            request.getSession().setAttribute("c_venue", venue);
        }
        if (null != sport && !"".equals(sport))
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
        request.getSession().setAttribute("c_v_back", Trans.toOrderChooseVenue(
                (String) request.getSession().getAttribute("c_venue")));
        request.getSession().setAttribute("c_s_back", Trans.toOrderChooseSport(
                (String) request.getSession().getAttribute("c_sport")));
        return "order";
    }

    @RequestMapping("/submitOrder")
    public ModelAndView sumbitOrder(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        String loginresult = (String) request.getSession().getAttribute("loginresult");
        if (username == null || "".equals(username) || loginresult.equals("fail")) {
            return new ModelAndView("login", "orderreslut", "unlogin");
        }
        String optionvalue = request.getParameter("option_value"); // 使用时间
        String venue = (String) request.getSession().getAttribute("c_venue");
        String sport = (String) request.getSession().getAttribute("c_sport");
        String result = orderService.sumbitOrder(username, venue, sport, optionvalue);
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

}
