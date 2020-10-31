package com.wright.module.ebank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wright.common.base.BaseService;
import com.wright.module.ebank.dto.FavoriteAccBalance;
import com.wright.system.entity.wechat.BankData;
import com.wright.utils.Page;

@Service
public class WeChatService  extends BaseService{

	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(WeChatService.class);
	
	 public List<BankData> getBankDataByParam(Map<String, Object> param) {
		 Integer monthInt = LocalDate.now().getMonthValue();
		 String month =monthInt<10?("0"+monthInt):monthInt.toString();
		 String year = String.valueOf(LocalDate.now().getYear());
			String jpql = "select\r\n" + 
					"	 bb.bank_name,\r\n" + 
					"	 SUM(ab.balance) as balance\r\n" + 
					"	 from bi_bank_account ba\r\n" + 
					"	 left join bi_bank bb on ba.bank_id=bb.id\r\n" + 
					"	 left join bi_organization bo on ba.org_id=bo.id\r\n" + 
					"	 left join ai_balance ab on ba.id=ab.account_id\r\n" + 
					"	 where \r\n" + 
					"	 bo.id='"+param.get("orgId")+"' and  ba.currency_code='"+param.get("currencyCode")+"' and ab.acc_year='"+ year+"' and ab.acc_period='"+month+"'"+
					"	 GROUP BY  bb.bank_name";
			Query query = entityManager.createNativeQuery(jpql,"BankChartBalance");
			@SuppressWarnings("unchecked")
			List<BankData> o = query.getResultList();
			return o;
	    }
	 
	 public List<BankData> getBankAccountDataByParam(Map<String, Object> param) {
		 Integer monthInt = LocalDate.now().getMonthValue();
		 String month =monthInt<10?("0"+monthInt):monthInt.toString();
		 String year = String.valueOf(LocalDate.now().getYear());
			String jpql = "	select\r\n" + 
					"	 bb.bank_name,\r\n" + 
					"	 ba.bank_account,\r\n" + 
					"	 ba.id as bank_account_id,\r\n" + 
					"	 ab.balance\r\n" + 
					"	 from bi_bank_account ba\r\n" + 
					"	 left join bi_bank bb on ba.bank_id=bb.id\r\n" + 
					"	 left join bi_organization bo on ba.org_id=bo.id\r\n" + 
					"	 left join ai_balance ab on ba.id=ab.account_id\r\n" + 
					"	 where \r\n" + 
					"	 bo.id='"+param.get("orgId")+"' and  ba.currency_code='"+param.get("currencyCode")+"' and ab.acc_year='"+ year+"' and ab.acc_period='"+month+"'";
			Query query = entityManager.createNativeQuery(jpql,"BankAccBalance");
			@SuppressWarnings("unchecked")
			List<BankData> o = query.getResultList();
			return o;
	    }
	 
	 
	 
	 public List<FavoriteAccBalance> getFavoriteAccBalance(Map<String, Object> param) {
		 Integer monthInt = LocalDate.now().getMonthValue();
		 String month =monthInt<10?("0"+monthInt):monthInt.toString();
		 String year = String.valueOf(LocalDate.now().getYear());
		 StringBuffer sBuffer = new StringBuffer("SELECT\r\n" + 
				 	"	bo.org_name,\r\n" + 
			 		"	b.bank_name,\r\n" + 
			 		"	c.branch_name,\r\n" + 
			 		"	ba.id AS bank_account_id,\r\n" + 
			 		"	bc.currency_name,\r\n" + 
			 		"	ba.currency_symbol,\r\n" + 
			 		"	COALESCE (ab.balance,0.00) as balance\r\n" + 
			 		"FROM\r\n" + 
			 		"	ui_favorite f\r\n" + 
			 		"	JOIN bi_bank_account ba ON ( f.account_id = ba.id )\r\n" + 
			 		"	LEFT JOIN bi_bank b ON ( b.id = ba.bank_id )\r\n" + 
			 		"	LEFT JOIN bi_currency bc ON ( bc.currency_code = ba.currency_code )\r\n" +
			 		"	LEFT JOIN bi_organization bo ON ( bo.id = ba.org_id )\r\n" +
			 		"	LEFT JOIN bi_bankbranch c ON ( c.id = ba.bankbranch_id )\r\n" + 
			 		"	LEFT JOIN ( SELECT account_id, balance FROM ai_balance WHERE acc_year = '"+ year+"' AND acc_period ='"+month+"' ) ab ON ( ab.account_id = f.account_id ) \r\n" + 
			 		"WHERE f.user_id = '"+param.get("userId")+"' " + 
			 		"ORDER BY\r\n" + 
			 		"	bo.org_code,b.sort_code,ba.bank_account");
			Query query = entityManager.createNativeQuery(sBuffer.toString(),"FavoriteAccBalance");
			@SuppressWarnings("unchecked")
			List<FavoriteAccBalance> s1 = query.getResultList();
			return s1;
	    }
	 
