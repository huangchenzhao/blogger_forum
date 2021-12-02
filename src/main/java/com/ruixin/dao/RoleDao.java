package com.ruixin.dao;

import com.ruixin.bean.Role;
import com.ruixin.common.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao extends CrudDao<Role> {

    List<Role> findRole(Role role);

    void insertUserRole(@Param("userId") int userId,@Param("roles") String[] roles);

    void batchDeleteUserRole(@Param("userId") int userId);
}