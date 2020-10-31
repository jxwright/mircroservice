package com.wright.common.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


/**
 * 	基础实体类，用于数据库表继承，
 * @author WRIGHT
 *
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6283583087551359733L;


	@Column
	protected String createBy;
	
	@Column( name = "create_date",
	        nullable = false,
	        updatable = false)
	protected Date createDate = new Date();

	@Column
	protected String updateBy;
	
	@Column( name = "update_date",
	        nullable = false)
	protected Date updateDate = new Date();
	
	@Column
	protected Date birthDate;
	
	@Column
	@Version
	protected Long version;
	
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}



}
