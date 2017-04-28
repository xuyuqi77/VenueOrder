package com.xyq.vo.service;

import com.xyq.vo.dao.MenuDAO;
import com.xyq.vo.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单服务层
 * Created by yqxu2 on 2017/4/24.
 */
@Service
public class MenuService {

    private MenuDAO menuDAO;

    @Autowired
    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public List<Menu> listAllMenu() {
        return menuDAO.listAllMenu();
    }

    public List<Menu> listAllParentMenu() {
        return menuDAO.listAllParentMenu();
    }

    public List<Menu> listSubMenuByParentId(Integer parentid) {
        return menuDAO.listSubMenuByParentId(parentid);
    }

    public Menu getMenuById(int menuid) {
        return menuDAO.getMenuById(menuid);
    }

    public boolean addMenu(Menu menu) {
        return menuDAO.addMenu(menu);
    }

    public boolean updateMenu(Menu menu) {
        return menuDAO.updateMenu(menu);
    }

    public boolean deleteMenuById(Integer menuid) {
        return menuDAO.deleteMenuById(menuid);
    }
}
