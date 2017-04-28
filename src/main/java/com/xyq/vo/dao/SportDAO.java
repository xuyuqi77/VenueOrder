package com.xyq.vo.dao;

import com.xyq.vo.model.Page;
import com.xyq.vo.model.Sport;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqxu2 on 2017/4/8.
 */
@Repository
public class SportDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public boolean addSport(Sport sport) {
        String sql = "insert into newsport(sport_id,sport_name,venue_id,opening_times,sport_num) values('" +
                String.valueOf(getSportNum()+1) + "','" + sport.getSport_name() + "','" + sport.getVenue_id() + "','" +
                sport.getOpenning_times() + "','" + sport.getSport_num() + "');";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    /**
     * 根据条件获得项目剩余数量
     * @param venueid
     * @param sportname
     * @param ordertime
     * @return
     */
    public String getSportNumByPar(String venueid, String sportname, String ordertime) {
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

    public Sport getSportById(String sportid) {
        String sql = "select * from newsport where sport_id ='" + sportid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        Sport sport = new Sport();
        List<Object[]> lists = query.list();
        if (query != null) {
            sport.setSport_id((String) lists.get(0)[0]);
            sport.setSport_name((String) lists.get(0)[1]);
            sport.setVenue_id((String) lists.get(0)[2]);
            sport.setOpenning_times((String) lists.get(0)[3]);
            sport.setSport_num((String) lists.get(0)[4]);
        }
        return sport;
    }

    /**
     * 设置项目剩余数量
     * @param venueid
     * @param sportname
     * @param ordertime
     * @param num
     */
    public void setSportNumByPar(String venueid, String sportname, String ordertime, int num) {
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

    /**
     * 获取所有运动项目
     * @param sport
     * @return
     */
    public List<Sport> getAllSport(Sport sport) {
        StringBuilder sql = new StringBuilder("select * from newsport where 1=1 ");
        if (StringUtils.isNotEmpty(sport.getSport_name())) {
            sql.append("and sport_name = '" + sport.getSport_name() + "' ");
        }
        if (StringUtils.isNotEmpty(sport.getVenue_id())) {
            sql.append("and venue_id = '" + sport.getVenue_id() + "' ");
        }
        if (StringUtils.isNotEmpty(sport.getOpenning_times())) {
            sql.append("and opening_times = '" + sport.getOpenning_times() + "' ");
        }
        sql.append(" order by venue_id,sport_name,opening_times;");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Sport> sportList = new ArrayList<Sport>();
        List<Object[]> lists = query.list();
        if (query != null) {
            for (Object[] list: lists) {
                Sport s = new Sport();
                s.setSport_id((String) list[0]);
                s.setSport_name((String) list[1]);
                s.setVenue_id((String) list[2]);
                sportList.add(s);
            }
        }
        return sportList;
    }

    /**
     * 分页获取运动项目
     * @param page
     * @return
     */
    public List<Sport> listPageSport(Sport sport, Page page) {
        StringBuilder sql = new StringBuilder("select u.sport_id,u.sport_name,r.venue_id,r.venue_name ,u.opening_times,u.sport_num " +
                "from newsport u " +
                "left join venue r on u.venue_id=r.venue_id " +
                "where 1=1 ");
        if (sport != null) {
            if (StringUtils.isNotEmpty(sport.getSport_name())) {
                sql.append(" and u.sport_name like '%" + sport.getSport_name() +"%' ");
            }
            if (StringUtils.isNotEmpty(sport.getVenue_id())) {
                sql.append(" and u.venue_id = '" + sport.getVenue_id() + "' ");
            }
            if (StringUtils.isNotEmpty(sport.getOpenning_times())) {
                sql.append(" and u.opening_times = '" + sport.getOpenning_times() + "' ");
            }
        }
        sql.append(" order by u.venue_id,u.sport_name,u.opening_times");
        if (page.getCurrentPage() == page.getTotalPage()) {
            sql.append(" limit " + (page.getCurrentPage()-1)*page.getShowCount() + "," + page.getTotalPage()*page.getShowCount());
        } else {
            sql.append(" limit " + (page.getCurrentPage()-1)*page.getShowCount() + "," + page.getShowCount() + " ");
        }
        sql.append(";");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Sport> sportList = new ArrayList<Sport>();
        if (query != null) {
            List<Object[]> lists = query.list();
            for (Object[] list:lists) {
                Sport s = new Sport();
                s.setSport_id((String) list[0]);
                s.setSport_name((String) list[1]);
                s.setVenue_id((String) list[2]);
                s.getVenue().setVenue_id((String) list[2]);
                s.getVenue().setVenue_name((String) list[3]);
                s.setOpenning_times((String) list[4]);
                s.setSport_num((String) list[5]);
                sportList.add(s);
            }
        }
        return sportList;
    }

    /**
     * 列出导出需要的运动项目
     * @return
     */
    public List<Sport> listExportSport() {
        StringBuilder sql = new StringBuilder("select u.sport_id,u.sport_name,r.venue_id,r.venue_name ,u.opening_times,u.sport_num " +
                "from newsport u " +
                "left join venue r on u.venue_id=r.venue_id ");
        sql.append(" order by u.venue_id,u.sport_name,u.opening_times;");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<Sport> sportList = new ArrayList<Sport>();
        if (query != null) {
            List<Object[]> lists = query.list();
            for (Object[] list:lists) {
                Sport s = new Sport();
                s.setSport_id((String) list[0]);
                s.setSport_name((String) list[1]);
                s.setVenue_id((String) list[2]);
                s.getVenue().setVenue_id((String) list[2]);
                s.getVenue().setVenue_name((String) list[3]);
                s.setOpenning_times((String) list[4]);
                s.setSport_num((String) list[5]);
                sportList.add(s);
            }
        }
        return sportList;
    }

    /**
     * 根据sportid删除项目
     * @param sportid
     * @return
     */
    public boolean delSport(String sportid) {
        String sql = "delete from newsport where sport_id ='" + sportid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    /**
     * 获取项目数
     * @return
     */
    public int getSportNum() {
        String sql = "select count(sport_id) from newsport;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<BigInteger> list = query.list();
        int num = list.get(0).intValue();
        return num;
    }

    /**
     * 更新项目表
     * @param sport
     * @return
     */
    public boolean updateSportBaseInfo(Sport sport) {
        String sql = "update newsport set sport_name = '" + sport.getSport_name() + "', " +
                "venue_id = '" + sport.getVenue_id() + "', " +
                "opening_times = '" + sport.getOpenning_times() + "', " +
                "sport_num = '" + sport.getSport_num() + "' " +
                "where sport_id = '" + sport.getSport_id() + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    public List<String> getOpeningTimes() {
        List<String> timeList = new ArrayList<String>();
        timeList.add("09:00-10:00");
        timeList.add("10:00-11:00");
        timeList.add("11:00-12:00");
        timeList.add("14:00-15:00");
        timeList.add("15:00-16:00");
        timeList.add("16:00-17:00");
        timeList.add("19:00-20:00");
        timeList.add("20:00-21:00");
        return timeList;
    }
}
