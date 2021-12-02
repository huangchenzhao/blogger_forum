package com.ruixin.service;

import com.ruixin.bean.Role;
import com.ruixin.bean.UserRole;
import com.ruixin.common.service.CrudService;
import com.ruixin.common.utils.CacheNames;
import com.ruixin.dao.RoleDao;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = CacheNames.ROLE_CACHE)
public class RoleService extends CrudService<RoleDao,Role>{

    @Cacheable
    public List<Role> findRole(Role role){
        return dao.findRole(role);
    }

    @Transactional(readOnly = false)
    @CachePut
    public void insertUserRole(int userId,String[] roles){
        dao.insertUserRole(userId,roles);
    }

    @Transactional(readOnly = false)
    @CacheEvict
    public void updateUserRole(int id,String[] roleIds) {
        dao.batchDeleteUserRole(id);
        dao.insertUserRole(id,roleIds);
    }
}
