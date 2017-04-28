package com.xyq.vo.dao;

import com.xyq.vo.model.Order;
import com.xyq.vo.model.Page;
import com.xyq.vo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yqxu2 on 2017/4/3.
 */
@Repository
@Transactional
public class OrderDAO {

    private SessionFactory sessionFactory;
    private VenueDAO venueDAO;
    private SportDAO sportDAO;
    private UserDAO userDAO;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 根据场馆ID 和项目名称 查询所有可预订场馆
     *
     * @param venueid
     * @param sport_name
     * @return
     */
    public List<Object[]> listOrderTable(String venueid, String sport_name) {
        // 设置默认值
//        if(venueid == null || "".equals(venueid)){
//            venueid = "1";
//        }
//        if(sport_name == null || "".equals(sport_name)){
//            sport_name = "羽毛球";
//        }
        if ((venueid == null || "".equals(venueid)) && (sport_name == null || "".equals(sport_name)))
            return null;
        String sql = "" +
                "select ns.opening_times,ns.sport_num " +
                "  from newsport ns " +
                " where ns.venue_id = '" + venueid + "'" +
                "   and ns.sport_name = '" + sport_name + "';";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> lists = query.list();
        return lists;
    }

    public List<Object[]> listOrderTableByVnameSname(String venue_name, String sport_name) {
        StringBuilder sql = new StringBuilder("select a.opening_times, a.sport_num from newsport a where 1=1");
        String venueid = null;
        if (venue_name != null && !"".equals(venue_name)) {
            venueid = venueDAO.findVenueIdByVenueName(venue_name);
        }
        if (venueid != null && !"".equals(venueid)) {
            sql.append(" and a.venue_id = '" + venueid + "' ");
        }
        if (sport_name != null && !"".equals(sport_name)) {
            sql.append(" and a.sport_name = '" + sport_name + "' ");
        }
        sql.append(" order by a.venue_id;");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> lists = query.list();
        return lists;
    }


    /**
     * 根据id获取订单
     * @param orderid
     * @return
     */
    public Order getOrderById(String orderid) {
        String sql = "select * from ordersport where order_id ='" + orderid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        Order order = new Order();
        if (query != null) {
            List<Object[]> lists = query.list();
            order.setOrder_id((String) lists.get(0)[0]);
            order.setUser_id((String) lists.get(0)[1]);
            order.setVenue_id((String) lists.get(0)[2]);
            order.setSport_name((String) lists.get(0)[3]);
            if (lists.get(0)[4] != null) {
                Timestamp ts = (Timestamp) lists.get(0)[4];
                order.setOrder_date(ts.toString());
            }
            order.setOrder_time((String) lists.get(0)[5]);
        }
        return order;
    }

