package com.xyq.vo.service;

import com.xyq.vo.dao.OrderDAO;
import com.xyq.vo.dao.SportDAO;
import com.xyq.vo.dao.VenueDAO;
import com.xyq.vo.model.Page;
import com.xyq.vo.model.Sport;
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
    private OrderDAO orderDAO;

    @Autowired
    public void setVenueDAO(VenueDAO venueDAO) {
        this.venueDAO = venueDAO;
    }

    @Autowired
    public void setSportDAO(SportDAO sportDAO) {
        this.sportDAO = sportDAO;
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Object[]> showSportList() {
        return venueDAO.listAllSport();
    }

    public Sport getSportById(String sportid) {
        return sportDAO.getSportById(sportid);
    }

    public List<Sport> getAllSport(Sport sport) {
        return sportDAO.getAllSport(sport);
    }

    public List<Sport> listPageSport(Sport sport, Page page) {
        return sportDAO.listPageSport(sport, page);
    }

    public List<Sport> listExportSport() {
        return sportDAO.listExportSport();
    }

    public String getSportNum(String venueid, String sportname, String ordertime) {
        return sportDAO.getSportNumByPar(venueid, sportname, ordertime);
    }

    public boolean delSport(String sportid) {
        Sport sport = sportDAO.getSportById(sportid);
        boolean a = sportDAO.delSport(sportid);
        boolean b = orderDAO.delOrderByVidSnameOtime(sport.getVenue_id(), sport.getSport_name(), sport.getOpenning_times());
        return (a && b);
    }

    public List<String> getOpeningTimes() {
        return sportDAO.getOpeningTimes();

    }

    public boolean addSport(Sport sport) {
        return sportDAO.addSport(sport);
    }

    public boolean updateSportBaseInfo(Sport sport) {
        return sportDAO.updateSportBaseInfo(sport);
    }
}
