package com.ruixin.common.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO支持类实现
 */
public interface CrudDao<T> extends BaseDao {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(@Param("id") int id);

	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);


	/**
	 * 查询数据列表数
	 * @param entity
	 * @return
	 */
	public long findListCount(T entity);

	/**
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);


	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param entity
	 * @return
	 */
	public int delete(T entity);

}