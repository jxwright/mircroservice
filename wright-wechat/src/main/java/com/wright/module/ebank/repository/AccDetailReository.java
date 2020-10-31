package com.wright.module.ebank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.wright.module.ebank.entity.AiTransdetails;


public interface AccDetailReository  extends JpaRepository<AiTransdetails, Integer>, QuerydslPredicateExecutor<AiTransdetails> {
	

}

