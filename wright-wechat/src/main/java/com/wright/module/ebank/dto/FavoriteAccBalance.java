package com.wright.module.ebank.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class FavoriteAccBalance  implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1547646862678379321L;
	
	private String orgName;
	private String bankName;
	private String bankShortName;
	private String branchName;
	private String bankAccountId;
	private String bankAccount;
	private String currencyName;
	private String currencySymbol;
	private Double balance;
	
	public FavoriteAccBalance(String orgName,String bankName,String bankShortName,String currencySymbol, double balance)
	{
		this.orgName = orgName;
		this.bankName = bankName;
		this.bankShortName = bankShortName;
		this.currencySymbol = currencySymbol;
		this.balance = balance;
	}
	public FavoriteAccBalance(String bankName,String bankAccount,String bankAccountId, double balance)
	{
		this.bankAccount = bankAccount;
		this.bankName = bankName;
		this.bankAccountId = bankAccountId;
		this.balance = balance;
	}
	public FavoriteAccBalance(String bankName, double balance)
	{
		this.bankName = bankName;
		this.balance = balance;
	}
}

