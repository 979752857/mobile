package com.tendy.controller;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.service.LoginService;
import com.tendy.service.PhoneInfoService;
import com.tendy.utils.ParamUtil;
import com.tendy.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("/phoneInfo")
public class PhoneInfoController extends BaseController {

    @Autowired
    private PhoneInfoService phoneInfoService;

    @ResponseBody
    @RequestMapping(value = "/phoneList", method = RequestMethod.GET)
    public String phoneList(@RequestParam("keyword") String keyword, @RequestParam("pageNo") Integer pageNo,
                             @RequestParam("pageSize") Integer pageSize, @RequestParam("status") String status,
                             HttpSession httpSession) {
        ReplyMap replyMap = new ReplyMap();
        if(ParamUtil.checkParamIsNull(keyword, status)){
            replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
            return replyMap.toJson();
        }
        if(pageNo == null){
            pageNo = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
        replyMap = phoneInfoService.getDataDetail(keyword, pageNo, pageSize, status, businessId);
        return replyMap.toJson();
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
}