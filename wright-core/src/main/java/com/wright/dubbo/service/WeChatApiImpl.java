//package com.wright.dubbo.service;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.dubbo.config.annotation.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.wright.api.WeChatApi;
//import com.wright.system.entity.wechat.BankData;
//
//
//@Service
//public class WeChatApiImpl    implements WeChatApi{
//
//	
//	@Autowired
//	private WeChatService weChatService;
//	
//	@Override
//	public List<BankData> getBankDataByParam(Map<String, Object> param) {
//		// TODO Auto-generated method stub
//		return weChatService.getBankDataByParam(param);
//	}
//
//	@Override
//	public List<BankData> getBankAccountDataByParam(Map<String, Object> param) {
//		// TODO Auto-generated method stub
//		return weChatService.getBankAccountDataByParam(param);
//	}
//
//}
