package com.ruixin.dao;

import com.ruixin.bean.User;
import com.ruixin.common.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

/**
 * 用户dao
 */
public interface UserDao extends CrudDao<User>{

    User findByAccount(@Param("userName") String userName);

    User getUserByCode(@Param("code") String code);

    void batchDelete(@Param("ids") String[] ids);

    void faceRegister(@Param("username") String username,@Param("userFace") String userFace);

    String findFace(@Param("username") String username);

    String findPassword(@Param("username") String username);
}