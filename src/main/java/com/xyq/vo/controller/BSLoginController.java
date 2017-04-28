package com.xyq.vo.controller;

import com.xyq.vo.common.Const;
import com.xyq.vo.common.Tools.RightsHelper;
import com.xyq.vo.common.Tools.Tools;
import com.xyq.vo.model.Menu;
import com.xyq.vo.model.Role;
import com.xyq.vo.model.User;
import com.xyq.vo.service.MenuService;
import com.xyq.vo.service.RoleService;
import com.xyq.vo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 后台登录控制器
 */
@Controller
@RequestMapping("/")
public class BSLoginController {

    private UserService userService;

    private MenuService menuService;

    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 访问登录页
     *
     * @return
     */
    @RequestMapping(value = "/bslogin", method = RequestMethod.GET)
    public String loginGet() {
        return "backstage_login";
    }

    /**
     * 请求登录，验证用户
     *
     * @param session
     * @param bs_loginname
     * @param bs_password
     * @param code
     * @return
     */
    @RequestMapping(value = "/bslogin", method = RequestMethod.POST)
    public ModelAndView loginPost(HttpSession session, @RequestParam String bs_loginname, @RequestParam String bs_password, @RequestParam String code) {
        String sessionCode = (String) session.getAttribute(Const.SESSION_SECURITY_CODE);
        ModelAndView mv;
        String errInfo = "";
        if (Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)) {
            User user = userService.getUserByNameAndPwd(bs_loginname, bs_password);
            if (user != null) {
                // 更新用户最新登录时间
                user.setLastlogintime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                userService.updateLastLogin(user);
                session.setAttribute(Const.SESSION_ADMIN, user);
                session.removeAttribute(Const.SESSION_SECURITY_CODE);
            } else {
                errInfo = "用户名或密码有误！";
            }
        } else {
            errInfo = "验证码输入有误！";
        }
        if (Tools.isEmpty(errInfo)) {
            // 如果没错误 跳转到后台界面
            mv = new ModelAndView("redirect:/bs");
        } else {
            mv = new ModelAndView();
            mv.addObject("errInfo", errInfo);
            mv.addObject("bs_loginname", bs_loginname);
            mv.addObject("bs_password", bs_password);
            mv.setViewName("backstage_login");
        }
        return mv;
    }

    /**
     * 访问系统首页
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value="/bs")
    public String index(HttpSession session,Model model){
        User user = (User)session.getAttribute(Const.SESSION_ADMIN);
        user = userService.getUserAndRoleById(user.getUser_id());
        Role role = roleService.getRoleByRoleid(user.getRole_id());
        String roleRights = role!=null ? role.getRole_rights() : "";
        String userRights = user.getRights();
        //避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
        session.setAttribute(Const.SESSION_ROLE_RIGHTS, roleRights); //将角色权限存入session
        session.setAttribute(Const.SESSION_ADMIN_RIGHTS, userRights); //将用户权限存入session

        List<Menu> menuList = menuService.listAllMenu();
        if(Tools.notEmpty(userRights) || Tools.notEmpty(roleRights)){
            for(Menu menu : menuList){
                menu.setHasMenu(RightsHelper.testRights(userRights, menu.getMenuId()) || RightsHelper.testRights(roleRights, menu.getMenuId()));
                if(menu.isHasMenu()){
                    List<Menu> subMenuList = menu.getSubMenu();
                    for(Menu sub : subMenuList){
                        sub.setHasMenu(RightsHelper.testRights(userRights, sub.getMenuId()) || RightsHelper.testRights(roleRights, sub.getMenuId()));
                    }
                }
            }
        }
        model.addAttribute("admin", user);
        model.addAttribute("menuList", menuList);
        return "backstage";
    }

    /**
     * 进入首页后的默认页面
     * @return
     */
    @RequestMapping(value="/default")
    public String defaultPage(){
        return "default";
    }

    /**
     * 用户注销
     * @param session
     * @return
     */
    @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        session.removeAttribute(Const.SESSION_ADMIN);
        session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
        session.removeAttribute(Const.SESSION_ADMIN_RIGHTS);
        return "backstage_login";
    }
}
