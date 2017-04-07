package com.xyq.vo.controller;

import com.xyq.vo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
     * @param request
     * @return
     */
    @RequestMapping("/afterchoose")
    public ModelAndView toOrderAfterChoose(HttpServletRequest request) {
        String venue = request.getParameter("c_venue");
        String sport = request.getParameter("c_sport");
        if (null != venue && !"".equals(venue))
            request.getSession().setAttribute("c_venue", venue);
        if (null != sport && !"".equals(sport))
            request.getSession().setAttribute("c_sport", sport);
        List<Object[]> list = orderService.listOrderTableByVnameSname(
                (String) request.getSession().getAttribute("c_venue"),
                (String) request.getSession().getAttribute("c_sport"));
        return new ModelAndView("order", "orderTable", list);
    }

    @RequestMapping("/submitOrder")
    public String sumbitOrder() {
        return null;
    }

}
