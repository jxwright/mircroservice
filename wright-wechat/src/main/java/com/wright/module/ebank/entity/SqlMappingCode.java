package com.wright.module.ebank.entity;


import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

import com.wright.module.ebank.dto.BankAccDetail;
import com.wright.module.ebank.dto.FavoriteAccBalance;


@MappedSuperclass
@SqlResultSetMapping(name="FavoriteAccBalance", classes = {
	    @ConstructorResult(targetClass = FavoriteAccBalance.class, 
	    columns = {
	    		@ColumnResult(name="org_name",type = String.class),
	    		@ColumnResult(name="bank_name",type = String.class), 
	    			@ColumnResult(name="branch_name",type = String.class), 
	    			@ColumnResult(name="bank_account_id",type = String.class), 
	    			@ColumnResult(name="currency_name",type = String.class), 
	    			@ColumnResult(name="currency_symbol",type = String.class), 
	    		   @ColumnResult(name="balance", type=Double.class)})
	})
@SqlResultSetMapping(name="BankBalance", classes = {
	    @ConstructorResult(targetClass = FavoriteAccBalance.class, 
	    columns = {
	    		@ColumnResult(name="org_name",type = String.class),
	    		@ColumnResult(name="bank_name",type = String.class), 
	    		@ColumnResult(name="bank_short_name",type = String.class), 
	    			@ColumnResult(name="currency_symbol",type = String.class), 
	    		   @ColumnResult(name="balance", type=Double.class)})
	})
@SqlResultSetMapping(name="BankAccBalance", classes = {
	    @ConstructorResult(targetClass = FavoriteAccBalance.class, 
	    columns = {
	    		@ColumnResult(name="bank_name",type = String.class), 
	    		@ColumnResult(name="bank_account",type = String.class), 
	    		@ColumnResult(name="bank_account_id",type = String.class), 
	    		   @ColumnResult(name="balance", type=Double.class)})
	})
@SqlResultSetMapping(name="BankChartBalance", classes = {
	    @ConstructorResult(targetClass = FavoriteAccBalance.class, 
	    columns = {
	    		@ColumnResult(name="bank_name",type = String.class), 
	    		   @ColumnResult(name="balance", type=Double.class)})
	})

@SqlResultSetMapping(name="BankAccDetail", classes = {
	    @ConstructorResult(targetClass = BankAccDetail.class, 
	    columns = {
	    		@ColumnResult(name="balance", type = Double.class),
	    		@ColumnResult(name="credit_amount",type = Double.class),
	    		@ColumnResult(name="debit_amount",type = Double.class),
	    		@ColumnResult(name="postscript",type = String.class),
	    		@ColumnResult(name="reciprocal_account",type = String.class),
	    		@ColumnResult(name="reciprocal_bank",type = String.class),
	    		@ColumnResult(name="reciprocal_name",type = String.class),
	    		@ColumnResult(name="ref_info",type = String.class),
	    		@ColumnResult(name="settle_mode",type = short.class),
	    		@ColumnResult(name="summary",type = String.class),
	    		@ColumnResult(name="trans_date" ,type = Date.class),
	    		@ColumnResult(name="trans_flag",type = String.class),
	    		@ColumnResult(name="trans_period",type = String.class),
	    		@ColumnResult(name="trans_year",type = String.class),
	    		@ColumnResult(name="voucher_no",type = String.class),
	    		@ColumnResult(name="voucher_type",type = String.class)
})
	})
public class SqlMappingCode {

}
