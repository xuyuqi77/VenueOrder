package com.xyq.vo.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yqxu2 on 2017/4/8.
 */
@Component
public class SportDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String getSportNum(String venueid, String sportname, String ordertime) {
        String sql =
                "select a.sport_num" +
                        " from newsport a " +
                        " where a.venue_id = '" + venueid + "'" +
                        " and a.sport_name = '" + sportname + "'" +
                        " and a.opening_times = '" + ordertime + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            List<String> lists = query.list();
            String str = lists.get(0);
            return str;
        }
        return null;
    }

    public void setSportNum(String venueid, String sportname, String ordertime, int num) {
        String sportnum = String.valueOf(num);
        String sql =
                "update newsport a " +
                        "set a.sport_num = '" + sportnum + "'" +
                        " where a.venue_id = '" + venueid + "'" +
                        " and a.sport_name = '" + sportname + "'" +
                        " and a.opening_times = '" + ordertime + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }
}
