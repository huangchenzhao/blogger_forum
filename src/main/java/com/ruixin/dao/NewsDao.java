package com.ruixin.dao;

import com.ruixin.bean.News;
import com.ruixin.bean.Type;
import com.ruixin.common.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NewsDao extends CrudDao<News>{

    List<Type> findTypeList();

    void batchDelete(@Param("ids")String[] ids);

    List<Map<String,Object>> getData();

    List<Map<String,Object>> getData1();
}