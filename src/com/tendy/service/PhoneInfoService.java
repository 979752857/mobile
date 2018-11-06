package com.tendy.service;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.utils.JsonMapper;
import com.tendy.utils.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tendy
 * @Description:
 * @Date: 2018/10/29
 */
@Service
public class PhoneInfoService {

    public ReplyMap getDataDetail1(String phoneParam, Integer pageNo, Integer pageSize, String status, Integer businessId){
        ReplyMap replyMap = new ReplyMap();
        List<UserAccountPhone> list = DataMapperUtil.selectUserAccountPhoneByPhoneAndBusiness(phoneParam, businessId, (pageNo-1)*pageSize, pageSize, status);
        if(CollectionUtils.isEmpty(list)) {
            replyMap.fail(BusinessConstants.RESULT_NULL_CODE, BusinessConstants.RESULT_NULL_MSG);
            return replyMap;
        }
        int total = DataMapperUtil.countUserAccountPhoneByPhoneAndCity(phoneParam, businessId, status);
        List<Map<String, String>> resultList = new ArrayList<>();
        for(int i = 0; i<list.size(); i++){
            Map<String, String> map = new HashMap<>();
            UserAccountPhone accountPhone = list.get(i);
            map.put("phone", accountPhone.getPhone());
            map.put("url", accountPhone.getUrl());
            if(accountPhone.getPrice() != null){
                map.put("price", accountPhone.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            }
            map.put("status", accountPhone.getStatus());
            if(accountPhone.getUpdateTime() != null && (accountPhone.getUpdateTime().getTime() > accountPhone.getCreateTime().getTime())){
                    map.put("time", TimeUtil.formatDate(accountPhone.getUpdateTime(), TimeUtil.YYYY_MM_DD));
            }else{
                map.put("time", TimeUtil.formatDate(accountPhone.getCreateTime(), TimeUtil.YYYY_MM_DD));
            }
            resultList.add(map);
        }
        replyMap.success();
        replyMap.put("list", JsonMapper.toJson(resultList));
        replyMap.put("total", total);

        return replyMap;
    }

    public Map<String, Object> getDataDetail(String phoneParam, Integer iDisplayStart, Integer pageSize, String status, Integer businessId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<UserAccountPhone> list = DataMapperUtil.selectUserAccountPhoneByPhoneAndBusiness(phoneParam, businessId, iDisplayStart, pageSize, status);
        if(CollectionUtils.isEmpty(list)) {
            resultMap.put("total", 0);
            return resultMap;
        }
        int total = DataMapperUtil.countUserAccountPhoneByPhoneAndCity(phoneParam, businessId, status);
        for(int i = 0; i<list.size(); i++){
            Map<String, Object> map = new HashMap<>();
            UserAccountPhone accountPhone = list.get(i);
            map.put("phone", accountPhone.getPhone());
            map.put("url", accountPhone.getUrl());
            if(accountPhone.getPrice() != null){
                map.put("price", accountPhone.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            }
            map.put("status", accountPhone.getStatus());
            if(accountPhone.getUpdateTime() != null && (accountPhone.getUpdateTime().getTime() > accountPhone.getCreateTime().getTime())){
                    map.put("time", TimeUtil.formatDate(accountPhone.getUpdateTime(), TimeUtil.YYYY_MM_DD));
            }else{
                map.put("time", TimeUtil.formatDate(accountPhone.getCreateTime(), TimeUtil.YYYY_MM_DD));
            }
            resultList.add(map);
        }
        resultMap.put("total", total);
        resultMap.put("list", resultList);
        return resultMap;
    }

    public ReplyMap updateUserAccountPhone(UserAccountPhone accountPhone, Integer businessId){
        ReplyMap replyMap = new ReplyMap();
        UserAccountPhone phone = DataMapperUtil.selectUserAccountPhoneByPrimaryKey(accountPhone.getId());
        if(phone == null){
            replyMap.fail(BusinessConstants.USER_NULL_CODE, "手机信息不存在");
            return replyMap;
        }
        if(phone.getBusinessId().equals(businessId)){
            replyMap.fail(BusinessConstants.USER_NULL_CODE, "这个号码不是您的哦！");
            return replyMap;
        }
        int num = DataMapperUtil.updateUserAccountPhoneByPrimaryKeySelective(accountPhone);
        if(num <= 0){
            replyMap.fail(BusinessConstants.SERVER_BUSY_CODE, BusinessConstants.SERVER_BUSY_MSG);
            return replyMap;
        }
        replyMap.success();
        return replyMap;
    }
}