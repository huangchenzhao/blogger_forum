package com.ruixin.service;

import com.ruixin.bean.User;
import com.ruixin.common.service.CrudService;
import com.ruixin.common.utils.CacheNames;
import com.ruixin.dao.UserDao;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@CacheConfig(cacheNames = CacheNames.USER_CACHE)
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao,User>{

    /**
     * 通过用户名或者邮箱查找用户
     * @param userName
     * @return
     */
    @Cacheable(key = "#userName")
    public User findByAccount(String userName) {
        return dao.findByAccount(userName);
    }

    /**
     * 通过code查找用户
     * @param code
     * @return
     */
    @Cacheable(key = "#code")
    public User getUserByCode(String code) {
        return dao.getUserByCode(code);
    }

    @CacheEvict
    public void batchDelete(String[] ids){
        dao.batchDelete(ids);
    }

    public void faceRegister(String username,String userFace){dao.faceRegister(username,userFace);}

    public String findFace(String username){ return dao.findFace(username);}

    public String findPassword(String username){ return dao.findPassword(username);}
}
