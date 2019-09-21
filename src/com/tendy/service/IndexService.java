package com.tendy.service;

import com.google.common.collect.Maps;
import com.tendy.common.BusinessConstants;
import com.tendy.common.Constants;
import com.tendy.common.GeomanticType;
import com.tendy.common.ReplyMap;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.BaseCity;
import com.tendy.dao.bean.MobileBussiness;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: tendy
 * @Description:
 * @Date: 2018/10/29
 */
@Service
public class IndexService {

	public ReplyMap getBusinessInfo(String businessCid) {
		ReplyMap replyMap = new ReplyMap();
		if (StringUtils.isBlank(businessCid)) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "运营商查询错误，请重新扫码");
			return replyMap;
		}
		MobileBussiness mobileBussiness = DataMapperUtil.selectMobileBussinessByCid(businessCid);
		if (mobileBussiness == null) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "运营商查询错误，请重新扫码");
			return replyMap;
		}
		replyMap.put("name", mobileBussiness.getBussinessName());
		replyMap.put("phone", mobileBussiness.getPhone());
		replyMap.put("address", mobileBussiness.getAddress());
		replyMap.success();
		return replyMap;
	}

	public ReplyMap getData(String phoneParam, String businessCid, Integer pageNo, Integer pageSize, String status, String tag, String notPhone, String position, String type) {
		ReplyMap replyMap = new ReplyMap();
		if (StringUtils.isBlank(businessCid)) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "运营商查询错误，请重新扫码");
			return replyMap;
		}
		MobileBussiness mobileBussiness = DataMapperUtil.selectMobileBussinessByCid(businessCid);
		if (mobileBussiness == null) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "运营商查询错误，请重新扫码");
			return replyMap;
		}
		//判断运营商有效期
		if (mobileBussiness.getEndTime().before(new Date())) {
			replyMap.fail(BusinessConstants.VALID_TIMEOUT_CODE, "运营商账户已过期，请联系管理员");
			return replyMap;
		}
		BaseCity baseCity = DataMapperUtil.selectBaseCityByPrimaryKey(mobileBussiness.getCityId());
		if (baseCity == null) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "运营商城市错误，请联系管理员");
			return replyMap;
		}
		Integer openBusinessId = null;
		String price = "---";
		Map<String, Object> roleMap = Maps.newHashMap();
		if (StringUtils.isNotBlank(mobileBussiness.getContent())) {
			roleMap = JsonMapper.json2Map(mobileBussiness.getContent());
		}
		if (roleMap.get(Constants.OPEN_BUSINESS_KEY) != null) {
			if (Boolean.valueOf(String.valueOf(roleMap.get(Constants.OPEN_BUSINESS_KEY)))) {
				if (ConfigUtil.getValue("open_businessid_" + mobileBussiness.getCityId()) != null) {
					openBusinessId = Integer.valueOf(ConfigUtil.getValue("open_businessid_" + mobileBussiness.getCityId()));
				}
				if (roleMap.get(Constants.OPEN_BUSINESSID_PRICE_KEY) != null) {
					price = String.valueOf(roleMap.get(Constants.OPEN_BUSINESSID_PRICE_KEY));
				}
			}
		}
		//没有任何条件参数 走默认查询
		if (StringUtils.isBlank(phoneParam) && StringUtils.isBlank(tag) && StringUtils.isBlank(notPhone) && StringUtils.isBlank(type) && StringUtils.isBlank(position)) {
			if (roleMap.get(Constants.SEARCH_KEY) != null) {
				phoneParam = String.valueOf(roleMap.get(Constants.SEARCH_KEY));
			}
		}
		boolean isGeomantic = roleMap.get(Constants.GEOMANTIC_KEY) != null ? Boolean.valueOf(String.valueOf(roleMap.get(Constants.GEOMANTIC_KEY))) : false;
		List<UserAccountPhone> list = DataMapperUtil.selectUserAccountPhoneByPhoneAndBusiness(phoneParam, mobileBussiness.getId(), pageNo * pageSize, pageSize, status, tag, notPhone, openBusinessId, position, type);
		if (CollectionUtils.isEmpty(list)) {
			replyMap.fail(BusinessConstants.RESULT_NULL_CODE, BusinessConstants.RESULT_NULL_MSG);
			return replyMap;
		} else {
			List<Map<String, Object>> phoneList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				UserAccountPhone accountPhone = list.get(i);
				Map<String, Object> itemMap = new HashMap<>();
				itemMap.put("phone", accountPhone.getPhone());
				itemMap.put("type", accountPhone.getType());
				itemMap.put("tag", accountPhone.getTag());
				itemMap.put("city", baseCity.getCityName());
				if(isGeomantic){
					LuckyRule luckyRule = GeomanticRule.getPhoneLuck(accountPhone.getPhone());
					if(GeomanticType.LUCKY.equals(GeomanticType.getBytag(luckyRule.getTag()))){
						itemMap.put("geomantic", luckyRule);
					}
				}
				if (accountPhone.getPrice() != null && BigDecimal.ZERO.compareTo(accountPhone.getPrice()) == 0) {
					itemMap.put("price", price);
				} else {
					itemMap.put("price", accountPhone.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				itemMap.put("phone", accountPhone.getPhone());
				if (StringUtils.isNotBlank(accountPhone.getRemark())) {
					Map<String, Object> map = JsonMapper.json2Map(accountPhone.getRemark());
					if (map != null && map.get("fare") != null) {
						itemMap.put("fare", String.valueOf(map.get("fare")));
					}
				}
				if (StringUtils.isNotBlank(tag)) {
					ItemRule itemRule = MobileRule.checkPhone(accountPhone.getPhone());
					if (itemRule != null) {
						itemMap.put("key", itemRule.getKeyword());
					}
				}
				phoneList.add(itemMap);
			}
			replyMap.put("list", phoneList);
			replyMap.put("keyWord", phoneParam);
		}
		replyMap.success();
		return replyMap;
	}
}