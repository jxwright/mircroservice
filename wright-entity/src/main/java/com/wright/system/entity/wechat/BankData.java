package com.wright.system.entity.wechat;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class BankData  implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4874046577942490793L;
	private String bankName;
	private String bankAccount;
	private double bankMoney;
	private String entityName;
	private String currency;
	
	public BankData(String bankName,double bankMoney)
	{
		this.bankName = bankName;
		this.bankMoney = bankMoney;
	}
	public BankData(String bankName,String bankAccount,double bankMoney)
	{
		this.bankName = bankName;
		this.bankMoney = bankMoney;
		this.bankAccount = bankAccount;
	}
}
