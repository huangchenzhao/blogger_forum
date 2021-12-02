package com.ruixin.service;

import com.ruixin.bean.Menu;
import com.ruixin.bean.Role;
import com.ruixin.bean.User;
import com.ruixin.common.service.CrudService;
import com.ruixin.common.utils.CacheNames;
import com.ruixin.dao.MenuDao;
import com.ruixin.dao.RoleDao;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = CacheNames.MENU_CACHE)
public class MenuService extends CrudService<MenuDao,Menu>{

}
