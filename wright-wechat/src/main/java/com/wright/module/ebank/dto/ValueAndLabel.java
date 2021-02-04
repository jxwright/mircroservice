package com.wright.module.ebank.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class ValueAndLabel implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1547646862678379321L;
	
	
	private String label;
	private Double amount; 


}
