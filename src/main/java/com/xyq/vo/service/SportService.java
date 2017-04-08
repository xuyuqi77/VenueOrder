package com.xyq.vo.service;

import com.xyq.vo.dao.SportDAO;
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
    private SportDAO sportDAO;

    @Autowired
    public void setVenueDAO(VenueDAO venueDAO) {
        this.venueDAO = venueDAO;
    }

    @Autowired
    public void setSportDAO(SportDAO sportDAO) {
        this.sportDAO = sportDAO;
    }

    public List<Object[]> showSportList() {
        return venueDAO.listAllSport();
    }


    public String getSportNum(String venueid, String sportname, String ordertime) {
        return sportDAO.getSportNum(venueid, sportname, ordertime);
    }
}
