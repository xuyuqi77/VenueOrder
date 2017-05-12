package com.xyq.vo.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户表
 * Created by yqxu2 on 2017/2/16.
 */
public class User {
    /**
     * 用户ID
     */
    private String user_id;
    /**
     * 登录账号
     */
    private String login_name;
    /**
     * 登录密码
     */
    private String password;

    /**
     * 新密码
     */
    private String n_password;
    /**
     * 用户名称
     */
    private String user_name;
//    /**
//     * 用户权限
//     */
//    private String user_rights;
    /**
     * 角色ID
     */
    private String role_id;
    /**
     * 最近登录时间
     */
    private String lastlogintime;
    /**
     * 预订状态
     */
    private String ordered;
    /**
     * 自定义权限
     */
    private String rights;

    private Role role;

    private Page page;


    public User() {
        this.page = new Page();
    }

    public User(String user_id, String login_name, String password, String user_name, String role_id, String lastlogintime, String ordered) {
        this.user_id = user_id;
        this.login_name = login_name;
        this.password = password;
        this.user_name = user_name;
        this.role_id = role_id;
        this.lastlogintime = lastlogintime;
        this.ordered = ordered;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getN_password() {
        return n_password;
    }

    public void setN_password(String n_password) {
        this.n_password = n_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String uesr_name) {
        this.user_name = uesr_name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(String lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getOrdered() {
        return ordered;
    }

    public void setOrdered(String ordered) {
        this.ordered = ordered;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public Role getRole() {
        if (role == null)
            role = new Role();
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Page getPage() {
        if (page == null)
            page = new Page();
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", login_name='" + login_name + '\'' +
                ", password='" + password + '\'' +
                ", uesr_name='" + user_name + '\'' +
                ", role_id='" + role_id + '\'' +
                ", lastlogintime=" + lastlogintime +
                ", ordered='" + ordered + '\'' +
                '}';
    }

}
