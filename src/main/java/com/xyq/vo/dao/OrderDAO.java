package com.xyq.vo.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yqxu2 on 2017/4/3.
 */
@Repository
public class OrderDAO {

    private SessionFactory sessionFactory;
    private VenueDAO venueDAO;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setVenueDAO(VenueDAO venueDAO) {
        this.venueDAO = venueDAO;
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
            sql.append(" and a.venue_id = " + venueid);
        }
        if (sport_name != null && !"".equals(sport_name)) {
            sql.append(" and a.sport_name = " + sport_name);
        }
        sql.append(";");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> lists = query.list();
        return lists;
    }
}
