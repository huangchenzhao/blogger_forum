package com.ruixin.dao;

import com.ruixin.bean.Menu;
import com.ruixin.bean.User;
import com.ruixin.common.dao.CrudDao;

import java.util.List;

public interface MenuDao extends CrudDao<Menu>{

    String getMenuinfoNamePath(Menu menu);
}