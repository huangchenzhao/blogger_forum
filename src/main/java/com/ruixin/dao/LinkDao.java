package com.ruixin.dao;


import com.ruixin.bean.Link;
import com.ruixin.common.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

public interface LinkDao extends CrudDao<Link> {
    void bathDelete(@Param("ids") String[] ids);
}
