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
public class BiBankbranch  extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6148698922558169911L;

	@Id
	@GeneratedValue(generator = "generateId")
	@GenericGenerator(name = "generateId", strategy = "com.wright.common.base.GenerateId")
	@Column(name= "id")
	private Long id; // 编号

	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "bank_id")
	  private BiBank biBank;
	  
	  @Column(nullable=false)
	  private String branchCode;
	  @Column(nullable=false)
	  private String branchName;
	  private String branchShortName;
	  private String parentid;
	  private short branchType;
	  private String address1;
	  private String address2;
	  private String postCode;
	  private String phone1;
	  private String phone2;
	  private String servicePhone;
	  private String fax;
	  private String email;
	  private String manager;
	  private String description;
	  private String seqno;
	  private short _remove;
	  private int sortCode;
	  
}
