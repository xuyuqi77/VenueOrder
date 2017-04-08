package com.xyq.vo.dao;

import com.xyq.vo.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yqxu2 on 2017/4/3.
 */
@Repository
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
     * 提交用户订单
     *
     * @param username  用户登录帐号
     * @param venuename   场馆名称
     * @param sportname 项目名称
     * @param ordertime 预定使用时间
     */
    @Transactional
    public String sumbitOrder(String username, String venuename, String sportname, String ordertime) {
        // 判断项目余量是否充足
        String venueid = venueDAO.findVenueIdByVenueName(venuename);
        String sportnum = sportDAO.getSportNum(venueid, sportname, ordertime);
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
        sportDAO.setSportNum(venueid, sportname, ordertime, Integer.parseInt(sportnum) - 1);
        // 修改用户预定状态
        userDAO.setUserOrdered(user.getUser_id(), "1");
        return "success";
    }
}
