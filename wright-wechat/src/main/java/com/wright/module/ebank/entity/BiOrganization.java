package com.wright.module.ebank.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.wright.common.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class BiOrganization  extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8561082712894870711L;

	@Id
	@GeneratedValue(generator = "generateId")
	@GenericGenerator(name = "generateId", strategy = "com.wright.common.base.GenerateId")
	@Column(name= "id")
	private Long id; // 编号
	
	  private String orgCode;
	  private String orgName;
	  private String orgShortName;
	  private Long parentid;
	  private String address1;
	  private String address2;
	  private String postCode;
	  private String phone1;
	  private String phone2;
	  private String fax;
	  private String email;
	  private String manager;
	  private short	orgType;
	  private String description;
	  private String seqno;
	  private short _remove;
	  private int	sortCode;
}
