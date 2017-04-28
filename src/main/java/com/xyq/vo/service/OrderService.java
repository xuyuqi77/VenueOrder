package com.xyq.vo.service;

import com.xyq.vo.dao.OrderDAO;
import com.xyq.vo.dao.UserDAO;
import com.xyq.vo.model.Order;
import com.xyq.vo.model.Page;
import com.xyq.vo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by yqxu2 on 2017/4/3.
 */
@Service("orderService")
@Transactional
public class OrderService {
    private OrderDAO orderDAO;
    private UserDAO userDAO;

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Order getOrderById(String orderid) {
        Order order = orderDAO.getOrderById(orderid);
        User user = userDAO.getUserByUserid(order.getUser_id());
        order.setLogin_name(user.getLogin_name());
        return order;
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
        return orderDAO.listOrderTableByVnameSname(venue_name, sport_name);
    }

    public String sumbitOrder(String username, String venuename, String sportname, String ordertime) {
        return orderDAO.sumbitOrder(username, venuename, sportname, ordertime);
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
        boolean a = orderDAO.delOrderByOrderid(orderid);
        boolean b = userDAO.setUserOrdered(order.getUser_id(), "0");
        return (a && b);
    }

}
