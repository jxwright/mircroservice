package com.wright.module.ebank.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.wright.common.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class AiBalance  extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -972227366916355468L;

	@Id
	@GeneratedValue(generator = "generateId")
	@GenericGenerator(name = "generateId", strategy = "com.wright.common.base.GenerateId")
	@Column(name= "id")
	private Long id; // 编号
	
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "account_id")
	  private BiBankAccount biBankAccount;
	  
	  private String accYear;
	  private String accPeriod;
	  private Double balance;
	  
}
