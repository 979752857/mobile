package com.tendy.service;

import com.tendy.common.BusinessConstants;
import com.tendy.common.Constants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.BaseCity;
import com.tendy.dao.bean.MobileBussiness;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: tendy
 * @Description:
 * @Date: 2018/10/29
 */
@Service
public class IndexService {

    public ReplyMap getBusinessInfo(String businessCid){
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
        replyMap.put("name", mobileBussiness.getBussinessName());
        replyMap.put("phone", mobileBussiness.getPhone());
        replyMap.put("address", mobileBussiness.getAddress());
        replyMap.success();
        return replyMap;
    }

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
        //判断运营商有效期
        if(mobileBussiness.getEndTime().before(new Date())){
            replyMap.fail(BusinessConstants.VALID_TIMEOUT_CODE, "运营商账户已过期，请联系管理员");
            return replyMap;
        }
        BaseCity baseCity = DataMapperUtil.selectBaseCityByPrimaryKey(mobileBussiness.getCityId());
        if(baseCity == null){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "运营商城市错误，请联系管理员");
            return replyMap;
        }
        Integer openBusinessId = null;
        if(StringUtils.isNotBlank(mobileBussiness.getContent())){
            Map<String, Object> roleMap = JsonMapper.json2Map(mobileBussiness.getContent());
            if(roleMap.get(Constants.OPEN_BUSINESS_KEY) != null){
                if(Boolean.valueOf(String.valueOf(roleMap.get(Constants.OPEN_BUSINESS_KEY)))){
                    if(ConfigUtil.getValue("open_businessid_"+mobileBussiness.getCityId()) != null){
                        openBusinessId = Integer.valueOf(ConfigUtil.getValue("open_businessid_"+mobileBussiness.getCityId()));
                    }
                }
            }
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
                phoneList.add(showKeyString(accountPhone, key, baseCity.getCityName()));
            }
            replyMap.put("list", phoneList);
        }
        replyMap.success();
        return replyMap;
    }

    public String showKeyString(UserAccountPhone userAccountPhone, String key, String cityName){
        String phone = userAccountPhone.getPhone();
        BigDecimal price = userAccountPhone.getPrice();
        String type = userAccountPhone.getType();
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
        StringBuilder sb = new StringBuilder("<li><div>").append(phone).append("</div>");
        sb = indexKeyWord(sb, 4);
        sb = indexKeyWord(sb, 8);
        sb.append("<div>").append("<span class=\"span-left-brand\">").append(cityName+Constants.getPhoneType(type));
        if(StringUtils.isNotBlank(userAccountPhone.getRemark())){
            Map<String, Object> map = JsonMapper.json2Map(userAccountPhone.getRemark());
            if(map != null && map.get("fare") != null){
                sb.append("</span><span class=\"span-right-brand\">含话费:"+String.valueOf(map.get("fare")));
            }
        }
        sb.append("</span></div></li>");
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