    /**
     * 添加订单
     * @param order
     * @return
     */
    public boolean addOrder(Order order) {
        String sql = "insert into ordersport(order_id,user_id,venue_id,sport_name,order_date,order_time) " +
                " values('" + String.valueOf(getOrderNum() + 1) + "','" +order.getUser_id() + "','" + order.getVenue_id() +
                "','" + order.getSport_name() + "','" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
                "','" + order.getOrder_time() + "');";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    /**
     * 获取订单表
     * @return
     */
    public List<Order> getAllOrder(Order order) {
        StringBuilder sql = new StringBuilder("select a.order_id,a.user_id,a.venue_id,a.sport_name,a.order_date,a.order_time,b.login_name,c.venue_name" +
                " from ordersport a " +
                " left join user b on a.user_id = b.user_id " +
                " left join venue c on a.venue_id = c.venue_id " +
                " where 1=1 "
        );
        if (order != null) {
            if (StringUtils.isNotEmpty(order.getUser().getLogin_name())) {
                sql.append(" and b.login_name like '%" + order.getUser().getLogin_name() + "%' ");
            }
            if (StringUtils.isNotEmpty(order.getVenue_id())) {
                sql.append(" and a.venue_id = '" + order.getVenue_id() + "' ");
            }
            if (StringUtils.isNotEmpty(order.getSport_name())) {
                sql.append(" and a.sport_name = '" + order.getSport_name() + "' ");
            }
        }
        sql.append(" order by a.user_id,a.sport_name,order_date;");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Order> orderList = new ArrayList<Order>();
        if (query != null) {
            List<Object[]> lists = query.list();
            for (Object[] list:lists) {
                Order o = new Order();
                o.setOrder_id((String) list[0]);
                o.setUser_id((String) list[1]);
                o.setVenue_id((String) list[2]);
                o.setSport_name((String) list[3]);
                if (list[4] != null) {
                    Timestamp ts = (Timestamp) list[4];
                    o.setOrder_date(ts.toString());
                }
                o.setOrder_time((String) list[5]);
                o.getUser().setLogin_name((String) list[6]);
                o.getVenue().setVenue_name((String) list[7]);
                orderList.add(o);
            }
        }
        return orderList;
    }

    /**
     * 分页获取订单表
     * @return
     */
    public List<Order> listPageOrder(Order order, Page page) {
        StringBuilder sql = new StringBuilder("select a.order_id,a.user_id,a.venue_id,a.sport_name,a.order_date,a.order_time,b.login_name,c.venue_name" +
                " from ordersport a " +
                " left join user b on a.user_id = b.user_id " +
                " left join venue c on a.venue_id = c.venue_id " +
                " where 1=1 "
        );
        if (order != null) {
            if (StringUtils.isNotEmpty(order.getUser().getLogin_name())) {
                sql.append(" and b.login_name like '%" + order.getUser().getLogin_name() + "%' ");
            }
            if (StringUtils.isNotEmpty(order.getVenue_id())) {
                sql.append(" and a.venue_id = '" + order.getVenue_id() + "' ");
            }
            if (StringUtils.isNotEmpty(order.getSport_name())) {
                sql.append(" and a.sport_name like '%" + order.getSport_name() + "%' ");
            }
        }
        sql.append(" order by a.user_id,a.sport_name,order_date ");
        if (page.getCurrentPage() == page.getTotalPage()) {
            sql.append(" limit " + (page.getCurrentPage() - 1) * page.getShowCount() + "," + page.getTotalPage() * page.getShowCount());
        } else {
            sql.append(" limit " + (page.getCurrentPage() - 1) * page.getShowCount() + "," + page.getShowCount() + " ");
        }
        sql.append(";");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Order> orderList = new ArrayList<Order>();
        if (query != null) {
            List<Object[]> lists = query.list();
            for (Object[] list : lists) {
                Order o = new Order();
                o.setOrder_id((String) list[0]);
                o.setUser_id((String) list[1]);
                o.setVenue_id((String) list[2]);
                o.setSport_name((String) list[3]);
                if (list[4] != null) {
                    Timestamp ts = (Timestamp) list[4];
                    o.setOrder_date(ts.toString());
                }
                o.setOrder_time((String) list[5]);
                o.getUser().setLogin_name((String) list[6]);
                o.getVenue().setVenue_name((String) list[7]);
                orderList.add(o);
            }
        }
        return orderList;
    }

    /**
     * 获取导出订单表
     * @return
     */
    public List<Order> listExportOrder() {
        StringBuilder sql = new StringBuilder("select a.order_id,a.user_id,a.venue_id,a.sport_name,a.order_date,a.order_time,b.login_name,c.venue_name" +
                " from ordersport a " +
                " left join user b on a.user_id = b.user_id " +
                " left join venue c on a.venue_id = c.venue_id " +
                " where 1=1 "
        );
        sql.append(" order by a.user_id,a.sport_name,order_date;");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Order> orderList = new ArrayList<Order>();
        if (query != null) {
            List<Object[]> lists = query.list();
            for (Object[] list:lists) {
                Order o = new Order();
                o.setOrder_id((String) list[0]);
                o.setUser_id((String) list[1]);
                o.setVenue_id((String) list[2]);
                o.setSport_name((String) list[3]);
                if (list[4] != null) {
                    Timestamp ts = (Timestamp) list[4];
                    o.setOrder_date(ts.toString());
                }
                o.setOrder_time((String) list[5]);
                o.getUser().setLogin_name((String) list[6]);
                o.getVenue().setVenue_name((String) list[7]);
                orderList.add(o);
            }
        }
        return orderList;
    }

    /**
     * 提交用户订单
     *
     * @param username  用户登录帐号
     * @param venuename   场馆名称
     * @param sportname 项目名称
     * @param ordertime 预定使用时间
     */
    public String sumbitOrder(String username, String venuename, String sportname, String ordertime) {
        // 判断项目余量是否充足
        String venueid = venueDAO.findVenueIdByVenueName(venuename);
        String sportnum = sportDAO.getSportNumByPar(venueid, sportname, ordertime);
        if (sportnum.equals("0")) {
            return "下单失败,余量不足";
        }
        // 判断用户是否为已预定状态
        User user = userDAO.getUserByLoginname(username);
        if (user.getOrdered().equals("1")) {
            return "用户已处于下单状态";
        }
        // 下单
        String sql = "insert into ordersport(user_id,venue_id,sport_name,order_date,order_time) " +
                "values ('" + user.getUser_id() + "', '" + venueid +"', '" + sportname +"', '" +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', '" +
                ordertime + "');";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
        // 修改项目余量
        sportDAO.setSportNumByPar(venueid, sportname, ordertime, Integer.parseInt(sportnum) - 1);
        // 修改用户预定状态
        userDAO.setUserOrdered(user.getUser_id(), "1");
        return "success";
    }


    /**
     * 获取订单数
     * @return
     */
    public int getOrderNum() {
        String sql = "select count(order_id) from ordersport;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<BigInteger> list = query.list();
        int num = list.get(0).intValue();
        return num;
    }

    /**
     * 更新订单表
     * @param order
     * @return
     */
    public boolean updateOrderBaseInfo(Order order) {
        String sql = "update ordersport set user_id = '" + order.getUser_id() + "', " +
                "venue_id = '" + order.getVenue_id() + "', " +
                "sport_name = '" + order.getSport_name() + "', " +
                "order_time = '" + order.getOrder_time() + "' " +
                "where order_id = '" + order.getOrder_id() + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    public boolean delOrderByOrderid(String orderid) {
        String sql = "delete from ordersport where order_id ='" + orderid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    public boolean delOrderByVidSnameOtime(String venueid, String sportname, String ordertime) {
        String sql = "delete from ordersport where venue_id = '" + venueid + "' and sport_name = '" + sportname + "' and" +
                " order_time = '" + ordertime + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }
}
