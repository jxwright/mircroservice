package com.wright.module.ebank.controller;
 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wright.module.ebank.service.WeChatService;
import com.wright.utils.RestResult;
import com.wright.utils.ResultUtil;

@RestController
public class EBankController {
	 

		@Autowired
		private WeChatService weChatService;
	    
	    @GetMapping(value = "getBankData")
	    public RestResult<?> getBankDataByParam(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getBankDataByParam(param));
	    }
	    
	    @GetMapping(value = "getBankAccData")
	    public RestResult<?> getBankAccData(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getBankAccountDataByParam(param));
	    }
	    
	    @GetMapping(value = "getFavoriteAccBalance")
	    public RestResult<?> getFavoriteAccBalance(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getFavoriteAccBalance(param));
	    }
	    
	    @GetMapping(value = "getBankBalance")
	    public RestResult<?> getBankBalance(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getBankBalance(param));
	    }
	    
	    @GetMapping(value = "getBankAccDetail")
	    public RestResult<?> getBankAccDetail(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getBankAccountDetail(param));
	    }
	    
	    @GetMapping(value = "getBankMonthAmount")
	    public RestResult<?> getBankMonthAmount(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getBankMonthAmount(param));
	    }
	    
	    @GetMapping(value = "getBankMonthAmountPie")
	    public RestResult<?> getBankMonthAmountPie(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getBankMonthAmountPie(param));
	    }
	    
	    @GetMapping(value = "getBankCustData")
	    public RestResult<?> getBankCustData(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getBankCustData(param));
	    }
	    
	    @GetMapping(value = "getCustMonthAmount")
	    public RestResult<?> getCustMonthAmount(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getCustMonthAmount(param));
	    }
	    
	    @GetMapping(value = "getCustMonthAmountPie")
	    public RestResult<?> getCustMonthAmountPie(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getCustMonthAmountPie(param));
	    }
	    
	    @GetMapping(value = "getCustDealDetail")
	    public RestResult<?> getCustDealDetail(@RequestParam Map<String,Object> param) {
	        return ResultUtil.success(weChatService.getCustDealDetail(param));
	    }

}
