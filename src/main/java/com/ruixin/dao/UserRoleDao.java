package com.ruixin.dao;

import com.ruixin.bean.UserRole;
import com.ruixin.common.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

public interface UserRoleDao extends CrudDao<UserRole>{

    UserRole get(@Param("userId") String userId);

}