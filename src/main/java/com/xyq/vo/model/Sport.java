package com.xyq.vo.model;

import javax.persistence.*;

/**
 * 运动项目表
 * Created by yqxu2 on 2017/2/20.
 */
public class Sport {
    /**
     * 项目编号
     */
    private String sport_id;

    /**
     *项目名称
     */
    private String sport_name;

    /**
     *所属场馆编号
     */
    private String venue_id;

    /**
     *开放时间
     */
    private String openning_times;

    /**
     *场地数量
     */
    private String sport_num;

    /**
     * 所属场馆
     */
    private Venue venue;

    public String getSport_id() {
        return sport_id;
    }

    public void setSport_id(String sport_id) {
        this.sport_id = sport_id;
    }

    public String getSport_name() {
        return sport_name;
    }

    public void setSport_name(String sport_name) {
        this.sport_name = sport_name;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getOpenning_times() {
        return openning_times;
    }

    public void setOpenning_times(String openning_times) {
        this.openning_times = openning_times;
    }

    public String getSport_num() {
        return sport_num;
    }

    public void setSport_num(String sport_num) {
        this.sport_num = sport_num;
    }

    public Venue getVenue() {
        if (venue == null)
            venue = new Venue();
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
