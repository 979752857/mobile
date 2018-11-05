package com.tendy.service;

import com.tendy.common.ReplyMap;
import com.tendy.common.BusinessConstants;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.AccountPhone;
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
public class DataService {

    public ReplyMap getData(String phoneParam, String cityIdParam, Integer pageNo, Integer pageSize, String status){
        ReplyMap replyMap = new ReplyMap();
        if(null == phoneParam || "".equals(phoneParam) || null == cityIdParam || "".equals(cityIdParam)){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "请输入查询的关键号码");
            return replyMap;
        }else{
            List<AccountPhone> list = DataMapperUtil.selectAccountPhoneByPhoneAndCity(phoneParam, Integer.valueOf(cityIdParam), pageNo*pageSize, pageSize, status);
            if(CollectionUtils.isEmpty(list)){
                replyMap.fail(BusinessConstants.RESULT_NULL_CODE, BusinessConstants.RESULT_NULL_MSG);
                return replyMap;
            }else{
                List<String> phoneList = new ArrayList<>();
                for(int i = 0; i<list.size(); i++){
                    AccountPhone accountPhone = list.get(i);
                    phoneList.add(showKeyString(accountPhone.getPhone(), accountPhone.getPrice(), phoneParam));
                }
                replyMap.put("list", phoneList);
            }
        }
        replyMap.success();
        return replyMap;
    }

    public ReplyMap getDataDetail(String phoneParam, String cityIdParam, Integer pageNo, Integer pageSize, String status){
        ReplyMap replyMap = new ReplyMap();
        List<AccountPhone> list = DataMapperUtil.selectAccountPhoneByPhoneAndCity(phoneParam, Integer.valueOf(cityIdParam), (pageNo-1)*pageSize, pageSize, status);
        if(CollectionUtils.isEmpty(list)){
            replyMap.fail(BusinessConstants.RESULT_NULL_CODE, BusinessConstants.RESULT_NULL_MSG);
        }else{
            int total = DataMapperUtil.countAccountPhoneByPhoneAndCity(phoneParam, Integer.valueOf(cityIdParam), status);
            List<Map<String, String>> resultList = new ArrayList<>();
            for(int i = 0; i<list.size(); i++){
                Map<String, String> map = new HashMap<>();
                AccountPhone accountPhone = list.get(i);
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
        }
        return replyMap;
    }

    public String showKeyString(String phone, BigDecimal price, String key){
        String str = "<span class=\"span-left\">"+phone+"</span><span class=\"span-right\">";
        if(price != null && BigDecimal.ZERO.compareTo(price) == 0){
            str += "---";
        }else{
            str += price.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
        str += "</span>";
        phone = str.replace(key, "<span class=\"text-red\">"+key+"</span>");
        StringBuilder sb = new StringBuilder("<li><div><p>").append(phone).append("</p></div></li>");
        sb = indexKeyWord(sb, 4);
        sb = indexKeyWord(sb, 8);
        return sb.toString();
    }

    public StringBuilder indexKeyWord(StringBuilder sb, int index){
        int numIndex = 0;
        for(int i = 0; i<sb.length(); i++){
            if(sb.charAt(i) >= 48 && sb.charAt(i) <= 57) {
                numIndex++;
                if (numIndex == index) {
                    StringBuilder newSb = new StringBuilder();
                    newSb.append(sb.substring(0, i));
                    newSb.append("&nbsp;&nbsp;");
                    newSb.append(sb.subSequence(i, sb.length()));
                    sb = newSb;
                }
            }
        }
        return sb;
    }
}