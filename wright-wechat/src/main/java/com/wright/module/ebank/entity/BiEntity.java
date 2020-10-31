package com.wright.module.ebank.entity;


import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.GenericGenerator;

import com.wright.common.base.BaseEntity;
import com.wright.system.entity.wechat.BankData;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@SqlResultSetMapping(name="BankData", classes = {
	    @ConstructorResult(targetClass = BankData.class, 
	    columns = {@ColumnResult(name="bank_name"), 
	    		   @ColumnResult(name="bank_money", type=Double.class)})
	})
@SqlResultSetMapping(name="BankAccountData", classes = {
	    @ConstructorResult(targetClass = BankData.class, 
	    columns = {@ColumnResult(name="bank_name"), 
	    			@ColumnResult(name="bank_account"), 
	    		   @ColumnResult(name="bank_money", type=Double.class)})
	})
public class BiEntity   extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8589479224715412355L;

	@Id
	@GeneratedValue(generator = "generateId")
	@GenericGenerator(name = "generateId", strategy = "com.wright.common.base.GenerateId")
	@Column(name= "id")
	private Long id; // 编号
	
	  private String entityCode;
	  private String entityName;
}
