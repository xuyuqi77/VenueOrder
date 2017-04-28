package com.xyq.vo.controller;

import com.xyq.vo.common.Excel.SportExcelView;
import com.xyq.vo.model.*;
import com.xyq.vo.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/bssport")
public class BSSportController {

    private SportService sportService;
    private VenueService venueService;

    @Autowired
    public void setSportService(SportService sportService) {
        this.sportService = sportService;
    }

    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    /**
     * 显示运动项目列表
     */
    @RequestMapping
    public ModelAndView list(Sport sport, HttpServletRequest request) {
        String sportName = request.getParameter("sportName");
        String venueId = request.getParameter("venueId");
        String openingTimes = request.getParameter("openingTimes");

        if (StringUtils.isNotEmpty(sportName)) {
            sport.setSport_name(sportName);
        }
        if (StringUtils.isNotEmpty(venueId)) {
            sport.setVenue_id(venueId);
        }
        if (StringUtils.isNotEmpty(openingTimes)) {
            sport.setOpenning_times(openingTimes);
        }
        List<Venue> venueList = venueService.getAllVenue();
        List<Sport> sportList = sportService.getAllSport(sport);

        Page page = (Page) request.getSession().getAttribute("sportpage");
        if (page == null){
            page = new Page();
        }
        page.setCurrentPage(1);
        page.setTotalPage(((sportList.size() + page.getShowCount() -1) / page.getShowCount()));
        List<Sport> pagesportList = sportService.listPageSport(sport, page);

        String pagemove = request.getParameter("pagemove");
        if (StringUtils.isNotEmpty(pagemove)) {
            if (pagemove.equals("1") && (page.getCurrentPage() > 1)) {
                // 上一页 且当前页面大于第一页
                // 分页查询上一页的数据
                page.setCurrentPage(page.getCurrentPage() - 1);
                pagesportList = sportService.listPageSport(sport, page);
            } else if (pagemove.equals("2") && (page.getCurrentPage() < page.getTotalPage())) {
                // 下一页 且当前页面小于总页数
                // 分页查询下一页的数据
                page.setCurrentPage(page.getCurrentPage() + 1);
                pagesportList = sportService.listPageSport(sport, page);
            }
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("bssports");
        mv.addObject("sportList", pagesportList);
        mv.addObject("venueList", venueList);
        mv.addObject("sport", sport);
        request.getSession().setAttribute("sportpage", page);
        return mv;
    }


    /**
     * 请求新增项目页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String toAdd(Model model) {
        List<Venue> venueList = venueService.getAllVenue();
        List<String> opentimeList = sportService.getOpeningTimes();
        model.addAttribute("venueList", venueList);
        model.addAttribute("opentimeList", opentimeList);
        return "bssport_info";
    }

    /**
     * 保存项目信息
     *
     * @param sport
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveSport(Sport sport) {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isEmpty(sport.getSport_id()) || sport.getSport_id().equals("0")) {
            if (!sportService.addSport(sport)) {
                mv.addObject("msg", "failed");
            } else {
                mv.addObject("msg", "success");
            }
        } else {
            sportService.updateSportBaseInfo(sport);
        }
        mv.setViewName("bssave_result");
        return mv;
    }

    /**
     * 请求编辑项目页面
     * @param sportId
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView toEdit(@RequestParam String sportId){
        ModelAndView mv = new ModelAndView();
        Sport sport = sportService.getSportById(sportId);
        List<Venue> venueList = venueService.getAllVenue();
        List<String> opentimeList = sportService.getOpeningTimes();
        mv.addObject("sport", sport);
        mv.addObject("venueList", venueList);
        mv.addObject("opentimeList", opentimeList);
        mv.setViewName("bssport_info");
        return mv;
    }

    /**
     * 删除某个项目
     * @param sportId
     * @param out
     */
    @RequestMapping(value="/delete")
    public void deleteUser(@RequestParam String sportId,PrintWriter out){
        sportService.delSport(sportId);
        out.write("success");
        out.close();
    }


    /**
     * 导出用户信息到excel
     *
     * @return
     */
    @RequestMapping(value="/excel")
    public ModelAndView export2Excel(){
        Map<String,Object> dataMap = new HashMap<String,Object>();
        List<String> titles = new ArrayList<String>();
        titles.add("项目名");
        titles.add("所属场馆");
        titles.add("开放时间");
        titles.add("剩余数量");
        dataMap.put("titles", titles);
        List<Sport> sportList = sportService.listExportSport();
        dataMap.put("sportList", sportList);
        SportExcelView erv = new SportExcelView();
        ModelAndView mv = new ModelAndView(erv,dataMap);
        return mv;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
}
