package com.ruixin.dao;


import com.ruixin.bean.Log;
import com.ruixin.common.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

/**
 * 日志DAO接口
 */
public interface LogDao extends CrudDao<Log> {

    void bathDelete(@Param("ids") String[] ids);
}
