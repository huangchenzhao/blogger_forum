package com.ruixin.bean;

import com.ruixin.common.entity.DataEntity;

/**
 *用户角色中间类
 */
public class UserRole extends DataEntity<UserRole>{

    private Integer userId;

    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}