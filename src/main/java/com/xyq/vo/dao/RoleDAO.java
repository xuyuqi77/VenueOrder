package com.xyq.vo.dao;

import com.xyq.vo.model.Role;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色处理层
 * Created by yqxu2 on 2017/4/25.
 */
@Repository
public class RoleDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 获取所有角色
     * @return
     */
    public List<Role> listAllRoles() {
        String sql = "select * from role";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Role> listrole = new ArrayList<Role>();
        List<Object[]> lists = query.list();
        if (query != null) {
            for (Object[] list: lists) {
                Role role = new Role();
                role.setRole_id((String) list[0]);
                role.setRole_name((String) list[1]);
                role.setRole_rights((String) list[2]);
                listrole.add(role);
            }
        }
        return listrole;
    }

    /**
     * 更新角色基本信息
     * @param role
     * @return
     */
    public boolean updateRoleBaseInfo(Role role) {
        String sql = "update role set role_name = '" + role.getRole_name() + "'" +
                " where role_id = '" + role.getRole_id() + "';";
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
     * 添加新角色
     * @param role
     * @return
     */
    public boolean insertRole(Role role) {
        String sql = "insert into role(role_id,role_name) " +
                "values('" + String.valueOf(getRoleMaxNum() + 1) + "','" +role.getRole_name() + "');";
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
     * 根据角色ID获取角色
     * @param roleid
     * @return
     */
    public Role getRoleByRoleid(String roleid) {
        String sql = "select * from role where role_id = '" + roleid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            Role role = new Role();
            List<Object[]> list = query.list();
            Object[] list1 = list.get(0);
            role.setRole_id((String) list1[0]);
            role.setRole_name((String) list1[1]);
            role.setRole_rights((String) list1[2]);
            return role;
        }
        return null;
    }

    /**
     * 更新角色权限
     * @param role
     * @return
     */
    public boolean updateRoleRights(Role role) {
        String sql = "update role set role_rights = '" + role.getRole_rights() + "'" +
                " where role_id = '" + role.getRole_id() + "';";
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
    public int getRoleMaxNum() {
        String sql = "select max(role_id) from role;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<String> list = query.list();
        int num = Integer.parseInt(list.get(0));
        return num;
    }

    public boolean delRole(String roleid) {
        String sql = "delete from role where role_id = '" + roleid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }
}
