package com.ruixin.service;

import com.github.pagehelper.PageHelper;
import com.ruixin.bean.News;
import com.ruixin.bean.Type;
import com.ruixin.common.service.CrudService;
import com.ruixin.common.utils.CacheNames;
import com.ruixin.dao.NewsDao;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = CacheNames.NEWS_CACHE)
public class NewsService extends CrudService<NewsDao,News> {

    @Cacheable
    public List<Type> findTypeList(){
        return dao.findTypeList();
    }

    @Cacheable
    @Transactional(readOnly = false)
    public void batchDelete(String[] ids){
        dao.batchDelete(ids);
    }

    public List<Map<String,Object>> getData() {
        return dao.getData();
    }

    public List<Map<String,Object>> getData1() {
        return dao.getData1();
    }
}