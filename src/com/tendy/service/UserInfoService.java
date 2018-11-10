package com.tendy.service;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.BaseCity;
import com.tendy.dao.bean.MobileBussiness;
import com.tendy.utils.EncryptUtil;
import com.tendy.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;

@Service
public class UserInfoService extends BaseService{

    public ReplyMap updateUserInfo(Integer businessId, String name, String password, String phone, String address, String remark){
        ReplyMap replyMap = new ReplyMap();
        MobileBussiness mobileBussiness = DataMapperUtil.selectMobileBussinessByPrimaryKey(businessId);
        if(!mobileBussiness.getName().equals(name)){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "账户不一致！！！");
            return replyMap;
        }
        if(StringUtils.isNotBlank(password)){
            password = EncryptUtil.MD5(mobileBussiness.getName()+password);
            mobileBussiness.setPassword(password);
        }
        if(StringUtils.isNotBlank(phone)){
            mobileBussiness.setPhone(phone);
        }
        if(StringUtils.isNotBlank(address)){
            mobileBussiness.setAddress(address);
        }
        if(StringUtils.isNotBlank(remark)){
            mobileBussiness.setRemark(remark);
        }
        mobileBussiness.setUpdateTime(new Date());
        int num = DataMapperUtil.updateMobileBussinessByPrimaryKeySelective(mobileBussiness);
        if(num <= 0){
            replyMap.fail(BusinessConstants.SERVER_BUSY_CODE, BusinessConstants.SERVER_BUSY_MSG);
        }else{
            replyMap.success();
        }
        return replyMap;
    }

    public ReplyMap getUserInfo(Integer businessId){
        ReplyMap replyMap = new ReplyMap();
        MobileBussiness mobileBussiness = DataMapperUtil.selectMobileBussinessByPrimaryKey(businessId);
        if(mobileBussiness == null){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "账户信息不存在，请重新登录");
            return replyMap;
        }
        BaseCity baseCity = DataMapperUtil.selectBaseCityByPrimaryKey(mobileBussiness.getCityId());
        if(baseCity == null){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "获取不到城市信息，请联系管理员");
            return replyMap;
        }
        replyMap.success();
        replyMap.put("name", mobileBussiness.getName());
        replyMap.put("businessName", mobileBussiness.getBussinessName());
        replyMap.put("phone", mobileBussiness.getPhone());
        replyMap.put("content", mobileBussiness.getContent());
        replyMap.put("address", mobileBussiness.getAddress());
        replyMap.put("remark", mobileBussiness.getRemark());
        replyMap.put("city", baseCity.getCityName());
        return replyMap;
    }
}