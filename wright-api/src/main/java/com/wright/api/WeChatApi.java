package com.wright.api;

import java.util.List;
import java.util.Map;

import com.wright.system.entity.wechat.BankData;


public interface WeChatApi {
	
	List<BankData> getBankDataByParam(Map<String,Object> param);
	
	List<BankData> getBankAccountDataByParam(Map<String,Object> param);
}
