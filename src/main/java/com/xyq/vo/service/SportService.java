package com.xyq.vo.service;

import com.xyq.vo.dao.VenueDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * SportService实现类
 * Created by yqxu2 on 2017/2/20.
 */
@Service("sportService")
@Transactional
public class SportService {
    private VenueDAO venueDAO;

    @Autowired
    public void setVenueDAO(VenueDAO venueDAO) {
        this.venueDAO = venueDAO;
    }

    public List<Object[]> showSportList() {
        return venueDAO.listAllSport();
    }
}
