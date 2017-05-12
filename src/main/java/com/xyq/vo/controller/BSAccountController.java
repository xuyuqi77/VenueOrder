package com.xyq.vo.controller;

import com.xyq.vo.model.Account;
import com.xyq.vo.model.Page;
import com.xyq.vo.model.User;
import com.xyq.vo.model.Venue;
import com.xyq.vo.service.AccountService;
import com.xyq.vo.service.MenuService;
import com.xyq.vo.service.UserService;
import com.xyq.vo.service.VenueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value = "/bsaccount")
public class BSAccountController {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 显示账户列表
     */
    @RequestMapping
    public ModelAndView list(User user, HttpServletRequest request) {
        String login_name = request.getParameter("login_name");
        if (StringUtils.isNotEmpty(login_name)) {
            user.setLogin_name(login_name);
        }
        List<Account> accountList = accountService.listAllAccount(user);

        Page page = (Page) request.getSession().getAttribute("accountpage");
        if (page == null){
            page = new Page();
            page.setCurrentPage(1);
        }
        page.setTotalPage(((accountList.size() + page.getShowCount() -1) / page.getShowCount()));
        List<Account> pageAccountList = accountService.listPageAccount(user, page);

        String pagemove = request.getParameter("pagemove");
        if (StringUtils.isNotEmpty(pagemove)) {
            if (pagemove.equals("1") && (page.getCurrentPage() > 1)) {
                // 上一页 且当前页面大于第一页
                // 分页查询上一页的数据
                page.setCurrentPage(page.getCurrentPage() - 1);
                pageAccountList = accountService.listPageAccount(user, page);
            } else if (pagemove.equals("2") && (page.getCurrentPage() < page.getTotalPage())) {
                // 下一页 且当前页面小于总页数
                // 分页查询下一页的数据
                page.setCurrentPage(page.getCurrentPage() + 1);
                pageAccountList = accountService.listPageAccount(user, page);
            }
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("bsaccount");
        mv.addObject("accountList", pageAccountList);
        mv.addObject("user", user);
        request.getSession().setAttribute("accountpage", page);
        return mv;
    }

    /**
     * 保存账户信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Account account, HttpServletRequest request) {
        String login_name = request.getParameter("login_name");
        if (StringUtils.isNotEmpty(login_name)) {
            User user = userService.getUserByLoginname(login_name);
            account.setUser_id(user.getUser_id());
        }
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isNotEmpty(account.getAccount_id()) && (Integer.parseInt(account.getAccount_id()) > 0)){
            accountService.updateAccountBaseInfo(account);
        } else {
            if (!accountService.insertAccount(account)) {
                mv.addObject("msg", "failed");
            } else {
                mv.addObject("msg", "success");
            }
        }
        mv.setViewName("bssave_result");
        return mv;
    }

    /**
     * 请求新增账户页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String toAdd(Model model) {
        return "bsaccount_info";
    }

    /**
     * 请求编辑账户页面
     * @param accountId
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView toEdit(@RequestParam String accountId){
        ModelAndView mv = new ModelAndView();
        Account account = accountService.getAccountById(accountId);
        mv.addObject("account", account);
        mv.setViewName("bsaccount_info");
        return mv;
    }

}


