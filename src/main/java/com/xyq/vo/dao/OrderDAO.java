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

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Object[]> listOrderTable(String venueid, String sport_name) {
        // 设置默认值
        if(venueid == null || "".equals(venueid)){
            venueid = "1";
        }
        if(sport_name == null || "".equals(sport_name)){
            sport_name = "羽毛球";
        }

        String sql = "" +
                "select ns.opening_times,ns.sport_num " +
                "  from newsport ns " +
                " where ns.venue_id = '" + venueid + "'" +
                "   and ns.sport_name = '" + sport_name + "';";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> lists = query.list();
        return lists;
    }
}
