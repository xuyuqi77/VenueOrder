package com.xyq.vo.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单表
 * Created by yqxu2 on 2017/2/21.
 */
public class Order {
    /**
     * 订单ID
     */
    private String order_id;

    /**
     * 订单用户ID
     */
    private String user_id;

    /**
     * 预订场馆ID
     */
    private String venue_id;

    /**
     * 预订项目
     */
    private String sport_name;

    /**
     * 预订项目场地位置
     */
    private String sport_site;

    /**
     * 下单时间
     */
    private String order_date;

    /**
     * 预订使用时间
     */
    private String order_time;

    private User user;

    private Venue venue;

    private String login_name;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getSport_name() {
        return sport_name;
    }

    public void setSport_name(String sport_name) {
        this.sport_name = sport_name;
    }

    public String getSport_site() {
        return sport_site;
    }

    public void setSport_site(String sport_site) {
        this.sport_site = sport_site;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public User getUser() {
        if (user == null)
            user = new User();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Venue getVenue() {
        if (venue == null)
            venue = new Venue();
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
}
