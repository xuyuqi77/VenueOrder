package com.xyq.vo.service;

import com.xyq.vo.dao.RoleDAO;
import com.xyq.vo.dao.UserDAO;
import com.xyq.vo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 角色服务层
 * Created by yqxu2 on 2017/4/25.
 */
@Service
@Transactional
public class RoleService {
    private RoleDAO roleDAO;
    private UserDAO userDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<Role> listAllRoles() {
        return roleDAO.listAllRoles();
    }

    public boolean updateRoleBaseInfo(Role role) {
        return roleDAO.updateRoleBaseInfo(role);
    }

    public boolean insertRole(Role role) {
        return roleDAO.insertRole(role);
    }

    public Role getRoleByRoleid(String roleid) {
        return roleDAO.getRoleByRoleid(roleid);
    }

    public boolean updateRoleRights(Role role) {
        return roleDAO.updateRoleRights(role);
    }

    public boolean deleteRole(String roleid) {
        boolean a = roleDAO.delRole(roleid);
        boolean b = userDAO.delUserRole(roleid);
        if (a && b)
            return true;
        return false;
    }
}
