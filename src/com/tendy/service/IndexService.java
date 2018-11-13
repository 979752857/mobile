package com.tendy.service;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.MobileBussiness;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tendy
 * @Description:
 * @Date: 2018/10/29
 */
@Service
public class IndexService {

    public ReplyMap getData(String phoneParam, String businessCid, Integer pageNo, Integer pageSize, String status, String tag, String notPhone, String position){
        ReplyMap replyMap = new ReplyMap();
        if(StringUtils.isBlank(businessCid)){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "运营商查询错误，请重新扫码");
            return replyMap;
        }
        MobileBussiness mobileBussiness = DataMapperUtil.selectMobileBussinessByCid(businessCid);
        if(mobileBussiness == null){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "运营商查询错误，请重新扫码");
            return replyMap;
        }
        Integer openBusinessId = null;
        if(ConfigUtil.getValue("open_businessid_"+mobileBussiness.getCityId()) != null){
            openBusinessId = Integer.valueOf(ConfigUtil.getValue("open_businessid_"+mobileBussiness.getCityId()));
        }
        List<UserAccountPhone> list = DataMapperUtil.selectUserAccountPhoneByPhoneAndBusiness(phoneParam, mobileBussiness.getId(), pageNo*pageSize, pageSize, status, tag, notPhone, openBusinessId, position);
        if(CollectionUtils.isEmpty(list)){
            replyMap.fail(BusinessConstants.RESULT_NULL_CODE, BusinessConstants.RESULT_NULL_MSG);
            return replyMap;
        }else{
            List<String> phoneList = new ArrayList<>();
            for(int i = 0; i<list.size(); i++){
                UserAccountPhone accountPhone = list.get(i);
                String key = null;
                if(StringUtils.isNotBlank(tag)){
                    ItemRule itemRule = MobileRule.checkPhone(accountPhone.getPhone());
                    if(itemRule != null){
                        key = itemRule.getKeyword();
                    }
                }
                if(StringUtils.isNotBlank(phoneParam)){
                    key = phoneParam;
                }
                phoneList.add(showKeyString(accountPhone.getPhone(), accountPhone.getPrice(), key));
            }
            replyMap.put("list", phoneList);
        }
        replyMap.success();
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
        if(StringUtils.isNotBlank(key)){
            phone = str.replace(key, "<span class=\"text-red\">"+key+"</span>");
        }
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