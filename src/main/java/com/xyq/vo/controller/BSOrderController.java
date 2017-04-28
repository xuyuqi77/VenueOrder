package com.xyq.vo.controller;

import com.xyq.vo.common.Excel.OrderExcelView;
import com.xyq.vo.model.*;
import com.xyq.vo.service.OrderService;
import com.xyq.vo.service.SportService;
import com.xyq.vo.service.UserService;
import com.xyq.vo.service.VenueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/bsorder")
public class BSOrderController {

    private OrderService orderService;
    private VenueService venueService;
    private SportService sportService;
    private UserService userService;

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

    /**
     * 显示订单列表
     */
    @RequestMapping
    public ModelAndView list(Order order, HttpServletRequest request) {
        String loginName = request.getParameter("loginName");
        String venueId = request.getParameter("venueId");
        String sportName = request.getParameter("sportName");

        if (StringUtils.isNotEmpty(loginName)) {
            order.getUser().setLogin_name(loginName);
        }
        if (StringUtils.isNotEmpty(venueId)) {
            order.setVenue_id(venueId);
        }
        if (StringUtils.isNotEmpty(sportName)) {
            order.setSport_name(sportName);
        }
        List<Venue> venueList = venueService.getAllVenue();
        List<User> userList = userService.listAllUser(new User());
        List<Order> orderList = orderService.getAllOrder(new Order());

        Page page = (Page) request.getSession().getAttribute("orderpage");
        if (page == null){
            page = new Page();
        }
        page.setCurrentPage(1);
        page.setTotalPage(((orderList.size() + page.getShowCount() -1) / page.getShowCount()));
        List<Order> pageorderList = orderService.listPageOrder(order, page);

        String pagemove = request.getParameter("pagemove");
        if (StringUtils.isNotEmpty(pagemove)) {
            if (pagemove.equals("1") && (page.getCurrentPage() > 1)) {
                // 上一页 且当前页面大于第一页
                // 分页查询上一页的数据
                page.setCurrentPage(page.getCurrentPage() - 1);
                pageorderList = orderService.listPageOrder(order, page);
            } else if (pagemove.equals("2") && (page.getCurrentPage() < page.getTotalPage())) {
                // 下一页 且当前页面小于总页数
                // 分页查询下一页的数据
                page.setCurrentPage(page.getCurrentPage() + 1);
                pageorderList = orderService.listPageOrder(order, page);
            }
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("bsorder");
        mv.addObject("orderList", pageorderList);
        mv.addObject("userList", userList);
        mv.addObject("venueList", venueList);
        mv.addObject("order", order);
        request.getSession().setAttribute("orderpage", page);
        return mv;
    }


    /**
     * 请求新增订单页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String toAdd(Model model) {
        List<Venue> venueList = venueService.getAllVenue();
        List<String> opentimeList = sportService.getOpeningTimes();
        model.addAttribute("venueList", venueList);
        model.addAttribute("opentimeList", opentimeList);
        return "bsorder_info";
    }

    /**
     * 保存项目信息
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveOrder(Order order) {
        ModelAndView mv = new ModelAndView();
        if (order.getLogin_name() != null) {
            User u = userService.getUserByLoginname(order.getLogin_name());
            order.setUser_id(u.getUser_id());
        }
        if (StringUtils.isEmpty(order.getOrder_id()) || order.getOrder_id().equals("0")) {
            if (!orderService.addOrder(order)) {
                mv.addObject("msg", "failed");
            } else {
                mv.addObject("msg", "success");
            }
        } else {
            orderService.updateOrderBaseInfo(order);
        }
        mv.setViewName("bssave_result");
        return mv;
    }

    /**
     * 请求编辑项目页面
     * @param orderId
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView toEdit(@RequestParam String orderId){
        ModelAndView mv = new ModelAndView();
        Order order = orderService.getOrderById(orderId);
        List<Venue> venueList = venueService.getAllVenue();
        List<String> opentimeList = sportService.getOpeningTimes();
        mv.addObject("order", order);
        mv.addObject("venueList", venueList);
        mv.addObject("opentimeList", opentimeList);
        mv.setViewName("bsorder_info");
        return mv;
    }

    /**
     * 删除某个订单
     * @param orderId
     * @param out
     */
    @RequestMapping(value="/delete")
    public void deleteUser(@RequestParam String orderId,PrintWriter out){
        orderService.delOrderByOrderId(orderId);
        out.write("success");
        out.close();
    }


    /**
     * 导出订单信息到excel
     *
     * @return
     */
    @RequestMapping(value="/excel")
    public ModelAndView export2Excel(){
        Map<String,Object> dataMap = new HashMap<String,Object>();
        List<String> titles = new ArrayList<String>();
        titles.add("预定项目");
        titles.add("场馆");
        titles.add("项目");
        titles.add("使用时间");
        titles.add("下单时间");
        dataMap.put("titles", titles);
        List<Order> orderList = orderService.listExportOrder();
        dataMap.put("orderList", orderList);
        OrderExcelView erv = new OrderExcelView();
        ModelAndView mv = new ModelAndView(erv,dataMap);
        return mv;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
}