	 public List<Map<String,Object>> getBankBalance(Map<String, Object> param) {
		 Integer monthInt = LocalDate.now().getMonthValue();
		 String month =monthInt<10?("0"+monthInt):monthInt.toString();
		 String year = String.valueOf(LocalDate.now().getYear());
		 StringBuffer sBuffer = new StringBuffer("	select \r\n" + 
		 		"	concat(bo.org_name,'-',bo.id) as org_name,\r\n" + 
		 		"	b.bank_name,\r\n" + 
		 		"	b.bank_short_name,\r\n" + 
		 		"	bc.currency_symbol,\r\n" + 
		 		"	SUM(COALESCE (ab.balance,0.00)) as balance\r\n" + 
		 		"	 from bi_bank b \r\n" + 
		 		"	 left join bi_bank_account ba on ba.bank_id=b.id\r\n" + 
		 		"	 LEFT JOIN bi_currency bc ON ( bc.currency_code = ba.currency_code )\r\n" + 
		 		"	 LEFT JOIN bi_organization bo ON ( bo.id = ba.org_id )\r\n" + 
		 		"	 left join ( SELECT account_id, balance FROM ai_balance WHERE acc_year = '"+year+"' AND acc_period ='"+month+"' ) ab  ON ( ab.account_id = ba.id ) \r\n" + 
		 		"	 GROUP BY  bo.org_name,\r\n" + 
		 		"	b.bank_name,\r\n" + 
		 		"	b.bank_short_name,\r\n" + 
		 		"	bc.currency_symbol");
			Query query = entityManager.createNativeQuery(sBuffer.toString(),"BankBalance");
			@SuppressWarnings("unchecked")
			List<FavoriteAccBalance> s1 = query.getResultList();
			Map<String, List<FavoriteAccBalance>> groupBy = s1.stream().collect(Collectors.groupingBy(FavoriteAccBalance::getOrgName));
			List<Map<String,Object>> r = new ArrayList<Map<String,Object>>(); 
			for(Map.Entry<String, List<FavoriteAccBalance>> entry : groupBy.entrySet()){
				Map<String,Object> r1 = new HashMap<String,Object>();
			    r1.put("orgName", entry.getKey());
			    r1.put("bankInfo", entry.getValue());
			    r.add(r1);
			}
			return r;
	    }
	 
	
	 
//	 public Page<AiTransdetails> getBankAccountDetailByParam(Map<String, Object> param) {
//		 QAiTransdetails details = QAiTransdetails.aiTransdetails;
//		        //初始化组装条件(类似where 1=1)
//		        Predicate predicate = details.isNotNull().or(details.isNull());	        
//		        //执行动态条件拼装	        
////		        if (null != sysUser.getName() && !"".equals(sysUser.getName()))
////		        	ExpressionUtils.and(predicate,details.name.like(sysUser.getName()));
////				if (null != sysUser.getPhone() && !"".equals(sysUser.getPhone()))
////					ExpressionUtils.and(predicate,details.phone.eq(sysUser.getPhone()));
//		 		Pages pages = new Pages();
//		 		pages.setPage(1);
//		 		pages.setSize(4);
//		        PagesUtils.createPageRequest(pages);
//		        Page<AiTransdetails> page = accDetailReository.findAll(predicate, PagesUtils.createPageRequest(pages));
//		        return page;
//		}
//	 
	 public Page getBankAccountDetailByParam1(Map<String, Object> param) {
		 		int page = (null==param.get("page")?1:Integer.parseInt(param.get("page").toString()));
		        String filterType = param.get("filterType").toString();
		        String condition = "";
		        if(filterType.equals("day"))
		        	condition = " and to_days(trans_date) = to_days(now())";
		        else if(filterType.equals("week"))
		        	condition = " and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(trans_date)";
		        else if(filterType.equals("month"))
		        	condition = " and DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(trans_date)";
		        else if(filterType.equals("week"))
		        {
		        	String begin = param.get("begin").toString();
		        	String end = param.get("end").toString();
		        	condition = " and unix_timestamp(trans_date) between unix_timestamp('"+begin+"') and unix_timestamp('"+end+"') ";
		        }
		        String sql = "select \r\n" + 
		        		" balance,\r\n" + 
		        		" credit_amount,\r\n" + 
		        		" debit_amount,\r\n" + 
		        		" postscript,\r\n" + 
		        		" reciprocal_account,\r\n" + 
		        		" reciprocal_bank,\r\n" + 
		        		" reciprocal_name,\r\n" + 
		        		" ref_info,\r\n" + 
		        		" settle_mode,\r\n" + 
		        		" summary,\r\n" + 
		        		" trans_date,\r\n" + 
		        		" trans_flag,\r\n" + 
		        		" trans_period,\r\n" + 
		        		" trans_year,\r\n" + 
		        		" voucher_no,\r\n" + 
		        		" voucher_type\r\n" + 
		        		" from ai_transdetails\r\n" + 
		        		" where account_id = '"+param.get("accId")+"'"+condition+" order by trans_date desc";
		 		return  this.getPageDataList(sql, "BankAccDetail", page, 15);
		}
}
