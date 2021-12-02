package com.ruixin.common.entity;

import java.util.Date;

import com.ruixin.bean.User;
import com.ruixin.util.UserUtils;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class DataEntity<T> extends BaseEntity<T>{
	private static final long serialVersionUID = 1L;
	protected String remark;	// 备注
	protected User createBy;	// 创建者
	protected Date createDate;	// 创建日期
	protected User updateBy;	// 更新者
	protected Date updateDate;	// 更新日期
	protected String status; 	// 删除标记（0：正常；1：删除；2：未启用）
	
	public DataEntity() {
		super();
		this.status = STATUS_NORMAL;
	}
	
	public DataEntity(int id) {
		super(id);
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert(){
		User userinfo = UserUtils.getUser();
		if (userinfo.getId()>0){
  			this.createBy = userinfo;
  			this.updateBy = userinfo;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate(){
		User userinfo = UserUtils.getUser();
		if (userinfo.getId()>0){
				this.updateBy = userinfo;
		}
		this.updateDate = new Date();
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
