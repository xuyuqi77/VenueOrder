package com.xyq.vo.controller;

import com.xyq.vo.model.Venue;
import com.xyq.vo.service.MenuService;
import com.xyq.vo.service.VenueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value = "/bsvenue")
public class BSVenueController {

    private VenueService venueService;

    private MenuService menuService;

    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 显示场馆列表
     */
    @RequestMapping
    public ModelAndView list() {
        List<Venue> venueList = venueService.getAllVenue();
        ModelAndView mav = new ModelAndView();
        mav.addObject("venueList", venueList);
        mav.setViewName("bsvenue");
        return mav;
    }

    /**
     * 保存角色信息
     *
     * @param out
     */
    @RequestMapping(value = "/save")
    public void save(PrintWriter out, Venue venue, HttpServletRequest request) {
        String venueId = request.getParameter("venueId");
        String venueName = request.getParameter("venueName");
        if (StringUtils.isNotEmpty(venueId)) {
            venue.setVenue_id(venueId);
        }
        if (StringUtils.isNotEmpty(venueName)) {
            venue.setVenue_name(venueName);
        }
        boolean flag = true;
        if (venue.getVenue_id() != null && (Integer.parseInt(venue.getVenue_id()) > 0)){
            flag = venueService.updateVenueBaseInfo(venue);
        } else {
            flag = venueService.insertVenue(venue);
        }
        if (flag) {
            out.write("success");
        } else {
            out.write("failed");
        }
        out.flush();
        out.close();
    }


    /**
     * 删除某个场馆
     * @param
     * @param out
     */
    @RequestMapping(value="/delete")
    public void deleteRole(@RequestParam String venueId,PrintWriter out){
        venueService.delVenue(venueId);
        out.write("success");
        out.close();
    }
}


