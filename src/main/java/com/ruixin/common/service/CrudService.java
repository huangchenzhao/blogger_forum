package com.ruixin.common.service;

import java.util.Arrays;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixin.common.dao.CrudDao;
import com.ruixin.common.entity.DataEntity;
import com.ruixin.common.entity.Page;
import com.ruixin.common.utils.CacheNames;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * Service基类
 */
@Transactional(readOnly = true)
@CacheConfig(cacheNames = CacheNames.SYS_CACHE)
public class CrudService<D extends CrudDao<T>, T extends DataEntity<T>>{
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	@Cacheable
	public T get(int id) {
			return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	@Cacheable
	public T get(T entity) {
		return dao.get(entity);
	}
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	@Cacheable
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	@Cacheable
	public Page<T> findPageList(Page<T> page,T entity) {
		entity.setPages(page);
		PageHelper.startPage(page.getPage(),page.getLimit());
		List<T> list=dao.findList(entity);
		PageInfo pageInfo = new PageInfo(list);
		page.setData(list);
		page.setCount(pageInfo.getTotal());
		page.setPageCount(page.getPageNum(page));
		return page;
	}

	/**
	 * 查询列表数据总记录数
	 * @param entity
	 * @return
	 */
	@Cacheable(key = "#entity.id+'findListCont'")
	public long findListCount(T entity) {
			return dao.findListCount(entity);
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	@CachePut
	public void save(T entity) {
		if (entity.getId()==0){
				entity.preInsert();
				dao.insert(entity);
		}else{
				entity.preUpdate();
				dao.update(entity);
		}
	}
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	@CacheEvict
	public void delete(T entity) {
		dao.delete(entity);
	}
}
