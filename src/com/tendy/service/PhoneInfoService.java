package com.tendy.service;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.MobileBussiness;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tendy
 * @Description:
 * @Date: 2018/10/29
 */
@Service
public class PhoneInfoService extends BaseService {

    public Map<String, Object> getDataDetail(String phoneParam, Integer iDisplayStart, Integer pageSize, String status, Integer businessId, String tag, String notPhone, Integer cityId, String position) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Integer openBusinessId = null;
        if(ConfigUtil.getValue("open_businessid_"+cityId) != null){
            openBusinessId = Integer.valueOf(ConfigUtil.getValue("open_businessid_"+cityId));
        }
        List<UserAccountPhone> list = DataMapperUtil.selectUserAccountPhoneByPhoneAndBusiness(phoneParam, businessId, iDisplayStart, pageSize, status, tag, notPhone, openBusinessId, position);
        if (CollectionUtils.isEmpty(list)) {
            resultMap.put("total", 0);
            return resultMap;
        }
        int total = DataMapperUtil.countUserAccountPhoneByPhoneAndCity(phoneParam, businessId, status, tag, notPhone, openBusinessId, position);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            UserAccountPhone accountPhone = list.get(i);
            map.put("phone", accountPhone.getPhone());
            map.put("url", accountPhone.getUrl());
            if (accountPhone.getPrice() != null) {
                map.put("price", accountPhone.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            }
            map.put("status", accountPhone.getStatus());
            if (accountPhone.getUpdateTime() != null && (accountPhone.getUpdateTime().getTime() > accountPhone.getCreateTime().getTime())) {
                map.put("time", TimeUtil.formatDate(accountPhone.getUpdateTime(), TimeUtil.YYYY_MM_DD));
            } else {
                map.put("time", TimeUtil.formatDate(accountPhone.getCreateTime(), TimeUtil.YYYY_MM_DD));
            }
            resultList.add(map);
        }
        resultMap.put("total", total);
        resultMap.put("list", resultList);
        return resultMap;
    }

    public ReplyMap getPhoneInfo(String phone, Integer businessId) {
        ReplyMap replyMap = new ReplyMap();
        UserAccountPhone userAccountPhone = DataMapperUtil.selectUserAccountPhoneByPhoneAndBusId(phone, businessId);
        if(userAccountPhone == null){
            replyMap.fail(BusinessConstants.RESULT_NULL_CODE, BusinessConstants.RESULT_NULL_MSG);
            return replyMap;
        }
        replyMap.put("userAccountPhone", userAccountPhone);
        replyMap.success();
        return replyMap;
    }

    public ReplyMap updateUserAccountPhone(UserAccountPhone accountPhone, Integer businessId) {
        ReplyMap replyMap = new ReplyMap();
        if(accountPhone.getId() == null || accountPhone.getId() == 0){
            accountPhone.setId(null);
            accountPhone.setBusinessId(businessId);
            MobileBussiness mobileBussiness = DataMapperUtil.selectMobileBussinessByPrimaryKey(businessId);
            if(mobileBussiness == null){
                replyMap.fail(BusinessConstants.USER_NULL_CODE, BusinessConstants.USER_NULL_MSG);
                return replyMap;
            }
            accountPhone.setCityId(mobileBussiness.getCityId());
            accountPhone.setCreateTime(new Date());
            if(StringUtils.isBlank(accountPhone.getStatus())){
                accountPhone.setStatus("private");
            }
        }
        accountPhone.setUpdateTime(new Date());
        ItemRule item = MobileRule.checkPhone(accountPhone.getPhone());
        if(item != null){
            accountPhone.setTag(item.getTag());
            accountPhone.setRemark(item.getRemark());
        }
        int num = DataMapperUtil.insertOrUpdateUserAccountPhoneSelective(accountPhone);
        if (num <= 0) {
            replyMap.fail(BusinessConstants.SERVER_BUSY_CODE, BusinessConstants.SERVER_BUSY_MSG);
            return replyMap;
        }
        replyMap.success();
        return replyMap;
    }

    public ReplyMap updateLockPhone(String phone, String status, Integer businessId) {
        ReplyMap replyMap = new ReplyMap();
        UserAccountPhone accountPhone = DataMapperUtil.selectUserAccountPhoneByPhoneAndBusId(phone, businessId);
        if (accountPhone == null) {
            replyMap.fail(BusinessConstants.USER_NULL_CODE, "手机信息不存在");
            return replyMap;
        }
        if (accountPhone.getStatus().equals(status)) {
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "号码状态没有改变！");
            return replyMap;
        }
        UserAccountPhone userAccountPhone = new UserAccountPhone();
        userAccountPhone.setId(accountPhone.getId());
        userAccountPhone.setStatus(status);
        userAccountPhone.setUpdateTime(new Date());
        int num = DataMapperUtil.updateUserAccountPhoneByPrimaryKeySelective(userAccountPhone);
        if (num <= 0) {
            replyMap.fail(BusinessConstants.SERVER_BUSY_CODE, BusinessConstants.SERVER_BUSY_MSG);
            return replyMap;
        }
        replyMap.success();
        return replyMap;
    }

    public ReplyMap processExcel(MultipartFile excelFile, String busName) throws Exception {
        ReplyMap replyMap = new ReplyMap();
        MobileBussiness business = DataMapperUtil.selectMobileBussinessByName(busName);
        if(business == null){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "请输入运营商账号");
            return replyMap;
        }
        File excelReadFile = new File(System.getProperty("java.io.tmpdir"),
                TimeUtil.formatDate(new Date(), TimeUtil.YYYYMMDDHHMMSS) + FileUtil.getExtensionName(excelFile.getOriginalFilename()));
        if (!excelReadFile.exists()) {
            excelReadFile.mkdirs();
        }
        excelFile.transferTo(excelReadFile);
        int successNum = 0;
        int failNum = 0;
        int processRow = 0;
        //excel格式：phone,price
        List<String[]> infoList = ExcelUtil.readExcelReturnList(excelReadFile, null);
        if (CollectionUtils.isEmpty(infoList)) {
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "没有获取到excel的内容");
            return replyMap;
        }
        List<Map<String, String>> failList = new ArrayList<>();
        replyMap.put("failList", failList);
        replyMap.put("processRow", processRow);
        replyMap.put("successNum", successNum);
        replyMap.put("failNum", failNum);
        //每次截取100个进行插入操作
        int processSize = 100;
        List<String[]> processList = new ArrayList<>();
        for(int i = infoList.size()-1; i >= 0; i--){
            String[] item = infoList.get(i);
            processList.add(item);
            infoList.remove(i);
            if(processList.size() == processSize){
                replyMap = insertPhoneProcessExcel(processList, business.getId(), business.getCityId(), replyMap);
                processList.clear();
                Thread.sleep(1000);
            }
        }
        if(!CollectionUtils.isEmpty(processList)){
            replyMap = insertPhoneProcessExcel(processList, business.getId(), business.getCityId(), replyMap);
            processList.clear();
        }
        replyMap.success();
        return replyMap;
    }

    public ReplyMap insertPhoneProcessExcel(List<String[]> infoList, Integer businessId, Integer cityId, ReplyMap replyMap){
        int successNum = Integer.parseInt(String.valueOf(replyMap.get("failNum")));
        int failNum = Integer.parseInt(String.valueOf(replyMap.get("successNum")));
        int processRow = Integer.parseInt(String.valueOf(replyMap.get("processRow")));
        List<Map<String, String>> failList = (List<Map<String, String>>) replyMap.get("failList");
        if (CollectionUtils.isEmpty(infoList)) {
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "没有获取到excel的内容");
            return replyMap;
        }
        for (int i = 0; i < infoList.size(); i++) {
            String[] codeArr = infoList.get(i);
            processRow++;
            if (codeArr == null || codeArr.length < 2 || ParamUtil.checkParamIsNull(codeArr[0], codeArr[1]) || ParamUtil.checkPhoneIllegal(codeArr[0])) {
                failNum++;
                continue;
            }
            try {
                UserAccountPhone userAccountPhone = DataMapperUtil.selectUserAccountPhoneByPhoneAndBusId(codeArr[0], businessId);
                if(userAccountPhone == null){
                    userAccountPhone = new UserAccountPhone();
                    userAccountPhone.setPhone(codeArr[0]);
                    userAccountPhone.setCreateTime(new Date());
                    userAccountPhone.setBusinessId(businessId);
                    userAccountPhone.setCityId(cityId);
                }
                userAccountPhone.setPrice(new BigDecimal(codeArr[1]));
                userAccountPhone.setUpdateTime(new Date());
                userAccountPhone.setStatus("private");
                ItemRule item = MobileRule.checkPhone(userAccountPhone.getPhone());
                if(item != null){
                    userAccountPhone.setTag(item.getTag());
                    userAccountPhone.setRemark(item.getRemark());
                }
                int num = DataMapperUtil.insertOrUpdateUserAccountPhoneSelective(userAccountPhone);
                if (num > 0) {
                    successNum++;
                } else {
                    failNum++;
                }
            } catch (Exception e) {
                failNum++;
                Map<String, String> failMap = new HashMap<String, String>();
                failMap.put("row", String.valueOf(i + 1));
                failMap.put("phone", codeArr[0]);
                failMap.put("failReason", e.getMessage());
                failList.add(failMap);
                logger.error("insertPhoneProcessExcel failed title:{}  message:{}", codeArr[0], e.getMessage(), e);
            }
        }
        replyMap.put("failList", failList);
        replyMap.put("successNum", successNum);
        replyMap.put("failNum", failNum);
        replyMap.put("processRow", processRow);
        return replyMap;
    }

    /**
     * 以后商户自己导入使用
     * @param excelFile
     * @param businessId
     * @return
     * @throws Exception
     */
    public ReplyMap insertPhoneProcessExcel(MultipartFile excelFile, Integer businessId) throws Exception {
        ReplyMap replyMap = new ReplyMap();
        MobileBussiness business = DataMapperUtil.selectMobileBussinessByPrimaryKey(businessId);
        File excelReadFile = new File(System.getProperty("java.io.tmpdir"),
                TimeUtil.formatDate(new Date(), TimeUtil.YYYYMMDDHHMMSS) + FileUtil.getExtensionName(excelFile.getOriginalFilename()));
        if (!excelReadFile.exists()) {
            excelReadFile.mkdirs();
        }
        excelFile.transferTo(excelReadFile);
        int successNum = 0;
        int failNum = 0;
        //excel格式：phone,price
        List<String[]> infoList = ExcelUtil.readExcelReturnList(excelReadFile, null);
        if (CollectionUtils.isEmpty(infoList)) {
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "没有获取到excel的内容");
            return replyMap;
        }
        List<Map<String, String>> failList = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            String[] codeArr = infoList.get(i);
            if (codeArr == null || codeArr.length < 2 || ParamUtil.checkParamIsNull(codeArr[0], codeArr[1]) || ParamUtil.checkPhoneIllegal(codeArr[0])) {
                failNum++;
                continue;
            }
            try {
                UserAccountPhone userAccountPhone = new UserAccountPhone();
                userAccountPhone.setPhone(codeArr[0]);
                userAccountPhone.setPrice(new BigDecimal(codeArr[1]));
                userAccountPhone.setBusinessId(business.getId());
                userAccountPhone.setCreateTime(new Date());
                userAccountPhone.setUpdateTime(new Date());
                userAccountPhone.setStatus("private");
                userAccountPhone.setCityId(business.getCityId());
                ItemRule item = MobileRule.checkPhone(userAccountPhone.getPhone());
                if(item != null){
                    userAccountPhone.setTag(item.getTag());
                    userAccountPhone.setRemark(item.getRemark());
                }
                int num = DataMapperUtil.insertOrUpdateUserAccountPhoneSelective(userAccountPhone);
                if (num > 0) {
                    successNum++;
                } else {
                    failNum++;
                }
            } catch (Exception e) {
                failNum++;
                Map<String, String> failMap = new HashMap<String, String>();
                failMap.put("row", String.valueOf(i + 1));
                failMap.put("phone", codeArr[0]);
                failMap.put("failReason", e.getMessage());
                failList.add(failMap);
                logger.error("insertPhoneProcessExcel failed title:{}  message:{}", codeArr[0], e.getMessage(), e);
            }
        }
        replyMap.success();
        replyMap.put("failList", failList);
        replyMap.put("successNum", successNum);
        replyMap.put("failNum", failNum);
        return replyMap;
    }
}