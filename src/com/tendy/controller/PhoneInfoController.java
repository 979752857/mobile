package com.tendy.controller;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.service.LoginService;
import com.tendy.service.PhoneInfoService;
import com.tendy.utils.DataTablesUtil;
import com.tendy.utils.ExcelUtil;
import com.tendy.utils.FileUtil;
import com.tendy.utils.ParamUtil;
import com.tendy.utils.StringUtils;
import com.tendy.utils.TimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/phoneInfo")
public class PhoneInfoController extends BaseController {

    @Autowired
    private PhoneInfoService phoneInfoService;

    @ResponseBody
    @RequestMapping(value = "/phoneList1", method = RequestMethod.GET)
    public String phoneList1(@RequestParam("keyword") String keyword, @RequestParam("pageNo") Integer pageNo,
                             @RequestParam("pageSize") Integer pageSize, @RequestParam("status") String status,
                             HttpSession httpSession) {
        ReplyMap replyMap = new ReplyMap();

        return replyMap.toJson();
    }

    /**
     * 分页查询显示table实例
     * @param aoData
     * @return
     */
    @RequestMapping(value="/phoneList", method=RequestMethod.GET)
    @ResponseBody
    public String getPhoneList(@RequestParam(value = "aoData") String aoData, HttpSession httpSession) {
        //解析参数
        Map<String, Object> map = DataTablesUtil.getParamMap(aoData);
        String start = (String) map.get("start");
        String end = (String) map.get("end");
        //插件返回的起始行数
        Integer iDisplayStart = (Integer) map.get("iDisplayStart");
        //插件返回的每页长度
        Integer iDisplayLength = (Integer) map.get("iDisplayLength");
        List<Map<String, Object>> list = new ArrayList<>();
		/*----------   数据库操作返回list数据   ----------*/
		String keyword = String.valueOf(map.get("keyword"));
		String status = String.valueOf(map.get("status"));
        Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
        Map<String, Object> resultMap = phoneInfoService.getDataDetail(keyword, iDisplayStart, iDisplayLength, status, businessId);
        Integer total = (Integer) resultMap.get("total");
        if(resultMap.get("list") != null){
            list = (List<Map<String, Object>>) resultMap.get("list");
        }
		/*------------------------------------------------*/
        String result = DataTablesUtil.getResultString(list, total, map);
        logger.info(result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePhone", method = RequestMethod.GET)
    public String updatePhone(@RequestParam("id") Long id, @RequestParam("phone") String phone,
                              @RequestParam("url") String url, @RequestParam("price") String price,
                              @RequestParam("status") String status, HttpSession httpSession) {
        ReplyMap replyMap = new ReplyMap();
        if(ParamUtil.checkParamIsNull(phone) || id == null || id <= 0){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
            return replyMap.toJson();
        }
        UserAccountPhone accountPhone = new UserAccountPhone();
        accountPhone.setId(id);
        accountPhone.setPhone(phone);
        if(!StringUtils.isBlank(url)){
            accountPhone.setUrl(url);
        }
        if(!StringUtils.isBlank(price)){
            accountPhone.setPrice(new BigDecimal(price));
        }
        if(!StringUtils.isBlank(status)){
            accountPhone.setStatus(status);
        }
        Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
        replyMap = phoneInfoService.updateUserAccountPhone(accountPhone, businessId);
        return replyMap.toJson();
    }

    @ResponseBody
    @RequestMapping(value = "/lockedPhone", method = RequestMethod.GET)
    public String lockedPhone(@RequestParam("phone") String phone, @RequestParam("status") String status,
                              HttpSession httpSession) {
        ReplyMap replyMap = new ReplyMap();
        if(ParamUtil.checkParamIsNull(phone, status)){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
            return replyMap.toJson();
        }
        Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
        replyMap = phoneInfoService.updateLockPhone(phone, status, businessId);
        return replyMap.toJson();
    }

    @RequestMapping(value = "/readExcel")
    @ResponseBody
    public String readExcel(@RequestParam(value = "excelFile")MultipartFile excelFile, HttpSession httpSession){
        ReplyMap replyMap = new ReplyMap();
        try{
            if(excelFile == null){
                replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
                return replyMap.toJson();
            }
            Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
            replyMap = phoneInfoService.insertPhoneProcessExcel(excelFile, businessId);
        }catch(Exception e){
            logger.error("PhoneInfoController readExcel is error fileName:{}", excelFile.getOriginalFilename(),e);
            replyMap.fail(BusinessConstants.SERVER_ERROR_CODE, BusinessConstants.SERVER_ERROR_MSG);
        }
        return replyMap.toJson();
    }
}