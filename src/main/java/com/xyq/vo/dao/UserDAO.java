package com.xyq.vo.dao;

import com.xyq.vo.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * UserDAO接口实现类
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
    public void add(User u) {
//        sessionFactory.getCurrentSession().save(u);
        String sql = "insert into user(user_id, login_name, password, user_name, role_id, lastlogintime, ordered)" +
                " values ('" + u.getUser_id() + "', '" + u.getLogin_name() + "', '" +
                u.getPassword() + "', '" + u.getUser_name() + "', '" +
                u.getRole_id() + "', '" + u.getLastlogintime() + "', '" +
                u.getOrdered() + "');";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    public List<User> getAllUser() {
        Query query = sessionFactory.getCurrentSession().createQuery("from com.xyq.vo.model.User");
        return (List<User>) query.list();
    }

    /**
     * 根据用户登录名获取用户
     * @param loginname
     * @return
     */
    public User getUserByLoginname(String loginname) {
        User user = new User();
        String sql = "select * from user where login_name = '" + loginname +"' ;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
//        user = (User) query.uniqueResult();
//        System.out.println(user.toString());
        List<Object[]> list = query.list();
        Object[] list1 = list.get(0);
        user.setUser_id((String) list1[0]);
        user.setLogin_name((String) list1[1]);
        user.setPassword((String) list1[2]);
        user.setUser_name((String) list1[3]);
        user.setRole_id((String) list1[4]);
//        user.setLastlogintime((String) list1[5]);
        user.setOrdered((String) list1[6]);
        return user;
    }

    public void setUserOrdered(String userid, String ordered) {
        String sql = "update user a set a.ordered = '" + ordered + "' " +
                "where a.user_id = '" + userid + "';";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    public int getUserNum() {
        String sql = "select count(user_id) from user;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<BigInteger> list = query.list();
        int num = list.get(0).intValue();
        return num;
    }
}
