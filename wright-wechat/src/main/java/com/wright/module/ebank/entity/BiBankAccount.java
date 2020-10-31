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
public class BiBankAccount  extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7982569964590761375L;


	@Id
	@GeneratedValue(generator = "generateId")
	@GenericGenerator(name = "generateId", strategy = "com.wright.common.base.GenerateId")
	@Column(name= "id")
	private Long id; // 编号
	
	
	 @Column(nullable=false)
	  private String bankAccount;
	  private boolean isDefault;
	  private short accountType = 0;
	  private String currencyCode;
	  private String currencySymbol;
	 
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "org_id")
	  private BiOrganization biOrganization;
	  
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "bank_id")
	  private BiBank biBank;

	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "bankbranch_id")
	  private BiBankbranch biBankbranch;
	  
	  private short accountStatus = 0;
	  private String description;
	  private short _remove = -1;
	  
}
