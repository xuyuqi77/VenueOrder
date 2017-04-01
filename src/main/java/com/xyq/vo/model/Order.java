package com.xyq.vo.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单表
 * Created by yqxu2 on 2017/2/21.
 */
@Entity
@Table(name = "order")
public class Order {
    /**
     * 订单ID
     */
    @Id
    @GeneratedValue
    private String order_id;

    /**
     * 订单用户ID
     */
    @Column(name = "user_id")
    private String user_id;

    /**
     * 预订场馆ID
     */
    @Column(name = "venue_id")
    private String venue_id;

    /**
     * 预订项目ID
     */
    @Column(name = "sport_id")
    private String sport_id;

    /**
     * 预订项目场地位置
     */
    @Column(name = "sport_site")
    private String sport_site;

    /**
     * 下单时间
     */
    @Column(name = "order_date")
    private Date order_date;

    /**
     * 预订使用时间
     */
    @Column(name = "order_time")
    private String order_time;

    public Order() {
    }

    public Order(String user_id, String venue_id, String sport_id, String sport_site, Date order_date, String order_time) {
        this.user_id = user_id;
        this.venue_id = venue_id;
        this.sport_id = sport_id;
        this.sport_site = sport_site;
        this.order_date = order_date;
        this.order_time = order_time;
    }

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

    public String getSport_id() {
        return sport_id;
    }

    public void setSport_id(String sport_id) {
        this.sport_id = sport_id;
    }

    public String getSport_site() {
        return sport_site;
    }

    public void setSport_site(String sport_site) {
        this.sport_site = sport_site;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", venue_id='" + venue_id + '\'' +
                ", sport_id='" + sport_id + '\'' +
                ", sport_site='" + sport_site + '\'' +
                ", order_date='" + order_date + '\'' +
                ", order_time='" + order_time + '\'' +
                '}';
    }
}
