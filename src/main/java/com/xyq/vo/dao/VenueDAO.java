package com.xyq.vo.dao;

import com.xyq.vo.model.Venue;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * VenueDAO接口实现类
 * Created by yqxu2 on 2017/2/21.
 */
@Repository
public class VenueDAO {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 查询所有场馆的信息
     */
    public List<Venue> getAllVenue() {
        String sql = "select * from venue";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Venue> list = query.list();
//        for (Object[] obj1 : list) {
//            for (Object obj2 : obj1) {
//                System.out.print(obj2 + " ");
//            }
//            System.out.println();
//        }
        return list;
    }

    /**
     * 查询所有场馆和可预订的项目列表
     *
     * @return
     */
    public List<Object[]> listAllSport() {
        String sql = "select concat(b.venue_name,'  ',a.sport_name),a.opening_times,a.sport_num from newsport a left join venue b on a.venue_id = b.venue_id;";
        String hql = "select new List(a.venue_name,b.sport_name,b.sport_num) " +
                "from com.xyq.vo.model.Venue as a left join com.xyq.vo.model.Sport as b " +
                "on a.venue_id = b.venue_id ";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> lists = query.list();
        return lists;
    }

    public String findVenueIdByVenueName(String venuename) {
        String sql = "select venue_id from venue where venue_name = '" + venuename + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            List<String> lists = query.list();
            String str = lists.get(0);
            return str;
        }
        return null;
    }
}
