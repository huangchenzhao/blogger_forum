package com.ruixin.common.entity;

import java.io.Serializable;

public abstract class BaseEntity<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 实体编号（唯一标识）
	 */
	protected int id;

	protected Page<T> pages;

	public BaseEntity() {
	}
	
	public BaseEntity(int id) {
		this();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Page<T> getPages() {
		return pages;
	}

	public void setPages(Page<T> pages) {
		this.pages = pages;
	}

	@Override
   public boolean equals(Object obj) {
       if (null == obj) {
           return false;
       }
       if (this == obj) {
           return true;
       }
       if (!getClass().equals(obj.getClass())) {
           return false;
       }
       BaseEntity<?> that = (BaseEntity<?>) obj;
       return 0 == this.getId() ? false : this.getId()==that.getId();
   }
	 
	/**
	 * 状态标记（0：正常；1：删除；2：未启用；）
	 */
	public static final String STATUS_NORMAL = "0";
	public static final String STATUS_DELETE = "1";
	public static final String STATUS_AUDIT = "2";
}
