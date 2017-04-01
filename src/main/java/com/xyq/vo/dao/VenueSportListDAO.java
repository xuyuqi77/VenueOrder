package com.xyq.vo.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 场馆项目列表
 * 展示所有可预约的项目
 * Created by yqxu2 on 2017/2/20.
 */
@Repository
public class VenueSportListDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List listAllSport() {
        return null;
    }
}
