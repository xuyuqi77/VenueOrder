package com.xyq.vo.model;

import javax.persistence.*;

/**
 * 运动项目表
 * Created by yqxu2 on 2017/2/20.
 */
@Entity
@Table(name = "sport")
public class Sport {
    /**
     * 项目编号
     */
    @Id
    @GeneratedValue
    private String sport_id;

    /**
     *项目名称
     */
    @Column(name = "sport_name")
    private String sport_name;

    /**
     *所属场馆编号
     */
    @Column(name = "venue_id")
    private String venue_id;

    /**
     *开放时间
     */
    @Column(name = "openning_times")
    private String openning_times;

    /**
     *场地数量
     */
    @Column(name = "sport_num")
    private String sport_num;

    public Sport() {
    }

    public Sport(String sport_id, String sport_name, String venue_id, String openning_times, String sport_num) {
        this.sport_id = sport_id;
        this.sport_name = sport_name;
        this.venue_id = venue_id;
        this.openning_times = openning_times;
        this.sport_num = sport_num;
    }

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

    @Override
    public String toString() {
        return "Sport{" +
                "sport_id='" + sport_id + '\'' +
                ", sport_name='" + sport_name + '\'' +
                ", venue_id='" + venue_id + '\'' +
                ", openning_times='" + openning_times + '\'' +
                ", sport_num='" + sport_num + '\'' +
                '}';
    }
}
