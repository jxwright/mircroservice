package com.wright.module.ebank.entity;


import java.util.Date;

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
public class AiTransdetails   extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1634383813129394233L;

	@Id
	@GeneratedValue(generator = "generateId")
	@GenericGenerator(name = "generateId", strategy = "com.wright.common.base.GenerateId")
	@Column(name= "id")
	private Long id; // 编号
	
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "account_id")
	  private BiBankAccount biBankAccount;
	  
	  private String transYear;
	  private String transPeriod;
	  private Date transDate;
	  private short settleMode;
	  private String voucherType;
	  private String voucherNo;
	  private String reciprocalBank;
	  private String reciprocalAccount;
	  private String reciprocalName;
	  private String summary;
	  private String transFlag;
	  private Double debitAmount;
	  private Double creditAmount;
	  private Double balance;
	  private String postscript;
	  private String refInfo;
	  
}
