package com.ruixin.service;

import com.ruixin.bean.Link;
import com.ruixin.common.service.CrudService;
import com.ruixin.common.utils.CacheNames;
import com.ruixin.dao.LinkDao;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = CacheNames.LINk_CACHE)
public class LinkService extends CrudService<LinkDao,Link>{

    @Transactional(readOnly = false)
    @CacheEvict
    public void bathDelete(String[] ids) {
        dao.bathDelete(ids);
    }
}
