package com.xyq.vo.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户表
 * Created by yqxu2 on 2017/2/16.
 */
@Entity
@Table(name = "user")
public class User {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue
    private String user_id;
    /**
     * 登录账号
     */
    @Column(name = "login_name")
    private String login_name;
    /**
     * 登录密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String user_name;
//    /**
//     * 用户权限
//     */
//    private String user_rights;
    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private String role_id;
    /**
     * 最近登录时间
     */
    @Column(name = "lastlogintime")
    private Timestamp lastlogintime;
    /**
     * 预订状态
     */
    @Column(name = "ordered")
    private String ordered;

    public User() {
    }

    public User(String user_id, String login_name, String password, String user_name, String role_id, Timestamp lastlogintime, String ordered) {
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

    public Timestamp getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Timestamp lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getOrdered(Object o) {
        return ordered;
    }

    public void setOrdered(String ordered) {
        this.ordered = ordered;
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
