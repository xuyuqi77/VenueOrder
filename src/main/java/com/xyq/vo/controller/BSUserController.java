package com.xyq.vo.controller;

import com.alibaba.fastjson.JSON;
import com.xyq.vo.common.Tools.RightsHelper;
import com.xyq.vo.common.Tools.Tools;
import com.xyq.vo.common.Excel.UserExcelView;
import com.xyq.vo.model.Menu;
import com.xyq.vo.model.Page;
import com.xyq.vo.model.Role;
import com.xyq.vo.model.User;
import com.xyq.vo.service.MenuService;
import com.xyq.vo.service.RoleService;
import com.xyq.vo.service.UserService;
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
@RequestMapping(value = "/bsuser")
public class BSUserController {

    private UserService userService;
    private RoleService roleService;
    private MenuService menuService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 显示用户列表
     *
     * @param user
     * @return
     */
    @RequestMapping
    public ModelAndView list(User user, HttpServletRequest request) {
        String login_name = request.getParameter("login_name");
        String role_id = request.getParameter("role_id");
        if (StringUtils.isNotEmpty(login_name)) {
            user.setLogin_name(login_name);
        }
        if (StringUtils.isNotEmpty(role_id)) {
            user.setRole_id(role_id);
        }
        List<Role> roleList = roleService.listAllRoles();
        List<User> alluserList = userService.listAllUser(user);

        Page page = (Page) request.getSession().getAttribute("userpage");
        if (page == null){
            page = new Page();
            page.setCurrentPage(1);
        }
        page.setTotalPage(((alluserList.size() + page.getShowCount() -1) / page.getShowCount()));
        List<User> pageuserList = userService.listPageUser(user, page);

        String pagemove = request.getParameter("pagemove");
        if (StringUtils.isNotEmpty(pagemove)) {
            if (pagemove.equals("1") && (page.getCurrentPage() > 1)) {
                // 上一页 且当前页面大于第一页
                // 分页查询上一页的数据
                page.setCurrentPage(page.getCurrentPage() - 1);
                pageuserList = userService.listPageUser(user, page);
            } else if (pagemove.equals("2") && (page.getCurrentPage() < page.getTotalPage())) {
                // 下一页 且当前页面小于总页数
                // 分页查询下一页的数据
                page.setCurrentPage(page.getCurrentPage() + 1);
                pageuserList = userService.listPageUser(user, page);
            }
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("bsusers");
        mv.addObject("userList", pageuserList);
        mv.addObject("roleList", roleList);
        mv.addObject("user", user);
        request.getSession().setAttribute("userpage", page);
        return mv;
    }


    /**
     * 请求新增用户页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String toAdd(Model model) {
        List<Role> roleList = roleService.listAllRoles();
        model.addAttribute("roleList", roleList);
        return "bsuser_info";
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(User user) {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isEmpty(user.getUser_id()) || user.getUser_id().equals("0")) {
            if (!userService.addUser(user)) {
                mv.addObject("msg", "failed");
            } else {
                mv.addObject("msg", "success");
            }
        } else {
            userService.updateUserBaseInfo(user);
        }
        mv.setViewName("bssave_result");
        return mv;
    }

    /**
     * 请求编辑用户页面
     * @param userId
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView toEdit(@RequestParam String userId){
        if (StringUtils.isNotEmpty(userId) && (userId.length() == 1)) {
            userId = "0" + userId;
        }
        ModelAndView mv = new ModelAndView();
        User user = userService.getUserByUserid(userId);
        List<Role> roleList = roleService.listAllRoles();
        mv.addObject("user", user);
        mv.addObject("roleList", roleList);
        mv.setViewName("bsuser_info");
        return mv;
    }

    /**
     * 删除某个用户
     * @param userId
     * @param out
     */
    @RequestMapping(value="/delete")
    public void deleteUser(@RequestParam String userId,PrintWriter out){
        if (StringUtils.isNotEmpty(userId) && (userId.length() == 1)) {
            userId = "0" + userId;
        }
        userService.delUser(userId);
        out.write("success");
        out.close();
    }

    /**
     * 请求用户授权页面
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value="/auth")
    public String auth(@RequestParam String userId,Model model){
        if (StringUtils.isNotEmpty(userId) && (userId.length() == 1)) {
            userId = "0" + userId;
        }
        List<Menu> menuList = menuService.listAllMenu();
        User user = userService.getUserByUserid(userId);
        String userRights = user.getRights();
        if(Tools.notEmpty(userRights)){
            for(Menu menu : menuList){
                menu.setHasMenu(RightsHelper.testRights(userRights, menu.getMenuId()));
                if(menu.isHasMenu()){
                    List<Menu> subRightsList = menu.getSubMenu();
                    for(Menu sub : subRightsList){
                        sub.setHasMenu(RightsHelper.testRights(userRights, sub.getMenuId()));
                    }
                }
            }
        }

        String json = JSON.toJSONString(menuList);
        json = json.replaceAll("menuId", "id").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
        model.addAttribute("zTreeNodes", json);
        model.addAttribute("userId", userId);
        return "authorization";
    }

    /**
     * 保存用户权限
     * @param userId
     * @param menuIds
     * @param out
     */
    @RequestMapping(value="/auth/save")
    public void saveAuth(@RequestParam String userId,@RequestParam String menuIds,PrintWriter out){
        User user = userService.getUserByUserid(userId);
        user.setRights(menuIds);
        userService.updateUserRights(user);
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
        titles.add("用户名");
        titles.add("名称");
        titles.add("角色");
        titles.add("最近登录");
        dataMap.put("titles", titles);
        List<User> userList = userService.listAllUser(new User());
        dataMap.put("userList", userList);
        UserExcelView erv = new UserExcelView();
        ModelAndView mv = new ModelAndView(erv,dataMap);
        return mv;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
}
