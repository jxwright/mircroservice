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
	        return ResultUtil.success(weChatService.getBankAccountDetailByParam1(param));
	    }

}
