package com.xyq.vo.controller;

import com.alibaba.fastjson.JSON;
import com.xyq.vo.common.Tools.RightsHelper;
import com.xyq.vo.common.Tools.Tools;
import com.xyq.vo.model.Menu;
import com.xyq.vo.model.Role;
import com.xyq.vo.service.MenuService;
import com.xyq.vo.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value = "/bsrole")
public class BSRoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 显示角色列表
     */
    @RequestMapping
    public ModelAndView list() {
        List<Role> roleList = roleService.listAllRoles();
        ModelAndView mav = new ModelAndView();
        mav.addObject("roleList", roleList);
        mav.setViewName("bsroles");
        return mav;
    }

    /**
     * 保存角色信息
     *
     * @param out
     */
    @RequestMapping(value = "/save")
    public void save(PrintWriter out, Role role, HttpServletRequest request) {
        String roleId = request.getParameter("roleId");
        String roleName = request.getParameter("roleName");
        if (StringUtils.isNotEmpty(roleId)) {
            role.setRole_id(roleId);
        }
        if (StringUtils.isNotEmpty(roleName)) {
            role.setRole_name(roleName);
        }
        boolean flag = true;
        if (role.getRole_id() != null && (Integer.parseInt(role.getRole_id()) > 0)){
            flag = roleService.updateRoleBaseInfo(role);
        } else {
            flag = roleService.insertRole(role);
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
     * 请求角色授权页面
     *
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/auth")
    public String auth(@RequestParam String roleId, Model model) {
        List<Menu> menuList = menuService.listAllMenu();
        Role role = roleService.getRoleByRoleid(roleId);
        String roleRights = role.getRole_rights();
        if (Tools.notEmpty(roleRights)) {
            for (Menu menu : menuList) {
                menu.setHasMenu(RightsHelper.testRights(roleRights, menu.getMenuId()));
                if (menu.isHasMenu()) {
                    List<Menu> subMenuList = menu.getSubMenu();
                    for (Menu sub : subMenuList) {
                        sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMenuId()));
                    }
                }
            }
        }
        String json = JSON.toJSONString(menuList);
        json = json.replaceAll("menuId", "id").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
        model.addAttribute("zTreeNodes", json);
        model.addAttribute("roleId", roleId);
        return "authorization";
    }

    /**
     * 保存角色权限
     *
     * @param roleId
     * @param menuIds
     * @param out
     */
    @RequestMapping(value = "/auth/save")
    public void saveAuth(@RequestParam String roleId, @RequestParam String menuIds, PrintWriter out) {
        Role role = roleService.getRoleByRoleid(roleId);
        role.setRole_rights(menuIds);
        roleService.updateRoleRights(role);
        out.write("success");
        out.close();
    }

    /**
     * 删除某个角色
     * @param
     * @param out
     */
    @RequestMapping(value="/delete")
    public void deleteRole(@RequestParam String roleId,PrintWriter out){
        roleService.deleteRole(roleId);
        out.write("success");
        out.close();
    }
}


