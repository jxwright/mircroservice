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
public class BiBank  extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 641536846648111507L;

	@Id
	@GeneratedValue(generator = "generateId")
	@GenericGenerator(name = "generateId", strategy = "com.wright.common.base.GenerateId")
	@Column(name= "id")
	private Long id; // 编号
	
	@Column(nullable=false)
	  private String bankCode;
	@Column(nullable=false)
	  private String bankName;
	  private String bankShortName;
	  private short bankType;
	  private String servicePhone;
	  private String webUrl;
	  private String serviceEmail;
	  private String description;
	  private short _remove;
	  private int sortCode;
	  
}
