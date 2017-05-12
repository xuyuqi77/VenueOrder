package com.xyq.vo.service;

import com.xyq.vo.dao.*;
import com.xyq.vo.model.Order;
import com.xyq.vo.model.Page;
import com.xyq.vo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yqxu2 on 2017/4/3.
 */
@Service("orderService")
@Transactional
public class OrderService {
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private VenueDAO venueDAO;
    private SportDAO sportDAO;
    private AccountDAO accountDAO;

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setVenueDAO(VenueDAO venueDAO) {
        this.venueDAO = venueDAO;
    }

    @Autowired
    public void setSportDAO(SportDAO sportDAO) {
        this.sportDAO = sportDAO;
    }

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Order getOrderById(String orderid) {
        Order order = orderDAO.getOrderById(orderid);
        User user = userDAO.getUserByUserid(order.getUser_id());
        order.setLogin_name(user.getLogin_name());
        return order;
    }

    public List<Order> getOrderByUserId(String userid) {
        return orderDAO.getOrderByUserId(userid);
    }

    public boolean addOrder(Order order) {
        User user = userDAO.getUserByLoginname(order.getLogin_name());
        order.setUser_id(user.getUser_id());
        boolean a = orderDAO.addOrder(order);
        boolean b = userDAO.setUserOrdered(order.getUser_id(), "1");
        return (a && b);
    }

    public List<Object[]> listOrderTable(String venueid, String sport_name) {
        return orderDAO.listOrderTable(venueid, sport_name);
    }

    public List<Object[]> listOrderTableByVnameSname(String venue_name, String sport_name) {
        String venueid = null;
        if (StringUtils.isNotEmpty(venue_name)) {
            venueid = venueDAO.findVenueIdByVenueName(venue_name);
        }
        return orderDAO.listOrderTableBySnameVid(sport_name, venueid);
    }

    public String sumbitOrder(String loginname, String venuename, String sportname, String ordertime) {
        // 判断项目余量是否充足
        String venueid = venueDAO.findVenueIdByVenueName(venuename);
        String sportnum = sportDAO.getSportNumByPar(venueid, sportname, ordertime);
        if (sportnum.equals("0")) {
            return "下单失败,余量不足";
        }
        // 判断用户是否为已预定状态
        User user = userDAO.getUserByLoginname(loginname);
        if (user.getOrdered().equals("1")) {
            return "用户已处于下单状态";
        }
        // 下单
        boolean a = orderDAO.sumbitOrder(user.getUser_id(), venueid, sportname, ordertime);
        // 修改项目余量
        boolean b = sportDAO.setSportNumByPar(venueid, sportname, ordertime, Integer.parseInt(sportnum) - 1);
        // 修改用户预定状态
        boolean c = userDAO.setUserOrdered(user.getUser_id(), "1");
        // 修改账户金额
        boolean d = accountDAO.cutAccountMoney(user.getUser_id());
        if (a && b && c && d)
            return "success";
        return "false";
    }

    public List<Order> getAllOrder(Order order) {
        return orderDAO.getAllOrder(order);
    }

    public List<Order> listPageOrder(Order order, Page page) {
        return orderDAO.listPageOrder(order, page);
    }

    public List<Order> listExportOrder() {
        return orderDAO.listExportOrder();
    }

    public boolean updateOrderBaseInfo(Order order) {
        return orderDAO.updateOrderBaseInfo(order);
    }

    public boolean delOrderByOrderId(String orderid) {
        Order order = orderDAO.getOrderById(orderid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp ts = Timestamp.valueOf(order.getOrder_date());
        String str1 = sdf.format(new Date());
        String str2 = sdf.format(ts);
        // 今日订单
        if (str1.equals(str2)) {
            boolean a = orderDAO.delOrderByOrderid(orderid);
            boolean b = userDAO.setUserOrdered(order.getUser_id(), "0");
            boolean c = accountDAO.setAccountMoney(order.getUser_id(), 5);
            boolean d = sportDAO.addSportNumByPar(order.getVenue_id(), order.getSport_name(), order.getOrder_time(), 1);
            return (a && b && c && d);
        } else {
            // 非今日订单
            boolean a = orderDAO.delOrderByOrderid(orderid);
            boolean b = userDAO.setUserOrdered(order.getUser_id(), "0");
            boolean d = sportDAO.addSportNumByPar(order.getVenue_id(), order.getSport_name(), order.getOrder_time(), 1);
            return (a && b && d);
        }
    }
}
