package com.xyq.vo.dao;

import com.xyq.vo.model.Menu;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单处理类
 * Created by yqxu2 on 2017/4/24.
 */
@Repository
public class MenuDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * 获取所有菜单
     * @return
     */
    public List<Menu> listAllMenu() {
        List<Menu> rl = this.listAllParentMenu();
        for(Menu menu : rl){
            List<Menu> subList = this.listSubMenuByParentId(menu.getMenuId());
            menu.setSubMenu(subList);
        }
        return rl;
    }

    /**
     * 根据父菜单ID获取所有子菜单
     * @param parentId
     * @return
     */
    public List<Menu> listSubMenuByParentId(Integer parentId) {
        String sql = "select * from menu where parent_id is not null and parent_id = '" + parentId + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> lists = query.list();
        List<Menu> listmenu = new ArrayList<Menu>();
        for (Object[] a:lists) {
            Menu menu = new Menu();
            menu.setMenuId((Integer) a[0]);
            menu.setMenuName((String) a[1]);
            menu.setMenuUrl((String) a[2]);
            menu.setParentId((Integer) a[3]);
            listmenu.add(menu);
        }
        return listmenu;
    }

    /**
     * 获取所有父菜单
     * @return
     */
    public List<Menu> listAllParentMenu() {
        String sql = "select * from menu where parent_id is null";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> lists = query.list();
        List<Menu> listmenu = new ArrayList<Menu>();
        for (Object[] a:lists) {
            Menu menu = new Menu();
            menu.setMenuId((Integer) a[0]);
            menu.setMenuName((String) a[1]);
            menu.setMenuUrl((String) a[2]);
            menu.setParentId((Integer) a[3]);
            listmenu.add(menu);
        }
        return listmenu;
    }

    /**
     * 根据MenuID获取Menu
     * @param menuid
     * @return
     */
    public Menu getMenuById(int menuid) {
        String sql = "select * from menu where menu_id = " + menuid + ";";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            Menu menu = new Menu();
            List<Object[]> lists = query.list();
            menu.setMenuId((Integer) lists.get(0)[0]);
            menu.setMenuName((String) lists.get(0)[1]);
            menu.setMenuUrl((String) lists.get(0)[2]);
            menu.setParentId((Integer) lists.get(0)[3]);
            return menu;
        }
        return null;
    }

    /**
     * 添加新菜单
     * @param menu
     * @return
     */
    public boolean addMenu(Menu menu) {
        String sql = "insert into menu(menu_id,menu_name,menu_url,parent_id) " +
                "values (" + (getMenuMaxNum()+1) + ",'" + menu.getMenuName() + "','" + menu.getMenuUrl() + "'," + menu.getParentId() +
                ");";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            int sf = query.executeUpdate();
            if (sf == 1)
                return true;
            return false;
        }
        return false;
    }

    /**
     * 根据MenuID更新菜单
     * @param menu
     * @return
     */
    public boolean updateMenu(Menu menu) {
        String sql = "update menu set menu_name = '" + menu.getMenuName() + "', menu_url = '" + menu.getMenuUrl() + "', parent_id = " + menu.getParentId() +
                " where menu_id = " + menu.getMenuId() + ";";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            int sf = query.executeUpdate();
            if (sf == 1)
                return true;
            return false;
        }
        return false;
    }

    /**
     * 根据MenuID删除Menu 包括子菜单
     * @param menuid
     * @return
     */
    public boolean deleteMenuById(Integer menuid) {
        String sql = "delete from menu where menu_id = " + menuid.intValue() + " or parent_id = " + menuid.intValue() + ";";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            int sf = query.executeUpdate();
            if (sf == 1)
                return true;
            return false;
        }
        return false;
    }

    /**
     * 获取最大角色编号
     * @return
     */
    public int getMenuMaxNum() {
        String sql = "select (menu_id) from menu;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<String> list = query.list();
        int num = Integer.parseInt(list.get(0));
        return num;
    }
}
