package com.ruixin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixin.bean.Comment;
import com.ruixin.common.entity.Page;
import com.ruixin.common.service.CrudService;
import com.ruixin.common.utils.CacheNames;
import com.ruixin.dao.CommentDao;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = CacheNames.COMMENT_CACHE)
public class CommentService extends CrudService<CommentDao,Comment> {

    @Cacheable
    public Page<Comment> getFindId(Page<Comment> page, Comment comment, int id) {
        comment.setPages(page);
        PageHelper.startPage(page.getPage(),page.getLimit());
        List<Comment> list=dao.getFindId(id);
        PageInfo pageInfo = new PageInfo(list);
        page.setData(list);
        page.setCount(pageInfo.getTotal());
        page.setPageCount(page.getPageNum(page));
        return page;
    }

}
