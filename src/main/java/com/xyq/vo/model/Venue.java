package com.xyq.vo.model;

import javax.persistence.*;

/**
 * 场馆表
 * Created by yqxu2 on 2017/2/20.
 */
@Entity
@Table(name = "venue")
public class Venue {
    /**
     * 场馆编号
     */
    @Id
    @GeneratedValue
    private String venue_id;

    /**
     * 场馆名称
     */
    @Column(name="venue_name")
    private String venue_name;

    /**
     *描述
     */
    @Column(name="venue_description")
    private String venue_description;

    public Venue() {
    }

    public Venue(String venue_id, String venue_name, String venue_description) {
        this.venue_id = venue_id;
        this.venue_name = venue_name;
        this.venue_description = venue_description;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_description() {
        return venue_description;
    }

    public void setVenue_description(String venue_description) {
        this.venue_description = venue_description;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "venue_id='" + venue_id + '\'' +
                ", venue_name='" + venue_name + '\'' +
                ", venue_description='" + venue_description + '\'' +
                '}';
    }
}
