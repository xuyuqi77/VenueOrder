package com.xyq.vo.dao;

import com.xyq.vo.model.Page;
import com.xyq.vo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAO 用户相关操作处理类
 * Created by yqxu2 on 2017/2/16.
 */
@Repository
public class UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 添加用户
     * @param u
     */
    public boolean add(User u) {
        if (u.getUser_id() == null) {
            u.setUser_id(String.valueOf(getUserMaxNum() + 1));
        }
        String sql = "insert into user(user_id, login_name, password, user_name, role_id)" +
                " values ('" + u.getUser_id() + "', '" + u.getLogin_name() + "', '" +
                u.getPassword() + "', '" + u.getUser_name() + "', '" +
                u.getRole_id() + "');";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    public boolean del(String userid) {
        String sql = "delete from user where user_id = '" + userid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    /**
     * 将角色id = roleid的用户设为普通用户
     * @param roleid
     * @return
     */
    public boolean delUserRole(String roleid) {
        String sql = "update user set role_id = '1'" + " where role_id = '" + roleid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    /**
     * 根据用户登录名获取用户
     * @param loginname
     * @return
     */
    public User getUserByLoginname(String loginname) {
        String sql = "select * from user where login_name = '" + loginname +"' ;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            List<Object[]> lists = query.list();
            if(lists.size() == 0)
                return null;
            Object[] list = lists.get(0);
            User user = new User();
            user.setUser_id((String) list[0]);
            user.setLogin_name((String) list[1]);
            user.setPassword((String) list[2]);
            user.setUser_name((String) list[3]);
            user.setRole_id((String) list[4]);
            if (list[5] != null) {
                Timestamp ts = (Timestamp) list[5];
                user.setLastlogintime(ts.toString());
            }
            user.setOrdered((String) list[6]);
            user.setRights((String) list[7]);
            return user;
        }
        return null;
    }

    /**
     * 根据用户ID获取用户
     * @param userid
     * @return
     */
    public User getUserByUserid(String userid) {
        String sql = "select * from user where user_id = '" + userid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            User user = new User();
            List<Object[]> lists = query.list();
            if (lists.size() == 0)
                return null;
            Object[] list = lists.get(0);
            user.setUser_id((String) list[0]);
            user.setLogin_name((String) list[1]);
            user.setPassword((String) list[2]);
            user.setUser_name((String) list[3]);
            user.setRole_id((String) list[4]);
            if (list[5] != null) {
                Timestamp ts = (Timestamp) list[5];
                user.setLastlogintime(ts.toString());
            }
            user.setOrdered((String) list[6]);
            user.setRights((String) list[7]);
            return user;
        }
        return null;
    }

    /**
     * 根据登录名和密码获取用户
     * @param loginname
     * @param password
     * @return
     */
    public User getUserByNameAndPwd(String loginname, String password) {
        String sql = "select * from user where login_name = '" + loginname + "' and password = '" + password + "' ;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            User user = new User();
            List<Object[]> list = query.list();
            if (list.size() == 0)
                return null;
            Object[] list1 = list.get(0);
            user.setUser_id((String) list1[0]);
            user.setLogin_name((String) list1[1]);
            user.setPassword((String) list1[2]);
            user.setUser_name((String) list1[3]);
            user.setRole_id((String) list1[4]);
//            user.setLastlogintime((String) list1[5]);
            user.setOrdered((String) list1[6]);
            return user;
        }
        return null;
    }

    /**
     * 根据userid获取用户
     * @param userid
     * @return
     */
    public User getUserAndRoleById(String userid) {
        String sql = "select * from user where user_id = '" + userid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query != null) {
            User user = new User();
            List<Object[]> lists = query.list();
            Object[] list = lists.get(0);
            user.setUser_id((String) list[0]);
            user.setLogin_name((String) list[1]);
            user.setPassword((String) list[2]);
            user.setUser_name((String) list[3]);
            user.setRole_id((String) list[4]);
            if (list[5] != null) {
                Timestamp ts = (Timestamp) list[5];
                user.setLastlogintime(ts.toString());
            }
            user.setOrdered((String) list[6]);
            user.setRights((String) list[7]);
            return user;
        }
        return null;
    }

    /**
     * 设置用户的预定状态
     * @param userid
     * @param ordered
     */
    public boolean setUserOrdered(String userid, String ordered) {
        String sql = "update user a set a.ordered = '" + ordered + "' " +
                "where a.user_id = '" + userid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int i = query.executeUpdate();
        if (i == 1)
            return true;
        return false;
    }

    /**
     * 更新用户的最新登陆时间
     * @param user
     */
    public void updateLastLogin(User user) {
        String sql = "update user a set a.lastlogintime = '" + user.getLastlogintime() + "'" +
                "where a.user_id = '" + user.getUser_id() + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    public boolean updateUserPassword(User user) {
        String sql = "update user a set a.password = '" + user.getN_password() + "' " +
                " where a.password = '" + user.getPassword() + "' and a.user_id = '" + user.getUser_id() + "' " +
                " and a.login_name = '" + user.getLogin_name() + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int i = query.executeUpdate();
        if (i == 1)
            return true;
        return false;
    }

    /**
     * 获取最大用户编号
     * @return
     */
    public int getUserMaxNum() {
        String sql = "select max(user_id) from user;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<String> list = query.list();
        int num = Integer.parseInt(list.get(0));
        return num;
    }

    /**
     * 获取所有用户
     * @param user
     * @return
     */
    public List<User> listAllUser(User user) {
        StringBuilder sql = new StringBuilder("select u.user_id,u.user_name,u.login_name,u.password,r.role_id,r.role_name,u.lastlogintime,u.ordered " +
                "from user u " +
                "left join role r on u.role_id=r.role_id " +
                "where 1=1 ");
        if (user != null) {
            if (StringUtils.isNotEmpty(user.getLogin_name())) {
                sql.append(" and u.login_name like '%" + user.getLogin_name() +"%' ");
            }
            if (StringUtils.isNotEmpty(user.getRole_id())) {
                sql.append(" and u.role_id = '" + user.getRole_id() + "' ");
            }
        }
        sql.append(" order by u.user_id;");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<User> listuser = new ArrayList<User>();
        if (query != null) {
            List<Object[]> lists = query.list();
            for (Object[] list:lists) {
                User u = new User();
                u.setUser_id((String) list[0]);
                u.setLogin_name((String) list[2]);
                u.setPassword((String) list[3]);
                u.setUser_name((String) list[1]);
                u.setRole_id((String) list[4]);
                u.getRole().setRole_name((String) list[5]);
                if (list[6] != null) {
                    Timestamp ts = (Timestamp) list[6];
                    u.setLastlogintime(ts.toString());
                }
                u.setOrdered((String) list[7]);
                listuser.add(u);
            }
        }
        return listuser;
    }


    /**
     * 分页获取用户
     * @param user
     * @param page
     * @return
     */
    public List<User> listPageUser(User user, Page page) {
        StringBuilder sql = new StringBuilder("select u.user_id,u.user_name,u.login_name,u.password,r.role_id,r.role_name ,u.lastlogintime,u.ordered " +
                " from user u " +
                " left join role r on u.role_id=r.role_id " +
                " where 1=1 ");
        if (user != null) {
            if (StringUtils.isNotEmpty(user.getLogin_name())) {
                sql.append(" and u.login_name like '%" + user.getLogin_name() +"%' ");
            }
            if (StringUtils.isNotEmpty(user.getRole_id())) {
                sql.append(" and u.role_id = '" + user.getRole_id() + "' ");
            }
        }
        sql.append(" order by u.user_id");
        if (page.getCurrentPage() == page.getTotalPage()) {
            sql.append(" limit " + (page.getCurrentPage()-1)*page.getShowCount() + "," + page.getTotalPage()*page.getShowCount());
        } else {
            sql.append(" limit " + (page.getCurrentPage()-1)*page.getShowCount() + "," + page.getShowCount() + " ");
        }
        sql.append(";");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        List<User> listuser = new ArrayList<User>();
        if (query != null) {
            List<Object[]> lists = query.list();
            for (Object[] list:lists) {
                User u = new User();
                u.setUser_id((String) list[0]);
                u.setLogin_name((String) list[2]);
                u.setPassword((String) list[3]);
                u.setUser_name((String) list[1]);
                u.setRole_id((String) list[4]);
                u.getRole().setRole_name((String) list[5]);
                if (list[6] != null) {
                    Timestamp ts = (Timestamp) list[6];
                    u.setLastlogintime(ts.toString());
                }
                u.setOrdered((String) list[7]);
                listuser.add(u);
            }
        }
        return listuser;
    }


    /**
     * 更新用户表
     * @param user
     * @return
     */
    public boolean updateUserBaseInfo(User user) {
        String sql = "update user set login_name = '" + user.getLogin_name() + "', " +
                "user_name = '" + user.getUser_name() + "', " +
                "role_id = '" + user.getRole_id() + "', " +
                "password = '" + user.getPassword() + "', " +
                "ordered = '" + user.getOrdered() + "' " +
                "where user_id = '" + user.getUser_id() + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    /**
     * 更新用户权限
     */
    public boolean updateUserRights(User user) {
        String sql = "update user set rights = '" + user.getRights() + "' where user_id = '" + user.getUser_id() + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }

    /**
     * 定时任务 将所有用户的预定状态置为 0
     * @return
     */
    public boolean updateAllUserOrder() {
        String sql = "update user set ordered = '0';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        int sf = query.executeUpdate();
        if (sf == 1)
            return true;
        return false;
    }
}
