package com.xyq.vo.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/21.
 */
@Entity
@Table(name = "role")
public class Role {
    /**
     * 角色ID
     */
    @Id
    @GeneratedValue
    private String role_id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String role_name;

    /**
     * 角色权限
     */
    @Column(name = "role_rights")
    private String role_rights;

    public Role() {
    }

    public Role(String role_name, String role_rights) {
        this.role_name = role_name;
        this.role_rights = role_rights;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_rights() {
        return role_rights;
    }

    public void setRole_rights(String role_rights) {
        this.role_rights = role_rights;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + role_id + '\'' +
                ", role_name='" + role_name + '\'' +
                ", role_rights='" + role_rights + '\'' +
                '}';
    }
}
