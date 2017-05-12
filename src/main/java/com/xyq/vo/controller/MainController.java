package com.xyq.vo.controller;

import com.xyq.vo.common.Tools.Trans;
import com.xyq.vo.model.Sport;
import com.xyq.vo.model.Venue;
import com.xyq.vo.service.OrderService;
import com.xyq.vo.service.SportService;
import com.xyq.vo.service.VenueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 主控制层
 * Created by yqxu2 on 2017/2/16.
 */
@Controller
@RequestMapping("/")
public class MainController {

    private SportService sportService;
    private VenueService venueService;
    private OrderService orderService;

    @Autowired
    public void setSportService(SportService sportService) {
        this.sportService = sportService;
    }

    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 首页界面
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request) {
        List<Object[]> sportList = sportService.showSportList();
        request.getSession().setAttribute("vp_lists", sportList);
        return "index";
    }

    /**
     * 预定界面
     * @return
     */
    @RequestMapping("/order")
    public String toOrder(HttpServletRequest request) {
        if (request.getSession().getAttribute("orderTable") == null) {
            List<Object[]> list = orderService.listOrderTable("","");
            request.getSession().setAttribute("orderTable", list);
            List<Venue> venueList = venueService.getAllVenue();
            List<Sport> sportList = sportService.getAllSportName();
            // 选择项目标红
            request.getSession().setAttribute("c_v_back", Trans.toOrderChooseVenue(null, venueList));
            request.getSession().setAttribute("c_s_back", Trans.toOrderChooseSport(null, sportList));
        }
        return "order";
    }

    /**
     * 场馆界面
     * @param request
     * @return
     */
    @RequestMapping("/venue")
    public String toVenue(HttpServletRequest request) {
        String pic = request.getParameter("pic");
        String word = request.getParameter("word");
        if (StringUtils.isNotBlank(pic) && StringUtils.isNotBlank(word)) {
            request.getSession().setAttribute("venue_pic", Trans.toPic(pic));
            request.getSession().setAttribute("venue_word", Trans.toWord(word));
        } else {
            request.getSession().setAttribute("venue_pic", Trans.toPic("1"));
            request.getSession().setAttribute("venue_word", Trans.toWord("1"));
        }
        return "venue";
    }

    /**
     * 相册界面
     * @return
     */
    @RequestMapping("/picture")
    public String toImage(HttpServletRequest request) {
        String ven = request.getParameter("ven");
        if (StringUtils.isNotBlank(ven)) {
            request.getSession().setAttribute("image_ven", Trans.toVenueName(ven));
            request.getSession().setAttribute("image_pic", Trans.toVenuePic(ven));
        } else {
            request.getSession().setAttribute("image_ven", Trans.toVenueName("1"));
            request.getSession().setAttribute("image_pic", Trans.toVenuePic("1"));
        }
        return "picture";
    }

    /**
     * 相关界面
     * @return
     */
    @RequestMapping("/about")
    public String toAbout() {
        return "about";
    }

    /**
     * 登录界面
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

}
