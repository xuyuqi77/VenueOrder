package com.xyq.vo.service;

import com.xyq.vo.dao.VenueDAO;
import com.xyq.vo.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by yqxu2 on 2017/2/21.
 */
@Service
@Transactional
public class VenueService {
    private VenueDAO venueDAO;

    @Autowired
    public void setVenueDAO(VenueDAO venueDAO) {
        this.venueDAO = venueDAO;
    }

    public List<Venue> getAllVenue() {
        List<Venue> list = venueDAO.getAllVenue();
        return list;
    }

    public boolean insertVenue(Venue venue) {
        return venueDAO.insertVenue(venue);
    }

    public boolean updateVenueBaseInfo(Venue venue) {
        return venueDAO.updateVenueBaseInfo(venue);
    }

    public boolean delVenue(String venueid) {
        return venueDAO.delVenue(venueid);
    }
}
