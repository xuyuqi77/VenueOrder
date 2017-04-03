package com.xyq.vo.service;

import com.xyq.vo.dao.OrderDAO;
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

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Object[]> listOrderTable(String venueid, String sport_name) {
        return orderDAO.listOrderTable(venueid,sport_name);
    }
}
