package com.wright.module.ebank.dto;


import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class BankAccDetail  implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1547646862678379321L;
	
	private Double balance; 
	private Double creditAmount;
	private Double debitAmount;
	  private String postscript;
	  private String reciprocalAccount;
	  private String reciprocalBank;
	  private String reciprocalName;
	  private String refInfo;
	  private short settleMode;
	  private String summary;
	  private Date transDate;
	  private String transFlag;
	  private String transPeriod;
	  private String transYear;
	  private String voucherNo;
	  private String voucherType;
	
}

