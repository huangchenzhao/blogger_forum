package com.ruixin.bean;

import com.ruixin.common.entity.DataEntity;

/**
 * 角色实体类
 */
public class Role extends DataEntity<Role>{

    public static final String ALL_NEWS = "all_news";
    public static final String OWNER_NEWS = "owner_news";

    private String name;

    private User user;

    public Role(User user) {
        this.user = user;
    }

    public Role(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

}