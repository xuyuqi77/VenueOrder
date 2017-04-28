package com.xyq.vo.dao;

import com.xyq.vo.model.Venue;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
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
        List<Venue> listvenue = new ArrayList<Venue>();
        List<Object[]> lists = query.list();
        if (query != null) {
            for (Object[] list: lists) {
                Venue venue = new Venue();
                venue.setVenue_id((String) list[0]);
                venue.setVenue_name((String) list[1]);
                venue.setVenue_description((String) list[2]);
                listvenue.add(venue);
            }
        }
        return listvenue;
    }

    /**
     * 更新场馆基本信息
     * @return
     */
    public boolean updateVenueBaseInfo(Venue venue) {
        String sql = "update venue set venue_name = '" + venue.getVenue_name() + "'" +
                " where venue_id = '" + venue.getVenue_id() + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            int sf = query.executeUpdate();
            if (sf == 1)
                return true;
            return false;
        }
        return false;
    }

    /**
     * 添加新场馆
     * @return
     */
    public boolean insertVenue(Venue venue) {
        String sql = "insert into venue(venue_id,venue_name) " +
                "values('" + String.valueOf(getVenueNum() + 1) + "','" +venue.getVenue_name() + "');";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            int sf = query.executeUpdate();
            if (sf == 1)
                return true;
            return false;
        }
        return false;
    }

    /**
     * 查询所有场馆和可预订的项目列表
     *
     * @return
     */
    public List<Object[]> listAllSport() {
        String sql =
                "select b.venue_name,a.sport_name,a.opening_times,a.sport_num " +
                "from newsport a " +
                "left join venue b " +
                "on a.venue_id = b.venue_id " +
                "order by b.venue_name,a.sport_name,a.opening_times;";
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

    /**
     * 获取场馆数量
     * @return
     */
    public int getVenueNum() {
        String sql = "select count(venue_id) from venue;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<BigInteger> list = query.list();
        int num = list.get(0).intValue();
        return num;
    }

    /**
     * 删除场馆
     * @param venueid
     * @return
     */
    public boolean delVenue(String venueid) {
        String sql = "delete from venue where venue_id = '" + venueid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }
}
